#pragma once

#include "ControlT.h"

class ButtonProvider;

class ButtonControl : public ControlT<ButtonControl, ButtonProvider>
{
public:
	ButtonControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	virtual void Focus(bool isFocused) override;
	void Invoke();
	void NotifyInvoked();
};
