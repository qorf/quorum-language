#define INITGUID
#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include <ole2.h>
#include <UIAutomation.h>

#include "ToggleButtonProvider.h"
#include "ToggleButtonControl.h"

#include <iostream>
#include <string>

ToggleButtonProvider::ToggleButtonProvider(HWND hwnd, ToggleButtonControl* pButtonControl) : m_refCount(1), m_buttonControlHWnd(hwnd), m_pButtonControl(pButtonControl)
{
	// Nothing to do.
}

ToggleButtonProvider::~ToggleButtonProvider()
{
	// Nothing to do.
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ToggleButtonProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) ToggleButtonProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP ToggleButtonProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
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
IFACEMETHODIMP ToggleButtonProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// Get the object that supports ISelectionItemPattern.
IFACEMETHODIMP ToggleButtonProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
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
IFACEMETHODIMP ToggleButtonProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"Toggle Button");
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"Help text here");
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
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pButtonControl->GetName());
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		// Tells the screen reader that this control is capable of getting keyboard focus.
		// This isn't enough for the screen reader to announce the control's existence to the user when it gains focus in Quorum.
		// UIA_HasKeyboardFocusPropertyId is responsible for whether or not the screen reader announces that this control gained focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		// This tells the screen reader whether or not this control has Keyboard focus. Normally, only one control/window is allowed to have keyboard focus at a time
		// but by lying and having every instance of this control report that it has keyboard focus then we don't have to mantain what has focus on the native level.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
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
IFACEMETHODIMP ToggleButtonProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(m_buttonControlHWnd, pRetVal);
}


// =========== ISelectionItemProvider implementation.

// Toggle: Cycles through the toggle states of a control. 
IFACEMETHODIMP ToggleButtonProvider::Toggle()
{

	if (m_pButtonControl->GetState() == ToggleState_On)
	{
		// Change state to off
		m_pButtonControl->SetState(ToggleState_Off);
	}
	else if (m_pButtonControl->GetState() == ToggleState_Off)
	{
		// Change state to off
		m_pButtonControl->SetState(ToggleState_On);
	}
	else
	{
		// State is ToggleState_Indeterminate. This state hasn't been implemented but it could be.
	}

	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{

		UiaRaiseAutomationEvent(this, UIA_AutomationPropertyChangedEventId);
	}

	return S_OK;
}

// get_ToggleState: Specifies the toggle state of the control.
IFACEMETHODIMP ToggleButtonProvider::get_ToggleState(_Out_ ToggleState * pRetVal)
{
	*pRetVal = m_pButtonControl->GetState();
	return S_OK;
}

// =========== Other Methods

void ToggleButtonProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}