#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"
#include "ControlTImpl.h"

RadioButtonControl::RadioButtonControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ ButtonGroupControl* parent, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem), m_parentButtonGroup(parent)
{
}

ButtonGroupControl* RadioButtonControl::GetParentButtonGroup()
{
	return m_parentButtonGroup;
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
