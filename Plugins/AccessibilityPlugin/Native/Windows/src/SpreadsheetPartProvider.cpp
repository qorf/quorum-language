#include "SpreadsheetPartProvider.h"
#include "SpreadsheetProvider.h"

namespace
{

// The kinds of part, for the runtime IDs.
constexpr int c_cellKind = 1;
constexpr int c_columnHeaderKind = 2;
constexpr int c_rowHeaderKind = 3;

// A one-element array holding a part, for the table patterns.
HRESULT PutSinglePart(const wil::com_ptr<IRawElementProviderFragment>& fragment, SAFEARRAY** pRetVal)
{
	*pRetVal = nullptr;
	if (!fragment)
	{
		return S_OK;
	}
	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	RETURN_IF_NULL_ALLOC(sa);
	LONG index = 0;
	const auto simple = fragment.try_query<IRawElementProviderSimple>();
	RETURN_IF_FAILED(SafeArrayPutElement(sa.get(), &index, simple.get()));
	*pRetVal = sa.release();
	return S_OK;
}

} // anonymous namespace

// =========== A cell

SpreadsheetCellProvider::SpreadsheetCellProvider(_In_ SpreadsheetControl* sheet, int row, int column)
	: SpreadsheetPartT(sheet), m_row(row), m_column(column)
{
}

int SpreadsheetCellProvider::GetPart()
{
	return m_sheet->GetCellPart(m_row, m_column);
}

int SpreadsheetCellProvider::GetRow() const noexcept
{
	return m_row;
}

int SpreadsheetCellProvider::GetColumn() const noexcept
{
	return m_column;
}

bool SpreadsheetCellProvider::Exists()
{
	return m_sheet->GetKnownState().HasCell(m_row, m_column);
}

bool SpreadsheetCellProvider::IsVisible()
{
	const SpreadsheetState& state = m_sheet->GetKnownState();
	return state.IsRowVisible(m_row) && state.IsColumnVisible(m_column);
}

int SpreadsheetCellProvider::GetKind() const noexcept
{
	return c_cellKind;
}

CONTROLTYPEID SpreadsheetCellProvider::GetControlType() const noexcept
{
	return UIA_DataItemControlTypeId;
}

std::wstring SpreadsheetCellProvider::GetName()
{
	return m_sheet->GetCellName(m_row, m_column);
}

std::wstring SpreadsheetCellProvider::GetRoleText()
{
	return m_sheet->GetRoleText(m_row, m_column);
}

bool SpreadsheetCellProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_GridItemPatternId || patternId == UIA_TableItemPatternId
		|| patternId == UIA_SelectionItemPatternId || patternId == UIA_ValuePatternId);
}

bool SpreadsheetCellProvider::HasFocus()
{
	if (m_sheet->GetRoot() == nullptr || !m_sheet->HasUiaFocus())
	{
		return false;
	}
	return m_sheet->GetKnownState().IsFocusTarget(m_row, m_column);
}

bool SpreadsheetCellProvider::IsFocusable() const noexcept
{
	return true;
}

