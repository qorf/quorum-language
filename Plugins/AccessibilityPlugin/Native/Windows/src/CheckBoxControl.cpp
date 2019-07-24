#include "CheckBoxControl.h"
#include "CheckBoxProvider.h"
#include "ControlTImpl.h"

CheckBoxControl::CheckBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
}

void CheckBoxControl::Focus(bool focused)
{
	this->focused = focused;
	GetProvider()->NotifyFocusGained();
}

void CheckBoxControl::SetState(_In_ ToggleState controlState)
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
}

ToggleState CheckBoxControl::GetState()
{
	JNIEnv* env = GetJNIEnv();
	jboolean toggleState = env->CallBooleanMethod(GetMe(), JavaClass_ToggleButton.GetToggleState);
	ToggleState result;

	boolean value = toggleState;
	
	if (!value)
	{
		result = ToggleState_Off;
	}
	else
	{
		result = ToggleState_On;
	}

	return result;
}
