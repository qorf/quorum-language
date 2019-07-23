#include "TreeControl.h"
#include "TreeProvider.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"
#include "ControlTImpl.h"

// For error reporting
#include <string>
#include <iostream>

bool TreeControl::Initialized = false;

TreeControl::TreeControl(JNIEnv* env, std::wstring&& treeName, jobject jItem) 
	: ControlT(env, std::move(treeName), L"", jItem)
{
}

TreeControl* TreeControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR * treeName, jobject jItem)
{
	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		TreeControl * control = new TreeControl(env, treeName, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_TREE",
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

bool TreeControl::HasFocus()
{
	return m_focused;
}

TreeItemControl* TreeControl::GetSelectedTreeItem()
{
	return m_pSelectedTreeItem;
}

void TreeControl::SetSelectedTreeItem(_In_opt_ TreeItemControl* selectedTreeItem)
{
	m_pSelectedTreeItem = selectedTreeItem;
	if (m_pSelectedTreeItem != nullptr && UiaClientsAreListening())
	{
		m_pSelectedTreeItem->GetProvider()->NotifyElementSelected();
	}
}

LRESULT TreeControl::StaticTreeControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	TreeControl * pThis = reinterpret_cast<TreeControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<TreeControl*>(createStruct->lpCreateParams);
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
		return pThis->TreeControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}

LRESULT TreeControl::TreeControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			return UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetProvider().get());
		}

		break;
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
	default:
		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;
	}  // switch (message)

	return lResult;
}

bool TreeControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticTreeControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_TREE";

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

void TreeControl::SetControlFocus(_In_ bool isFocused)
{
	m_focused = isFocused;
}
