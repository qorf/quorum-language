#include <windows.h>
#include <windowsx.h>
#include <stdio.h>
#include <stdlib.h>
#include <new>

#include <ole2.h>
#include <UIAutomation.h>
#include "CustomMessages.h"



class ButtonProvider;

class ButtonControl
{
public:
	ButtonControl();
	virtual ~ButtonControl();

	ButtonProvider* GetButtonProvider(HWND hwnd);
	HWND GetHWND();


	void InvokeButton(HWND hwnd);
	static void RegisterButtonControl(HINSTANCE hInstance);
	WCHAR* GetName();
	void SetName(WCHAR* name);
	void SetFocus();

private:
	bool m_isFocused;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	ButtonProvider* m_buttonProvider;

};