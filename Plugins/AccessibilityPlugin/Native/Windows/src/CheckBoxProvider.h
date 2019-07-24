#pragma once

#include "CheckBoxControl.h"
#include "ProviderT.h"

class CheckBoxProvider : public ProviderT<CheckBoxProvider, CheckBoxControl, IToggleProvider>
{
public:
	CheckBoxProvider(CheckBoxControl* pButtonControl);

	// ProviderT methods
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IToggleProvider methods
	IFACEMETHODIMP Toggle();
	IFACEMETHODIMP get_ToggleState(_Out_ ToggleState *pRetVal);
};
