#pragma once

#include "ButtonGroupControl.h"
#include "ProviderT.h"

class ButtonGroupProvider : public ProviderT<ButtonGroupProvider, ButtonGroupControl, ISelectionProvider>
{
public:
	ButtonGroupProvider(_In_ ButtonGroupControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// ISelectionProvider methods
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal) override;
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal) override;
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal) override;
};