#include <windows.h>
#include <windowsx.h>
#include <stdio.h>
#include <stdlib.h>
#include <new>

#include <ole2.h>
#include <UIAutomation.h>
#include "CustomMessages.h"



class RadioButtonProvider;

class RadioButtonControl
{
public:
	RadioButtonControl();
	virtual ~RadioButtonControl();

	static HWND Create(_In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription);

	RadioButtonProvider* GetButtonProvider(_In_ HWND hwnd);
	HWND GetHWND();

	WCHAR* GetName();
	void SetName(_In_ WCHAR* name);

	void InvokeButton(_In_ HWND hwnd);
	void SetState(_In_ bool controlState);
	bool GetState();

	bool HasFocus();

private:
	static LRESULT CALLBACK StaticRadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK RadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus();
	void KillControlFocus();


	bool m_isOn;
	bool m_focused;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	RadioButtonProvider* m_buttonProvider;

};