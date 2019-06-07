#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "TabPaneProvider.h"

class TabPaneProvider;
class TabControl;

class TabPaneControl : public Item
{
public:
	TabPaneControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~TabPaneControl();

	static TabPaneControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	TabPaneProvider* GetProvider();

	TabControl* GetSelectedTab();
	void SetSelectedTab(_In_opt_ TabControl* selectedTab);
private:

	static LRESULT CALLBACK StaticTabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	TabPaneProvider* provider;
	TabControl* selectedTab;
};