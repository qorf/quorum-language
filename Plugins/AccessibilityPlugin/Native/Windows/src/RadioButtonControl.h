#pragma once

#include "ControlT.h"

class RadioButtonProvider;
class ButtonGroupControl;

class RadioButtonControl : public ControlT<RadioButtonControl, RadioButtonProvider>
{
public:
	RadioButtonControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, _In_ ButtonGroupControl* parent, jobject jItem);

	ButtonGroupControl* GetParentButtonGroup();
	void SetState(_In_ bool controlState);
	bool GetState();

private:
	ButtonGroupControl* m_parentButtonGroup;
};
