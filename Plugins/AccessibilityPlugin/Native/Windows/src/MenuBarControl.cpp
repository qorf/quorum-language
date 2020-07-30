#include "MenuBarControl.h"
#include "MenuBarProvider.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "ControlTImpl.h"

MenuBarControl::MenuBarControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem) 
	: ControlT(env, std::move(menuBarName), L"", jItem)
{
}

MenuItemControl* MenuBarControl::GetSelectedMenuItem() const noexcept
{
	return m_pSelectedMenuItem;
}

Item* MenuBarControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelectedMenuItem();
}

void MenuBarControl::SetSelectedMenuItem(_In_opt_ MenuItemControl * selectedMenuItem)
{
	m_pSelectedMenuItem = selectedMenuItem;
	if (m_pSelectedMenuItem != nullptr && IsReadyForEvents())
	{
		m_pSelectedMenuItem->GetProvider()->NotifyElementSelected();
	}
}

void MenuBarControl::NotifyFocusGained()
{
	Item::NotifyFocusGained();
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeStartEventId);
	}
}

void MenuBarControl::NotifyFocusLost()
{
	Item::NotifyFocusLost();
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeEndEventId);
	}
}
