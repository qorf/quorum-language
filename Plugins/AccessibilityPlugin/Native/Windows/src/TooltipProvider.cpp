#include "TooltipProvider.h"

TooltipProvider::TooltipProvider(TooltipControl* control) : ProviderT(control)
{
}

CONTROLTYPEID TooltipProvider::GetControlType() const noexcept
{
	if (m_control != nullptr && m_control->HasContent())
	{
		return UIA_PaneControlTypeId;
	}
	return UIA_ToolTipControlTypeId;
}

bool TooltipProvider::IsKeyboardFocusable() const noexcept
{
	return false;
}
