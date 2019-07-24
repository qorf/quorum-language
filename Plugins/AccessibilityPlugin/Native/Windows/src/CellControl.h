#pragma once

#include "CustomMessages.h"
#include "ControlT.h"

class CellProvider;
class TableControl;

class CellControl : public ControlT<CellControl, CellProvider>
{
public:
	CellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ TableControl* parent, jobject jItem);
	static CellControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, TableControl* parent, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem);

	TableControl* GetParentTable();

	// Gets the text contained within the cell from Quorum.
	std::wstring GetText();

private:
	static LRESULT CALLBACK StaticCellControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	LRESULT CALLBACK CellControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
	// The provider for this tab
	static bool Initialize(_In_ HINSTANCE hInstance);
	static bool Initialized;

	TableControl* m_parentTable;
};
