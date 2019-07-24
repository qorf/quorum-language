#pragma once

#include "ControlT.h"

class TabPaneProvider;
class TabControl;

class TabPaneControl : public ControlT<TabPaneControl, TabPaneProvider>
{
public:
	TabPaneControl(JNIEnv* env, std::wstring&& name, jobject jItem);

	TabControl* GetSelectedTab();
	void SetSelectedTab(_In_opt_ TabControl* selectedTab);

private:
	TabControl* m_selectedTab = nullptr;
};
