#include <string>
#include <windows.h>
#include <iostream>

#include "ButtonControl.h"
#include "ButtonProvider.h"
#include "ControlTImpl.h"

bool ButtonControl::Initialized = false;

/**** Button methods ***/

// ButtonControl: Constructor. Sets the default values for the button.
ButtonControl::ButtonControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
}

void ButtonControl::Invoke()
{
	// TODO: Call up to Quorum to invoke the button.
}

void ButtonControl::NotifyInvoked()
{
	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_Invoke_InvokedEventId);
	}
}

// RegisterButtonControl: Registers the ButtonControl with Windows API so that it can used
bool ButtonControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticButtonControlWndProc;
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
		//std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}

ButtonControl* ButtonControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		ButtonControl* control = new ButtonControl(env, buttonName, buttonDescription, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_BUTTON",
			buttonName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parentWindow,
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_ControlHWND == NULL)
		{
			DWORD errorMessageID = ::GetLastError();

			LPSTR messageBuffer = nullptr;
			size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
				NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

			std::string message(messageBuffer, size);
			//std::cout << "Native Code - CreateWindowExW Error " << errorMessageID << ": " << message;
			fflush(stdout);

			//Free the buffer.
			LocalFree(messageBuffer);
		}
		else
		{
			return control;
		}
	}

	return NULL; // Indicates failure to create window.

}

void ButtonControl::Focus(bool focused)
{
	this->focused = focused;
	GetProvider()->NotifyFocusGained();
}

LRESULT CALLBACK ButtonControl::StaticButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	ButtonControl * pThis = reinterpret_cast<ButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<ButtonControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
		pThis->m_ControlHWND = hwnd;
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
LRESULT CALLBACK ButtonControl::ButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, GetProvider().get());
		}

		break;
	}
	case WM_SETFOCUS:
	{
		this->Focus(true);
		break;
	}
	case WM_KILLFOCUS:
	{
		this->Focus(false);
		break;
	}
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}
