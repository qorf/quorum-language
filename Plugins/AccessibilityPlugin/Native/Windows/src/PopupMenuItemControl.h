#pragma once

#include "ControlT.h"

class PopupMenuItemProvider;
class MenuControl;

class PopupMenuItemControl : public ControlT<PopupMenuItemControl, PopupMenuItemProvider>
{
public:
	PopupMenuItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& menuItemShortcut, _In_ bool isMenu, _In_ MenuControl* parentMenuBar, jobject jItem);

	MenuControl* GetParentMenuBar();

	bool IsMenu();

	void SetShortcut(std::wstring&& shortcut);
	const std::wstring& GetShortcut();

	void NotifyExpandCollapse();

private:
	std::wstring m_shortcut;
	bool m_isMenu;

	// The MenuBar that this MenuItem belongs to. This won't always be
	// this MenuItem's direct parent but it is always an ancestor.
	MenuControl* m_parentMenuBar;
};
