#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "TreeControl.h"
#include "TreeProvider.h"
#include "ControlTImpl.h"

TreeItemControl::TreeItemControl(JNIEnv* env, std::wstring&& treeItemName, std::wstring&& treeItemDescription, _In_ bool isSubtree, _In_ bool isExpanded, _In_ TreeControl* parentTree, jobject jItem)
	: ControlT(env, std::move(treeItemName), std::move(treeItemDescription), jItem)
	, m_parentTree(parentTree)
	, m_isSubtree(isSubtree)
	, m_isExpanded(isExpanded)
{
}

TreeControl* TreeItemControl::GetParentTree()
{
	return m_parentTree;
}

bool TreeItemControl::IsExpanded()
{
	return m_isExpanded;
}

void TreeItemControl::SetExpanded(bool expanded)
{
	m_isExpanded = expanded;
	GetProvider()->NotifyElementExpandCollapse();
}

bool TreeItemControl::IsSubtree()
{
	return m_isSubtree;
}
