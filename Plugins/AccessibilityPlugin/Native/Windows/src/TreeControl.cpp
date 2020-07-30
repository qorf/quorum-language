#include "TreeControl.h"
#include "TreeProvider.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "ControlTImpl.h"

TreeControl::TreeControl(JNIEnv* env, std::wstring&& treeName, jobject jItem) 
	: ControlT(env, std::move(treeName), L"", jItem)
{
}

TreeItemControl* TreeControl::GetSelectedTreeItem() const noexcept
{
	return m_pSelectedTreeItem;
}

Item* TreeControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelectedTreeItem();
}

void TreeControl::SetSelectedTreeItem(_In_opt_ TreeItemControl* selectedTreeItem)
{
	m_pSelectedTreeItem = selectedTreeItem;
	if (m_pSelectedTreeItem != nullptr && IsReadyForEvents())
	{
		m_pSelectedTreeItem->GetProvider()->NotifyElementSelected();
	}
}
