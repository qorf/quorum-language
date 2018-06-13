#include <windows.h>
#include <UIAutomation.h>

#include "MenuControl.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"


MENUITEM_ITERATOR MenuControl::GetMenuItemAt(_In_ int index)
{
	return m_menuItemCollection.begin() + index;
}

bool MenuControl::RemoveMenuItem(_In_ int index)
{
	MENUITEM_ITERATOR menuItemToRemove = GetMenuItemAt(index);

	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(*menuItemToRemove);
	
	if (pMenuItem->GetParentMenuBar()->GetSelectedMenuItem() == pMenuItem)
		pMenuItem->GetParentMenuBar()->SetSelectedMenuItem(nullptr);

	// Raise a UIA event
	MenuItemProvider* pMenuItemProvider = pMenuItem->GetMenuItemProvider();
	pMenuItemProvider->NotifyMenuItemRemoved();

	// Remove from list
	m_menuItemCollection.erase(menuItemToRemove);

	delete pMenuItem;

	return true;
}

int MenuControl::GetCount()
{
	return static_cast<int>(m_menuItemCollection.size());
}

bool MenuControl::hasChildren()
{
	return !m_menuItemCollection.empty();
}

ULONG MenuControl::CreateUniqueId()
{
	static ULONG uniqueId;
	return InterlockedIncrement(&uniqueId);
}

bool MenuControl::AddMenuItem(_In_ MenuItemControl* pNewMenuItem)
{
	// Create a unique id for the new item.
	int id = CreateUniqueId();

	if (pNewMenuItem != NULL)
	{
		// TODO: Unique Id must be changed when becoming a child of another control

		// Add to MenuBar's collection
		m_menuItemCollection.push_back(pNewMenuItem);

		// Raise UI Automation Event
		MenuItemProvider* provider = pNewMenuItem->GetMenuItemProvider();
		
		if (provider != NULL)
			provider->NotifyMenuItemAdded();

		return true;
	}
	else
		return false;
}
