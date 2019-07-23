#pragma once

#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Subtree.h"
#include "Item.h"

class TreeItemProvider;
class TreeControl;

class TreeItemControl : public Subtree, public Item
{
public:
	TreeItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& treeItemDescription, _In_ bool isSubtree, _In_ bool isExpanded, _In_opt_ TreeItemControl* parentMenuItem, _In_ TreeControl* parentMenuBar, jobject jItem);
	virtual ~TreeItemControl();

	TreeControl* GetParentTree();
	void SetParentTreeItem(_In_ TreeControl* menuBar);
	TreeItemControl* GetParentTreeItem();
	TreeItemProvider* GetTreeItemProvider();

	bool IsExpanded();
	bool IsSubtree();
	Subtree* GetSubtree();

	int GetTreeItemIndex();
	void SetTreeItemIndex(_In_ int index);

	bool HasFocus();

	void Expand();
	void Collapse();

private:

	// Where this MenuItem is located in the collection.
	int m_myIndex;

	bool m_isSubtree;
	bool m_isExpanded;

	// The provider for this MenuItem
	TreeItemProvider* m_pTreeItemProvider;
	
	// The Tree that this TreeItem belongs to. This won't always be
	// this TreeItem's direct parent but it is always an ancestor.
	TreeControl* m_pParentTree;

	// The TreeItem that this TreeItem is nested in. This can be null.
	// If this is null then the Tree is the direct parent of this control.
	TreeItemControl* m_pParentTreeItem;

	void SetControlFocus(_In_ bool focused);
};
