#pragma once
#include "TextBoxControl.h"
#include "ProviderT.h"

// Used to create Int SafeArray for RuntimeId
SAFEARRAY* BuildIntSafeArray(_In_reads_(length) const int * data, _In_ int length);

class TextBoxControl;

class TextBoxProvider : public ProviderT<TextBoxProvider, TextBoxControl, ITextProvider, IValueProvider>
{
public:
	TextBoxProvider(_In_ TextBoxControl* control);

	// Overridden ProviderT functions.
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// ITextProvider methods
	IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal);
	IFACEMETHODIMP GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal);
	IFACEMETHODIMP RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal);

	// Methods from IValueProvider.
	IFACEMETHODIMP get_IsReadOnly(BOOL* returnValue);
	IFACEMETHODIMP SetValue(LPCWSTR value);
	IFACEMETHODIMP get_Value(BSTR* returnValue);
};

void NotifyCaretPositionChanged(_In_ TextBoxControl* control);
