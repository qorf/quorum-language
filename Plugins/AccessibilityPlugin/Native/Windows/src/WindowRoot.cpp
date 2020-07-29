#include "WindowRoot.h"
#include "WindowRootProvider.h"
#include "RootItemTImpl.h"

constexpr auto c_propName = L"Quorum.WindowRootObject";

namespace
{

void DisconnectAndDestroyRecursive(_In_ Item* item)
{
	auto child = item->GetFirstChild();
	while (child != nullptr)
	{
		auto next = child->GetNextSibling();
		DisconnectAndDestroyRecursive(child);
		child = next;
	}

	item->Disconnect();
	delete item;
}

} // anonymous namespace

WindowRoot::WindowRoot(HWND hwnd, WCHAR* name)
	: RootItemT(nullptr /* env */, name /* name */, L"" /* description */, nullptr /* jItem */)
	, m_hwnd(hwnd)
	, m_isWindowFocused(::GetFocus() == hwnd)
{
	SetProp(hwnd, c_propName, reinterpret_cast<HANDLE>(this));
	m_originalWndProc = reinterpret_cast<WNDPROC>(SetWindowLongPtr(hwnd, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(StaticOverrideWindowProc)));
}

WindowRoot::~WindowRoot()
{
	SetWindowLongPtr(m_hwnd, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(m_originalWndProc));
	RemoveProp(m_hwnd, c_propName);
}

HWND WindowRoot::GetHwnd() const noexcept
{
	return m_hwnd;
}

bool WindowRoot::IsHostFocused() const noexcept
{
	return m_isWindowFocused;
}

/* static */LRESULT CALLBACK WindowRoot::StaticOverrideWindowProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	const auto self = reinterpret_cast<WindowRoot*>(GetProp(hwnd, c_propName));
	FAIL_FAST_IF_NULL(self);
	return self->OverrideWindowProc(msg, wParam, lParam);
}

LRESULT WindowRoot::OverrideWindowProc(UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	if ((msg == WM_GETOBJECT) && !m_isDisconnecting)
	{
		return UiaReturnRawElementProvider(m_hwnd, wParam, lParam, GetProvider().get());
	}
	else if ((msg == WM_SETFOCUS) && !m_isWindowFocused)
	{
		m_isWindowFocused = true;
		NotifyHostFocusGained();
		// Let the message pass on to the original window proc.
	}
	else if ((msg == WM_KILLFOCUS) && m_isWindowFocused)
	{
		m_isWindowFocused = false;
		NotifyHostFocusLost();
		// Let the message pass on to the original window proc.
	}
	else if (msg == WM_KEYDOWN)
	{
		// We need to let Quorum know that we need to process this keyboard input before we continue pumping messages.
		
		JNIEnv* env = GetJNIEnv();
		if (env != NULL)
		{
			env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.PauseEventPolling);
		}

		// We still let this message pass on to the original window procedure so GLFW can handle it.
	}

	return CallWindowProc(m_originalWndProc, m_hwnd, msg, wParam, lParam);
}

void WindowRoot::DisconnectAndDestroyAll()
{
	m_isDisconnecting = true;
	DisconnectAndDestroyRecursive(this);
}
