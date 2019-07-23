#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class MenuBarProvider;
class MenuItemControl;

class MenuBarControl : public ControlT<MenuBarControl, MenuBarProvider>
{
public:
	MenuBarControl(JNIEnv* env, std::wstring&& menuBarName, jobject jItem);

	static MenuBarControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* menuBarName, jobject jItem);

	MenuItemControl* GetSelectedMenuItem();
	void SetSelectedMenuItem(_In_opt_ MenuItemControl* selectedMenuItem);
	void Focus(bool isFocused) override;

private:
	static LRESULT CALLBACK StaticMenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK MenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	MenuItemControl* m_pSelectedMenuItem = nullptr;
};
