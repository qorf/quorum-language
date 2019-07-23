#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"
#include "MenuBarProvider.h"
#include "ControlTImpl.h"

MenuItemControl::MenuItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& menuItemShortcut, _In_ bool isMenu, _In_ MenuBarControl* parentMenuBar, jobject jItem)
	: ControlT(env, std::move(menuItemName), L"", jItem)
	, m_shortcut(std::move(menuItemShortcut))
	, m_parentMenuBar(parentMenuBar)
	, m_isMenu(isMenu)
{
}

MenuBarControl * MenuItemControl::GetParentMenuBar()
{
	return m_parentMenuBar;
}

bool MenuItemControl::IsMenu()
{
	return m_isMenu;
}

void MenuItemControl::SetShortcut(std::wstring&& shortcut)
{
	m_shortcut = std::move(shortcut);
}

const std::wstring& MenuItemControl::GetShortcut()
{
	return m_shortcut;
}

bool MenuItemControl::HasFocus()
{
	return (GetParentMenuBar()->GetSelectedMenuItem() == this) && (GetParentMenuBar()->HasFocus());
}

void MenuItemControl::Expand()
{
	GetProvider()->Expand();
}

void MenuItemControl::Collapse()
{
	GetProvider()->Collapse();
}

void MenuItemControl::Focus(bool isFocused)
{
}
