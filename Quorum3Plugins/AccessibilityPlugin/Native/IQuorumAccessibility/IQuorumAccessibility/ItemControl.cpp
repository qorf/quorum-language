#include "ItemControl.h"
#include "ItemProvider.h"

// Used to push native error up to Quorum
#include <iostream> 
#include <string>

bool ItemControl::Initialized = false;

/**** ItemControl methods ***/

ItemControl::ItemControl() : m_pItemProvider(NULL), m_pItemName(L"Item")
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

ItemProvider* ItemControl::GetItemProvider(_In_ HWND hwnd)
{
	if (m_pItemProvider == NULL)
	{
		m_pItemProvider = new ItemProvider(hwnd, this);
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

		std::string message(messageBuffer, size);
		std::cout << "RegisterClass Error " << errorMessageID << ": " << message << std::endl;
		fflush(stdout);
		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}

HWND ItemControl::Create(_In_ HINSTANCE instance, _In_ WCHAR* itemName, _In_ WCHAR* itemDescription)
{
	UNREFERENCED_PARAMETER(itemDescription);
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		ItemControl * control = new ItemControl();

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_ITEM",
			itemName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			GetMainWindowHandle(), // Parent window
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_ItemHWND == 0)
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
			control->SetName(itemName);
			return control->m_ItemHWND;
		}
	}

	return 0; // Indicates failure to create window.

}

WCHAR* ItemControl::GetName()
{
	return m_pItemName;
}

void ItemControl::SetName(_In_ WCHAR* name)
{
	m_pItemName = name;
}

void ItemControl::SetControlFocus()
{
	m_focused = true;
	this->m_pItemProvider->NotifyFocusGained();
}

void ItemControl::KillControlFocus()
{
	m_focused = false;
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
		pThis->m_ItemHWND = hwnd;
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
			lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetItemProvider(this->m_ItemHWND));
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
