#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "jni.h"

#include "CustomMessages.h"
#include "Menu.h"
#include "Item.h"

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl : public Menu, public Item
{
public:
	MenuBarControl(JNIEnv* env, _In_ WCHAR* menuBarName, jobject jItem);
	virtual ~MenuBarControl();

	static MenuBarControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* menuBarName, jobject jItem);

	MenuBarProvider* GetMenuBarProvider();

	MenuItemControl* GetSelectedMenuItem();
	void SetSelectedMenuItem(_In_opt_ MenuItemControl* selectedMenuItem);
	virtual void Focus(bool isFocused) override;
private:

	static LRESULT CALLBACK StaticMenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK MenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	

	bool m_focused;

	MenuBarProvider* m_menuBarProvider;
	MenuItemControl* m_pSelectedMenuItem;

};
