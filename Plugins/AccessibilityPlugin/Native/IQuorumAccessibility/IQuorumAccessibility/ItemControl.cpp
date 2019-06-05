#include "ItemControl.h"
#include "ItemProvider.h"

// Used to push native error up to Quorum
#include <iostream> 
#include <string>

bool ItemControl::Initialized = false;

/**** ItemControl methods ***/

ItemControl::ItemControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem) 
	: Item(env, name, description, jItem), m_pItemProvider(NULL), m_focused(false)
{
}

ItemControl::~ItemControl()
{
	if (m_pItemProvider != NULL)
	{
		m_pItemProvider->Release();
		m_pItemProvider = NULL;
	}
}

ItemProvider* ItemControl::GetItemProvider()
{
	if (m_pItemProvider == NULL)
	{
		m_pItemProvider = new ItemProvider(this);
		UiaRaiseAutomationEvent(m_pItemProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_pItemProvider;
}


// Register the control class.
bool ItemControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticItemControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_ITEM";

	if (RegisterClassExW(&wc) == 0)
	{
		DWORD errorMessageID = ::GetLastError();

		LPSTR messageBuffer = nullptr;
		size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

		//std::string message(messageBuffer, size);
		//std::cout << "RegisterClass Error " << errorMessageID << ": " << message << std::endl;
		//fflush(stdout);
		//Free the buffer.
		//LocalFree(messageBuffer);

		return false;
	}

	return true;
}

ItemControl* ItemControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		ItemControl * control = new ItemControl(env, itemName, itemDescription, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_ITEM",
			itemName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parentWindow,
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_ControlHWND == 0)
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
			if (UiaClientsAreListening())
				control->GetItemProvider();
			return control;
		}
	}

	return NULL; // Indicates failure to create window.

}

void ItemControl::Focus(bool focused)
{
	if (focused) {
		this->m_pItemProvider->NotifyFocusGained();
	}
}

bool ItemControl::HasFocus()
{
	return m_focused;
}

LRESULT ItemControl::StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	ItemControl * pThis = reinterpret_cast<ItemControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<ItemControl*>(createStruct->lpCreateParams);
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
		return pThis->ItemControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

// Control window procedure.
LRESULT CALLBACK ItemControl::ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	// Register with UI Automation.
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, GetItemProvider());
		}
		break;
	}
	case WM_DESTROY:
	{
		// Disconnect the provider
		IRawElementProviderSimple* provider = this->GetItemProvider();
		if (provider != NULL)
		{
			HRESULT hr = UiaDisconnectProvider(provider);
			if (FAILED(hr))
			{
				// An error occurred while trying to disconnect the provider. For now, print the error message.
				//std::cout << "UiaDisconnectProvider failed: UiaDisconnectProvider returned HRESULT 0x" << hr << std::endl;
			}
		}
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
