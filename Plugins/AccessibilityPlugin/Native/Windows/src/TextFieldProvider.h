#pragma once

#include <windows.h>
#include <UIAutomation.h>

#include "ProviderT.h"
#include "TextFieldControl.h"

class TextFieldProvider : public ProviderT<TextFieldProvider, TextFieldControl, ITextProvider, IValueProvider>
{
	public:
		TextFieldProvider(TextFieldControl* parentControl);

		// Overridden ProviderT functions.
		bool IsPatternSupported(PATTERNID patternId) const noexcept;
		CONTROLTYPEID GetControlType() const noexcept;

		// Methods from ITextProvider.
		IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal);
		IFACEMETHODIMP GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal);
		IFACEMETHODIMP RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
		IFACEMETHODIMP RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
		IFACEMETHODIMP get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal);
		IFACEMETHODIMP get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal);

		// Methods from IValueProvider.
		IFACEMETHODIMP get_IsReadOnly(BOOL *returnValue);
		IFACEMETHODIMP SetValue(LPCWSTR value);
		IFACEMETHODIMP get_Value(BSTR* returnValue);
};
