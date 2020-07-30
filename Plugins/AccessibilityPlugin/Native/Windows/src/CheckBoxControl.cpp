#include "CheckBoxControl.h"
#include "CheckBoxProvider.h"
#include "ControlTImpl.h"

CheckBoxControl::CheckBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
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
	return m_toggleState;
}

void CheckBoxControl::UpdateToggleState(_In_ ToggleState state)
{
	VARIANT oldState, newState;
	oldState.vt = VT_I4;
	newState.vt = VT_I4;
	oldState.lVal = m_toggleState;
	newState.lVal = state;

	m_toggleState = state;

	if (IsReadyForEvents())
	{
		const auto provider = GetProvider();
		UiaRaiseAutomationPropertyChangedEvent(provider.get(), UIA_ToggleToggleStatePropertyId, oldState, newState);
	}
}
