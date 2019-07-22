#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "SpreadsheetControl.h"
#include "TreeTableProvider.h"

class TreeTableProvider;
class CellControl;
class SpreadsheetControl;

class TreeTableControl : public SpreadsheetControl
{
public:
	TreeTableControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~TreeTableControl();

	static TreeTableControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);
private:
	static LRESULT CALLBACK StaticTreeTableControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK TreeTableControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool InitializeTreeTable(_In_ HINSTANCE hInstance);
	static bool Initialized;
};