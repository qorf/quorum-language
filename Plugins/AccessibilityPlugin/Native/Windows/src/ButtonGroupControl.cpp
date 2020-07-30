#include "ButtonGroupControl.h"
#include "ButtonGroupProvider.h"
#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"
#include "ControlTImpl.h"

ButtonGroupControl::ButtonGroupControl(JNIEnv* env, std::wstring&& groupName, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(groupName), std::move(description), jItem)
{
}

RadioButtonControl* ButtonGroupControl::GetSelectedRadioButton() const noexcept
{
	return m_pSelectedRadioButton;
}

Item* ButtonGroupControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelectedRadioButton();
}

void ButtonGroupControl::SetSelectedRadioButton(_In_opt_ RadioButtonControl* selectedButton)
{
	m_pSelectedRadioButton = selectedButton;
	if (m_pSelectedRadioButton != nullptr && IsReadyForEvents())
	{
		m_pSelectedRadioButton->GetProvider()->NotifyElementSelected();
	}
}
