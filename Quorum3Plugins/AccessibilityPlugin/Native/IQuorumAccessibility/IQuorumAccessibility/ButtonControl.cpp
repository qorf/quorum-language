#include <string>
#include <windowsx.h>
#include <iostream>

#include "ButtonControl.h"
#include "ButtonProvider.h"

// Forward declarations.
LRESULT CALLBACK ButtonControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam);

/**** Button methods ***/

// ButtonControl: Constructor. Sets the default values for the button.
ButtonControl::ButtonControl() : m_buttonProvider(NULL), m_buttonName(L"Button")
{
	// Nothing to do here.
}

// ~ButtonControl: Release the reference to the ButtonProvider if there is one.
ButtonControl::~ButtonControl()
{
	if (m_buttonProvider != NULL)
	{
		m_buttonProvider->Release();
		m_buttonProvider = NULL;
	}
}

// GetButtonProvider: Gets the UI Automation provider for this control or creates one.
ButtonProvider* ButtonControl::GetButtonProvider(HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new (std::nothrow) ButtonProvider(hwnd, this);
	}
	return m_buttonProvider;
}

// GetHWND: Get the HWND associated with this control.
HWND ButtonControl::GetHWND()
{
	return m_buttonControlHWND;
}

// InvokeButton: Handle button click or invoke.
void ButtonControl::InvokeButton(HWND hwnd)
{

	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the ButtonControl with Windows API so that it can used and later be registered with UI Automation
void ButtonControl::RegisterButtonControl(HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = ButtonControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_BUTTON";

	if (RegisterClassExW(&wc) == 0)
	{
		// An error occured. Output this error so it can be seen from Quorum.
		DWORD errorMessageID = ::GetLastError();

		LPSTR messageBuffer = nullptr;
		size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

		std::string message(messageBuffer, size);
		std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);
	}
}

WCHAR* ButtonControl::GetName()
{
	return m_buttonName;
}

void ButtonControl::SetName(WCHAR* name)
{
	m_buttonName = name;
}

void ButtonControl::SetFocus()
{
	m_buttonProvider->NotifyFocusGained();
}

// GetButtonControl: Helper function that is easier to type then the code that it returns.
ButtonControl* GetButtonControl(HWND hwnd)
{
	return reinterpret_cast<ButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
}

// Control window procedure.
LRESULT CALLBACK ButtonControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam)
{

	switch (message)
	{
	case WM_CREATE:
	{
		ButtonControl* pButtonControl = new (std::nothrow) ButtonControl();
		if (pButtonControl == NULL)
		{
			PostQuitMessage(-1);
		}
		// Save the class instance as extra window data so that members can be accessed
		//  from within this function.
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pButtonControl));
		break;
	}

	case WM_DESTROY:
	{
		ButtonControl* pButtonControl = GetButtonControl(hwnd);
		delete pButtonControl;
		break;
	}
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			ButtonControl* pButtonControl = GetButtonControl(hwnd);
			IRawElementProviderSimple* pButtonProvider = pButtonControl->GetButtonProvider(hwnd);
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, pButtonProvider);
		}

		return 0;
	}
	case CUSTOM_SETFOCUS:
	{
		ButtonControl* pButtonControl = GetButtonControl(hwnd);
				
		pButtonControl->SetFocus();

		return 0;
	}
	case CUSTOM_INVOKEBUTTON:
	{
		ButtonControl* pButtonControl = GetButtonControl(hwnd);

		pButtonControl->InvokeButton(hwnd);

		return 0;
	}
	case CUSTOM_SETNAME:
	{
		ButtonControl* pButtonControl = GetButtonControl(hwnd);
		pButtonControl->SetName((WCHAR*)lParam);
	}

	break;
	}  // switch (message)

	return DefWindowProc(hwnd, message, wParam, lParam);
}
