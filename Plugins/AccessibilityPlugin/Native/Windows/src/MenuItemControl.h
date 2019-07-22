#pragma once

#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Menu.h"
#include "Item.h"

class MenuItemProvider;
class MenuBarControl;

class MenuItemControl : public Menu, public Item
{
public:
	MenuItemControl(JNIEnv* env, _In_ std::wstring menuItemName, _In_ std::wstring menuItemShortcut, _In_ bool isMenu, _In_opt_ MenuItemControl* parentMenuItem, _In_ MenuBarControl* parentMenuBar, jobject jItem);
	virtual ~MenuItemControl();

	MenuBarControl* GetParentMenuBar();
	void SetParentMenuBar(_In_ MenuBarControl* menuBar);
	MenuItemControl* GetParentMenuItem();
	MenuItemProvider* GetMenuItemProvider();

	bool IsMenu();
	Menu* GetMenu();

	void SetShortcut(std::wstring shortcut);
	const WCHAR* GetShortcut();

	int GetMenuItemIndex();
	void SetMenuItemIndex(_In_ int index);

	bool HasFocus();

	void Expand();
	void Collapse();
	virtual void Focus(bool focused) override;
private:

	// Where this MenuItem is located in the collection.
	int m_myIndex;

	std::wstring m_shortcut;
	bool m_isMenu;

	// The provider for this MenuItem
	MenuItemProvider* m_pMenuItemProvider;
	
	// The MenuBar that this MenuItem belongs to. This won't always be
	// this MenuItem's direct parent but it is always an ancestor.
	MenuBarControl* m_pParentMenuBar;

	// The MenuItem that this MenuItem is nested in. This can be null.
	// If this is null then the MenuBar is the direct parent of this control.
	MenuItemControl* m_pParentMenuItem;

	
};
