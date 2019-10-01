#include <windows.h>
#include <UIAutomation.h>

#include "CheckBoxProvider.h"
#include "CheckBoxControl.h"

#include <iostream>
#include <string>

CheckBoxProvider::CheckBoxProvider(CheckBoxControl* control) : ProviderT(control)
{
	// Nothing to do.
}

bool CheckBoxProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_TogglePatternId);
}

CONTROLTYPEID CheckBoxProvider::GetControlType() const noexcept
{
	return UIA_CheckBoxControlTypeId;
}

// Toggle: Cycles through the toggle states of a control. 
IFACEMETHODIMP CheckBoxProvider::Toggle()
{
	VARIANT oldValue, newValue;
	oldValue.vt = VT_I4;
	newValue.vt = VT_I4;

	if (m_control->GetState() == ToggleState_On)
	{
		oldValue.lVal = ToggleState_On;
		newValue.lVal = ToggleState_Off;

		// Change state to off
		m_control->SetState(ToggleState_Off);
	}
	else
	{
		oldValue.lVal = ToggleState_Off;
		newValue.lVal = ToggleState_On;

		// Change state to on
		m_control->SetState(ToggleState_On);
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
	*pRetVal = m_control->GetState();
	return S_OK;
}
