#pragma once

#include "ControlT.h"

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl : public ControlT<MenuBarControl, MenuBarProvider>
{
public:
	MenuBarControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem);

	MenuItemControl* GetSelectedMenuItem() const noexcept;
	Item* GetUiaFocusDescendant() const noexcept override;
	void SetSelectedMenuItem(_In_opt_ MenuItemControl* selectedMenuItem);

	void NotifyFocusGained() override;
	void NotifyFocusLost() override;

private:
	MenuItemControl* m_pSelectedMenuItem = nullptr;
};
