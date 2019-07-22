#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "ListProvider.h"

class ListProvider;
class ListItemControl;

class ListControl : public Item
{
public:
	ListControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~ListControl();

	static ListControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	ListProvider* GetProvider();

	ListItemControl* GetSelected();
	void SetSelected(_In_opt_ ListItemControl* selectedTab);
private:

	static LRESULT CALLBACK StaticListControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ListControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	ListProvider* provider;
	ListItemControl* selected;
};