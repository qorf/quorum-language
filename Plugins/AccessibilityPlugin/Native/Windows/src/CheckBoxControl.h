#pragma once

#include "CustomMessages.h"
#include "Resources.h"
#include "ControlT.h"

class CheckBoxProvider;

class CheckBoxControl : public ControlT<CheckBoxControl, CheckBoxProvider>
{
public:
	CheckBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem);

	static CheckBoxControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem);
	virtual void Focus(bool isFocused) override;

	// Toggle functions.
	void SetState(_In_ ToggleState controlState);
	ToggleState GetState();

private:
	static LRESULT CALLBACK StaticToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;
};
