#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <wil/com.h>

#include "CustomMessages.h"
#include "Resources.h"
#include "Item.h"

class ButtonProvider;

class ButtonControl : public Item
{
public:
	ButtonControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem);

	static ButtonControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem);
	virtual void Focus(bool isFocused) override;
	const wil::com_ptr<ButtonProvider>& GetProvider();
	void InvokeButton();
private:
	static LRESULT CALLBACK StaticButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);


	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	wil::com_ptr<ButtonProvider> m_provider;
};
