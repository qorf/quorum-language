#include "SpreadsheetControl.h"
#include "SpreadsheetProvider.h"
#include "SpreadsheetPartProvider.h"
#include "ControlTImpl.h"

#include <algorithm>

namespace
{

// The number of values GetSpreadsheetState gives (AccessibilityManager.java).
constexpr jsize c_stateSize = 14;

void ClearJavaException(JNIEnv* env)
{
	if (env->ExceptionCheck())
	{
		env->ExceptionClear();
	}
}

std::wstring ToWideString(JNIEnv* env, jstring value)
{
	ClearJavaException(env);
	if (value == nullptr)
	{
		return L"";
	}
	const jchar* characters = env->GetStringChars(value, NULL);
	jsize length = env->GetStringLength(value);
	std::wstring result;
	if (characters != nullptr)
	{
		result.assign(reinterpret_cast<const wchar_t*>(characters), length);
		env->ReleaseStringChars(value, characters);
	}
	env->DeleteLocalRef(value);
	return result;
}

} // anonymous namespace

// =========== SpreadsheetState

bool SpreadsheetState::HasCurrent() const noexcept
{
	return currentRow >= 0 && currentColumn >= 0;
}

bool SpreadsheetState::IsCurrent(int row, int column) const noexcept
{
	return HasCurrent() && row == currentRow && column == currentColumn;
}

bool SpreadsheetState::Contains(int row, int column) const noexcept
{
	if (!HasCurrent())
	{
		return false;
	}
	if (kind == Everything)
	{
		return true;
	}
	if (kind == Rows)
	{
		return row >= firstRow && row <= lastRow;
	}
	if (kind == Columns)
	{
		return column >= firstColumn && column <= lastColumn;
	}
	return row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn;
}

bool SpreadsheetState::IsRange() const noexcept
{
	if (!HasCurrent())
	{
		return false;
	}
	return kind != Cells || firstRow != lastRow || firstColumn != lastColumn;
}

bool SpreadsheetState::HasRow(int row) const noexcept
{
	return row >= 0 && row < rows && columns > 0;
}

bool SpreadsheetState::HasColumn(int column) const noexcept
{
	return column >= 0 && column < columns && rows > 0;
}

bool SpreadsheetState::HasCell(int row, int column) const noexcept
{
	return HasRow(row) && HasColumn(column);
}

int SpreadsheetState::GetVisibleRowCount() const noexcept
{
	if (rows <= 0 || columns <= 0 || firstVisibleRow < 0)
	{
		return 0;
	}
	int last = (std::min)(lastVisibleRow, rows - 1);
	return (std::max)(0, last - firstVisibleRow + 1);
}

int SpreadsheetState::GetVisibleColumnCount() const noexcept
{
	if (rows <= 0 || columns <= 0 || firstVisibleColumn < 0)
	{
		return 0;
	}
	int last = (std::min)(lastVisibleColumn, columns - 1);
	return (std::max)(0, last - firstVisibleColumn + 1);
}

bool SpreadsheetState::IsRowVisible(int row) const noexcept
{
	return row >= firstVisibleRow && row < firstVisibleRow + GetVisibleRowCount();
}

bool SpreadsheetState::IsColumnVisible(int column) const noexcept
{
	return column >= firstVisibleColumn && column < firstVisibleColumn + GetVisibleColumnCount();
}

bool SpreadsheetState::SameSelection(const SpreadsheetState& other) const noexcept
{
	return currentRow == other.currentRow && currentColumn == other.currentColumn
		&& kind == other.kind && firstRow == other.firstRow && lastRow == other.lastRow
		&& firstColumn == other.firstColumn && lastColumn == other.lastColumn;
}

bool SpreadsheetState::SameVisibleArea(const SpreadsheetState& other) const noexcept
{
	return rows == other.rows && columns == other.columns
		&& firstVisibleRow == other.firstVisibleRow && lastVisibleRow == other.lastVisibleRow
		&& firstVisibleColumn == other.firstVisibleColumn && lastVisibleColumn == other.lastVisibleColumn;
}

int SpreadsheetState::GetFocusPart() const noexcept
{
	if (!HasCurrent())
	{
		return FocusNone;
	}
	if (kind == Rows)
	{
		return FocusRowHeader;
	}
	if (kind == Columns)
	{
		return FocusColumnHeader;
	}
	return FocusCell;
}

