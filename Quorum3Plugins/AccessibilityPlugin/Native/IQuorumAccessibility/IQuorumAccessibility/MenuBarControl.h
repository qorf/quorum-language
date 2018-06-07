#include <windows.h>
#include <UIAutomation.h>
#include "CustomMessages.h"
#include <deque>

#define MENUITEM_ITERATOR std::deque<MenuItemControl*>::iterator

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl
{
public:
	MenuBarControl(_In_ WCHAR* menuBarName);

	static HWND Create(_In_ HINSTANCE instance, _In_ WCHAR* menuBarName);

	MenuBarProvider* GetMenuBarProvider();
	HWND GetHWND();

	WCHAR* GetName();
	void SetName(_In_ WCHAR* menuBarName);

	bool HasFocus();

	MENUITEM_ITERATOR GetMenuItemAt(_In_ int index);
	
	// Number of child items for this MenuBarControl.
	int GetCount();

	// Generates a unique id for this instance of this control for RuntimeIds of children.
	int CreateUniqueId();

	bool AddMenuItem(_In_ MenuItemControl* pMenuItem);

	MenuItemControl* GetSelectedMenuItem();
	void SetSelectedMenuItem(_In_ MenuItemControl* selectedMenuItem);

private:
	virtual ~MenuBarControl();

	static LRESULT CALLBACK StaticMenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK MenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus(_In_ bool isFocused);

	bool m_focused;
	WCHAR* m_menuBarName;
	HWND m_menuBarControl;
	MenuBarProvider* m_menuBarProvider;
	std::deque<MenuItemControl*> m_menuItemCollection;
	MenuItemControl* m_pSelectedMenuItem;

};
