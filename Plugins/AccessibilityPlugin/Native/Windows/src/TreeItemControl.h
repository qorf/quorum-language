#pragma once

#include "ControlT.h"

class TreeItemProvider;
class TreeControl;

class TreeItemControl : public ControlT<TreeItemControl, TreeItemProvider>
{
public:
	TreeItemControl(JNIEnv* env, std::wstring&& treeItemName, std::wstring&& treeItemDescription, bool isSubtree, bool isExpanded, _In_ TreeControl* parentTree, jobject jItem);

	TreeControl* GetParentTree();

	bool IsExpanded();
	void SetExpanded(bool expanded);
	bool IsSubtree();

private:
	bool m_isSubtree;
	bool m_isExpanded;

	// The Tree that this TreeItem belongs to. This won't always be
	// this TreeItem's direct parent but it is always an ancestor.
	TreeControl* m_parentTree;
};
