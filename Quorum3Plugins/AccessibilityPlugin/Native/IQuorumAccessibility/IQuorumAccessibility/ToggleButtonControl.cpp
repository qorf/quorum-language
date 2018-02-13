#include <string>
#include <windowsx.h>
#include <iostream>

#include "ToggleButtonControl.h"
#include "ToggleButtonProvider.h"

// Forward declarations.
LRESULT CALLBACK ToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

/**** Button methods ***/

// ToggleButtonControl: Constructor. Sets the default values for the button.
ToggleButtonControl::ToggleButtonControl() : m_buttonProvider(NULL), m_buttonName(L"Toggle Button"), m_toggleState(ToggleState_Off)
{
	// Nothing to do here.
}

// ~ToggleButtonControl: Release the reference to the ToggleButtonProvider if there is one.
ToggleButtonControl::~ToggleButtonControl()
{
	if (m_buttonProvider != NULL)
	{
		m_buttonProvider->Release();
		m_buttonProvider = NULL;
	}
}

// GetButtonProvider: Gets the UI Automation provider for this control or creates one.
ToggleButtonProvider* ToggleButtonControl::GetButtonProvider(HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new (std::nothrow) ToggleButtonProvider(hwnd, this);
	}
	return m_buttonProvider;
}

// GetHWND: Get the HWND associated with this control.
HWND ToggleButtonControl::GetHWND()
{
	return m_buttonControlHWND;
}

// InvokeButton: Handle button click or invoke.
void ToggleButtonControl::InvokeButton(HWND hwnd)
{
	if (UiaClientsAreListening())
	{

		if (m_buttonProvider == NULL)
		{
			m_buttonProvider = GetButtonProvider(hwnd);
		}
	
		m_buttonProvider->Toggle();

		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the ToggleButtonControl with Windows API so that it can used and later be registered with UI Automation
void ToggleButtonControl::RegisterButtonControl(HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = ToggleButtonControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_TOGGLEBUTTON";

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

WCHAR* ToggleButtonControl::GetName()
{
	return m_buttonName;
}

void ToggleButtonControl::SetName(WCHAR* name)
{
	m_buttonName = name;
}

void ToggleButtonControl::SetFocus()
{
	m_buttonProvider->NotifyFocusGained();
}

void ToggleButtonControl::SetState(ToggleState controlState)
{
	m_toggleState = controlState;
}

ToggleState ToggleButtonControl::GetState()
{
	return m_toggleState;
}

// GetButtonControl: Helper function that is easier to type then the code that it returns.
ToggleButtonControl* GetButtonControl(HWND hwnd)
{
	return reinterpret_cast<ToggleButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
}

// Control window procedure.
LRESULT CALLBACK ToggleButtonControlWndProc(_In_ HWND hwnd, _In_  UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{

	switch (message)
	{
	case WM_CREATE:
	{
		ToggleButtonControl* pButtonControl = new (std::nothrow) ToggleButtonControl();
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
		ToggleButtonControl* pButtonControl = GetButtonControl(hwnd);
		delete pButtonControl;
		break;
	}
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			ToggleButtonControl* pButtonControl = GetButtonControl(hwnd);
			IRawElementProviderSimple* pButtonProvider = pButtonControl->GetButtonProvider(hwnd);
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, pButtonProvider);
		}

		return 0;
	}
	case CUSTOM_SETFOCUS:
	{
		ToggleButtonControl* pButtonControl = GetButtonControl(hwnd);
				
		pButtonControl->SetFocus();

		return 0;
	}
	case CUSTOM_INVOKEBUTTON:
	{
		ToggleButtonControl* pButtonControl = GetButtonControl(hwnd);

		pButtonControl->InvokeButton(hwnd);

		bool state = static_cast<bool>(wParam);

		if (state)
		{
			pButtonControl->SetState(ToggleState_On);
		}
		else
		{
			pButtonControl->SetState(ToggleState_Off);
		}
		
		return 0;
	}
	case CUSTOM_SETNAME:
	{
		ToggleButtonControl* pButtonControl = GetButtonControl(hwnd);
		pButtonControl->SetName((WCHAR*)lParam);
	}

	break;
	}  // switch (message)

	return DefWindowProc(hwnd, message, wParam, lParam);
}
