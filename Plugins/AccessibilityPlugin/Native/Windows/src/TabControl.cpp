#include "TabControl.h"
#include "TabProvider.h"
#include "TabPaneProvider.h"
#include "ControlTImpl.h"

TabControl::TabControl(JNIEnv* env, std::wstring&& name, _In_ TabPaneControl* parentTabPane, jobject jItem)
	: ControlT(env, std::move(name), L"", jItem)
	, m_parentTabPane(parentTabPane)
{
}

TabPaneControl* TabControl::GetParentTabPane()
{
	return m_parentTabPane;
}
