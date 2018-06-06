#include <windows.h>
#include <UIAutomation.h>

#include "MenuBarProvider.h"
#include "MenuBarControl.h"

// =========== IUnknown implementation.

MenuBarProvider::MenuBarProvider(HWND MenuBarControlHWND, MenuBarControl * pMenuBarControl) : m_menuBarControl(MenuBarControlHWND), m_pMenuBarControl(pMenuBarControl)
{
}

IFACEMETHODIMP_(ULONG) MenuBarProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) MenuBarProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP MenuBarProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	//else if (riid == __uuidof(IRawElementProviderFragment))
	//{
	//	*ppInterface = static_cast<IRawElementProviderFragment*>(this);
	//}
	//else if (riid == __uuidof(IRawElementProviderFragmentRoot))
	//{
	//	*ppInterface = static_cast<IRawElementProviderFragmentRoot*>(this);
	//}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

// Gets UI Automation provider options.
//
IFACEMETHODIMP MenuBarProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// The MenuBar doesn't support any patterns so NULL is correct.
//
IFACEMETHODIMP MenuBarProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Gets the custom properties for this control.
//
IFACEMETHODIMP MenuBarProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (propertyId == UIA_AccessKeyPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"ALT");
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_MenuBarControlTypeId;
	}
	else if (propertyId == UIA_IsContentElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_FALSE;
	}
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->m_pMenuBarControl->GetName());
	}
	else if (propertyId == UIA_OrientationPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->m_pMenuBarControl->GetName());
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
	}

	return S_OK;
}

IFACEMETHODIMP MenuBarProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	if (!IsWindow(m_menuBarControl))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	HRESULT hr = UiaHostProviderFromHwnd(m_menuBarControl, pRetVal);
	return hr;
}



MenuBarProvider::~MenuBarProvider()
{
}


