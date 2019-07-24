#include "MenuBarControl.h"
#include "MenuBarProvider.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "ControlTImpl.h"

MenuBarControl::MenuBarControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem) 
	: ControlT(env, std::move(menuBarName), L"", jItem)
{
}

MenuItemControl* MenuBarControl::GetSelectedMenuItem()
{
	return m_pSelectedMenuItem;
}

void MenuBarControl::SetSelectedMenuItem(_In_opt_ MenuItemControl * selectedMenuItem)
{
	m_pSelectedMenuItem = selectedMenuItem;
	if (m_pSelectedMenuItem != nullptr && UiaClientsAreListening())
	{
		m_pSelectedMenuItem->GetProvider()->NotifyElementSelected();
	}
}

void MenuBarControl::Focus(bool isFocused)
{
	Item::Focus(isFocused);
	if (isFocused && UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeStartEventId);
	}
	else
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeEndEventId);
	}
}
