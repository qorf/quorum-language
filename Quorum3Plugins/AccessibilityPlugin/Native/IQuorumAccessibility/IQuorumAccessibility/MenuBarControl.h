#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "MenuControl.h"
#include "Item.h"

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl : public MenuControl, public Item
{
public:
	MenuBarControl(_In_ WCHAR* menuBarName);
	virtual ~MenuBarControl();

	static MenuBarControl* Create(_In_ HINSTANCE instance, _In_ WCHAR* menuBarName);

	MenuBarProvider* GetMenuBarProvider();

	MenuItemControl* GetSelectedMenuItem();
	void SetSelectedMenuItem(_In_ MenuItemControl* selectedMenuItem);

	bool HasFocus();
private:

	static LRESULT CALLBACK StaticMenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK MenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus(_In_ bool isFocused);

	bool m_focused;

	MenuBarProvider* m_menuBarProvider;
	MenuItemControl* m_pSelectedMenuItem;

};
