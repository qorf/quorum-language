#include <windows.h>
#include <UIAutomation.h>

#include "MenuBarProvider.h"
#include "MenuBarControl.h"
#include "MenuItemProvider.h"

MenuItemProvider::MenuItemProvider(MenuItemControl * pControl)
{
}

MenuItemProvider::~MenuItemProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) MenuItemProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) MenuItemProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP MenuItemProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
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

IFACEMETHODIMP MenuItemProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	// TODO: Implement the patterns and provide them here.
	*pRetVal = NULL;
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		// TODO:
		pRetVal->vt = VT_EMPTY;

	}
	else if (propertyId == UIA_NamePropertyId)
	{
		// TODO: Insert Name here.
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"");
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_MenuItemControlTypeId;
	}
	// HasKeyboardFocus is true if the MenuBar has focus, and this MenuItem is selected.
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		// TODO: Implement this.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_FALSE;
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

// Gets the UI Automation provider for the host window. 
// Return NULL since menuitems are not directly hosted in a window and therefore don't have an HWND
IFACEMETHODIMP MenuItemProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}