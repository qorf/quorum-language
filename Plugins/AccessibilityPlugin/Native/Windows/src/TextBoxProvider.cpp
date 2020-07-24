#include <UIAutomation.h>
#include "jni.h"

#include "TextBoxControl.h"
#include "TextBoxTextRange.h"
#include "TextBoxProvider.h"


void NotifyCaretPositionChanged(_In_ TextBoxControl* control)
{
	TextBoxProvider* eventControl = control->GetProvider().get();
	THROW_IF_FAILED(UiaRaiseAutomationEvent(eventControl, UIA_Text_TextSelectionChangedEventId));
}

TextBoxProvider::TextBoxProvider(_In_ TextBoxControl* control) : ProviderT(control)
{
	// Nothing to do here.
}

// =========== Overridden ProviderT functions

bool TextBoxProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_TextPatternId || patternId == UIA_ValuePatternId);
}

CONTROLTYPEID TextBoxProvider::GetControlType() const noexcept
{
	return UIA_DocumentControlTypeId;
}

// =========== ITextProvider implementation

// get_SupportedTextSelection: Retrieves a value that specifies the type of text selection that is supported by the control.
IFACEMETHODIMP TextBoxProvider::get_SupportedTextSelection(_Out_ SupportedTextSelection* pRetVal)
{
	*pRetVal = SupportedTextSelection_Single;
	return S_OK;
}

// GetSelection: Retrieves a collection of text ranges that represents the currently selected text in a text-based control.
//				 For this control, the selection will either be a single text range or a degenerate text range.
//				 Note: A degenerate text range is an empty text range. Which means its EndPoint begin and end are equal.
IFACEMETHODIMP TextBoxProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	Range caretRange = m_control->GetSelectionRange();

	ITextRangeProvider* selectionRangeProvider = new TextBoxTextRange(m_control, caretRange);
	HRESULT hr = S_OK;
	if (selectionRangeProvider == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
		if (*pRetVal == NULL)
		{
			hr = E_OUTOFMEMORY;
		}
		else
		{
			long index = 0;
			hr = SafeArrayPutElement(*pRetVal, &index, selectionRangeProvider);
			if (FAILED(hr))
			{
				SafeArrayDestroy(*pRetVal);
				*pRetVal = NULL;
			}
		}
		selectionRangeProvider->Release();
	}

	return hr;
}

// GetVisibleRanges: Retrieves an array of disjoint text ranges from a text-based control where each text range represents a contiguous span of visible text.
IFACEMETHODIMP TextBoxProvider::GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	// Not Implemented yet.
	*pRetVal = NULL;
	return S_OK;
}

// RangeFromChild: Retrieves a text range enclosing a child element such as an image, hyperlink, or other embedded object. 
IFACEMETHODIMP TextBoxProvider::RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	UNREFERENCED_PARAMETER(childElement);
	// There are no children of this text control
	*pRetVal = NULL;
	return S_OK;
}

// RangeFromPoint: Returns the degenerate (empty) text range nearest to the specified screen coordinates.
IFACEMETHODIMP TextBoxProvider::RangeFromPoint(UiaPoint point, _Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	// Not implemented
	*pRetVal = nullptr;
	return E_NOTIMPL;
}

// get_DocumentRange: Retrieves a text range that encloses the main text of a document.
//		NOTE: When this function is implemented it is the reason that a screen reader will automatically
//			  read all text contained within the textbox. 
IFACEMETHODIMP TextBoxProvider::get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	// Get the full text range that encompasses the document. From the first character on the first line
	// all the way to the last character on the last line.
	Range fullDocumentRange = { { 0 }, m_control->GetTextboxEndpoint() };

	int point = m_control->GetTextboxEndpoint();

	*pRetVal = new TextBoxTextRange(m_control, fullDocumentRange);
	return (*pRetVal == NULL) ? E_OUTOFMEMORY : S_OK;

}

IFACEMETHODIMP TextBoxProvider::get_IsReadOnly(BOOL* returnValue)
{
#if LOG
	log("TextFieldProvider::get_IsReadOnly Start");
#endif

	// Currently hard-coded to false -- Quorum text fields can't be read-only in the current version.
	* returnValue = VARIANT_FALSE;

#if LOG
	log("TextFieldProvider::get_IsReadOnly Finish");
#endif

	return S_OK;
}

IFACEMETHODIMP TextBoxProvider::SetValue(LPCWSTR value)
{
#if LOG
	log("TextFieldProvider::SetValue Start");
#endif

	// NYI

#if LOG
	log("TextFieldProvider::SetValue Finish");
#endif

	return UIA_E_NOTSUPPORTED;
}

IFACEMETHODIMP TextBoxProvider::get_Value(BSTR* returnValue)
{
#if LOG
	log("TextFieldProvider::get_Value Start");
#endif

	std::wstring text = m_control->GetText();
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));

#if LOG
	log("TextFieldProvider::get_Value Finish");
#endif

	return S_OK;
}

