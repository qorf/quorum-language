#include <windows.h>
#include <UIAutomation.h>
#include "CustomMessages.h"

class PushButtonProvider;

class PushButtonControl
{
public:
	PushButtonControl();
	virtual ~PushButtonControl();

	static HWND Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription);

	PushButtonProvider* GetButtonProvider(_In_ HWND hwnd);
	HWND GetHWND();


	void InvokeButton(_In_ HWND hwnd);
	WCHAR* GetName();
	void SetName(_In_ WCHAR* name);
	void SetFocus();

private:
	static LRESULT CALLBACK StaticButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);


	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;


	bool m_isFocused;
	WCHAR* m_buttonName;
	HWND m_buttonControlHWND;
	PushButtonProvider* m_buttonProvider;

};