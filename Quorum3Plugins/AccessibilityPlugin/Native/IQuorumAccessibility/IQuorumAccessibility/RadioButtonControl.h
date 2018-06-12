#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Item.h"

class RadioButtonProvider;

class RadioButtonControl : public Item
{
public:
	RadioButtonControl();
	virtual ~RadioButtonControl();

	static RadioButtonControl* Create(_In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription);

	RadioButtonProvider* GetButtonProvider(_In_ HWND hwnd);

	void InvokeButton(_In_ HWND hwnd);
	void SetState(_In_ bool controlState);
	bool GetState();

	bool HasFocus();

private:
	static LRESULT CALLBACK StaticRadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK RadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	void SetControlFocus(_In_ bool focused);

	bool m_focused;

	bool m_isOn;
	RadioButtonProvider* m_buttonProvider;

};