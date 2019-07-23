#pragma once

#include "TabControl.h"
#include "ProviderT.h"

class TabProvider : public ProviderT<TabProvider, TabControl, ISelectionItemProvider>
{
public:
	TabProvider(_In_ TabControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	bool IsKeyboardFocusable() const noexcept;

	// IRawElementProviderFragment override
	IFACEMETHODIMP SetFocus() noexcept override;

	// ISelectionItemProvider
	IFACEMETHODIMP Select() noexcept override;
	IFACEMETHODIMP AddToSelection() noexcept override;
	IFACEMETHODIMP RemoveFromSelection() noexcept override;
	IFACEMETHODIMP get_IsSelected(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept override;

	void NotifyElementSelected();
};
