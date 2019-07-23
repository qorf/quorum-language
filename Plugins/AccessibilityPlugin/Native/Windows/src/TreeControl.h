#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class TreeProvider;
class TreeItemControl;

class TreeControl : public ControlT<TreeControl, TreeProvider>
{
public:
	TreeControl(JNIEnv* env, std::wstring&& treeName, jobject jItem);

	static TreeControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	TreeItemControl* GetSelectedTreeItem();
	void SetSelectedTreeItem(_In_opt_ TreeItemControl* selectedTreeItem);

	bool HasFocus();
private:

	static LRESULT CALLBACK StaticTreeControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TreeControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus(_In_ bool isFocused);

	bool m_focused = false;

	TreeItemControl* m_pSelectedTreeItem = nullptr;
};
