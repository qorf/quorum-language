#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "ToolBarProvider.h"

class ToolBarProvider;

class ToolBarControl : public Item
{
public:
	ToolBarControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~ToolBarControl();

	static ToolBarControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* name, jobject jItem);

	ToolBarProvider* GetProvider();
private:

	static LRESULT CALLBACK StaticToolBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ToolBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	ToolBarProvider* provider;
};