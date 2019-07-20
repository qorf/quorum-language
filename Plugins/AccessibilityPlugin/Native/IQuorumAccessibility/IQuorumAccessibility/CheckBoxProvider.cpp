#include <windows.h>
#include <UIAutomation.h>

#include "CheckBoxProvider.h"
#include "CheckBoxControl.h"

#include <iostream>
#include <string>

CheckBoxProvider::CheckBoxProvider(HWND hwnd, CheckBoxControl* pButtonControl) : m_refCount(1), m_buttonControlHWnd(hwnd), m_pButtonControl(pButtonControl)
{
	// Nothing to do.
}

CheckBoxProvider::~CheckBoxProvider()
{
	// Nothing to do.
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) CheckBoxProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) CheckBoxProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP CheckBoxProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IToggleProvider))
	{
		*ppInterface = static_cast<IToggleProvider*>(this);
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
IFACEMETHODIMP CheckBoxProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// Get the object that supports ISelectionItemPattern.
IFACEMETHODIMP CheckBoxProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	if (patternId == UIA_TogglePatternId)
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
IFACEMETHODIMP CheckBoxProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = m_pButtonControl->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"check box");
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
		pRetVal->lVal = UIA_CheckBoxControlTypeId;
	}
	else if (propertyId == UIA_IsTogglePatternAvailablePropertyId)
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
IFACEMETHODIMP CheckBoxProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(m_buttonControlHWnd, pRetVal);
}


// =========== ISelectionItemProvider implementation.

// Toggle: Cycles through the toggle states of a control. 
IFACEMETHODIMP CheckBoxProvider::Toggle()
{
	VARIANT oldValue, newValue;
	oldValue.vt = VT_I4;
	newValue.vt = VT_I4;

	if (m_pButtonControl->GetState() == ToggleState_On)
	{
		oldValue.lVal = ToggleState_On;
		newValue.lVal = ToggleState_Off;

		// Change state to off
		m_pButtonControl->SetState(ToggleState_Off);
	}
	else
	{
		oldValue.lVal = ToggleState_Off;
		newValue.lVal = ToggleState_On;

		// Change state to on
		m_pButtonControl->SetState(ToggleState_On);
	}

	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationPropertyChangedEvent(this, UIA_ToggleToggleStatePropertyId, oldValue, newValue);
	}

	return S_OK;
}

// get_ToggleState: Specifies the toggle state of the control.
IFACEMETHODIMP CheckBoxProvider::get_ToggleState(_Out_ ToggleState * pRetVal)
{
	*pRetVal = m_pButtonControl->GetState();
	return S_OK;
}

// =========== Other Methods

void CheckBoxProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}
