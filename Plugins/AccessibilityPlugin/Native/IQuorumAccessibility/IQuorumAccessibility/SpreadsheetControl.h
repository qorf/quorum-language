#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <deque>

#include "CustomMessages.h"
#include "Item.h"
#include "SpreadsheetProvider.h"

class SpreadsheetProvider;
class CellControl;

class SpreadsheetControl : public Item
{
public:
	SpreadsheetControl(JNIEnv* env, _In_ WCHAR* name, jobject jItem);
	virtual ~SpreadsheetControl();

	static SpreadsheetControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, jobject jItem);

	SpreadsheetProvider* GetProvider();

	CellControl* GetSelected();
	void SetSelected(CellControl* selected);

protected:

	SpreadsheetProvider* provider;
	CellControl* selected;

private:

	static LRESULT CALLBACK StaticSpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK SpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	
};