bool SpreadsheetState::IsFocusTarget(int row, int column) const noexcept
{
	switch (GetFocusPart())
	{
		case FocusCell:
			return row == currentRow && column == currentColumn;
		case FocusRowHeader:
			return row == currentRow && column == -1;
		case FocusColumnHeader:
			return row == -1 && column == currentColumn;
		default:
			return false;
	}
}

// =========== SpreadsheetControl

SpreadsheetControl::SpreadsheetControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	if (IsAvailableForQueries())
	{
		m_state = ReadState();
	}
}

bool SpreadsheetControl::IsAvailableForQueries()
{
	return GetMe() != nullptr && GetJNIEnv() != NULL;
}

const SpreadsheetState& SpreadsheetControl::GetKnownState() const noexcept
{
	return m_state;
}

SpreadsheetState SpreadsheetControl::ReadState()
{
	SpreadsheetState state;
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetState == nullptr)
	{
		return m_state;
	}
	jintArray valuesArray = (jintArray)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetState, GetMe());
	ClearJavaException(env);
	if (valuesArray == nullptr)
	{
		return m_state;
	}
	if (env->GetArrayLength(valuesArray) >= c_stateSize)
	{
		jint values[c_stateSize];
		env->GetIntArrayRegion(valuesArray, 0, c_stateSize, values);
		state.rows = values[0];
		state.columns = values[1];
		state.firstVisibleRow = values[2];
		state.lastVisibleRow = values[3];
		state.firstVisibleColumn = values[4];
		state.lastVisibleColumn = values[5];
		state.currentRow = values[6];
		state.currentColumn = values[7];
		state.kind = values[8];
		state.firstRow = values[9];
		state.lastRow = values[10];
		state.firstColumn = values[11];
		state.lastColumn = values[12];
		state.editing = values[13] != 0;
	}
	env->DeleteLocalRef(valuesArray);
	return state;
}

void SpreadsheetControl::RefreshVisibleArea()
{
	if (!IsAvailableForQueries())
	{
		return;
	}
	SpreadsheetState state = ReadState();
	if (state.rows != m_state.rows || state.columns != m_state.columns)
	{
		// A change Quorum hasn't reported yet: NotifyChanged will see it.
		return;
	}
	m_state.firstVisibleRow = state.firstVisibleRow;
	m_state.lastVisibleRow = state.lastVisibleRow;
	m_state.firstVisibleColumn = state.firstVisibleColumn;
	m_state.lastVisibleColumn = state.lastVisibleColumn;
}

std::wstring SpreadsheetControl::GetCellText(int row, int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetCellText == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetCellText, GetMe(), (jint)row, (jint)column);
	return ToWideString(env, value);
}

std::wstring SpreadsheetControl::GetCellName(int row, int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetCellName == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetCellName, GetMe(), (jint)row, (jint)column);
	return ToWideString(env, value);
}

std::wstring SpreadsheetControl::GetColumnLabel(int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetColumnLabel == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetColumnLabel, GetMe(), (jint)column);
	return ToWideString(env, value);
}

std::wstring SpreadsheetControl::GetRoleText(int row, int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetRoleText == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetRoleText, GetMe(), (jint)row, (jint)column);
	return ToWideString(env, value);
}

bool SpreadsheetControl::IsCellSelected(int row, int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.IsSpreadsheetCellSelected == nullptr)
	{
		return m_state.Contains(row, column);
	}
	jboolean selected = env->CallStaticBooleanMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.IsSpreadsheetCellSelected, GetMe(), (jint)row, (jint)column);
	ClearJavaException(env);
	return selected == JNI_TRUE;
}

UiaRect SpreadsheetControl::GetCellBounds(int row, int column)
{
	UiaRect bounds = {};
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetSpreadsheetCellBounds == nullptr)
	{
		return bounds;
	}
	jdoubleArray valuesArray = (jdoubleArray)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetCellBounds, GetMe(), (jint)row, (jint)column);
	ClearJavaException(env);
	if (valuesArray == nullptr)
	{
		return bounds;
	}
	if (env->GetArrayLength(valuesArray) >= 4)
	{
		jdouble values[4];
		env->GetDoubleArrayRegion(valuesArray, 0, 4, values);
		bounds = { values[0], values[1], values[2], values[3] };
	}
	env->DeleteLocalRef(valuesArray);
	return bounds;
}

void SpreadsheetControl::MoveTo(int row, int column, bool extend)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.MoveSpreadsheetTo == nullptr)
	{
		return;
	}
	env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.MoveSpreadsheetTo, GetMe(), (jint)row, (jint)column, extend ? JNI_TRUE : JNI_FALSE);
	ClearJavaException(env);
}

