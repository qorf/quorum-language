#include <string>
#include <iostream>

#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"

bool RadioButtonControl::Initialized = false;

/**** Button methods ***/

// RadioButtonControl: Constructor. Sets the default values for the button.
RadioButtonControl::RadioButtonControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem) : Item(env, name, description, jItem), m_buttonProvider(NULL), m_focused(false)
{
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
RadioButtonProvider* RadioButtonControl::GetButtonProvider(_In_ HWND hwnd)
{
	if (m_buttonProvider == NULL)
	{
		m_buttonProvider = new (std::nothrow) RadioButtonProvider(hwnd, this);
		UiaRaiseAutomationEvent(m_buttonProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_buttonProvider;
}

// InvokeButton: Handle button click or invoke.
void RadioButtonControl::InvokeButton(_In_ HWND hwnd)
{

	if (UiaClientsAreListening())
	{
		// Raise an event.
		UiaRaiseAutomationEvent(GetButtonProvider(hwnd), UIA_Invoke_InvokedEventId);
	}

}

// RegisterButtonControl: Registers the RadioButtonControl with Windows API so that it can used and later be registered with UI Automation
bool RadioButtonControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticRadioButtonControlWndProc;
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

		//std::string message(messageBuffer, size);
		//std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		//fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}

RadioButtonControl* RadioButtonControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		RadioButtonControl * control = new RadioButtonControl(env, buttonName, buttonDescription, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_RADIOBUTTON",
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

			//std::string message(messageBuffer, size);
			//std::cout << "Native Code - CreateWindowExW Error " << errorMessageID << ": " << message;
			//fflush(stdout);

			//Free the buffer.
			//LocalFree(messageBuffer);
		}
		else
			return control;
	}

	return NULL; // Indicates failure to create window.

}

void RadioButtonControl::SetState(_In_ bool controlState)
{
	jboolean toggle;

	if (controlState == ToggleState_On)
	{
		toggle = JNI_TRUE;
	}
	else
	{
		toggle = JNI_FALSE;
	}

	//jstring currentLineText = reinterpret_cast<jstring>(env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetCurrentLineText));
	JNIEnv* env = GetJNIEnv();
	env->CallVoidMethod(GetMe(), JavaClass_ToggleButton.SetToggleState, toggle);

	if (controlState)
		m_buttonProvider->Select();

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(m_buttonProvider, UIA_AutomationPropertyChangedEventId);
	}
}

bool RadioButtonControl::GetState()
{
	JNIEnv* env = GetJNIEnv();
	jboolean toggleState = env->CallBooleanMethod(GetMe(), JavaClass_ToggleButton.GetToggleState);
	ToggleState result;

	if (toggleState == JNI_FALSE)
	{
		result = ToggleState_Off;
	}
	else
	{
		result = ToggleState_On;
	}

	return result;
}

void RadioButtonControl::SetControlFocus(_In_ bool focused)
{
	m_focused = focused;
	if (focused)
		m_buttonProvider->NotifyFocusGained();
}

bool RadioButtonControl::HasFocus()
{
	return m_focused;
}

LRESULT RadioButtonControl::StaticRadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	RadioButtonControl * pThis = reinterpret_cast<RadioButtonControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<RadioButtonControl*>(createStruct->lpCreateParams);
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
		return pThis->RadioButtonControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

// Control window procedure.
LRESULT CALLBACK RadioButtonControl::RadioButtonControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, GetButtonProvider(GetHWND()));
		}

		break;
	}
	case WM_DESTROY:
	{
		// Disconnect the provider
		IRawElementProviderSimple* provider = this->GetButtonProvider(hwnd);
		if (provider != NULL)
		{
			HRESULT hr = UiaDisconnectProvider(provider);
			if (FAILED(hr))
			{
				// An error occurred while trying to disconnect the provider. For now, print the error message.
				std::cout << "UiaDisconnectProvider failed: UiaDisconnectProvider returned HRESULT 0x" << hr << std::endl;
			}
		}
	}
	case WM_SETFOCUS:
	{
		this->SetControlFocus(true);
		break;
	}
	case WM_KILLFOCUS:
	{
		this->SetControlFocus(false);
		break;
	}
	case QUORUM_INVOKEBUTTON:
	{
		bool state = static_cast<bool>(wParam);

		// Update the state of the radio button
		this->SetState(state);

		// Raise UIA Event
		this->InvokeButton(hwnd);

		break;
	}
	case QUORUM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
		break;
	}
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}
