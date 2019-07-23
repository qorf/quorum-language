#pragma once

#include "ButtonControl.h"
#include "ProviderT.h"

class ButtonProvider : public ProviderT<ButtonProvider, ButtonControl, IInvokeProvider>
{
public:
	ButtonProvider(ButtonControl* pButtonControl);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IInvokeProvider methods
	IFACEMETHODIMP Invoke() noexcept;

	// Other methods
	void ButtonProvider::NotifyFocusGained(); // Fires a UI Automation Event when called.
};
