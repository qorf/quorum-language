#pragma once

#include "ControlT.h"

class RadioButtonProvider;

class RadioButtonControl : public ControlT<RadioButtonControl, RadioButtonProvider>
{
public:
	RadioButtonControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	void SetState(_In_ bool controlState);
	bool GetState();
};
