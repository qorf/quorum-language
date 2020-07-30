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
	JNIEnv* env = GetJNIEnv();
	env->CallVoidMethod(GetMe(), JavaClass_Button.Activate);
}

void ButtonControl::NotifyInvoked()
{
	if (IsReadyForEvents())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_Invoke_InvokedEventId);
	}
}
