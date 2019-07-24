#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class TableProvider;
class CellControl;

class TableControl : public ControlT<TableControl, TableProvider>
{
public:
	TableControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem);

	static TableControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* treeName, _In_ WCHAR* description, jobject jItem);

	CellControl* GetSelected();
	void SetSelected(CellControl* selected);

protected:

	CellControl* selected;

private:

	static LRESULT CALLBACK StaticSpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK SpreadsheetControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	
};
