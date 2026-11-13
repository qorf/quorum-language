#pragma once

#include "TooltipControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum Tooltip: the tooltip control type for a
	tooltip of plain text, whose name is its words, or the pane control type
	for one holding content, such as buttons. The tooltip itself never takes
	the keyboard focus; the buttons inside it do.
*/
class TooltipProvider : public ProviderT<TooltipProvider, TooltipControl>
{
public:
	TooltipProvider(TooltipControl* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;
	bool IsKeyboardFocusable() const noexcept;
};
