#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Item.h"

class RadioButtonProvider;

class RadioButtonControl : public Item
{
public:
	RadioButtonControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem);
	virtual ~RadioButtonControl();

	static RadioButtonControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem);

	RadioButtonProvider* GetButtonProvider(_In_ HWND hwnd);

	void SetState(_In_ bool controlState);
	bool GetState();
	virtual void Focus(bool isFocused) override;
private:
	static LRESULT CALLBACK StaticRadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK RadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	RadioButtonProvider* m_buttonProvider;

};