void SpreadsheetControl::SetCellText(int row, int column, const std::wstring& text)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.SetSpreadsheetCellText == nullptr)
	{
		return;
	}
	jstring value = env->NewString(reinterpret_cast<const jchar*>(text.data()), static_cast<jsize>(text.size()));
	if (value == nullptr)
	{
		ClearJavaException(env);
		return;
	}
	env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.SetSpreadsheetCellText, GetMe(), (jint)row, (jint)column, value);
	ClearJavaException(env);
	env->DeleteLocalRef(value);
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetCellFragment(int row, int column)
{
	if (!m_state.HasCell(row, column))
	{
		return nullptr;
	}
	const auto key = std::make_pair(row, column);
	auto found = m_cells.find(key);
	if (found != m_cells.end())
	{
		return found->second.provider.try_query<IRawElementProviderFragment>();
	}
	wil::com_ptr<SpreadsheetCellProvider> made = wil::MakeOrThrow<SpreadsheetCellProvider>(this, row, column);
	CellEntry entry;
	entry.provider = made.query<IRawElementProviderSimple>();
	entry.name = GetCellName(row, column);
	entry.value = GetCellText(row, column);
	entry.roleText = GetRoleText(row, column);
	entry.selected = m_state.Contains(row, column);
	auto fragment = entry.provider.try_query<IRawElementProviderFragment>();
	m_cells[key] = std::move(entry);
	return fragment;
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetColumnHeaderFragment(int column)
{
	if (!m_state.HasColumn(column))
	{
		return nullptr;
	}
	auto found = m_columnHeaders.find(column);
	if (found != m_columnHeaders.end())
	{
		return found->second.provider.try_query<IRawElementProviderFragment>();
	}
	wil::com_ptr<SpreadsheetHeaderProvider> made = wil::MakeOrThrow<SpreadsheetHeaderProvider>(this, true, column);
	HeaderEntry entry;
	entry.provider = made.query<IRawElementProviderSimple>();
	entry.roleText = GetRoleText(-1, column);
	auto fragment = entry.provider.try_query<IRawElementProviderFragment>();
	m_columnHeaders[column] = std::move(entry);
	return fragment;
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetRowHeaderFragment(int row)
{
	if (!m_state.HasRow(row))
	{
		return nullptr;
	}
	auto found = m_rowHeaders.find(row);
	if (found != m_rowHeaders.end())
	{
		return found->second.provider.try_query<IRawElementProviderFragment>();
	}
	wil::com_ptr<SpreadsheetHeaderProvider> made = wil::MakeOrThrow<SpreadsheetHeaderProvider>(this, false, row);
	HeaderEntry entry;
	entry.provider = made.query<IRawElementProviderSimple>();
	entry.roleText = GetRoleText(row, -1);
	auto fragment = entry.provider.try_query<IRawElementProviderFragment>();
	m_rowHeaders[row] = std::move(entry);
	return fragment;
}

int SpreadsheetControl::GetPartCount()
{
	int rows = m_state.GetVisibleRowCount();
	int columns = m_state.GetVisibleColumnCount();
	if (rows == 0 || columns == 0)
	{
		return 0;
	}
	return columns + rows * (columns + 1);
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetPartFragment(int part)
{
	if (part < 0 || part >= GetPartCount())
	{
		return nullptr;
	}
	int columns = m_state.GetVisibleColumnCount();
	if (part < columns)
	{
		return GetColumnHeaderFragment(m_state.firstVisibleColumn + part);
	}
	int index = part - columns;
	int row = m_state.firstVisibleRow + index / (columns + 1);
	int place = index % (columns + 1);
	if (place == 0)
	{
		return GetRowHeaderFragment(row);
	}
	return GetCellFragment(row, m_state.firstVisibleColumn + place - 1);
}

int SpreadsheetControl::GetCellPart(int row, int column)
{
	if (!m_state.IsRowVisible(row) || !m_state.IsColumnVisible(column))
	{
		return -1;
	}
	int columns = m_state.GetVisibleColumnCount();
	return columns + (row - m_state.firstVisibleRow) * (columns + 1) + 1 + (column - m_state.firstVisibleColumn);
}

int SpreadsheetControl::GetColumnHeaderPart(int column)
{
	if (m_state.GetVisibleRowCount() == 0 || !m_state.IsColumnVisible(column))
	{
		return -1;
	}
	return column - m_state.firstVisibleColumn;
}

int SpreadsheetControl::GetRowHeaderPart(int row)
{
	if (m_state.GetVisibleColumnCount() == 0 || !m_state.IsRowVisible(row))
	{
		return -1;
	}
	int columns = m_state.GetVisibleColumnCount();
	return columns + (row - m_state.firstVisibleRow) * (columns + 1);
}

// The last Quorum child, such as the edit field, which comes before the
// parts.
wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetLastChildFragment()
{
	Item* last = GetLastChild();
	if (last == nullptr)
	{
		return nullptr;
	}
	return last->GetProviderFragment();
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetFocusTargetFragment()
{
	if (!m_state.HasCell(m_state.currentRow, m_state.currentColumn))
	{
		return nullptr;
	}
	switch (m_state.GetFocusPart())
	{
		case SpreadsheetState::FocusRowHeader:
			return GetRowHeaderFragment(m_state.currentRow);
		case SpreadsheetState::FocusColumnHeader:
			return GetColumnHeaderFragment(m_state.currentColumn);
		case SpreadsheetState::FocusCell:
			return GetCellFragment(m_state.currentRow, m_state.currentColumn);
		default:
			return nullptr;
	}
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetUiaFocusFragment()
{
	if (!IsAvailableForQueries() || m_state.editing)
	{
		return GetProviderFragment();
	}
	const auto target = GetFocusTargetFragment();
	if (!target)
	{
		return GetProviderFragment();
	}
	return target;
}

wil::com_ptr<IRawElementProviderFragment> SpreadsheetControl::GetFragmentAfterChildren()
{
	RefreshVisibleArea();
	return GetPartFragment(0);
}

void SpreadsheetControl::RaiseStringChanged(IRawElementProviderSimple* provider, PROPERTYID property, const std::wstring& value)
{
	VARIANT oldValue, newValue;
	VariantInit(&oldValue);
	VariantInit(&newValue);
	newValue.vt = VT_BSTR;
	newValue.bstrVal = SysAllocStringLen(value.data(), static_cast<UINT>(value.size()));
	UiaRaiseAutomationPropertyChangedEvent(provider, property, oldValue, newValue);
	VariantClear(&newValue);
}

void SpreadsheetControl::RaiseSelectedChanged(IRawElementProviderSimple* provider, bool selected)
{
	VARIANT oldValue, newValue;
	VariantInit(&oldValue);
	VariantInit(&newValue);
	oldValue.vt = VT_BOOL;
	newValue.vt = VT_BOOL;
	oldValue.boolVal = selected ? VARIANT_FALSE : VARIANT_TRUE;
	newValue.boolVal = selected ? VARIANT_TRUE : VARIANT_FALSE;
	UiaRaiseAutomationPropertyChangedEvent(provider, UIA_SelectionItemIsSelectedPropertyId, oldValue, newValue);
}

void SpreadsheetControl::RaiseFocusOnCurrent()
{
	const auto fragment = GetFocusTargetFragment();
	const auto provider = fragment.try_query<IRawElementProviderSimple>();
	if (provider)
	{
		UiaRaiseAutomationEvent(provider.get(), UIA_AutomationFocusChangedEventId);
	}
}

void SpreadsheetControl::NotifyChanged()
{
	if (!IsAvailableForQueries())
	{
		return;
	}

	const SpreadsheetState old = m_state;
	const SpreadsheetState state = ReadState();
	m_state = state;

	bool areaChanged = !state.SameVisibleArea(old);
	bool currentChanged = old.currentRow != state.currentRow || old.currentColumn != state.currentColumn;
	// The focus goes to another part: the current cell moved, or whole rows
	// or columns became selected or stopped being selected.
	bool focusTargetChanged = currentChanged || old.GetFocusPart() != state.GetFocusPart();
	bool selectionChanged = !state.SameSelection(old);
	bool editingEnded = old.editing && !state.editing;

	bool ready = IsReadyForEvents();

	// Parts whose row or column no longer exists leave the tree; parts no
	// longer on screen are forgotten, and made again when asked for.
	for (auto cell = m_cells.begin(); cell != m_cells.end();)
	{
		int row = cell->first.first;
		int column = cell->first.second;
		if (!state.HasCell(row, column))
		{
			if (ready)
			{
				UiaDisconnectProvider(cell->second.provider.get());
			}
			cell = m_cells.erase(cell);
		}
		else if (!(state.IsRowVisible(row) && state.IsColumnVisible(column)) && !state.IsCurrent(row, column))
		{
			cell = m_cells.erase(cell);
		}
		else
		{
			++cell;
		}
	}
	for (auto header = m_columnHeaders.begin(); header != m_columnHeaders.end();)
	{
		if (!state.HasColumn(header->first))
		{
			if (ready)
			{
				UiaDisconnectProvider(header->second.provider.get());
			}
			header = m_columnHeaders.erase(header);
		}
		else if (!state.IsColumnVisible(header->first) && header->first != state.currentColumn)
		{
			header = m_columnHeaders.erase(header);
		}
		else
		{
			++header;
		}
	}
	for (auto header = m_rowHeaders.begin(); header != m_rowHeaders.end();)
	{
		if (!state.HasRow(header->first))
		{
			if (ready)
			{
				UiaDisconnectProvider(header->second.provider.get());
			}
			header = m_rowHeaders.erase(header);
		}
		else if (!state.IsRowVisible(header->first) && header->first != state.currentRow)
		{
			header = m_rowHeaders.erase(header);
		}
		else
		{
			++header;
		}
	}

	if (!ready)
	{
		for (auto& cell : m_cells)
		{
			cell.second.selected = state.Contains(cell.first.first, cell.first.second);
		}
		return;
	}

	// Other rows or columns on screen: the spreadsheet's parts are read again.
	if (areaChanged)
	{
		int rid[] = { UiaAppendRuntimeId, GetUniqueId() };
		UiaRaiseStructureChangedEvent(GetProvider().get(), StructureChangeType_ChildrenInvalidated, rid, ARRAYSIZE(rid));
	}

	// The cells screen readers know of: their names, values and selection.
	for (auto& cell : m_cells)
	{
		int row = cell.first.first;
		int column = cell.first.second;
		CellEntry& entry = cell.second;
		IRawElementProviderSimple* provider = entry.provider.get();

		std::wstring name = GetCellName(row, column);
		if (name != entry.name)
		{
			entry.name = name;
			RaiseStringChanged(provider, UIA_NamePropertyId, name);
		}
		std::wstring value = GetCellText(row, column);
		if (value != entry.value)
		{
			entry.value = value;
			RaiseStringChanged(provider, UIA_ValueValuePropertyId, value);
		}
		std::wstring roleText = GetRoleText(row, column);
		if (roleText != entry.roleText)
		{
			entry.roleText = roleText;
			RaiseStringChanged(provider, UIA_LocalizedControlTypePropertyId, roleText);
		}
		bool selected = state.Contains(row, column);
		if (selected != entry.selected)
		{
			entry.selected = selected;
			RaiseSelectedChanged(provider, selected);
		}
	}

	// The column letters and row numbers: whether wholly selected.
	for (auto& header : m_columnHeaders)
	{
		std::wstring roleText = GetRoleText(-1, header.first);
		if (roleText != header.second.roleText)
		{
			header.second.roleText = roleText;
			RaiseStringChanged(header.second.provider.get(), UIA_LocalizedControlTypePropertyId, roleText);
		}
	}
	for (auto& header : m_rowHeaders)
	{
		std::wstring roleText = GetRoleText(header.first, -1);
		if (roleText != header.second.roleText)
		{
			header.second.roleText = roleText;
			RaiseStringChanged(header.second.provider.get(), UIA_LocalizedControlTypePropertyId, roleText);
		}
	}

	bool focusFollows = GetRoot() != nullptr && HasUiaFocus() && !state.editing
		&& state.HasCell(state.currentRow, state.currentColumn);

	// The selection: a range is read again; a single cell is selected, which
	// the focus already says when it moved there.
	if (selectionChanged && state.HasCurrent())
	{
		if (state.IsRange())
		{
			UiaRaiseAutomationEvent(GetProvider().get(), UIA_Selection_InvalidatedEventId);
		}
		else if (!(currentChanged && focusFollows) && state.HasCell(state.currentRow, state.currentColumn))
		{
			const auto fragment = GetCellFragment(state.currentRow, state.currentColumn);
			const auto provider = fragment.try_query<IRawElementProviderSimple>();
			if (provider)
			{
				UiaRaiseAutomationEvent(provider.get(), UIA_SelectionItem_ElementSelectedEventId);
			}
		}
	}

	// While the spreadsheet has the focus, the focus follows the current cell
	// (or its row's number or column's letters, while whole rows or columns
	// are selected), and comes back to it when editing ends.
	if ((focusTargetChanged || editingEnded) && focusFollows)
	{
		RaiseFocusOnCurrent();
	}
}

// Generate ControlT's members here, so other files can link to them (the
// quorum-natives build, WindowsDllMacBuild.md fix 2).
template class ControlT<SpreadsheetControl, SpreadsheetProvider>;
