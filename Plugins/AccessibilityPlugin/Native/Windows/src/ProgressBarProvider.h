#pragma once

#include "ProgressBarControl.h"
#include "ProviderT.h"

class ProgressBarProvider : public ProviderT<ProgressBarProvider, ProgressBarControl, IRangeValueProvider>
{
public:
	ProgressBarProvider(ProgressBarControl* pButtonControl);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IInvokeProvider methods
	IFACEMETHODIMP get_IsReadOnly(BOOL* pRetVal) noexcept;
	IFACEMETHODIMP get_LargeChange(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Maximum(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Minimum(double* pRetVal) noexcept;
	IFACEMETHODIMP get_SmallChange(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Value(double* pRetVal) noexcept;
	IFACEMETHODIMP SetValue(double val) noexcept;
};
