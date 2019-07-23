#pragma once
#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "TabPaneControl.h"
#include "TabProvider.h"
#include "Item.h"

class TabProvider;
class TabPaneControl;

class TabControl : public Item
{
public:
	TabControl(JNIEnv* env, std::wstring&& name, _In_ TabPaneControl* parent, jobject jItem);
	virtual ~TabControl();
	static TabControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, TabPaneControl* parent, _In_ WCHAR* name, jobject jItem);

	TabPaneControl* GetParent();
	TabProvider* GetProvider();
	//ButtonControl* GetCloseButton();
private:
	static LRESULT CALLBACK StaticTabControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TabControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	// The provider for this tab
	TabProvider* provider;
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;
	// The tab pane that owns this particular tab
	TabPaneControl* parent;
};
