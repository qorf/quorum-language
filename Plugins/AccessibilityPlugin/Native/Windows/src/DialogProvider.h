#pragma once
#include "DialogControl.h"
#include "ProviderT.h"

class DialogProvider : public ProviderT<DialogProvider, DialogControl>
{
public:
	DialogProvider(_In_ DialogControl* control);
	
	// Overridden functions from ProviderT.
	CONTROLTYPEID GetControlType() const noexcept;
};