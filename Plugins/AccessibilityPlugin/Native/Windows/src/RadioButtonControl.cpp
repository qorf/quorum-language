#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"
#include "ControlTImpl.h"

RadioButtonControl::RadioButtonControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

void RadioButtonControl::SetState(_In_ bool controlState)
{
	jboolean toggle;

	if (controlState == ToggleState_On)
	{
		toggle = JNI_TRUE;
	}
	else
	{
		toggle = JNI_FALSE;
	}

	//jstring currentLineText = reinterpret_cast<jstring>(env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetCurrentLineText));
	JNIEnv* env = GetJNIEnv();
	env->CallVoidMethod(GetMe(), JavaClass_ToggleButton.SetToggleState, toggle);

	if (controlState)
		GetProvider()->Select();

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_AutomationPropertyChangedEventId);
	}
}

bool RadioButtonControl::GetState()
{
	JNIEnv* env = GetJNIEnv();
	jboolean toggleState = env->CallBooleanMethod(GetMe(), JavaClass_ToggleButton.GetToggleState);
	ToggleState result;

	if (toggleState == JNI_FALSE)
	{
		result = ToggleState_Off;
	}
	else
	{
		result = ToggleState_On;
	}

	return result;
}

void RadioButtonControl::Focus(bool isFocused)
{
	this->focused = focused;
	GetProvider()->NotifyFocusGained();
}
