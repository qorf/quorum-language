#include "CellProvider.h"
#include "TableProvider.h"

CellProvider::CellProvider(CellControl* pControl) : ProviderT(pControl)
{
}

bool CellProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_TableItemPatternId) || (patternId == UIA_GridItemPatternId) || (patternId == UIA_ExpandCollapsePatternId) || (patternId == UIA_ValuePatternId) || (patternId == UIA_SelectionItemPatternId));
}

CONTROLTYPEID CellProvider::GetControlType() const noexcept
{
	return UIA_DataItemControlTypeId;
}

IFACEMETHODIMP CellProvider::get_Column(int* pRetVal) 
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		index = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCellColumnIndex, m_control->GetMe());
		*pRetVal = (int)index;
	}

	return S_OK;
}

IFACEMETHODIMP CellProvider::get_ColumnSpan(int* pRetVal) 
{
	// Currently, cells in Quorum can't span multiple columns.
	*pRetVal = 1;

	return S_OK;
}
IFACEMETHODIMP CellProvider::get_ContainingGrid(IRawElementProviderSimple** pRetVal) 
{
	m_control->GetParentTable()->GetProvider().query_to(pRetVal);

	return S_OK;
}
IFACEMETHODIMP CellProvider::get_Row(int* pRetVal) 
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		index = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCellRowIndex, m_control->GetMe());
		*pRetVal = (int)index;
	}

	return S_OK;
}

IFACEMETHODIMP CellProvider::get_RowSpan(int* pRetVal) 
{
	// Currently, cells in Quorum can't span multiple rows.
	*pRetVal = 1;

	return S_OK;
}

//ITableItemProvider Methods
IFACEMETHODIMP CellProvider::GetColumnHeaderItems(SAFEARRAY** pRetVal) {
	return S_OK;
}
IFACEMETHODIMP CellProvider::GetRowHeaderItems(SAFEARRAY** pRetVal) {
	return S_OK;
}

IFACEMETHODIMP CellProvider::get_ExpandCollapseState(ExpandCollapseState* pRetVal)
{
	*pRetVal = expanded;
	return S_OK;
}

IFACEMETHODIMP CellProvider::Expand()
{
	expanded = ExpandCollapseState_Expanded;
	NotifyElementExpandCollapse();
	return S_OK;
}

IFACEMETHODIMP CellProvider::Collapse()
{
	expanded = ExpandCollapseState_Collapsed;
	NotifyElementExpandCollapse();
	return S_OK;
}

void CellProvider::NotifyElementExpandCollapse()
{
	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{
		VARIANT oldValue, newValue;
		oldValue.vt = VT_I4;
		newValue.vt = VT_I4;

		if (m_control->IsExpanded())
		{
			oldValue.lVal = ExpandCollapseState_Collapsed;
			newValue.lVal = ExpandCollapseState_Expanded;
		}
		else
		{
			oldValue.lVal = ExpandCollapseState_Expanded;
			newValue.lVal = ExpandCollapseState_Collapsed;
		}

		THROW_IF_FAILED(UiaRaiseAutomationPropertyChangedEvent(this, UIA_ExpandCollapseExpandCollapseStatePropertyId, oldValue, newValue));
	}
}

// Raises a UIA Event when an item is selected.
void CellProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
		UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId);
	}
}

IFACEMETHODIMP CellProvider::get_IsReadOnly(BOOL* returnValue)
{
	// Currently hard-coded to true -- Quorum cells are read-only in the current version.
	* returnValue = VARIANT_TRUE;

	return S_OK;
}

IFACEMETHODIMP CellProvider::SetValue(LPCWSTR value)
{
	// NYI

	return UIA_E_NOTSUPPORTED;
}

IFACEMETHODIMP CellProvider::get_Value(BSTR* returnValue)
{
	std::wstring text = L"";//m_control->GetText();
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));

	return S_OK;
}

IFACEMETHODIMP CellProvider::Select(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP CellProvider::AddToSelection(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP CellProvider::RemoveFromSelection(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP CellProvider::get_IsSelected(BOOL* pRetVal)
{
	TableControl* sheet = m_control->GetParentTable();
	BOOL selected = (sheet->GetSelected() == m_control);

	*pRetVal = selected;
	return S_OK;
}

IFACEMETHODIMP CellProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal)
{
	m_control->GetParentTable()->GetProvider().query_to(pRetVal);

	return S_OK;
}
