#include "DialogProvider.h"

DialogProvider::DialogProvider(_In_ DialogControl* control) : ProviderT(control)
{
}

CONTROLTYPEID DialogProvider::GetControlType() const noexcept
{
	return UIA_PaneControlTypeId;
}
