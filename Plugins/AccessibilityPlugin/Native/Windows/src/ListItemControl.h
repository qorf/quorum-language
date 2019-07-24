#pragma once

#include "ControlT.h"

class ListItemProvider;
class ListControl;

class ListItemControl : public ControlT<ListItemControl, ListItemProvider>
{
public:
	ListItemControl(JNIEnv* env, std::wstring&& name, _In_ ListControl* parentList, jobject jItem);

	ListControl* GetParentList();

	std::wstring GetText();

private:
	ListControl* m_parentList;
};
