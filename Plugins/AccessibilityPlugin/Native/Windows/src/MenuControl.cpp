#include "MenuControl.h"
#include "MenuProvider.h"
#include "PopupMenuItemControl.h"
#include "PopupMenuItemProvider.h"
#include "ControlTImpl.h"

MenuControl::MenuControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem) 
	: ControlT(env, std::move(menuBarName), L"", jItem)
{
}

PopupMenuItemControl* MenuControl::GetSelectedMenuItem() const noexcept
{
	return m_pSelectedMenuItem;
}

Item* MenuControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelectedMenuItem();
}

void MenuControl::SetSelectedMenuItem(_In_opt_ PopupMenuItemControl * selectedMenuItem)
{
	m_pSelectedMenuItem = selectedMenuItem;
	if (m_pSelectedMenuItem != nullptr && UiaClientsAreListening())
	{
		m_pSelectedMenuItem->GetProvider()->NotifyElementSelected();
	}
}

void MenuControl::NotifyFocusGained()
{
	Item::NotifyFocusGained();
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeStartEventId);
	}
}

void MenuControl::NotifyFocusLost()
{
	Item::NotifyFocusLost();
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_MenuModeEndEventId);
	}
}
