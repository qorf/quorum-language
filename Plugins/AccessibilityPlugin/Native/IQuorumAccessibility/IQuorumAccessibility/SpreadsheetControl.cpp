
#include "SpreadsheetControl.h"
#include "CellControl.h"

// For error reporting
#include <string>
#include <iostream>

bool SpreadsheetControl::Initialized = false;

SpreadsheetControl::SpreadsheetControl(JNIEnv* env, _In_ WCHAR* treeName, jobject jItem)
	: Item(env, treeName, L"", jItem), provider(NULL), selected(NULL)
{
}

SpreadsheetControl::~SpreadsheetControl()
{
}

SpreadsheetControl* SpreadsheetControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		SpreadsheetControl* control = new SpreadsheetControl(env, treeName, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_SPREADSHEET",
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

SpreadsheetProvider* SpreadsheetControl::GetProvider()
{
	if (provider == NULL)
	{
		provider = new SpreadsheetProvider(this);
		UiaRaiseAutomationEvent(provider, UIA_Window_WindowOpenedEventId);
	}
	return new SpreadsheetProvider(this);
}

CellControl* SpreadsheetControl::GetSelected()
{
	return selected;
}

void SpreadsheetControl::SetSelected(_In_opt_ CellControl* cell)
{
	selected = cell;
	if (cell != nullptr && UiaClientsAreListening())
	{
		cell->GetProvider()->NotifyElementSelected();
	}
}

LRESULT SpreadsheetControl::StaticSpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	SpreadsheetControl* pThis = reinterpret_cast<SpreadsheetControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT* createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<SpreadsheetControl*>(createStruct->lpCreateParams);
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
		return pThis->SpreadsheetControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

LRESULT SpreadsheetControl::SpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
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
	case QUORUM_SELECTTREEITEM:
	{
		CellControl* tab = (CellControl*)lParam;

		SetSelected(tab);

		break;
	}
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}

bool SpreadsheetControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticSpreadsheetControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_SPREADSHEET";

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
