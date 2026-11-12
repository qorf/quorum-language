#pragma once

#include <string>
#include "ControlT.h"

class SliderProvider;

/*
	A Quorum Slider for UI Automation (Slider plan, step 6): a range value
	that a screen reader can read and set, with its value as text, such as
	"40 degrees", through the value pattern.
*/
class SliderControl : public ControlT<SliderControl, SliderProvider>
{
public:
	SliderControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	double GetValue();
	double GetMinimum();
	double GetMaximum();
	double GetSmallChange();
	double GetLargeChange();
	std::wstring GetValueText();

	// A value set by a screen reader: changed as the user does, so the
	// slider's listeners are told.
	void ChangeValue(double value);

	// Called from Quorum after the value changed: raises the value events.
	void NotifyValueChanged(double value);
private:
	double CallDouble(jmethodID method, double fallback);

	double currentValue;
	std::wstring currentText;
};
