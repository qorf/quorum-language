#include "PopupMenuItemControl.h"
#include "PopupMenuItemProvider.h"
#include "MenuControl.h"
#include "MenuProvider.h"
#include "ControlTImpl.h"

PopupMenuItemControl::PopupMenuItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& menuItemShortcut, _In_ bool isMenu, _In_ MenuControl* parentMenuBar, jobject jItem)
	: ControlT(env, std::move(menuItemName), L"", jItem)
	, m_shortcut(std::move(menuItemShortcut))
	, m_parentMenuBar(parentMenuBar)
	, m_isMenu(isMenu)
{
}

MenuControl * PopupMenuItemControl::GetParentMenuBar()
{
	return m_parentMenuBar;
}

bool PopupMenuItemControl::IsMenu()
{
	return m_isMenu;
}

void PopupMenuItemControl::SetShortcut(std::wstring&& shortcut)
{
	m_shortcut = std::move(shortcut);
}

const std::wstring& PopupMenuItemControl::GetShortcut()
{
	return m_shortcut;
}

void PopupMenuItemControl::NotifyExpandCollapse()
{
	GetProvider()->NotifyElementExpandCollapse();
}
