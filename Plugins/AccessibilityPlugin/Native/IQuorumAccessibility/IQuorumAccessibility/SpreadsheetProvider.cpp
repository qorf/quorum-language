#include "SpreadsheetProvider.h"
#include "CellProvider.h"

SpreadsheetProvider::SpreadsheetProvider(_In_ SpreadsheetControl* control) : m_refCount(1), control(control)
{
}

SpreadsheetProvider::~SpreadsheetProvider()
{
}
// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) SpreadsheetProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) SpreadsheetProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP SpreadsheetProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IGridProvider))
	{
		*ppInterface = static_cast<IGridProvider*>(this);
	}
	else if (riid == __uuidof(ITableProvider))
	{
		*ppInterface = static_cast<ITableProvider*>(this);
	}
	else if (riid == __uuidof(ISelectionProvider))
	{
		*ppInterface = static_cast<ISelectionProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	if (*ppInterface != NULL) {
		(static_cast<IUnknown*>(*ppInterface))->AddRef();
	}
	return S_OK;
}

// Gets UI Automation provider options.
IFACEMETHODIMP SpreadsheetProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// The Tree doesn't support any patterns so NULL is correct.
IFACEMETHODIMP SpreadsheetProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	switch (patternId)
	{
	case UIA_GridPatternId:
		*pRetVal = static_cast<IGridProvider*>(this);
		break;
	case UIA_TablePatternId:
		*pRetVal = static_cast<ITableProvider*>(this);
		break;
	case UIA_SelectionPatternId:
		*pRetVal = static_cast<ISelectionProvider*>(this);
		break;
	default:
		*pRetVal = NULL;
	}

	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}
	return S_OK;
}

// Gets the custom properties for this control.
IFACEMETHODIMP SpreadsheetProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = control->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->control->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_TabControlTypeId;
	}
	else if (propertyId == UIA_IsContentElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
		// This tells the screen reader whether or not the control can be interacted with.
		// Hardcoded to true but this property could be dynamic depending on the needs of the Quorum GUI.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_LabeledByPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->control->GetName());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"spreadsheet");
	}
	else if (propertyId == UIA_OrientationPropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->boolVal = OrientationType_Horizontal;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
	}

	return S_OK;
}

IFACEMETHODIMP SpreadsheetProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
}

//IGridProvider Methods
IFACEMETHODIMP SpreadsheetProvider::get_ColumnCount(int* pRetVal) {
	return S_OK;
}
IFACEMETHODIMP SpreadsheetProvider::get_RowCount(int* pRetVal) {
	return S_OK;
}
IFACEMETHODIMP SpreadsheetProvider::GetItem(int row, int column, IRawElementProviderSimple** pRetVal) {
	return S_OK;
}

//ITableProvider Methods
IFACEMETHODIMP SpreadsheetProvider::get_RowOrColumnMajor(RowOrColumnMajor* major) {
	return S_OK;
}
IFACEMETHODIMP SpreadsheetProvider::GetColumnHeaders(SAFEARRAY** pRetVal) {
	return S_OK;
}
IFACEMETHODIMP SpreadsheetProvider::GetRowHeaders(SAFEARRAY** pRetVal) {
	return S_OK;
}

IFACEMETHODIMP SpreadsheetProvider::GetSelection(SAFEARRAY** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	JNIEnv* env = GetJNIEnv();
	jlong selectionPointer = env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetSpreadsheetSelectionPointer, control->GetMe());

	if (selectionPointer == 0)
	{
		*pRetVal = NULL;
		return S_OK;
	}

	CellControl* cellControl = reinterpret_cast<CellControl*>(selectionPointer);
	CellProvider* cellProvider = cellControl->GetProvider();

	HRESULT hr = S_OK;

	*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		long index = 0;
		hr = SafeArrayPutElement(*pRetVal, &index, cellProvider);
		if (FAILED(hr))
		{
			SafeArrayDestroy(*pRetVal);
			*pRetVal = NULL;
		}
		else
		{
			// Since the provider is being passed out of our domain, we need to increment its reference counter.
			cellProvider->AddRef();
		}
	}

	return hr;
}

IFACEMETHODIMP SpreadsheetProvider::get_CanSelectMultiple(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetProvider::get_IsSelectionRequired(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}
