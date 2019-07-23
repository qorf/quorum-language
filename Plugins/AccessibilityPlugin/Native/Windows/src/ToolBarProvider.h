#pragma once

#include "ProviderT.h"
#include "ToolBarControl.h"

class ToolBarProvider : public ProviderT<ToolBarProvider, ToolBarControl>
{
public:
	ToolBarProvider(_In_ ToolBarControl* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;
	bool IsKeyboardFocusable() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;
};
