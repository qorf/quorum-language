#include "TextFieldProvider.h"
#include "TextFieldControl.h"
#include "TextFieldTextRange.h"

TextFieldProvider::TextFieldProvider(TextFieldControl* control) : ProviderT(control)
{
	// No further work necessary -- we've already set the fields we care about in the constructor header above.
}

void TextFieldProvider::NotifyFocusGained()
{
	#if LOG
	log("TextFieldProvider::NotifyFocusGained Start");
	#endif

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}

	#if LOG
	log("TextFieldProvider::NotifyFocusGained Finish");
	#endif
}

// =========== Overridden ProviderT functions

bool TextFieldProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_TextPatternId || patternId == UIA_ValuePatternId);
}

CONTROLTYPEID TextFieldProvider::GetControlType() const noexcept
{
	return UIA_DocumentControlTypeId;
}

// ===========

IFACEMETHODIMP TextFieldProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal)
{
	#if LOG
	log("TextFieldProvider::GetSelection Start");
	#endif

	Range caretRange = m_control->GetSelectionRange();


	ITextRangeProvider* selectionRangeProvider = new TextFieldTextRange(m_control, caretRange);
	HRESULT hr = S_OK;
	if (selectionRangeProvider == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		*retVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
		if (*retVal == NULL)
		{
			hr = E_OUTOFMEMORY;
		}
		else
		{
			long index = 0;
			hr = SafeArrayPutElement(*retVal, &index, selectionRangeProvider);
			if (FAILED(hr))
			{
				SafeArrayDestroy(*retVal);
				*retVal = NULL;
			}
		}
		selectionRangeProvider->Release();
	}

	#if LOG
	log("TextFieldProvider::GetSelection Finish");
	#endif

	return hr;
}

IFACEMETHODIMP TextFieldProvider::GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal)
{
	#if LOG
	log("TextFieldProvider::GetVisibleRanges Start");
	#endif

	// Not Implemented yet.
	*retVal = NULL;

	#if LOG
	log("TextFieldProvider::GetVisibleRanges Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::RangeFromChild Start");
	#endif

	UNREFERENCED_PARAMETER(childElement);

	// There are no children of this text control
	*retVal = NULL;

	#if LOG
	log("TextFieldProvider::RangeFromChild Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::RangeFromPoint Start");
	#endif

	/*
	*	Since the textfield control is a 1x1 pixel box the given UiaPoint isn't going to be correct. So if UIA calls this
	*	function then we'll report the caret position as the closest RangeFromPoint since Quorum handles mouse events.
	*/
	UNREFERENCED_PARAMETER(screenLocation); // This will never be used. Instead we get the point from Quorum.


	int caretPosition = m_control->GetCaretPosition();
	Range closestRange(caretPosition, caretPosition);

	*retVal = new TextFieldTextRange(m_control, closestRange);

	#if LOG
	log("TextFieldProvider::RangeFromPoint Finish");
	#endif

	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::get_DocumentRange Start");
	#endif

	// Get the full text range that encompasses the document. From the first character on the first line
	// all the way to the last character on the last line.
	Range fullDocumentRange = { { 0 }, m_control->GetTextFieldEndpoint() };

	*retVal = new TextFieldTextRange(m_control, fullDocumentRange);

	#if LOG
	log("TextFieldProvider::get_DocumentRange Finish");
	#endif

	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal)
{
	#if LOG
	log("TextFieldProvider::get_SupportedTextSelection Start");
	#endif

	#if LOG
	log("TextFieldProvider::get_SupportedTextSelection Finish");
	#endif

	*retVal = SupportedTextSelection_Single;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_IsReadOnly(BOOL* returnValue)
{
	#if LOG
	log("TextFieldProvider::get_IsReadOnly Start");
	#endif

	// Currently hard-coded to false -- Quorum text fields can't be read-only in the current version.
	*returnValue = VARIANT_FALSE;

	#if LOG
	log("TextFieldProvider::get_IsReadOnly Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::SetValue(LPCWSTR value)
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

IFACEMETHODIMP TextFieldProvider::get_Value(BSTR* returnValue)
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
