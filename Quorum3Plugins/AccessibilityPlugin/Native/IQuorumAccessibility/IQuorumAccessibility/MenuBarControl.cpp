#include <windows.h>

#include "MenuBarControl.h"
#include "MenuBarProvider.h"

// For error reporting
#include <string>
#include <iostream>

bool MenuBarControl::Initialized = false;

MenuBarControl::MenuBarControl(_In_ WCHAR* menuBarName) : m_menuBarName(menuBarName), m_menuBarControl(NULL), m_menuBarProvider(NULL), m_focused(false), m_pSelectedMenuItem(NULL)
{

}

HWND MenuBarControl::Create(_In_ HINSTANCE instance, _In_ WCHAR * menuBarName)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		MenuBarControl * control = new MenuBarControl(menuBarName);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_MENU_BAR",
			menuBarName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			GetMainWindowHandle(), // Parent window
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_menuBarControl == NULL)
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
			return control->m_menuBarControl;
	}

	return NULL; // Indicates failure to create window.
}

MenuBarProvider * MenuBarControl::GetMenuBarProvider()
{
	if (m_menuBarProvider == NULL)
	{
		m_menuBarProvider = new MenuBarProvider(m_menuBarControl, this);
		UiaRaiseAutomationEvent(m_menuBarProvider, UIA_Window_WindowOpenedEventId);
	}
	return m_menuBarProvider;
}

HWND MenuBarControl::GetHWND()
{
	return m_menuBarControl;
}

WCHAR * MenuBarControl::GetName()
{
	return m_menuBarName;
}

void MenuBarControl::SetName(_In_ WCHAR * menuBarName)
{
	m_menuBarName = menuBarName;
}

bool MenuBarControl::HasFocus()
{
	return m_focused;
}

MenuItemControl * MenuBarControl::GetSelectedMenuItem()
{
	return m_pSelectedMenuItem;
}

void MenuBarControl::SetSelectedMenuItem(_In_ MenuItemControl * selectedMenuItem)
{
	m_pSelectedMenuItem = selectedMenuItem;
}

MenuBarControl::~MenuBarControl()
{
}

LRESULT MenuBarControl::StaticMenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	MenuBarControl * pThis = reinterpret_cast<MenuBarControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<MenuBarControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
		pThis->m_menuBarControl = hwnd;
	}

	if (message == WM_NCDESTROY)
	{
		pThis = NULL;
		SetWindowLongPtr(hwnd, GWLP_USERDATA, NULL);
	}

	if (pThis != NULL)
	{
		return pThis->MenuBarControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

LRESULT MenuBarControl::MenuBarControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetMenuBarProvider());
		}

		break;
	}
	case WM_DESTROY:
	{
		lResult = UiaReturnRawElementProvider(hwnd, 0, 0, NULL);
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

bool MenuBarControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticMenuBarControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_MENU_BAR";

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

void MenuBarControl::SetControlFocus(_In_ bool isFocused)
{
	m_focused = isFocused;
	if (isFocused)
	{
		// TODO: Raise a UIA Event
	}
}