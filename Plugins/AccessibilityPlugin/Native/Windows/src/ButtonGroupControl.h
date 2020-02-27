#pragma once

#include "ControlT.h"

class ButtonGroupProvider;
class RadioButtonControl;

class ButtonGroupControl : public ControlT<ButtonGroupControl, ButtonGroupProvider>
{
public:
	ButtonGroupControl(JNIEnv* env, std::wstring&& groupName, std::wstring&& description, jobject jItem);

	RadioButtonControl* GetSelectedRadioButton() const noexcept;
	Item* GetUiaFocusDescendant() const noexcept override;
	void SetSelectedRadioButton(_In_opt_ RadioButtonControl* selectedButton);

private:
	RadioButtonControl* m_pSelectedRadioButton = nullptr;
};
