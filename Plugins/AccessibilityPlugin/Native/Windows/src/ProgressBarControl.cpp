#include "ProgressBarControl.h"
#include "ProgressBarProvider.h"
#include "ControlTImpl.h"

// ProgressBarControl: Constructor. Sets the default values for the button.
ProgressBarControl::ProgressBarControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	currentValue = 0;
}

double ProgressBarControl::GetValue() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetValue);
		return (int) value;
	}
	return 0;
}
void ProgressBarControl::SetValue(double value) {
	if (currentValue == value) {
		return;
	}
	if (IsReadyForEvents())
	{
		VARIANT oldState, newState;
		oldState.vt = VT_R8;
		newState.vt = VT_R8;
		oldState.lVal = currentValue;
		newState.lVal = value;
		currentValue = value;
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_RangeValueValuePropertyId, oldState, newState);

		VARIANT oldText, newText;
		oldText.vt = VT_BSTR;
		newText.vt = VT_BSTR;
		int truncatedCurrent = (int)GetPercent(currentValue);
		int truncatedNew = (int)GetPercent(value);
		std::wstring oldWstr = L"" + std::to_wstring(truncatedCurrent) + L"%";
		std::wstring newWstr = L"" + std::to_wstring(truncatedNew) + L"%";
		oldText.bstrVal = wil::make_bstr(oldWstr.c_str()).release();
		newText.bstrVal = wil::make_bstr(newWstr.c_str()).release();
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_ValueValuePropertyId, oldText, newText);
	}
}

double ProgressBarControl::GetPercent(double value) {
	double result = value - GetMinimum();
	double range = GetMaximum() - GetMinimum();
	result = 100 * result / range;
	return result;
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
