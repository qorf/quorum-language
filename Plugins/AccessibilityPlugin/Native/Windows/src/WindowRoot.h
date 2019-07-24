#pragma once

#include "RootItemT.h"

class WindowRootProvider;

class WindowRoot : public RootItemT<WindowRoot, WindowRootProvider>
{
public:
	WindowRoot(HWND hwnd);
	~WindowRoot();

	HWND GetHwnd() const noexcept;

private:
	static LRESULT CALLBACK StaticOverrideWindowProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) noexcept;
	LRESULT OverrideWindowProc(UINT msg, WPARAM wParam, LPARAM lParam) noexcept;

	HWND m_hwnd;
	WNDPROC m_originalWndProc;
};
