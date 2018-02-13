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

	ToggleButtonProvider* GetButtonProvider(HWND hwnd);
	HWND GetHWND();


	void InvokeButton(HWND hwnd);
	static void RegisterButtonControl(HINSTANCE hInstance);
	WCHAR* GetName();
	void SetName(WCHAR* name);
	void SetFocus();
	void SetState(ToggleState controlState);
	ToggleState GetState();

private:
	ToggleState m_toggleState;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	ToggleButtonProvider* m_buttonProvider;

};