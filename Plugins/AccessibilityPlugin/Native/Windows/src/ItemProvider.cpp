#include <windows.h>
#include <UIAutomation.h>
#include <iostream>
#include "ItemProvider.h"
#include "ItemControl.h"
#include "Item.h"

ItemProvider::ItemProvider(ItemControl* control) : ProviderT(control)
{
	// Nothing to do.
}
