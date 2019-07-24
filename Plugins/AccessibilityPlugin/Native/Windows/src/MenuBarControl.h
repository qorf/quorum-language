#pragma once

#include "ControlT.h"

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl : public ControlT<MenuBarControl, MenuBarProvider>
{
public:
	MenuBarControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem);

	MenuItemControl* GetSelectedMenuItem();
	void SetSelectedMenuItem(_In_opt_ MenuItemControl* selectedMenuItem);
	void Focus(bool isFocused) override;

private:
	MenuItemControl* m_pSelectedMenuItem = nullptr;
};
