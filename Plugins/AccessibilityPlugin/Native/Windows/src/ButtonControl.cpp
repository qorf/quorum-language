#include "ButtonControl.h"
#include "ButtonProvider.h"
#include "ControlTImpl.h"

// ButtonControl: Constructor. Sets the default values for the button.
ButtonControl::ButtonControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
}

void ButtonControl::Invoke()
{
	// TODO: Call up to Quorum to invoke the button.
}

void ButtonControl::NotifyInvoked()
{
	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_Invoke_InvokedEventId);
	}
}

void ButtonControl::Focus(bool focused)
{
	this->focused = focused;
	GetProvider()->NotifyFocusGained();
}
