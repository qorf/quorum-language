#include "ProgressBarControl.h"
#include "ProgressBarProvider.h"
#include "ControlTImpl.h"

// ProgressBarControl: Constructor. Sets the default values for the button.
ProgressBarControl::ProgressBarControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
}

double ProgressBarControl::GetValue() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetValue);
		return value;
	}
	return 0;
}
void ProgressBarControl::SetValue(double value) {
	if (UiaClientsAreListening()) {
		VARIANT oldState, newState;
		oldState.vt = VT_R8;
		newState.vt = VT_R8;
		oldState.lVal = GetValue();
		newState.lVal = value;
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_RangeValueValuePropertyId, oldState, newState);
	}
}
double ProgressBarControl::GetMinimum() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetMinimum);
		return value;
	}
	return 0;
}
double ProgressBarControl::GetMaximum() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetMaximum);
		return value;
	}
	return 100;
}
