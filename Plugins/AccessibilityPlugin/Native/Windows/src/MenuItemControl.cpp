#include <windows.h>
#include <UIAutomation.h>

#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"

MenuItemControl::MenuItemControl(JNIEnv* env, _In_ std::wstring menuItemName, _In_ std::wstring menuItemShortcut, _In_ bool isMenu, _In_opt_ MenuItemControl* parentMenuItem, _In_ MenuBarControl* parentMenuBar, jobject jItem)
	: Item(env, menuItemName, L"", jItem), m_shortcut(menuItemShortcut), m_pParentMenuBar(parentMenuBar),
	  m_pParentMenuItem(parentMenuItem), m_pMenuItemProvider(NULL), m_myIndex(-1), m_isMenu(isMenu)
{
}

MenuItemControl::~MenuItemControl()
{
}

MenuBarControl * MenuItemControl::GetParentMenuBar()
{
	return m_pParentMenuBar;
}

void MenuItemControl::SetParentMenuBar(_In_ MenuBarControl * menuBar)
{
	m_pParentMenuBar = menuBar;
}

MenuItemControl * MenuItemControl::GetParentMenuItem()
{
	return m_pParentMenuItem;
}

MenuItemProvider * MenuItemControl::GetMenuItemProvider()
{
	if (m_pMenuItemProvider == NULL)
	{
		m_pMenuItemProvider = new MenuItemProvider(this);
		UiaRaiseAutomationEvent(m_pMenuItemProvider, UIA_Window_WindowOpenedEventId);
	}
	return new MenuItemProvider(this);
}

bool MenuItemControl::IsMenu()
{
	return m_isMenu;
}

Menu * MenuItemControl::GetMenu()
{
	Menu* menuControl = GetParentMenuItem();
	if (menuControl == NULL)
		menuControl = GetParentMenuBar();

	return menuControl;
}

void MenuItemControl::SetShortcut(std::wstring shortcut)
{
	m_shortcut = shortcut;
}

const WCHAR * MenuItemControl::GetShortcut()
{
	return m_shortcut.c_str();
}

// GetMenuItemIndex: Retreives the MenuItem index by iterating through the collection.
int MenuItemControl::GetMenuItemIndex()
{
	Menu* pMenuControl = GetMenu();

	for (int i = 0; i < pMenuControl->GetCount(); i++)
	{
		MENUITEM_ITERATOR menuItem = pMenuControl->GetMenuItemAt(i);
		MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(*menuItem);
		if (pMenuItem == this)
		{
			m_myIndex = i;
			break;
		}
	}
	
	return m_myIndex;
	
}

// SetMenuItemIndex: Sets the MenuItem index to the given index.
void MenuItemControl::SetMenuItemIndex(_In_ int index)
{
	m_myIndex = index;
}

bool MenuItemControl::HasFocus()
{
	return (GetParentMenuBar()->GetSelectedMenuItem() == this) && (GetParentMenuBar()->HasFocus());
}

void MenuItemControl::Expand()
{
	GetMenuItemProvider()->Expand();
}

void MenuItemControl::Collapse()
{
	GetMenuItemProvider()->Collapse();
}

void MenuItemControl::Focus(bool isFocused)
{
}



