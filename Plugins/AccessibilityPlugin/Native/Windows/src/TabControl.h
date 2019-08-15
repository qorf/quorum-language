#pragma once

#include "ControlT.h"

class TabProvider;
class TabPaneControl;

class TabControl : public ControlT<TabControl, TabProvider>
{
public:
	TabControl(JNIEnv* env, std::wstring&& name, _In_ TabPaneControl* parentTabPane, jobject jItem);

	TabPaneControl* GetParentTabPane();

private:
	// The tab pane that owns this particular tab
	TabPaneControl* m_parentTabPane;
};
