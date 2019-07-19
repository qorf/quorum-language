#include "ToolBarProvider.h"

ToolBarProvider::ToolBarProvider(_In_ ToolBarControl* control) : m_refCount(1), control(control)
{
}

ToolBarProvider::~ToolBarProvider()
{
}
// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ToolBarProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) ToolBarProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP ToolBarProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
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
IFACEMETHODIMP ToolBarProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// The Tree doesn't support any patterns so NULL is correct.
IFACEMETHODIMP ToolBarProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	* pRetVal = NULL;
	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}
	return S_OK;
}

// Gets the custom properties for this control.
IFACEMETHODIMP ToolBarProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
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
		pRetVal->lVal = UIA_ToolBarControlTypeId;
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
		pRetVal->boolVal = VARIANT_FALSE;
	}
	else if (propertyId == UIA_LabeledByPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->control->GetName());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"toolbar");
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

IFACEMETHODIMP ToolBarProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
}
