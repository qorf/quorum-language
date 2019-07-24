#include "WindowRootProvider.h"

WindowRootProvider::WindowRootProvider(_In_ WindowRoot* control)
	: ProviderT(control)
{
}

CONTROLTYPEID WindowRootProvider::GetControlType() const noexcept
{
	return UIA_WindowControlTypeId;
}
