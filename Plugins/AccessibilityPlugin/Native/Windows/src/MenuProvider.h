#pragma once

#include "MenuControl.h"
#include "ProviderT.h"

class MenuProvider : public ProviderT<MenuProvider, MenuControl>
{
public:
	MenuProvider(_In_ MenuControl* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;
};
