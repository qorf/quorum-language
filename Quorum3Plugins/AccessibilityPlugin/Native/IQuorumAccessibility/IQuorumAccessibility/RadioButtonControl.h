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

	RadioButtonProvider* GetButtonProvider(HWND hwnd);
	HWND GetHWND();


	void InvokeButton(HWND hwnd);
	static void RegisterButtonControl(HINSTANCE hInstance);
	WCHAR* GetName();
	void SetName(WCHAR* name);
	void SetFocus();
	void SetState(bool controlState);
	bool GetState();

private:
	bool m_isOn;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	RadioButtonProvider* m_buttonProvider;

};