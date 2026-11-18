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
	if (env != NULL && javaItem != nullptr)
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
	// The old value is kept for the events, and the new one is stored even
	// when events can't be sent yet, so the value never goes stale.
	double oldValue = currentValue;
	currentValue = value;

	if (IsReadyForEvents())
	{
		// The range value is a double (VT_R8), so it goes in dblVal, as
		// SliderControl does.
		VARIANT oldNumber, newNumber;
		VariantInit(&oldNumber);
		VariantInit(&newNumber);
		oldNumber.vt = VT_R8;
		oldNumber.dblVal = oldValue;
		newNumber.vt = VT_R8;
		newNumber.dblVal = value;
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_RangeValueValuePropertyId, oldNumber, newNumber);

		std::wstring oldText = std::to_wstring((int)GetPercent(oldValue)) + L"%";
		std::wstring newText = std::to_wstring((int)GetPercent(value)) + L"%";
		VARIANT oldWords, newWords;
		VariantInit(&oldWords);
		VariantInit(&newWords);
		oldWords.vt = VT_BSTR;
		oldWords.bstrVal = SysAllocString(oldText.c_str());
		newWords.vt = VT_BSTR;
		newWords.bstrVal = SysAllocString(newText.c_str());
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_ValueValuePropertyId, oldWords, newWords);
		VariantClear(&oldWords);
		VariantClear(&newWords);
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
	if (env != NULL && javaItem != nullptr)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetMinimum);
		return value;
	}
	return 0;
}
double ProgressBarControl::GetMaximum() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		jdouble value = env->CallDoubleMethod(javaItem, JavaClass_ProgressBar.GetMaximum);
		return value;
	}
	return 100;
}
