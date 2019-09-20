#include "ProgressBarControl.h"
#include "ProgressBarProvider.h"
#include "ControlTImpl.h"

// ProgressBarControl: Constructor. Sets the default values for the button.
ProgressBarControl::ProgressBarControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
}

double ProgressBarControl::GetValue() {
	return 0;
}
void ProgressBarControl::SetValue(double value) {
	if (UiaClientsAreListening()) {
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_AutomationPropertyChangedEventId);
	}
}
double ProgressBarControl::GetMinimum() {
	return 0;
}
double ProgressBarControl::GetMaximum() {
	return 100;
}
