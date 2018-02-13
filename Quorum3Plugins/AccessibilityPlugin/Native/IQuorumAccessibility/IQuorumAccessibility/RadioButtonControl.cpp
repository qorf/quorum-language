#include <string>
#include <windowsx.h>
#include <iostream>

#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"

// Forward declarations.
LRESULT CALLBACK RadioButtonControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam);

/**** Button methods ***/

// RadioButtonControl: Constructor. Sets the default values for the button.
RadioButtonControl::RadioButtonControl() : m_buttonProvider(NULL), m_buttonName(L"Radio Button"), m_isOn(false)
{
	// Nothing to do here.
}

// ~RadioButtonControl: Release the reference to the RadioButtonProvider if there is one.
RadioButtonControl::~RadioButtonControl()
{
	if (m_buttonProvider != NULL)
	{
		m_buttonProvider->Release();
		m_buttonProvider = NULL;
	}
}

// GetButtonProvider: Gets the UI Automation provider for this control or creates one.
RadioButtonProvider* RadioButtonControl::GetButtonProvider(HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new (std::nothrow) RadioButtonProvider(hwnd, this);
	}
	return m_buttonProvider;
}

// GetHWND: Get the HWND associated with this control.
HWND RadioButtonControl::GetHWND()
{
	return m_buttonControlHWND;
}

// InvokeButton: Handle button click or invoke.
void RadioButtonControl::InvokeButton(HWND hwnd)
{

	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the RadioButtonControl with Windows API so that it can used and later be registered with UI Automation
void RadioButtonControl::RegisterButtonControl(HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = RadioButtonControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_RADIOBUTTON";

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

WCHAR* RadioButtonControl::GetName()
{
	return m_buttonName;
}

void RadioButtonControl::SetName(WCHAR* name)
{
	m_buttonName = name;
}

void RadioButtonControl::SetFocus()
{
	m_buttonProvider->NotifyFocusGained();
}

void RadioButtonControl::SetState(bool controlState)
{
	m_isOn = controlState;
	if (m_isOn)
		m_buttonProvider->Select();

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(m_buttonProvider, UIA_AutomationPropertyChangedEventId);
	}
}

bool RadioButtonControl::GetState()
{
	return m_isOn;
}

// GetButtonControl: Helper function that is easier to type then the code that it returns.
RadioButtonControl* GetButtonControl(HWND hwnd)
{
	return reinterpret_cast<RadioButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
}

// Control window procedure.
LRESULT CALLBACK RadioButtonControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam)
{

	switch (message)
	{
	case WM_CREATE:
	{
		RadioButtonControl* pButtonControl = new (std::nothrow) RadioButtonControl();
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
		RadioButtonControl* pButtonControl = GetButtonControl(hwnd);
		delete pButtonControl;
		break;
	}
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			RadioButtonControl* pButtonControl = GetButtonControl(hwnd);
			IRawElementProviderSimple* pButtonProvider = pButtonControl->GetButtonProvider(hwnd);
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, pButtonProvider);
		}

		return 0;
	}
	case CUSTOM_SETFOCUS:
	{
		RadioButtonControl* pButtonControl = GetButtonControl(hwnd);
				
		pButtonControl->SetFocus();

		return 0;
	}
	case CUSTOM_INVOKEBUTTON:
	{
		RadioButtonControl* pButtonControl = GetButtonControl(hwnd);

		pButtonControl->InvokeButton(hwnd);

		bool state = static_cast<bool>(lParam);

		pButtonControl->SetState(state);

		return 0;
	}
	case CUSTOM_SETNAME:
	{
		RadioButtonControl* pButtonControl = GetButtonControl(hwnd);
		pButtonControl->SetName((WCHAR*)lParam);
	}

	break;
	}  // switch (message)

	return DefWindowProc(hwnd, message, wParam, lParam);
}
