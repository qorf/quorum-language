#include <deque>

#ifndef Subtree_HEADER
#define Subtree_HEADER

class TreeItemControl;
#define TREEITEM_ITERATOR std::deque<TreeItemControl*>::iterator


class Subtree
{
public:

	bool AddTreeItem(_In_ TreeItemControl* pTreeItem);
	bool RemoveTreeItem(TreeItemControl * pTreeItem);
	TREEITEM_ITERATOR GetTreeItemAt(_In_ int index);

	// Number of child items for this Subtree.
	int GetCount();

	bool HasChildren();

	// Generates a unique id for this instance of this control for RuntimeIds of children.
	int CreateUniqueId();

private:
	std::deque<TreeItemControl*> m_treeItemCollection;

};

#endif