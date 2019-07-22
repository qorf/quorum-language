#pragma once
#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "SpreadsheetControl.h"
#include "CellProvider.h"
#include "Item.h"

class CellProvider;
class SpreadsheetControl;

class CellControl : public Item
{
public:
	CellControl(JNIEnv* env, _In_ std::wstring name, _In_ SpreadsheetControl* parent, jobject jItem);
	virtual ~CellControl();
	static CellControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, SpreadsheetControl* parent, _In_ WCHAR* name, jobject jItem);

	SpreadsheetControl* GetParent();
	CellProvider* GetProvider();

	// Gets the text contained within the cell from Quorum.
	std::wstring GetText();

private:
	static LRESULT CALLBACK StaticCellControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK CellControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	// The provider for this tab
	CellProvider* provider;
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	SpreadsheetControl* parent;
};