#define INITGUID
#include <windows.h>
#include <UIAutomation.h>

#include "ButtonProvider.h"
#include "ButtonControl.h"

ButtonProvider::ButtonProvider(ButtonControl* control) : ProviderT(control)
{
}

bool ButtonProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_InvokePatternId);
}

CONTROLTYPEID ButtonProvider::GetControlType() const noexcept
{
	return UIA_ButtonControlTypeId;
}

// Invoke
IFACEMETHODIMP ButtonProvider::Invoke() noexcept
{
	PostMessage(m_control->GetHWND(), QUORUM_INVOKEBUTTON, 0, 0);
	return S_OK;
}

void ButtonProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}
