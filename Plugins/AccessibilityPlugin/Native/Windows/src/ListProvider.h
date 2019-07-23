#pragma once

#include "ListControl.h"
#include "ProviderT.h"

class ListProvider : public ProviderT<ListProvider, ListControl, ISelectionProvider>
{
public:
	ListProvider(_In_ ListControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// Temporary IRawElementProviderFragmentRoot override
	IFACEMETHODIMP GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override;

	//ISelectionProvider
	IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override;
	IFACEMETHODIMP get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept override;
};
