#pragma once

#include "ItemControl.h"
#include "ProviderT.h"

class ItemProvider : public ProviderT<ItemProvider, ItemControl>
{
public:
	ItemProvider(ItemControl* pItem);
};
