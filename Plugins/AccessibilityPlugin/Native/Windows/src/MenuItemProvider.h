#pragma once

#include "MenuItemControl.h"
#include "ProviderT.h"

class MenuItemProvider : public ProviderT<
	MenuItemProvider,
	MenuItemControl,
	IInvokeProvider,
	IExpandCollapseProvider>
{
public:
	MenuItemProvider(_In_ MenuItemControl* control);

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
