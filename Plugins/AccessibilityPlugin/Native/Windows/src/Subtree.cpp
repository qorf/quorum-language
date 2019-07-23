#include <windows.h>
#include <UIAutomation.h>

#include "Subtree.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "TreeControl.h"

int Subtree::GetCount()
{
	return static_cast<int>(m_treeItemCollection.size());
}

bool Subtree::HasChildren()
{
	return !m_treeItemCollection.empty();
}

TREEITEM_ITERATOR Subtree::GetTreeItemAt(_In_ int index)
{
	return m_treeItemCollection.begin() + index;
}


bool Subtree::AddTreeItem(_In_ TreeItemControl* pNewTreetem)
{
	if (pNewTreetem != NULL)
	{
		// Add to collection
		m_treeItemCollection.push_back(pNewTreetem);

		pNewTreetem->SetTreeItemIndex(GetCount() - 1);

		// Raise UI Automation Event
		if (UiaClientsAreListening())
		{
			TreeItemProvider* provider = pNewTreetem->GetTreeItemProvider();

			if (provider != NULL)
				provider->NotifyTreeItemAdded();
		}
		
		return true;
	}
	else
		return false;
}

bool Subtree::RemoveTreeItem(TreeItemControl* pTreeItem)
{
	TREEITEM_ITERATOR menuItemToRemove = GetTreeItemAt(pTreeItem->GetTreeItemIndex());

	if (pTreeItem->GetParentTree()->GetSelectedTreeItem() == pTreeItem)
		pTreeItem->GetParentTree()->SetSelectedTreeItem(nullptr);

	// Raise a UIA event
	TreeItemProvider* pMenuItemProvider = pTreeItem->GetTreeItemProvider();
	if (pMenuItemProvider != NULL)
	{
		pMenuItemProvider->NotifyTreeItemRemoved();
		UiaDisconnectProvider(pMenuItemProvider);
	}


	// Remove from list
	m_treeItemCollection.erase(menuItemToRemove);


	delete pTreeItem;

	return true;
}
