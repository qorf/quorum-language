#pragma once

#include "TreeControl.h"
#include "ProviderT.h"

class TreeProvider :	public ProviderT<TreeProvider, TreeControl, ISelectionProvider>
{
public:
	TreeProvider(_In_ TreeControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// ISelectionProvider methods
	IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override;
	IFACEMETHODIMP get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept override;
	IFACEMETHODIMP get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept override;
};
