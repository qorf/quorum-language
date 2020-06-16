#pragma once

#include "ControlT.h"

class ProgressBarProvider;

class ProgressBarControl : public ControlT<ProgressBarControl, ProgressBarProvider>
{
public:
	ProgressBarControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	double GetValue();
	void SetValue(double value);
	double GetMinimum();
	double GetMaximum();
	double GetPercent(double value);
private:
	double currentValue;
};
