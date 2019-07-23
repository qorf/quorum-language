#pragma once

#include "CustomMessages.h"
#include "Resources.h"
#include "ControlT.h"


class ItemProvider;

class ItemControl : public ControlT<ItemControl, ItemProvider>
{
public:
	ItemControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	static ItemControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription, jobject jItem);
	virtual void Focus(bool focus) override;

private:
	static LRESULT CALLBACK StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;
};
