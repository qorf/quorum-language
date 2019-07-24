#pragma once

#include "ControlT.h"

class MenuItemProvider;
class MenuBarControl;

class MenuItemControl : public ControlT<MenuItemControl, MenuItemProvider>
{
public:
	MenuItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& menuItemShortcut, _In_ bool isMenu, _In_ MenuBarControl* parentMenuBar, jobject jItem);

	MenuBarControl* GetParentMenuBar();

	bool IsMenu();

	void SetShortcut(std::wstring&& shortcut);
	const std::wstring& GetShortcut();

	void Expand();
	void Collapse();

private:
	std::wstring m_shortcut;
	bool m_isMenu;

	// The MenuBar that this MenuItem belongs to. This won't always be
	// this MenuItem's direct parent but it is always an ancestor.
	MenuBarControl* m_parentMenuBar;
};
