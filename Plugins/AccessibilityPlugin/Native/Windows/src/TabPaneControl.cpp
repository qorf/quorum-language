#include "TabPaneControl.h"
#include "TabPaneProvider.h"
#include "TabControl.h"
#include "TabProvider.h"
#include "ControlTImpl.h"

TabPaneControl::TabPaneControl(JNIEnv* env, std::wstring&& name, jobject jItem)
	: ControlT(env, std::move(name), L"", jItem)
{
}

TabControl* TabPaneControl::GetSelectedTab()
{
	return m_selectedTab;
}

void TabPaneControl::SetSelectedTab(_In_opt_ TabControl* tab)
{
	m_selectedTab = tab;
	if (tab != nullptr && IsReadyForEvents())
	{
		tab->GetProvider()->NotifyElementSelected();
	}
}
