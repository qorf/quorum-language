#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class TabProvider;
class TabPaneControl;

class TabControl : public ControlT<TabControl, TabProvider>
{
public:
	TabControl(JNIEnv* env, std::wstring&& name, _In_ TabPaneControl* parentTabPane, jobject jItem);
	static TabControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, TabPaneControl* parent, _In_ WCHAR* name, jobject jItem);

	TabPaneControl* GetParentTabPane();

private:
	static LRESULT CALLBACK StaticTabControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TabControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;
	// The tab pane that owns this particular tab
	TabPaneControl* m_parentTabPane;
};
