#include <windows.h>
#include <UIAutomation.h>

#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "TreeControl.h"

TreeItemControl::TreeItemControl(JNIEnv* env, _In_ std::wstring treeItemName, _In_ std::wstring treeItemDescription, _In_ bool isSubtree, _In_ bool isExpanded, _In_opt_ TreeItemControl* parentTreeItem, _In_ TreeControl* parentTree, jobject jItem)
	: Item(env, treeItemName, treeItemDescription, jItem), m_pParentTree(parentTree),
	  m_pParentTreeItem(parentTreeItem), m_pTreeItemProvider(NULL), m_myIndex(-1), m_isSubtree(isSubtree), m_isExpanded(isExpanded)
{
}

TreeItemControl::~TreeItemControl()
{
}

TreeControl* TreeItemControl::GetParentTree()
{
	return m_pParentTree;
}

void TreeItemControl::SetParentTreeItem(_In_ TreeControl* tree)
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
	}
	else {
	}
	return new TreeItemProvider(this);
}

bool TreeItemControl::IsExpanded()
{
	return m_isExpanded;
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
	m_isExpanded = true;
	GetTreeItemProvider()->Expand();
}

void TreeItemControl::Collapse()
{
	m_isExpanded = false;
	GetTreeItemProvider()->Collapse();
}

void TreeItemControl::SetControlFocus(_In_ bool focused)
{
}



