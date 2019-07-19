#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Item.h"

class CheckBoxProvider;

class CheckBoxControl : public Item
{
public:
	CheckBoxControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem);
	virtual ~CheckBoxControl();

	static CheckBoxControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem);

	CheckBoxProvider* GetButtonProvider(_In_ HWND hwnd);
	
	void SetState(_In_ ToggleState controlState);
	ToggleState GetState();
	virtual void Focus(bool isFocused) override;

private:
	static LRESULT CALLBACK StaticToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK ToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	CheckBoxProvider* m_buttonProvider;
};
