#include <string>
#include <iostream>

#include "CheckBoxControl.h"
#include "CheckBoxProvider.h"

bool CheckBoxControl::Initialized = false;

/**** Button methods ***/

// CheckBoxControl: Constructor. Sets the default values for the button.
CheckBoxControl::CheckBoxControl() : m_buttonProvider(NULL), m_buttonName(L"Check Box"), m_toggleState(ToggleState_Off)
{
	// Nothing to do here.
}

// ~CheckBoxControl: Release the reference to the CheckBoxProvider if there is one.
CheckBoxControl::~CheckBoxControl()
{
	if (m_buttonProvider != NULL)
	{
		m_buttonProvider->Release();
		m_buttonProvider = NULL;
	}
}

// GetButtonProvider: Gets the UI Automation provider for this control or creates one.
CheckBoxProvider* CheckBoxControl::GetButtonProvider(_In_ HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new CheckBoxProvider(hwnd, this);
		UiaRaiseAutomationEvent(m_buttonProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_buttonProvider;
}

// GetHWND: Get the HWND associated with this control.
HWND CheckBoxControl::GetHWND()
{
	return m_buttonControlHWND;
}

// InvokeButton: Handle button click or invoke.
void CheckBoxControl::InvokeButton(_In_ HWND hwnd)
{
	if (UiaClientsAreListening())
	{

		if (m_buttonProvider == NULL)
		{
			m_buttonProvider = GetButtonProvider(hwnd);
		}
			
		//m_buttonProvider->Toggle();

		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the CheckBoxControl with Windows API so that it can used and later be registered with UI Automation
bool CheckBoxControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticToggleButtonControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_CHECKBOX";

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

		return false;
	}

	return true;
}

HWND CheckBoxControl::Create(_In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription)
{
	UNREFERENCED_PARAMETER(buttonDescription);
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		CheckBoxControl * control = new CheckBoxControl();

		control->m_buttonControlHWND = CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_CHECKBOX",
			buttonName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			GetMainWindowHandle(), // Parent window
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_buttonControlHWND == 0)
		{
			DWORD errorMessageID = ::GetLastError();

			LPSTR messageBuffer = nullptr;
			size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
				NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

			std::string message(messageBuffer, size);
			std::cout << "Native Code - CreateWindowExW Error " << errorMessageID << ": " << message;
			fflush(stdout);

			//Free the buffer.
			LocalFree(messageBuffer);
		}
		else
		{
			control->SetName(buttonName);
			return control->m_buttonControlHWND;
		}
	}

	return 0; // Indicates failure to create window.

}

WCHAR* CheckBoxControl::GetName()
{
	return m_buttonName;
}

void CheckBoxControl::SetName(_In_ WCHAR* name)
{
	m_buttonName = name;
}

void CheckBoxControl::SetControlFocus()
{
	m_focused = true;
	m_buttonProvider->NotifyFocusGained();
}

void CheckBoxControl::KillControlFocus()
{
	m_focused = false;
}

bool CheckBoxControl::HasFocus()
{
	return m_focused;
}

void CheckBoxControl::SetState(_In_ ToggleState controlState)
{
	m_toggleState = controlState;
}

ToggleState CheckBoxControl::GetState()
{
	return m_toggleState;
}


LRESULT CheckBoxControl::StaticToggleButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	CheckBoxControl * pThis = reinterpret_cast<CheckBoxControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<CheckBoxControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
	}

	if (message == WM_NCDESTROY)
	{
		pThis = NULL;
		SetWindowLongPtr(hwnd, GWLP_USERDATA, NULL);
	}

	if (pThis != NULL)
	{
		return pThis->ToggleButtonControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

// Control window procedure.
LRESULT CALLBACK CheckBoxControl::ToggleButtonControlWndProc(_In_ HWND hwnd, _In_  UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{

	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetButtonProvider(this->m_buttonControlHWND));
		}

		break;
	}
	case WM_DESTROY:
	{
		lResult = UiaReturnRawElementProvider(hwnd, 0, 0, NULL);
	}
	case WM_SETFOCUS:
	{
		this->SetControlFocus();
		break;
	}
	case WM_KILLFOCUS:
	{
		this->KillControlFocus();
		break;
	}
	case QUORUM_INVOKEBUTTON:
	{
		// TODO:  This message should notify the user that the button was checked. It does not do that.
		//		  Maybe the provider doesn't implement the correct interface
		bool state = static_cast<bool>(wParam);

		this->InvokeButton(hwnd);

		if (state)
		{
			this->SetState(ToggleState_On);
		}
		else
		{
			this->SetState(ToggleState_Off);
		}

		break;
	}
	case QUORUM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
	}
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}
