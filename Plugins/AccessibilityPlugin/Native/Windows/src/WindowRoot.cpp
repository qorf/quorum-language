#include "WindowRoot.h"
#include "WindowRootProvider.h"
#include "ControlTImpl.h"

constexpr auto c_propName = L"Quorum.WindowRootObject";

WindowRoot::WindowRoot(HWND hwnd)
	: ControlT(nullptr /* env */, L"" /* name */, L"" /* description */, nullptr /* jItem */)
{
	m_ControlHWND = hwnd;
	SetProp(hwnd, c_propName, reinterpret_cast<HANDLE>(this));
	m_originalWndProc = reinterpret_cast<WNDPROC>(SetWindowLongPtr(hwnd, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(StaticOverrideWindowProc)));
}

WindowRoot::~WindowRoot()
{
	SetWindowLongPtr(m_ControlHWND, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(m_originalWndProc));
	RemoveProp(m_ControlHWND, c_propName);
}

/* static */LRESULT CALLBACK WindowRoot::StaticOverrideWindowProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	const auto self = reinterpret_cast<WindowRoot*>(GetProp(hwnd, c_propName));
	FAIL_FAST_IF_NULL(self);
	return self->OverrideWindowProc(msg, wParam, lParam);
}

LRESULT WindowRoot::OverrideWindowProc(UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	if (msg == WM_GETOBJECT)
	{
		return UiaReturnRawElementProvider(m_ControlHWND, wParam, lParam, GetProvider().get());
	}

	return CallWindowProc(m_originalWndProc, m_ControlHWND, msg, wParam, lParam);
}
