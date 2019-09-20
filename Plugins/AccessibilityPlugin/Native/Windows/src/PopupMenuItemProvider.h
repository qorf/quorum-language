#pragma once

#include "PopupMenuItemControl.h"
#include "ProviderT.h"

class PopupMenuItemProvider : public ProviderT<
	PopupMenuItemProvider,
	PopupMenuItemControl,
	IInvokeProvider,
	IExpandCollapseProvider>
{
public:
	PopupMenuItemProvider(_In_ PopupMenuItemControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;

	// IRawElementProviderFragment override
	IFACEMETHODIMP SetFocus() noexcept override;

	// IExpandCollapseProvider methods
	IFACEMETHODIMP get_ExpandCollapseState(_Out_ ExpandCollapseState* retVal) noexcept override;
	IFACEMETHODIMP Expand() noexcept override;
	IFACEMETHODIMP Collapse() noexcept override;

	// IInvokeProvider methods
	IFACEMETHODIMP Invoke() noexcept override;

	// Various methods
	void NotifyElementSelected();
	void NotifyElementInvoked();
	void NotifyElementExpandCollapse();
};
