#pragma once

#include "ComboBoxControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum ComboBox: the combo box control type,
	the value pattern (its text, which a screen reader can set), the expand
	collapse pattern (the list below it), and the text pattern, so its caret
	and selection are read as in a text field.
*/
class ComboBoxProvider : public ProviderT<ComboBoxProvider, ComboBoxControl, ITextProvider, IValueProvider, IExpandCollapseProvider>
{
public:
	ComboBoxProvider(ComboBoxControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// ITextProvider
	IFACEMETHODIMP GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept;
	IFACEMETHODIMP GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept;
	IFACEMETHODIMP RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept;
	IFACEMETHODIMP RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept;
	IFACEMETHODIMP get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept;
	IFACEMETHODIMP get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal) noexcept;

	// IValueProvider
	IFACEMETHODIMP get_IsReadOnly(BOOL* returnValue) noexcept;
	IFACEMETHODIMP SetValue(LPCWSTR value) noexcept;
	IFACEMETHODIMP get_Value(BSTR* returnValue) noexcept;

	// IExpandCollapseProvider
	IFACEMETHODIMP Expand() noexcept;
	IFACEMETHODIMP Collapse() noexcept;
	IFACEMETHODIMP get_ExpandCollapseState(ExpandCollapseState* returnValue) noexcept;
};
