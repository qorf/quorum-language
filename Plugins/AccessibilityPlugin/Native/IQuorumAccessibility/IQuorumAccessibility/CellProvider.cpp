#include "CellProvider.h"

CellProvider::CellProvider(CellControl* pControl, SpreadsheetControl* parent) : 
	referenceCount(1), control(pControl), parent(parent), expanded(ExpandCollapseState_Collapsed)
{
}

CellProvider::~CellProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) CellProvider::AddRef()
{
	return InterlockedIncrement(&referenceCount);
}

IFACEMETHODIMP_(ULONG) CellProvider::Release()
{
	long val = InterlockedDecrement(&referenceCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP CellProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(ITableItemProvider))
	{
		*ppInterface = static_cast<ITableItemProvider*>(this);
	}
	else if (riid == __uuidof(IGridItemProvider))
	{
		*ppInterface = static_cast<IGridItemProvider*>(this);
	}
	else if (riid == __uuidof(IExpandCollapseProvider))
	{
		*ppInterface = static_cast<IExpandCollapseProvider*>(this);
	}
	else if (riid == __uuidof(IValueProvider))
	{
		*ppInterface = static_cast<IValueProvider*>(this);
	}
	else if (riid == __uuidof(ISelectionItemProvider))
	{
		*ppInterface = static_cast<ISelectionItemProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

IFACEMETHODIMP CellProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

IFACEMETHODIMP CellProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	switch (patternId)
	{
	case UIA_GridItemPatternId:
		*pRetVal = static_cast<IGridItemProvider*>(this);
		break;
	case UIA_TableItemPatternId:
		*pRetVal = static_cast<ITableItemProvider*>(this);
		break;
	case UIA_ExpandCollapsePatternId:
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
		break;
	case UIA_ValuePatternId:
		*pRetVal = static_cast<IValueProvider*>(this);
		break;
	case UIA_SelectionItemPatternId:
		*pRetVal = static_cast<ISelectionItemProvider*>(this);
		break;
	default:
		*pRetVal = NULL;
	}

	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}
	return S_OK;
}

IFACEMETHODIMP CellProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		ULONG Id = control->GetHashCode();

		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_DataItemControlTypeId;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;// control->HasFocus(); //tabs in our system cannot receive the focus directly, only the tab pane or the containing item
	}
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsContentElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"cell");
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
	}
	return S_OK;
}

/*
Microsoft's documentation is vague here. It seems to imply that since we're a root, we should return ourselves:

https://docs.microsoft.com/en-us/windows/desktop/api/uiautomationcore/nf-uiautomationcore-irawelementprovidersimple-get_hostrawelementprovider

However, when we do that, it breaks in the inspector, while returning NULL does not, so we're going with null. If, for some reason,
this ends up being wrong, the replacement code is

return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
*/
IFACEMETHODIMP CellProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
	//return S_OK;
}

IFACEMETHODIMP CellProvider::get_Column(int* pRetVal) 
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		index = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCellColumnIndex, control->GetMe());
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
	*pRetVal = parent->GetProvider();

	return S_OK;
}
IFACEMETHODIMP CellProvider::get_Row(int* pRetVal) 
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		index = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCellRowIndex, control->GetMe());
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
	{ //no idea if this is correct for trees, but it seems to be implied by the often incorrect documentation
		UiaRaiseAutomationEvent(this, UIA_ExpandCollapseExpandCollapseStatePropertyId);
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
	std::wstring text = control->GetText();
	*returnValue = SysAllocStringLen(text.data(), text.size());

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
	SpreadsheetControl* sheet = control->GetParent();
	BOOL selected = (sheet->GetSelected() == control);

	*pRetVal = selected;
	return S_OK;
}

IFACEMETHODIMP CellProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal)
{
	*pRetVal = static_cast<IRawElementProviderSimple*>(parent->GetProvider());

	return S_OK;
}