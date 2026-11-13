#pragma once

#include <string>
#include "ControlT.h"

class TooltipProvider;

/*
	A Quorum Tooltip for UI Automation. A tooltip of plain text is a tooltip
	element whose name is everything it says. A tooltip with content, such as
	buttons, is a pane holding them, so a screen reader can reach them. When
	it opens and closes, UI Automation's tooltip events are raised, as for a
	native Windows tooltip.
*/
class TooltipControl : public ControlT<TooltipControl, TooltipProvider>
{
public:
	TooltipControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, bool hasContent, jobject jItem);

	// Whether the tooltip holds content, such as buttons, instead of plain text.
	bool HasContent() const noexcept;

	// Called from Quorum after the tooltip opened: raises the tooltip opened event.
	void NotifyOpened();

	// Called from Quorum before the tooltip closes: raises the tooltip closed event.
	void NotifyClosed();
private:
	bool content;
};
