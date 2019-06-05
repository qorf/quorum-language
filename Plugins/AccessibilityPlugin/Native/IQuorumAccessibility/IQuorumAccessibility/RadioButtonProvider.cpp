#define INITGUID
#include <windows.h>
#include <UIAutomation.h>

#include "RadioButtonProvider.h"
#include "RadioButtonControl.h"

#include <iostream>
#include <string>

RadioButtonProvider::RadioButtonProvider(HWND hwnd, RadioButtonControl* pButtonControl) : m_refCount(1), m_buttonControlHWnd(hwnd), m_pButtonControl(pButtonControl)
{
	// Nothing to do.
}

RadioButtonProvider::~RadioButtonProvider()
{
	// Nothing to do.
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) RadioButtonProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) RadioButtonProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP RadioButtonProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
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


// =========== IRawElementProviderSimple implementation

// Get provider options.
IFACEMETHODIMP RadioButtonProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider | ProviderOptions_UseComThreading;
	return S_OK;
}

// Get the object that supports ISelectionItemPattern.
IFACEMETHODIMP RadioButtonProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (patternId == UIA_SelectionItemPatternId)
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}
	else
	{
		*pRetVal = NULL;
	}
	return S_OK;
}

// Gets custom properties.
IFACEMETHODIMP RadioButtonProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = m_pButtonControl->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"radio button");
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pButtonControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pButtonControl->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_RadioButtonControlTypeId;
	}
	else if (propertyId == UIA_IsSelectionItemPatternAvailablePropertyId)
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
		// Tells the screen reader that this control is capable of getting keyboard focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;

	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		// UIA_HasKeyboardFocusPropertyId is responsible for whether or not the screen reader announces that this control gained focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pButtonControl->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
		// UI Automation will attempt to get the property from the host window provider.
		// If the property is found then it will have the UI Automation defaults listed in the Microsoft Developer's Network documentation.
		// More often than not the default values are responsible for a control not functioning properly with a screen reader.
	}

	return S_OK;
}

// Gets the UI Automation provider for the host window. This provider supplies most properties.
IFACEMETHODIMP RadioButtonProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(m_buttonControlHWnd, pRetVal);
}


// =========== ISelectionItemProvider implementation.

// Select: Deselects any selected items and then selects the current element.
IFACEMETHODIMP RadioButtonProvider::Select()
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId);
	}

	return S_OK;
}

// AddToSelection: Adds the current element to the collection of selected items.
IFACEMETHODIMP RadioButtonProvider::AddToSelection()
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	Select();
	return S_OK;
}

// RemoveFromSelection: Removes the current element from the collection of selected items.
//						One and only one item must always be selected, so this is not implemented.
IFACEMETHODIMP RadioButtonProvider::RemoveFromSelection()
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	return UIA_E_INVALIDOPERATION;
}

// get_IsSelected: Indicates whether an item is selected. 
IFACEMETHODIMP RadioButtonProvider::get_IsSelected(_Out_ BOOL * pRetVal)
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (m_pButtonControl->GetState())
	{
		*pRetVal = TRUE;
	}
	else
	{
		*pRetVal = FALSE;
	}


	return S_OK;


}

// get_SelectionContainer: Specifies the provider that implements ISelectionProvider and acts as the container for the calling object.
IFACEMETHODIMP RadioButtonProvider::get_SelectionContainer(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	if (!IsWindow(m_pButtonControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

// =========== Other Methods

void RadioButtonProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}