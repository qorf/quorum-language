#include <windows.h>
#include <UIAutomation.h>

#include "Menu.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"


int Menu::GetCount()
{
	return static_cast<int>(m_menuItemCollection.size());
}

bool Menu::hasChildren()
{
	return !m_menuItemCollection.empty();
}

ULONG Menu::CreateUniqueId()
{
	static ULONG uniqueId;
	return InterlockedIncrement(&uniqueId);
}

MENUITEM_ITERATOR Menu::GetMenuItemAt(_In_ int index)
{
	return m_menuItemCollection.begin() + index;
}


bool Menu::AddMenuItem(_In_ MenuItemControl* pNewMenuItem)
{
	// Create a unique id for the new item.
	int id = CreateUniqueId();

	if (pNewMenuItem != NULL)
	{
		// TODO: Unique Id must be changed when becoming a child of another control

		// Add to MenuBar's collection
		m_menuItemCollection.push_back(pNewMenuItem);

		pNewMenuItem->SetMenuItemIndex(GetCount() - 1);

		// Raise UI Automation Event
		if (UiaClientsAreListening())
		{
			MenuItemProvider* provider = pNewMenuItem->GetMenuItemProvider();

			if (provider != NULL)
				provider->NotifyMenuItemAdded();
		}
		
		return true;
	}
	else
		return false;
}

bool Menu::RemoveMenuItem(_In_ int index)
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

	//IRawElementProviderSimple* provider = pMenuItem->GetMenuItemProvider();
	//if (provider != NULL)
	//{
	//	HRESULT hr = UiaDisconnectProvider(provider);
	//	if (FAILED(hr))
	//	{
	//		// An error occurred while trying to disconnect the provider. For now, print the error message.
	//	}
	//}

	delete pMenuItem;

	return true;
}