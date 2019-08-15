#pragma once

#include "TreeItemControl.h"
#include "ProviderT.h"

class TreeControl;

class TreeItemProvider : public ProviderT<
	TreeItemProvider,
	TreeItemControl,
	ISelectionItemProvider,
	IExpandCollapseProvider>
{
public:
	TreeItemProvider(_In_ TreeItemControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IRawElementProviderFragment override
	IFACEMETHODIMP SetFocus() noexcept override;

	// IExpandCollapseProvider methods
	IFACEMETHODIMP get_ExpandCollapseState(_Out_ ExpandCollapseState* retVal) noexcept override;
	IFACEMETHODIMP Expand() noexcept override;
	IFACEMETHODIMP Collapse() noexcept override;

	// ISelectionItemProvider methods
	IFACEMETHODIMP Select() noexcept override;
	IFACEMETHODIMP AddToSelection() noexcept override;
	IFACEMETHODIMP RemoveFromSelection() noexcept override;
	IFACEMETHODIMP get_IsSelected(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept override;

	// Various methods
	void NotifyElementSelected();
	void NotifyElementExpandCollapse();
};
