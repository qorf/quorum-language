#pragma once

#include "ControlT.h"

class TreeProvider;
class TreeItemControl;

class TreeControl : public ControlT<TreeControl, TreeProvider>
{
public:
	TreeControl(JNIEnv* env, std::wstring&& treeName, jobject jItem);

	TreeItemControl* GetSelectedTreeItem();
	void SetSelectedTreeItem(_In_opt_ TreeItemControl* selectedTreeItem);

private:
	TreeItemControl* m_pSelectedTreeItem = nullptr;
};
