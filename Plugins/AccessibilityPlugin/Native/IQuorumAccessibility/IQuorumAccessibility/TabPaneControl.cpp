
#include "TabPaneControl.h"

// For error reporting
#include <string>
#include <iostream>

bool TabPaneControl::Initialized = false;

TabPaneControl::TabPaneControl(JNIEnv* env, _In_ WCHAR* treeName, jobject jItem)
	: Item(env, treeName, L"", jItem), provider(NULL), selectedTab(NULL)
{
}

TabPaneControl::~TabPaneControl()
{
}

TabPaneControl* TabPaneControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		TabPaneControl* control = new TabPaneControl(env, treeName, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_TAB_PANE",
			treeName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parentWindow, // Parent window
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_ControlHWND == NULL)
		{
			DWORD errorMessageID = ::GetLastError();

			LPSTR messageBuffer = nullptr;
			size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
				NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)& messageBuffer, 0, NULL);
			std::cout << messageBuffer << std::endl;
			//Free the buffer.
			LocalFree(messageBuffer);
		}
		else {
			return control;
		}
	}
	return NULL; // Indicates failure to create window.
}

TabPaneProvider* TabPaneControl::GetProvider()
{
	if (provider == NULL)
	{
		provider = new TabPaneProvider(this);
		UiaRaiseAutomationEvent(provider, UIA_Window_WindowOpenedEventId);
	}
	return provider;
}

TabControl* TabPaneControl::GetSelectedTab()
{
	return selectedTab;
}

void TabPaneControl::SetSelectedTab(_In_opt_ TabControl* tab)
{
	selectedTab = tab;
	if (selectedTab != nullptr && UiaClientsAreListening())
	{
		//selectedTab->GetProvider()->NotifyElementSelected();
	}
}

LRESULT TabPaneControl::StaticTabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	TabPaneControl* pThis = reinterpret_cast<TabPaneControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT* createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<TabPaneControl*>(createStruct->lpCreateParams);
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
		return pThis->TabPaneControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

LRESULT TabPaneControl::TabPaneControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetProvider());
		}

		break;
	}
	case WM_DESTROY:
	{
		// Disconnect the provider
		IRawElementProviderSimple* provider = this->GetProvider();
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
	case QUORUM_ADD_TAB:
	{
		TabControl* tab = (TabControl*)lParam;

		//Subtree* subtree = newTreeItem->GetSubtree();

		//subtree->AddTreeItem(newTreeItem);

		break;
	}
	case QUORUM_REMOVE_TAB:
	{
		TabControl* tab = (TabControl*)lParam;

		//Subtree* subtree = treeItemToRemove->GetSubtree();

		//subtree->RemoveTreeItem(treeItemToRemove);
		break;
	}
	case QUORUM_SELECTTREEITEM:
	{
		TabControl* tab = (TabControl*)lParam;

		SetSelectedTab(tab);

		break;
	}
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}

bool TabPaneControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticTabPaneControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_TAB_PANE";

	if (RegisterClassExW(&wc) == 0)
	{
		// An error occured. Output this error so it can be seen from Quorum.
		DWORD errorMessageID = ::GetLastError();

		LPSTR messageBuffer = nullptr;
		size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)& messageBuffer, 0, NULL);

		//std::string message(messageBuffer, size);
		//std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		//fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}