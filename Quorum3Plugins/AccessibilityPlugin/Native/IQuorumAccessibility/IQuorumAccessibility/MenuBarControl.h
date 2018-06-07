#include <windows.h>
#include <UIAutomation.h>
#include "CustomMessages.h"

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

};
