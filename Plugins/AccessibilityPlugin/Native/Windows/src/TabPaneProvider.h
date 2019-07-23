#pragma once

#include "TabPaneControl.h"
#include "ProviderT.h"

class TabPaneProvider : public ProviderT<TabPaneProvider, TabPaneControl, ISelectionProvider>
{
public:
	TabPaneProvider(_In_ TabPaneControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;

	// Temporary IRawElementProviderFragmentRoot override
	IFACEMETHODIMP GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override;

	//ISelectionProvider
	IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override;
	IFACEMETHODIMP get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept override;
};
