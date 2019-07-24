#pragma once

#include "ControlT.h"

class CheckBoxProvider;

class CheckBoxControl : public ControlT<CheckBoxControl, CheckBoxProvider>
{
public:
	CheckBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem);

	virtual void Focus(bool isFocused) override;

	// Toggle functions.
	void SetState(_In_ ToggleState controlState);
	ToggleState GetState();
};
