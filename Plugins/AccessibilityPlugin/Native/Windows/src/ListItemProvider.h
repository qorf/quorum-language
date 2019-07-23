#pragma once

#include "ListItemControl.h"
#include "ProviderT.h"

class ListItemProvider : public ProviderT<ListItemProvider, ListItemControl, ISelectionItemProvider, IValueProvider>
{
public:
	ListItemProvider(_In_ ListItemControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IRawElementProviderFragment override
	IFACEMETHODIMP SetFocus() noexcept override;

	// ISelectionItemProvider
	IFACEMETHODIMP Select() noexcept override;
	IFACEMETHODIMP AddToSelection() noexcept override;
	IFACEMETHODIMP RemoveFromSelection() noexcept override;
	IFACEMETHODIMP get_IsSelected(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept override;

	// IValueProvider
	IFACEMETHODIMP get_IsReadOnly(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP SetValue(LPCWSTR value) noexcept override;
	IFACEMETHODIMP get_Value(_Out_ BSTR* retVal) noexcept override;

	void NotifyElementSelected();
};
