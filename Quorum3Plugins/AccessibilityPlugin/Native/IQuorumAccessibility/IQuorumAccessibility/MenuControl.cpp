#include <windows.h>
#include <UIAutomation.h>

#include "MenuControl.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"


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

bool MenuControl::AddMenuItem(_In_ MenuItemControl* pNewMenuItem)
{
	// Create a unique id for the new item.
	int id = CreateUniqueId();

	if (pNewMenuItem != NULL)
	{
		// Set id field
		pNewMenuItem->SetId(id);

		// Add to MenuBar's collection
		m_menuItemCollection.push_back(pNewMenuItem);

		// Raise UI Automation Event
		MenuItemProvider* provider = pNewMenuItem->GetMenuItemProvider();
		provider->NotifyMenuItemAdded();

		return true;
	}
	else
		return false;
}
