#include <iostream> // Used to push native error up to Quorum
#include <string>

#include <windowsx.h>
#include "Item.h"
#include "ItemProvider.h"

bool Item::Initialized = false;

/**** Item methods ***/

Item::Item() : m_pItemProvider(NULL), m_pItemName(L"Item")
{
}

Item::~Item()
{
	if (m_pItemProvider != NULL)
	{
		m_pItemProvider->Release();
		m_pItemProvider = NULL;
	}
}

ItemProvider* Item::GetItemProvider(_In_ HWND hwnd)
{
	if (m_pItemProvider == NULL)
	{
		m_pItemProvider = new ItemProvider(hwnd, this);
	}
	return m_pItemProvider;
}


// Register the control class.
bool Item::Initialize(_In_ HINSTANCE hInstance)
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

HWND Item::Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* itemName, _In_ WCHAR* buttonDescription)
{

	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		Item * control = new Item();

		control->m_ItemHWND = CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_ITEM",
			itemName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parent, // Parent window
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

WCHAR* Item::GetName()
{
	return m_pItemName;
}

void Item::SetName(_In_ WCHAR* name)
{
	m_pItemName = name;
}

LRESULT Item::StaticItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	Item * pThis = reinterpret_cast<Item*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<Item*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
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
LRESULT CALLBACK Item::ItemControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	switch (message)
	{
	// Register with UI Automation.
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetItemProvider(this->m_ItemHWND));
		}
		return 0;
	}
	case CUSTOM_SETFOCUS:
	{
		this->m_pItemProvider->NotifyFocusGained();
		return 0;
	}
	case CUSTOM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
		return 0;
	}

	break;
	}  // switch (message)

	return DefWindowProc(hwnd, message, wParam, lParam);
}
