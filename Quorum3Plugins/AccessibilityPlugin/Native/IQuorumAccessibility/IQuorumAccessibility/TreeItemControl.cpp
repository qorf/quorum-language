#include <windows.h>
#include <UIAutomation.h>

#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "TreeControl.h"

TreeItemControl::TreeItemControl(_In_ std::wstring treeItemName, _In_ std::wstring treeItemDescription, _In_ bool isSubtree, _In_ int uniqueId, _In_opt_ TreeItemControl* parentTreeItem, _In_ TreeControl* parentTree)
	: Item(treeItemName, treeItemDescription), m_pParentTree(parentTree),
	  m_pParentTreeItem(parentTreeItem), m_pTreeItemProvider(NULL), m_uniqueId(uniqueId), m_myIndex(-1), m_isSubtree(isSubtree)
{
}

TreeItemControl::~TreeItemControl()
{
}

TreeControl* TreeItemControl::GetParentTree()
{
	return m_pParentTree;
}

void TreeItemControl::SetParentMenuBar(_In_ TreeControl* tree)
{
	m_pParentTree = tree;
}

TreeItemControl* TreeItemControl::GetParentTreeItem()
{
	return m_pParentTreeItem;
}

TreeItemProvider* TreeItemControl::GetTreeItemProvider()
{
	if (m_pTreeItemProvider == NULL)
	{
		m_pTreeItemProvider = new TreeItemProvider(this);
		UiaRaiseAutomationEvent(m_pTreeItemProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_pTreeItemProvider;
}

bool TreeItemControl::IsSubtree()
{
	return m_isSubtree;
}

Subtree* TreeItemControl::GetSubtree()
{
	Subtree* subtree = GetParentTreeItem();
	if (subtree == NULL)
		subtree = GetParentTree();

	return subtree;
}

int TreeItemControl::GetId()
{
	return m_uniqueId;
}

// GetTreeItemIndex: Retreives the MenuItem index by iterating through the collection.
int TreeItemControl::GetTreeItemIndex()
{
	Subtree* pSubtree = GetSubtree();

	for (int i = 0; i < pSubtree->GetCount(); i++)
	{
		TREEITEM_ITERATOR treeItem = pSubtree->GetTreeItemAt(i);
		TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(*treeItem);
		if (pTreeItem == this)
		{
			m_myIndex = i;
			break;
		}
	}
	
	return m_myIndex;
	
}

// SetTreeItemIndex: Sets the TreeItem index to the given index.
void TreeItemControl::SetTreeItemIndex(_In_ int index)
{
	m_myIndex = index;
}

bool TreeItemControl::HasFocus()
{
	return (GetParentTree()->GetSelectedTreeItem() == this) && (GetParentTree()->HasFocus());
}

void TreeItemControl::Expand()
{
	GetTreeItemProvider()->Expand();
}

void TreeItemControl::Collapse()
{
	GetTreeItemProvider()->Collapse();
}

void TreeItemControl::SetControlFocus(_In_ bool focused)
{
}



