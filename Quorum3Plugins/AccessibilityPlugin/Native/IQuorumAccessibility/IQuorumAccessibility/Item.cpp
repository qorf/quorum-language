#include <iostream> // Used to push native error up to Quorum
#include <string>

#include <windowsx.h>
#include "Item.h"
#include "ItemProvider.h"

// Forward declarations.
LRESULT CALLBACK ControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam);

/**** Item methods ***/

Item::Item() : m_itemProvider(NULL), m_itemName(L"Item")
{
}

Item::~Item()
{
	if (m_itemProvider != NULL)
	{
		m_itemProvider->Release();
		m_itemProvider = NULL;
	}
}

ItemProvider* Item::GetUIAutomationProvider(HWND hwnd)
{
	if (m_itemProvider == NULL)
	{
		m_itemProvider = new (std::nothrow) ItemProvider(hwnd, this);
	}
	return m_itemProvider;
}


// Register the control class.
void Item::RegisterControl(HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = ControlWndProc;
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
	}
}


WCHAR* Item::GetName()
{
	return m_itemName;
}

void Item::SetName(WCHAR* name)
{
	m_itemName = name;
}

// Control window procedure.
LRESULT CALLBACK ControlWndProc(HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	switch (message)
	{
	case WM_CREATE:
	{
		Item* pControl = new (std::nothrow) Item();
		if (pControl == NULL)
		{
			PostQuitMessage(-1);
		}
		// Save the class instance as extra window data so that members can be accessed
		//  from within this function.
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pControl));
		break;
	}

	case WM_DESTROY:
	{
		Item* pControl = reinterpret_cast<Item*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
		delete pControl;
		break;
	}
	// Register with UI Automation.
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			Item* pControl = reinterpret_cast<Item*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
			IRawElementProviderSimple* pRootProvider = pControl->GetUIAutomationProvider(hwnd);
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, pRootProvider);
		}
		return 0;
	}
	case CUSTOM_SETFOCUS:
	{
		Item* pControl = reinterpret_cast<Item*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
		ItemProvider* itemProvider = pControl->GetUIAutomationProvider(hwnd);
		itemProvider->NotifyFocusGained();
		return 0;
	}
	case CUSTOM_SETNAME:
	{
		Item* pControl = reinterpret_cast<Item*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
		pControl->SetName((WCHAR*)lParam);
		
		return 0;
	}

	break;
	}  // switch (message)

	return DefWindowProc(hwnd, message, wParam, lParam);
}
