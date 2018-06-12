#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"


class ItemProvider;

class ItemControl
{
public:
	ItemControl();
	virtual ~ItemControl();
	ItemProvider* GetItemProvider(_In_ HWND hwnd);

	static HWND Create(_In_ HINSTANCE instance, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription);

	WCHAR* GetName();
	void SetName(_In_ WCHAR* name);

	bool HasFocus();

private:
	static LRESULT CALLBACK StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus();
	void KillControlFocus();

	bool m_focused;
	WCHAR* m_pItemName;
	ItemProvider* m_pItemProvider;
	HWND m_ItemHWND;
	
};