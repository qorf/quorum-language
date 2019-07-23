#pragma once

#include "ItemControl.h"
#include "ProviderT.h"

class ItemProvider : public ProviderT<ItemProvider, ItemControl>
{
public:
	ItemProvider(ItemControl* pItem);

	// Other methods
	void ItemProvider::NotifyFocusGained(); // Fires a UI Automation event when called.
};
