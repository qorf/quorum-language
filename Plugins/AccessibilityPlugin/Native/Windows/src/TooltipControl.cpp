#include "TooltipControl.h"
#include "TooltipProvider.h"
#include "ControlTImpl.h"

TooltipControl::TooltipControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, bool hasContent, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	content = hasContent;
}

bool TooltipControl::HasContent() const noexcept
{
	return content;
}

void TooltipControl::NotifyOpened()
{
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_ToolTipOpenedEventId);
	}
}

void TooltipControl::NotifyClosed()
{
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProvider().get(), UIA_ToolTipClosedEventId);
	}
}

// Generate ControlT's members here, so other files can link to them (the
// quorum-natives build, WindowsDllMacBuild.md fix 2).
template class ControlT<TooltipControl, TooltipProvider>;
