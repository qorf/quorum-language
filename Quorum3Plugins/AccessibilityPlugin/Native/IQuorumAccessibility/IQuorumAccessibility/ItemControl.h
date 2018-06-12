#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Item.h"


class ItemProvider;

class ItemControl : public Item
{
public:
	ItemControl();
	virtual ~ItemControl();
	ItemProvider* GetItemProvider(_In_ HWND hwnd);

	static ItemControl* Create(_In_ HINSTANCE instance, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription);

	bool HasFocus();

private:
	static LRESULT CALLBACK StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus(_In_ bool focused);

	bool m_focused;

	ItemProvider* m_pItemProvider;
	
};