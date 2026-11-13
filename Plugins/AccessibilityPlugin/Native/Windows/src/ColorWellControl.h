#pragma once

#include <string>
#include "ControlT.h"

class ColorWellProvider;

/*
	A Quorum ColorWell for UI Automation: a button that opens the color
	picker, whose value is its color in words, such as "#4A5A6E, dim gray".
*/
class ColorWellControl : public ControlT<ColorWellControl, ColorWellProvider>
{
public:
	ColorWellControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	// Opens the picker, as a click does.
	void Invoke();

	// The color in words.
	std::wstring GetColorText();

	// Called from Quorum after the color changed: raises the value's change.
	void NotifyColorChanged();
private:
	std::wstring currentText;
};
