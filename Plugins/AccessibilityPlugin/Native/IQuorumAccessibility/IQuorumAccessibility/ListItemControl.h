#pragma once

#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "ListControl.h"
#include "ListItemProvider.h"
#include "Item.h"

class ListItemProvider;
class ListControl;

class ListItemControl : public Item
{
public:
	ListItemControl(JNIEnv* env, _In_ std::wstring name, _In_ ListControl* parent, jobject jItem);
	virtual ~ListItemControl();
	static ListItemControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, ListControl* parent, _In_ WCHAR* name, jobject jItem);

	ListControl* GetParent();
	ListItemProvider* GetProvider();

	std::wstring GetText();

private:
	static LRESULT CALLBACK StaticListItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ListItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	// The provider for this tab
	ListItemProvider* provider;
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;
	// The tab pane that owns this particular tab
	ListControl* parent;
};