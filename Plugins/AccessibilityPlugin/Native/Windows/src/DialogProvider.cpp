#include "DialogProvider.h"

DialogProvider::DialogProvider(_In_ DialogControl* control) : ProviderT(control)
{
}

CONTROLTYPEID DialogProvider::GetControlType() const noexcept
{
	return UIA_WindowControlTypeId;
}

bool DialogProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_WindowPatternId);
}

IFACEMETHODIMP DialogProvider::Close() noexcept
{
	return E_NOTIMPL;
}

IFACEMETHODIMP DialogProvider::get_CanMaximize(_Out_ BOOL* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::get_CanMinimize(_Out_ BOOL* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::get_IsModal(_Out_ BOOL* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::get_IsTopmost(_Out_ BOOL* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::get_WindowInteractionState(_Out_ WindowInteractionState* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::get_WindowVisualState(_Out_ WindowVisualState* retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::SetVisualState(_In_ WindowVisualState retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::WaitForInputIdle(_Out_ int  milliseconds, _Out_ BOOL* pRetVal) noexcept
{
	return E_NOTIMPL;
}