IFACEMETHODIMP SpreadsheetCellProvider::SetFocus() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_sheet->MoveTo(m_row, m_column, false);
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_Row(int* pRetVal) noexcept
{
	*pRetVal = 0;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	*pRetVal = m_row;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_Column(int* pRetVal) noexcept
{
	*pRetVal = 0;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	*pRetVal = m_column;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_RowSpan(int* pRetVal) noexcept
{
	*pRetVal = 1;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_ColumnSpan(int* pRetVal) noexcept
{
	*pRetVal = 1;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_ContainingGrid(IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	m_sheet->GetProvider().query_to(pRetVal);
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetCellProvider::GetRowHeaderItems(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	return PutSinglePart(m_sheet->GetRowHeaderFragment(m_row), pRetVal);
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetCellProvider::GetColumnHeaderItems(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	return PutSinglePart(m_sheet->GetColumnHeaderFragment(m_column), pRetVal);
}
CATCH_RETURN();

// Makes this the current cell and the only one selected, as an arrow key
// does.
IFACEMETHODIMP SpreadsheetCellProvider::Select() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_sheet->MoveTo(m_row, m_column, false);
	return S_OK;
}

// Grows the selection from its anchor to this cell, as Shift with an arrow
// key does.
IFACEMETHODIMP SpreadsheetCellProvider::AddToSelection() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	if (m_sheet->IsCellSelected(m_row, m_column))
	{
		return S_OK;
	}
	m_sheet->MoveTo(m_row, m_column, m_sheet->GetKnownState().HasCurrent());
	return S_OK;
}

// The selection is always a block from its anchor to the current cell, so a
// cell can't be taken out of it alone.
IFACEMETHODIMP SpreadsheetCellProvider::RemoveFromSelection() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	if (!m_sheet->IsCellSelected(m_row, m_column))
	{
		return S_OK;
	}
	return UIA_E_INVALIDOPERATION;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_IsSelected(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	if (!IsAvailable())
	{
		return S_OK;
	}
	*pRetVal = m_sheet->IsCellSelected(m_row, m_column) ? TRUE : FALSE;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	m_sheet->GetProvider().query_to(pRetVal);
	return S_OK;
}
CATCH_RETURN();

// Sets the cell's text as typing does, so it can be undone.
IFACEMETHODIMP SpreadsheetCellProvider::SetValue(LPCWSTR value) noexcept try
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_sheet->SetCellText(m_row, m_column, value == nullptr ? std::wstring() : std::wstring(value));
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetCellProvider::get_Value(BSTR* pRetVal) noexcept
{
	*pRetVal = nullptr;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	std::wstring text = m_sheet->GetCellText(m_row, m_column);
	*pRetVal = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
	return *pRetVal == nullptr ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP SpreadsheetCellProvider::get_IsReadOnly(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	return S_OK;
}

// =========== A column's letters or a row's number

SpreadsheetHeaderProvider::SpreadsheetHeaderProvider(_In_ SpreadsheetControl* sheet, bool isColumn, int index)
	: SpreadsheetPartT(sheet), m_isColumn(isColumn), m_index(index)
{
}

int SpreadsheetHeaderProvider::GetPart()
{
	if (m_isColumn)
	{
		return m_sheet->GetColumnHeaderPart(m_index);
	}
	return m_sheet->GetRowHeaderPart(m_index);
}

int SpreadsheetHeaderProvider::GetRow() const noexcept
{
	return m_isColumn ? -1 : m_index;
}

int SpreadsheetHeaderProvider::GetColumn() const noexcept
{
	return m_isColumn ? m_index : -1;
}

bool SpreadsheetHeaderProvider::Exists()
{
	const SpreadsheetState& state = m_sheet->GetKnownState();
	return m_isColumn ? state.HasColumn(m_index) : state.HasRow(m_index);
}

bool SpreadsheetHeaderProvider::IsVisible()
{
	const SpreadsheetState& state = m_sheet->GetKnownState();
	return m_isColumn ? state.IsColumnVisible(m_index) : state.IsRowVisible(m_index);
}

int SpreadsheetHeaderProvider::GetKind() const noexcept
{
	return m_isColumn ? c_columnHeaderKind : c_rowHeaderKind;
}

CONTROLTYPEID SpreadsheetHeaderProvider::GetControlType() const noexcept
{
	return UIA_HeaderItemControlTypeId;
}

std::wstring SpreadsheetHeaderProvider::GetName()
{
	if (m_isColumn)
	{
		return m_sheet->GetColumnLabel(m_index);
	}
	return std::to_wstring(m_index + 1);
}

std::wstring SpreadsheetHeaderProvider::GetRoleText()
{
	return m_sheet->GetRoleText(GetRow(), GetColumn());
}

bool SpreadsheetHeaderProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	UNREFERENCED_PARAMETER(patternId);
	return false;
}

// While whole rows are selected, the current row's number has the focus;
// while whole columns are, the current column's letters.
bool SpreadsheetHeaderProvider::HasFocus()
{
	if (m_sheet->GetRoot() == nullptr || !m_sheet->HasUiaFocus())
	{
		return false;
	}
	return m_sheet->GetKnownState().IsFocusTarget(GetRow(), GetColumn());
}

bool SpreadsheetHeaderProvider::IsFocusable() const noexcept
{
	return m_sheet->GetKnownState().IsFocusTarget(GetRow(), GetColumn());
}
