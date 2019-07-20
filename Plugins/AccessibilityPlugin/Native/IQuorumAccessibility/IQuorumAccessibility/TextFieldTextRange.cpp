#include <strsafe.h>

#include "TextFieldTextRange.h"
#include "TextFieldProvider.h"

//For debugging
#include <iostream>

TextFieldTextRange::TextFieldTextRange(_In_ HWND hwnd, _In_ TextFieldControl* control, _In_ Range range) : referenceCount(1), myHWND(hwnd), textFieldControl(control), range(range)
{

}

TextFieldTextRange::~TextFieldTextRange()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) TextFieldTextRange::AddRef()
{
	return InterlockedIncrement(&referenceCount);
}

IFACEMETHODIMP_(ULONG) TextFieldTextRange::Release()
{
	long val = InterlockedDecrement(&referenceCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP TextFieldTextRange::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<ITextRangeProvider*>(this);
	}
	else if (riid == __uuidof(ITextRangeProvider))
	{
		*ppInterface = static_cast<ITextRangeProvider*>(this);
	}
	else if (riid == __uuidof(TextFieldTextRange))
	{
		*ppInterface = this;
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

// =========== ITextRangeProvider implementation.

// Clone: Returns a new ITextRangeProvider identical to the original ITextRangeProvider and inheriting all properties of the original.
IFACEMETHODIMP TextFieldTextRange::Clone(_Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	HRESULT hr = S_OK;

	*pRetVal = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, range);

	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	return hr;
}


// Compare: Retrieves a value that specifies whether this text range has the same endpoints as another text range.
IFACEMETHODIMP TextFieldTextRange::Compare(_In_opt_ ITextRangeProvider* rangeProvider, _Out_ BOOL* pRetVal)
{
	*pRetVal = FALSE;
	if (rangeProvider != NULL)
	{
		TextFieldTextRange* rangeInternal;
		if (SUCCEEDED(rangeProvider->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
		{
			if (textFieldControl->GetHWND() == rangeInternal->textFieldControl->GetHWND() &&
				CompareEndpointPair(rangeInternal->range.begin, range.begin) == 0 &&
				CompareEndpointPair(rangeInternal->range.end, range.end) == 0)
			{
				*pRetVal = TRUE;
			}
			rangeInternal->Release();
		}
	}
	return S_OK;
}


// CompareEndpoints: Returns a value that specifies whether two text ranges have identical endpoints.
IFACEMETHODIMP TextFieldTextRange::CompareEndpoints(TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider* targetRange, _In_ TextPatternRangeEndpoint targetEndpoint, _Out_ int* pRetVal)
{
	if (targetRange == NULL)
	{
		return E_INVALIDARG;
	}

	TextFieldTextRange* rangeInternal;
	if (FAILED(targetRange->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
	{
		return E_INVALIDARG;
	}

	HRESULT hr = S_OK;
	if (textFieldControl->GetHWND() != rangeInternal->textFieldControl->GetHWND())
	{
		hr = E_INVALIDARG;
	}
	else
	{
		Range target = rangeInternal->range;

		EndPoint thisEnd = (endpoint == TextPatternRangeEndpoint_Start) ? range.begin : range.end;
		EndPoint targetEnd = (targetEndpoint == TextPatternRangeEndpoint_Start) ? target.begin : target.end;

		*pRetVal = CompareEndpointPair(thisEnd, targetEnd);
	}
	rangeInternal->Release();


	return hr;
}

// ExpandToEnclosingUnit: Normalizes the text range by the specified text unit. The range is expanded if it is
//						  smaller than the specified unit, or shortened if it is longer than the specified unit.
IFACEMETHODIMP TextFieldTextRange::ExpandToEnclosingUnit(_In_ TextUnit unit)
{
	// This control supports the units:  TextUnit_Character, TextUnit_Format, TextUnit_Word, TextUnit_Paragraph, TextUnit_Document
	// This control does not support: TextUnit_Line, TextUnit_Page
	HRESULT hr = S_OK;
	if (unit == TextUnit_Character)
	{
		range.end = range.begin;
		range.end.character++;

		int size = textFieldControl->GetSize();

		if (range.end.character > size)
		{
			range.end.character = size;
		}
	}
	else if (unit == TextUnit_Format || unit == TextUnit_Word)
	{
		int walked;
		range.begin = Walk(range.begin, false, unit, 0, 0, &walked);
		range.end = Walk(range.begin, true, unit, 0, 1, &walked);

		if (walked < 1)
		{
			range.begin = Walk(range.end, false, unit, 0, 1, &walked);
		}
	}
	else if (unit == TextUnit_Line || unit == TextUnit_Paragraph || unit == TextUnit_Page || unit == TextUnit_Document)
	{
		range.begin.character = 0;
		range.end.character = textFieldControl->GetSize();
	}
	else
	{
		hr = E_INVALIDARG;
	}

	return hr;
}

// FindAttribute: Retrieves the value of the specified text attribute across the text range. For example, if the text is read-only.
IFACEMETHODIMP TextFieldTextRange::FindAttribute(_In_ TEXTATTRIBUTEID textAttributeId, _In_ VARIANT val, _In_ BOOL searchBackward, _Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	HRESULT hr = S_OK;
	*pRetVal = NULL;

	EndPoint start = searchBackward ? range.end : range.begin;
	EndPoint finish = searchBackward ? range.begin : range.end;
	EndPoint current = start;

	// This will loop until 'current' passes or is equal to the end
	while (CompareEndpointPair(searchBackward ? finish : current, searchBackward ? current : finish) < 0)
	{
		int walked;
		EndPoint next = Walk(current, !searchBackward, TextUnit_Format, textAttributeId, 1, &walked);
		VARIANT curValue = textFieldControl->GetAttributeAtPoint(searchBackward ? current : next, textAttributeId);

		hr = VarCmp(&val, &curValue, LOCALE_NEUTRAL);

		if (hr == VARCMP_EQ)
		{
			Range found;
			found.begin = searchBackward ? next : current;

			// If next is past the end of the current range, end at the end of the current range instead
			if (CompareEndpointPair(searchBackward ? finish : next, searchBackward ? next : finish) > 0)
			{
				found.end = finish;
			}
			else
			{
				found.end = searchBackward ? current : next;
			}

			*pRetVal = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, found);

			if (*pRetVal == NULL)
			{
				hr = E_OUTOFMEMORY;
			}
			break;
		}

		current = next;
	}

	return hr;
}

// FindText: Returns a text range subset that contains the specified text.
IFACEMETHODIMP TextFieldTextRange::FindText(_In_ BSTR text, BOOL searchBackward, BOOL ignoreCase, _Outptr_result_maybenull_ ITextRangeProvider** pRetVal)
{
	UNREFERENCED_PARAMETER(text);
	UNREFERENCED_PARAMETER(searchBackward);
	UNREFERENCED_PARAMETER(ignoreCase);

	// The FindText operation is not implemented yet.
	*pRetVal = NULL;
	return E_NOTIMPL;
}

// GetAttributeValue: Retrieves the value of the specified text attribute across the text range.
IFACEMETHODIMP TextFieldTextRange::GetAttributeValue(_In_ TEXTATTRIBUTEID textAttributeId, _Out_ VARIANT* pRetVal)
{
	HRESULT hr = S_OK;
	VariantInit(pRetVal);

	int walked;
	EndPoint endOfAttribute = Walk(range.begin, true, TextUnit_Format, textAttributeId, 1, &walked);

	if (CompareEndpointPair(endOfAttribute, range.end) < 0)
	{
		hr = UiaGetReservedMixedAttributeValue(&pRetVal->punkVal);
		if (SUCCEEDED(hr))
		{
			pRetVal->vt = VT_UNKNOWN;
		}
	}
	else
	{
		*pRetVal = textFieldControl->GetAttributeAtPoint(range.begin, textAttributeId);
	}

	return hr;
}

// GetBoundingRectangles: Retrieves a collection of bounding rectangles for each fully or partially visible line of text in a text range.
IFACEMETHODIMP TextFieldTextRange::GetBoundingRectangles(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	UNREFERENCED_PARAMETER(pRetVal);
	return E_NOTIMPL;
}

// GetEnclosingElement: Returns the innermost element that encloses the text range. The enclosing element is typically the text provider that supplies the text range.
//						However, if the text provider supports child elements such as tables or hyperlinks, the enclosing element could be a descendant of the text provider.
IFACEMETHODIMP TextFieldTextRange::GetEnclosingElement(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	*pRetVal = new TextFieldProvider(textFieldControl);
	return (*pRetVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

// GetText: Retrieves the plain text of the range. That text is then given to the screen reader to be read aloud.
IFACEMETHODIMP TextFieldTextRange::GetText(_In_ int maxLength, _Out_ BSTR* pRetVal)
{
	UNREFERENCED_PARAMETER(maxLength);

	HRESULT hr = S_OK;

	std::wstring text = textFieldControl->GetText();
	int startPosition = range.begin.character;
	int length = range.end.character - range.begin.character;

	if (length <= 0)
		length = 1;

	*pRetVal = SysAllocString(text.substr(startPosition, length).c_str());

	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	return hr;
}

IFACEMETHODIMP TextFieldTextRange::Move(_In_ TextUnit unit, _In_ int count, _Out_ int* pRetVal)
{
	*pRetVal = 0;

	bool isDegenerate = (CompareEndpointPair(range.begin, range.end) == 0);

	int walked;
	EndPoint back = Walk(range.begin, false, unit, 0, 0, &walked);

	EndPoint destination = Walk(back, count > 0, unit, 0, abs(count), pRetVal);

	range.begin = destination;
	range.end = destination;

	if (!isDegenerate)
	{
		return ExpandToEnclosingUnit(unit);
	}

	return S_OK;
}


IFACEMETHODIMP TextFieldTextRange::MoveEndpointByUnit(_In_ TextPatternRangeEndpoint endpoint, _In_ TextUnit unit, _In_ int count, _Out_ int* pRetVal)
{
	*pRetVal = 0;

	if (endpoint == TextPatternRangeEndpoint_Start)
	{
		range.begin = Walk(range.begin, count > 0, unit, 0, abs(count), pRetVal);

		if (CompareEndpointPair(range.begin, range.end) > 0)
		{
			range.end = range.begin;
		}
	}
	else
	{
		range.end = Walk(range.end, count > 0, unit, 0, abs(count), pRetVal);

		if (CompareEndpointPair(range.begin, range.end) > 0)
		{
			range.begin = range.end;
		}
	}


	return S_OK;
}


IFACEMETHODIMP TextFieldTextRange::MoveEndpointByRange(_In_ TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider* targetRange, _In_ TextPatternRangeEndpoint targetEndpoint)
{
	if (targetRange == NULL)
	{
		return E_INVALIDARG;
	}

	TextFieldTextRange* rangeInternal;
	if (FAILED(targetRange->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
	{
		return E_INVALIDARG;
	}

	HRESULT hr = S_OK;
	if (textFieldControl->GetHWND() != rangeInternal->textFieldControl->GetHWND())
	{
		hr = E_INVALIDARG;
	}
	else
	{
		EndPoint src;
		if (targetEndpoint == TextPatternRangeEndpoint_Start)
		{
			src = rangeInternal->range.begin;
		}
		else
		{
			src = rangeInternal->range.end;
		}

		if (endpoint == TextPatternRangeEndpoint_Start)
		{
			range.begin = src;
			if (CompareEndpointPair(range.begin, range.end) > 0)
			{
				range.end = range.begin;
			}
		}
		else
		{
			range.end = src;
			if (CompareEndpointPair(range.begin, range.end) > 0)
			{
				range.begin = range.end;
			}
		}
	}
	rangeInternal->Release();
	return hr;
}

// This control does not support selection yet
IFACEMETHODIMP TextFieldTextRange::Select()
{
	return UIA_E_INVALIDOPERATION;
}


// This control does not support selection yet
IFACEMETHODIMP TextFieldTextRange::AddToSelection()
{
	return UIA_E_INVALIDOPERATION;
}


// This control does not support selection yet
IFACEMETHODIMP TextFieldTextRange::RemoveFromSelection()
{
	return UIA_E_INVALIDOPERATION;
}


IFACEMETHODIMP TextFieldTextRange::ScrollIntoView(_In_ BOOL alignToTop)
{
	// The ScrollInView operation is not implemented.
	UNREFERENCED_PARAMETER(alignToTop);
	return E_NOTIMPL;
}


IFACEMETHODIMP TextFieldTextRange::GetChildren(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	// No children
	*pRetVal = NULL;
	return S_OK;
}

bool TextFieldTextRange::CheckEndPointIsUnitEndpoint(_In_ EndPoint check, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute)
{
	UNREFERENCED_PARAMETER(specificAttribute);

	if (unit == TextUnit_Character)
	{
		return true;
	}

	EndPoint next;
	EndPoint prev;

	if (!textFieldControl->StepCharacter(check, true, &next) ||
		!textFieldControl->StepCharacter(check, false, &prev))
	{
		// If we're at the beginning or end, we're at an endpoint
		return true;
	}

	else if (unit == TextUnit_Word)
	{
		if (IsWhiteSpace(prev) && !IsWhiteSpace(check))
		{
			return true;
		}
		return false;
	}

	else if (unit == TextUnit_Line || unit == TextUnit_Paragraph)
	{
		return true;
	}

	// TextUnit_Page and TextUnit_Document are covered by the initial beginning/end check
	else if (unit == TextUnit_Page || unit == TextUnit_Document)
	{
		return false;
	}

	else if (unit == TextUnit_Format)
	{
		// TextUnit_Format isn't implemented since the textbox control won't contain formatted text.
		// At some point it may be possible to implement TextUnit_Format for code highlighting.
		// For now, if the unit comes in as TextUnit_Format then we say that the EndPoint is a UnitEndPoint.
		return true;
	}

	return false;
}

EndPoint TextFieldTextRange::Walk(_In_ EndPoint start, _In_ bool forward, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute, _In_ int count, _Out_ int* walked)
{
	*walked = 0;

	// Use count of zero to normalize
	if (count == 0)
	{
		if (CheckEndPointIsUnitEndpoint(start, unit, specificAttribute))
		{
			return start;
		}
		count = 1;
	}

	TextUnit walkUnit = unit;
	if (unit == TextUnit_Word)
	{
		walkUnit = TextUnit_Character;
	}

	if (unit == TextUnit_Line)
	{
		walkUnit = TextUnit_Paragraph;
	}

	if (unit == TextUnit_Page)
	{
		walkUnit = TextUnit_Document;
	}

	if (unit == TextUnit_Format)
	{
		// TextUnit_Format is not implemented. So if it's encountered then the walkUnit is TextUnit_Character.
		walkUnit = TextUnit_Character;
	}

	EndPoint current = start;
	for (int i = 0; i < count; i++)
	{
		EndPoint checkNext;
		if (!textFieldControl->StepCharacter(current, forward, &checkNext))
		{
			// We're at the beginning or end so stop now and return
			break;
		}

		do
		{
			if (walkUnit == TextUnit_Character)
			{
				EndPoint next;
				if (textFieldControl->StepCharacter(current, forward, &next))
				{
					current = next;
				}
			}
			else if (walkUnit == TextUnit_Paragraph)
			{
				// TODO: Implement this
			}
			else if (walkUnit == TextUnit_Document)
			{
				if (forward)
				{
					current = textFieldControl->GetTextFieldEndpoint();
				}
				else
				{
					current.character = 0;
				}
			}
		} while (!CheckEndPointIsUnitEndpoint(current, unit, specificAttribute));
		(*walked)++;
	}

	// When walking backwards, clients expect negative values for distance walked
	if (!forward)
	{
		*walked = -*walked;
	}

	return current;
}

bool TextFieldTextRange::IsWhiteSpace(_In_ EndPoint check)
{
	if (check.character >= textFieldControl->GetSize())
	{
		return true;
	}

	std::wstring line = textFieldControl->GetText();

	int isSpace = iswspace(line[check.character]);
	return isSpace != 0;
}

