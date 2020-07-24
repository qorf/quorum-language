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
	m_control->Close();
	return S_OK;
}

IFACEMETHODIMP DialogProvider::get_CanMaximize(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::get_CanMinimize(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::get_IsModal(_Out_ BOOL* retVal) noexcept
{
	bool result = m_control->IsModal();
	*retVal = result;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::get_IsTopmost(_Out_ BOOL* retVal) noexcept
{
	*retVal = TRUE;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::get_WindowInteractionState(_Out_ WindowInteractionState* retVal) noexcept
{
	*retVal = WindowInteractionState_ReadyForUserInteraction;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::get_WindowVisualState(_Out_ WindowVisualState* retVal) noexcept
{
	*retVal = WindowVisualState_Normal;
	return S_OK;
}
IFACEMETHODIMP DialogProvider::SetVisualState(_In_ WindowVisualState retVal) noexcept
{
	return E_NOTIMPL;
}
IFACEMETHODIMP DialogProvider::WaitForInputIdle(_Out_ int  milliseconds, _Out_ BOOL* pRetVal) noexcept
{
	return E_NOTIMPL;
}


void DialogProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
	case UIA_IsDialogPropertyId:
		retVal->boolVal = VARIANT_TRUE;
		break;
	}
}