#include <string>
#include <windows.h>
#include <iostream>

#include "PushButtonControl.h"
#include "PushButtonProvider.h"

bool PushButtonControl::Initialized = false;

/**** Button methods ***/

// PushButtonControl: Constructor. Sets the default values for the button.
PushButtonControl::PushButtonControl() : m_buttonProvider(NULL), m_buttonName(L"Button")
{
	// Nothing to do here.
}

// ~PushButtonControl: Release the reference to the PushButtonProvider if there is one.
PushButtonControl::~PushButtonControl()
{
	if (m_buttonProvider != NULL)
	{
		m_buttonProvider->Release();
		m_buttonProvider = NULL;
	}
}

// GetButtonProvider: Gets the UI Automation provider for this control or creates one.
PushButtonProvider* PushButtonControl::GetButtonProvider(_In_ HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new PushButtonProvider(hwnd, this);
		UiaRaiseAutomationEvent(m_buttonProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_buttonProvider;
}

// GetHWND: Get the HWND associated with this control.
HWND PushButtonControl::GetHWND()
{
	return m_buttonControlHWND;
}

// InvokeButton: Handle button click or invoke.
void PushButtonControl::InvokeButton(_In_ HWND hwnd)
{

	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the PushButtonControl with Windows API so that it can used and later be registered with UI Automation
bool PushButtonControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticButtonControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_PUSHBUTTON";

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

HWND PushButtonControl::Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription)
{

	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		PushButtonControl * control = new PushButtonControl();

		control->m_buttonControlHWND = CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_PUSHBUTTON",
			buttonName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parent, // Parent window
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

WCHAR* PushButtonControl::GetName()
{
	return m_buttonName;
}

void PushButtonControl::SetName(_In_ WCHAR* name)
{
	m_buttonName = name;
}

void PushButtonControl::SetFocus()
{
	m_buttonProvider->NotifyFocusGained();
}

LRESULT CALLBACK PushButtonControl::StaticButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	PushButtonControl * pThis = reinterpret_cast<PushButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<PushButtonControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
	}

	if (message == WM_NCDESTROY)
	{
		pThis = NULL;
		SetWindowLongPtr(hwnd, GWLP_USERDATA, NULL);
	}

	if (pThis != NULL)
	{
		return pThis->ButtonControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

// Control window procedure.
LRESULT CALLBACK PushButtonControl::ButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetButtonProvider(this->m_buttonControlHWND));
		}

		break;
	}
	case WM_SETFOCUS:
	case QUORUM_SETFOCUS:
	{
		this->SetFocus();
		break;
	}
	case WM_KILLFOCUS:
	{
		//KillFocus();
		break;
	}
	case QUORUM_INVOKEBUTTON:
	{
		this->InvokeButton(hwnd);
		break;
	}
	case QUORUM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
		break;
	}
	default:
		// Forward the event to the main GLFW window so it can handle the message.
		lResult = SendMessage(GetMainWindowHandle(), message, wParam, lParam);
	}  // switch (message)

	return lResult;
}
