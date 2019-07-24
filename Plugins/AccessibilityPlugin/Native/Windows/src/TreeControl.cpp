#include "TreeControl.h"
#include "TreeProvider.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "ControlTImpl.h"

TreeControl::TreeControl(JNIEnv* env, std::wstring&& treeName, jobject jItem) 
	: ControlT(env, std::move(treeName), L"", jItem)
{
}

TreeItemControl* TreeControl::GetSelectedTreeItem()
{
	return m_pSelectedTreeItem;
}

void TreeControl::SetSelectedTreeItem(_In_opt_ TreeItemControl* selectedTreeItem)
{
	m_pSelectedTreeItem = selectedTreeItem;
	if (m_pSelectedTreeItem != nullptr && UiaClientsAreListening())
	{
		m_pSelectedTreeItem->GetProvider()->NotifyElementSelected();
	}
}
