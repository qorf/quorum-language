#pragma once

#include "ColorWellControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum ColorWell: the button control type,
	called a "color well", the invoke pattern (opens the picker) and a
	read-only value pattern giving the color in words, which Narrator and
	NVDA read after the button's name.
*/
class ColorWellProvider : public ProviderT<ColorWellProvider, ColorWellControl, IInvokeProvider, IValueProvider>
{
public:
	ColorWellProvider(ColorWellControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;

	// IInvokeProvider
	IFACEMETHODIMP Invoke() noexcept;

	// IValueProvider
	IFACEMETHODIMP get_IsReadOnly(BOOL* returnValue) noexcept;
	IFACEMETHODIMP SetValue(LPCWSTR value) noexcept;
	IFACEMETHODIMP get_Value(BSTR* returnValue) noexcept;
};
