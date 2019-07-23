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

bool TreeItemControl::IsSubtree()
{
	return m_isSubtree;
}

bool TreeItemControl::HasFocus()
{
	return (GetParentTree()->GetSelectedTreeItem() == this) && (GetParentTree()->HasFocus());
}

void TreeItemControl::Expand()
{
	m_isExpanded = true;
	GetProvider()->Expand();
}

void TreeItemControl::Collapse()
{
	m_isExpanded = false;
	GetProvider()->Collapse();
}

void TreeItemControl::SetControlFocus(_In_ bool focused)
{
}
