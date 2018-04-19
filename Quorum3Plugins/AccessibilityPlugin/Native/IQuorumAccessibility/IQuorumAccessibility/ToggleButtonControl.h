#include <windows.h>
#include <windowsx.h>
#include <stdio.h>
#include <stdlib.h>

#include <ole2.h>
#include <UIAutomation.h>
#include "CustomMessages.h"


class ToggleButtonProvider;

class ToggleButtonControl
{
public:
	ToggleButtonControl();
	virtual ~ToggleButtonControl();

	static HWND Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription);

	ToggleButtonProvider* GetButtonProvider(_In_ HWND hwnd);
	HWND GetHWND();

	void InvokeButton(_In_ HWND hwnd);
	
	WCHAR* GetName();
	void SetName(_In_ WCHAR* name);
	void SetFocus();
	void SetState(_In_ ToggleState controlState);
	ToggleState GetState();

private:
	static LRESULT CALLBACK StaticToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	ToggleState m_toggleState;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	ToggleButtonProvider* m_buttonProvider;

};