#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class ListProvider;
class ListItemControl;

class ListControl : public ControlT<ListControl, ListProvider>
{
public:
	ListControl(JNIEnv* env, std::wstring&& name, jobject jItem);

	static ListControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	ListItemControl* GetSelected();
	void SetSelected(_In_opt_ ListItemControl* selectedItem);

private:
	static LRESULT CALLBACK StaticListControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ListControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	ListItemControl* m_selectedItem = nullptr;
};
