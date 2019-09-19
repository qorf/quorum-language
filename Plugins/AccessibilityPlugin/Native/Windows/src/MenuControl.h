#pragma once

#include "ControlT.h"

class MenuProvider;
class PopupMenuItemControl;

class MenuControl : public ControlT<MenuControl, MenuProvider>
{
public:
	MenuControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem);

	PopupMenuItemControl* GetSelectedMenuItem() const noexcept;
	Item* GetUiaFocusDescendant() const noexcept override;
	void SetSelectedMenuItem(_In_opt_ PopupMenuItemControl* selectedMenuItem);

	void NotifyFocusGained() override;
	void NotifyFocusLost() override;

private:
	PopupMenuItemControl* m_pSelectedMenuItem = nullptr;
};
