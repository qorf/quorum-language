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
	ItemProvider* GetItemProvider(_In_ HWND hwnd);

	static HWND Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription);

	WCHAR* GetName();
	void SetName(_In_ WCHAR* name);

private:
	static LRESULT CALLBACK StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;


	WCHAR* m_pItemName;
	ItemProvider* m_pItemProvider;
	HWND m_ItemHWND;
	
};