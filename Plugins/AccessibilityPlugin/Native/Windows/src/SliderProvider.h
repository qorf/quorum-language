#pragma once

#include "SliderControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum Slider: the slider control type, the
	range value pattern (its number, range, step and page step, settable),
	and the value pattern (its value as text, such as "40 degrees").
*/
class SliderProvider : public ProviderT<SliderProvider, SliderControl, IRangeValueProvider, IValueProvider>
{
public:
	SliderProvider(SliderControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IRangeValueProvider, and IValueProvider's get_IsReadOnly.
	IFACEMETHODIMP get_IsReadOnly(BOOL* pRetVal) noexcept;
	IFACEMETHODIMP get_LargeChange(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Maximum(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Minimum(double* pRetVal) noexcept;
	IFACEMETHODIMP get_SmallChange(double* pRetVal) noexcept;
	IFACEMETHODIMP get_Value(double* pRetVal) noexcept;
	IFACEMETHODIMP SetValue(double val) noexcept;

	// IValueProvider
	IFACEMETHODIMP SetValue(LPCWSTR value) noexcept;
	IFACEMETHODIMP get_Value(BSTR* returnValue) noexcept;
};
