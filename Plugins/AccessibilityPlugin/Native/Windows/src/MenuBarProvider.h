#pragma once

#include "MenuBarControl.h"
#include "ProviderT.h"

class MenuBarProvider : public ProviderT<MenuBarProvider, MenuBarControl>
{
public:
	MenuBarProvider(_In_ MenuBarControl* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;
};
