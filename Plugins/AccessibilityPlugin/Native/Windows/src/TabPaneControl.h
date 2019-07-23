#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class TabPaneProvider;
class TabControl;

class TabPaneControl : public ControlT<TabPaneControl, TabPaneProvider>
{
public:
	TabPaneControl(JNIEnv* env, std::wstring&& name, jobject jItem);

	static TabPaneControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	TabControl* GetSelectedTab();
	void SetSelectedTab(_In_opt_ TabControl* selectedTab);

private:
	static LRESULT CALLBACK StaticTabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	TabControl* m_selectedTab = nullptr;
};
