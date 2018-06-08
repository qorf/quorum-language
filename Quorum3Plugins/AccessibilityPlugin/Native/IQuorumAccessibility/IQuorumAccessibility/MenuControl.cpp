#include <windows.h>
#include <UIAutomation.h>

#include "MenuControl.h"


MENUITEM_ITERATOR MenuControl::GetMenuItemAt(_In_ int index)
{
	return m_menuItemCollection.begin() + index;
}

int MenuControl::GetCount()
{
	return static_cast<int>(m_menuItemCollection.size());
}

ULONG MenuControl::CreateUniqueId()
{
	static ULONG uniqueId;
	return InterlockedIncrement(&uniqueId);
}

bool MenuControl::AddMenuItem(_In_ MenuItemControl* pMenuItem)
{
	// Create a unique id for the new item.
	int id = CreateUniqueId();

	if (pMenuItem != NULL)
	{
		// Add to MenuBar's collection
		m_menuItemCollection.push_back(pMenuItem);

		// TODO: Raise UI Automation Event


		return true;
	}
	else
		return false;
}
