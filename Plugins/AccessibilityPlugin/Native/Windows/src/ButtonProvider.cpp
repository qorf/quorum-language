#include "ButtonProvider.h"

ButtonProvider::ButtonProvider(ButtonControl* control) : ProviderT(control)
{
}

bool ButtonProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_InvokePatternId);
}

CONTROLTYPEID ButtonProvider::GetControlType() const noexcept
{
	return UIA_ButtonControlTypeId;
}

// Invoke
IFACEMETHODIMP ButtonProvider::Invoke() noexcept
{
	m_control->Invoke();
	return S_OK;
}
