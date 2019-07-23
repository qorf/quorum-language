#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "DialogProvider.h"

class DialogProvider;

class DialogControl : public Item
{
public:
	DialogControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~DialogControl();

	static DialogControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* name, jobject jItem);

	DialogProvider* GetProvider();
private:

	static LRESULT CALLBACK StaticDialogControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK DialogControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	DialogProvider* provider;
};
