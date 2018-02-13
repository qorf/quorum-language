#include <windows.h>
#include <windowsx.h>
#include <stdio.h>
#include <stdlib.h>
#include <new>

#include <ole2.h>
#include <UIAutomation.h>
#include "CustomMessages.h"


class ItemProvider;

class Item
{
public:
	Item();
	virtual ~Item();
	ItemProvider* GetUIAutomationProvider(HWND hwnd);
	static void RegisterControl(HINSTANCE hInstance);
	WCHAR* GetName();
	void SetName(WCHAR* name);

private:
	WCHAR* m_itemName;
	ItemProvider* m_itemProvider;
	
};