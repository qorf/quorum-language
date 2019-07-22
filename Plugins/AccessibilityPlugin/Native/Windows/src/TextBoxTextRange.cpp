#include <strsafe.h>

#include "TextBoxTextRange.h"
#include "TextBoxProvider.h"

//For debugging
#include <iostream>

TextBoxTextRange::TextBoxTextRange(_In_ HWND hwnd, _In_ TextBoxControl *control, _In_ Range range) : m_refCount(1), m_TextBoxControlHWND(hwnd), m_pTextBoxControl(control), m_range(range)
{

}

TextBoxTextRange::~TextBoxTextRange()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) TextBoxTextRange::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) TextBoxTextRange::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP TextBoxTextRange::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<ITextRangeProvider*>(this);
	}
	else if (riid == __uuidof(ITextRangeProvider))
	{
		*ppInterface = static_cast<ITextRangeProvider*>(this);
	}
	else if (riid == __uuidof(TextBoxTextRange))
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
IFACEMETHODIMP TextBoxTextRange::Clone(_Outptr_result_maybenull_ ITextRangeProvider ** pRetVal)
{
	HRESULT hr = S_OK;

	*pRetVal = new TextBoxTextRange(m_TextBoxControlHWND, m_pTextBoxControl, m_range);

	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	return hr;
}


// Compare: Retrieves a value that specifies whether this text range has the same endpoints as another text range.
IFACEMETHODIMP TextBoxTextRange::Compare(_In_opt_ ITextRangeProvider * range, _Out_ BOOL *pRetVal)
{
	*pRetVal = FALSE;
	if (range != NULL)
	{
		TextBoxTextRange *rangeInternal;
		if (SUCCEEDED(range->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
		{
			if (m_TextBoxControlHWND == rangeInternal->m_TextBoxControlHWND &&
				CompareEndpointPair(rangeInternal->m_range.begin, m_range.begin) == 0 &&
				CompareEndpointPair(rangeInternal->m_range.end, m_range.end) == 0)
			{
				*pRetVal = TRUE;
			}
			rangeInternal->Release();
		}
	}
	return S_OK;
}


// CompareEndpoints: Returns a value that specifies whether two text ranges have identical endpoints.
IFACEMETHODIMP TextBoxTextRange::CompareEndpoints(TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider *targetRange, _In_ TextPatternRangeEndpoint targetEndpoint, _Out_ int *pRetVal)
{
	if (targetRange == NULL)
	{
		return E_INVALIDARG;
	}

	TextBoxTextRange *rangeInternal;
	if (FAILED(targetRange->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
	{
		return E_INVALIDARG;
	}

	HRESULT hr = S_OK;
	if (m_TextBoxControlHWND != rangeInternal->m_TextBoxControlHWND)
	{
		hr = E_INVALIDARG;
	}
	else
	{
		Range target = rangeInternal->m_range;

		EndPoint thisEnd = (endpoint == TextPatternRangeEndpoint_Start) ? m_range.begin : m_range.end;
		EndPoint targetEnd = (targetEndpoint == TextPatternRangeEndpoint_Start) ? target.begin : target.end;

		*pRetVal = CompareEndpointPair(thisEnd, targetEnd);
	}
	rangeInternal->Release();


	return hr;
}

// ExpandToEnclosingUnit: Normalizes the text range by the specified text unit. The range is expanded if it is
//						  smaller than the specified unit, or shortened if it is longer than the specified unit.
IFACEMETHODIMP TextBoxTextRange::ExpandToEnclosingUnit(_In_ TextUnit unit)
{
	// This control supports the units:  TextUnit_Character, TextUnit_Format, TextUnit_Word, TextUnit_Paragraph, TextUnit_Document
	// This control does not support: TextUnit_Line, TextUnit_Page
	HRESULT hr = S_OK;
	if (unit == TextUnit_Character)
	{
		m_range.end = m_range.begin;
		m_range.end.character++;

		if (m_range.end.character > m_pTextBoxControl->GetLineLength())
		{
			m_range.end.character = m_pTextBoxControl->GetLineLength();
		}
	}
	else if (unit == TextUnit_Format || unit == TextUnit_Word)
	{
		int walked;
		m_range.begin = Walk(m_range.begin, false, unit, 0, 0, &walked);
		m_range.end = Walk(m_range.begin, true, unit, 0, 1, &walked);

		if (walked < 1)
		{
			m_range.begin = Walk(m_range.end, false, unit, 0, 1, &walked);
		}
	}
	else if (unit == TextUnit_Line || unit == TextUnit_Paragraph)
	{
		m_range.begin.character =  m_pTextBoxControl->GetIndexOfLine(m_pTextBoxControl->GetCaretLine());
		m_range.end.character = m_range.begin.character + m_pTextBoxControl->GetLineLength() - 1; // -1 for new line character which is skipped by screen readers
		//std::cout << "Range (" << m_range.begin.character << ", " << m_range.end.character << ")" << std::endl;
	}
	else if (unit == TextUnit_Page || unit == TextUnit_Document)
	{
		m_range.begin.character = 0;
		m_range.end = m_pTextBoxControl->GetTextboxEndpoint();
	}
	else
	{
		hr = E_INVALIDARG;
	}

	return hr;
}

// FindAttribute: Retrieves the value of the specified text attribute across the text range. For example, if the text is read-only.
IFACEMETHODIMP TextBoxTextRange::FindAttribute(_In_ TEXTATTRIBUTEID textAttributeId, _In_ VARIANT val, _In_ BOOL searchBackward, _Outptr_result_maybenull_ ITextRangeProvider **pRetVal)
{
	HRESULT hr = S_OK;
	*pRetVal = NULL;

	EndPoint start = searchBackward ? m_range.end : m_range.begin;
	EndPoint finish = searchBackward ? m_range.begin : m_range.end;
	EndPoint current = start;

	// This will loop until 'current' passes or is equal to the end
	while (CompareEndpointPair(searchBackward ? finish : current, searchBackward ? current : finish) < 0)
	{
		int walked;
		EndPoint next = Walk(current, !searchBackward, TextUnit_Format, textAttributeId, 1, &walked);
		VARIANT curValue = m_pTextBoxControl->GetAttributeAtPoint(searchBackward ? current : next, textAttributeId);

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

			*pRetVal = new TextBoxTextRange(m_TextBoxControlHWND, m_pTextBoxControl, found);

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
IFACEMETHODIMP TextBoxTextRange::FindText(_In_ BSTR text, BOOL searchBackward, BOOL ignoreCase, _Outptr_result_maybenull_ ITextRangeProvider **pRetVal)
{
	UNREFERENCED_PARAMETER(text);
	UNREFERENCED_PARAMETER(searchBackward);
	UNREFERENCED_PARAMETER(ignoreCase);

	// The FindText operation is not implemented yet.
	*pRetVal = NULL;
	return E_NOTIMPL;
}

// GetAttributeValue: Retrieves the value of the specified text attribute across the text range.
IFACEMETHODIMP TextBoxTextRange::GetAttributeValue(_In_ TEXTATTRIBUTEID textAttributeId, _Out_ VARIANT *pRetVal)
{
	HRESULT hr = S_OK;
	VariantInit(pRetVal);

	int walked;
	EndPoint endOfAttribute = Walk(m_range.begin, true, TextUnit_Format, textAttributeId, 1, &walked);

	if (CompareEndpointPair(endOfAttribute, m_range.end) < 0)
	{
		hr = UiaGetReservedMixedAttributeValue(&pRetVal->punkVal);
		if (SUCCEEDED(hr))
		{
			pRetVal->vt = VT_UNKNOWN;
		}
	}
	else
	{
		*pRetVal = m_pTextBoxControl->GetAttributeAtPoint(m_range.begin, textAttributeId);
	}

	return hr;
}

// GetBoundingRectangles: Retrieves a collection of bounding rectangles for each fully or partially visible line of text in a text range.
IFACEMETHODIMP TextBoxTextRange::GetBoundingRectangles(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	UNREFERENCED_PARAMETER(pRetVal);
	return E_NOTIMPL;
}

// GetEnclosingElement: Returns the innermost element that encloses the text range. The enclosing element is typically the text provider that supplies the text range.
//						However, if the text provider supports child elements such as tables or hyperlinks, the enclosing element could be a descendant of the text provider.
IFACEMETHODIMP TextBoxTextRange::GetEnclosingElement(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	*pRetVal = new TextBoxProvider(m_TextBoxControlHWND, m_pTextBoxControl);
	return (*pRetVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

// GetText: Retrieves the plain text of the range. That text is then given to the screen reader to be read aloud.
IFACEMETHODIMP TextBoxTextRange::GetText(_In_ int maxLength, _Out_ BSTR * pRetVal)
{
	UNREFERENCED_PARAMETER(maxLength);

	HRESULT hr = S_OK;

	std::wstring text = m_pTextBoxControl->GetText();
	int startPosition = m_range.begin.character;
	int length = m_range.end.character - m_range.begin.character;
	
	if (length <= 0)
		length = 1;

	*pRetVal = SysAllocString(text.substr(startPosition, length).c_str());

	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	return hr;
}

IFACEMETHODIMP TextBoxTextRange::Move(_In_ TextUnit unit, _In_ int count, _Out_ int *pRetVal)
{
	*pRetVal = 0;

	bool isDegenerate = (CompareEndpointPair(m_range.begin, m_range.end) == 0);

	int walked;
	EndPoint back = Walk(m_range.begin, false, unit, 0, 0, &walked);

	EndPoint destination = Walk(back, count > 0, unit, 0, abs(count), pRetVal);

	m_range.begin = destination;
	m_range.end = destination;

	if (!isDegenerate)
	{
		return ExpandToEnclosingUnit(unit);
	}

	return S_OK;
}


IFACEMETHODIMP TextBoxTextRange::MoveEndpointByUnit(_In_ TextPatternRangeEndpoint endpoint, _In_ TextUnit unit, _In_ int count, _Out_ int *pRetVal)
{
	*pRetVal = 0;

	if (endpoint == TextPatternRangeEndpoint_Start)
	{
		m_range.begin = Walk(m_range.begin, count > 0, unit, 0, abs(count), pRetVal);

		if (CompareEndpointPair(m_range.begin, m_range.end) > 0)
		{
			m_range.end = m_range.begin;
		}
	}
	else
	{
		m_range.end = Walk(m_range.end, count > 0, unit, 0, abs(count), pRetVal);

		if (CompareEndpointPair(m_range.begin, m_range.end) > 0)
		{
			m_range.begin = m_range.end;
		}
	}


	return S_OK;
}


IFACEMETHODIMP TextBoxTextRange::MoveEndpointByRange(_In_ TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider *targetRange, _In_ TextPatternRangeEndpoint targetEndpoint)
{
	if (targetRange == NULL)
	{
		return E_INVALIDARG;
	}

	TextBoxTextRange *rangeInternal;
	if (FAILED(targetRange->QueryInterface(IID_PPV_ARGS(&rangeInternal))))
	{
		return E_INVALIDARG;
	}

	HRESULT hr = S_OK;
	if (m_TextBoxControlHWND != rangeInternal->m_TextBoxControlHWND)
	{
		hr = E_INVALIDARG;
	}
	else
	{
		EndPoint src;
		if (targetEndpoint == TextPatternRangeEndpoint_Start)
		{
			src = rangeInternal->m_range.begin;
		}
		else
		{
			src = rangeInternal->m_range.end;
		}

		if (endpoint == TextPatternRangeEndpoint_Start)
		{
			m_range.begin = src;
			if (CompareEndpointPair(m_range.begin, m_range.end) > 0)
			{
				m_range.end = m_range.begin;
			}
		}
		else
		{
			m_range.end = src;
			if (CompareEndpointPair(m_range.begin, m_range.end) > 0)
			{
				m_range.begin = m_range.end;
			}
		}
	}
	rangeInternal->Release();
	return hr;
}

// This control does not support selection yet
IFACEMETHODIMP TextBoxTextRange::Select()
{
	return UIA_E_INVALIDOPERATION;
}


// This control does not support selection yet
IFACEMETHODIMP TextBoxTextRange::AddToSelection()
{
	return UIA_E_INVALIDOPERATION;
}


// This control does not support selection yet
IFACEMETHODIMP TextBoxTextRange::RemoveFromSelection()
{
	return UIA_E_INVALIDOPERATION;
}


IFACEMETHODIMP TextBoxTextRange::ScrollIntoView(_In_ BOOL alignToTop)
{
	// The ScrollInView operation is not implemented.
	UNREFERENCED_PARAMETER(alignToTop);
	return E_NOTIMPL;
}


IFACEMETHODIMP TextBoxTextRange::GetChildren(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	// No children
	*pRetVal = NULL;
	return S_OK;
}

bool TextBoxTextRange::CheckEndPointIsUnitEndpoint(_In_ EndPoint check, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute)
{
	UNREFERENCED_PARAMETER(specificAttribute);

	if (unit == TextUnit_Character)
	{
		return true;
	}

	EndPoint next;
	EndPoint prev;

	if (!m_pTextBoxControl->StepCharacter(check, true, &next) ||
		!m_pTextBoxControl->StepCharacter(check, false, &prev))
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

EndPoint TextBoxTextRange::Walk(_In_ EndPoint start, _In_ bool forward, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute, _In_ int count, _Out_ int *walked)
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
		if (!m_pTextBoxControl->StepCharacter(current, forward, &checkNext))
		{
			// We're at the beginning or end so stop now and return
			break;
		}

		do
		{
			if (walkUnit == TextUnit_Character)
			{
				EndPoint next;
				if (m_pTextBoxControl->StepCharacter(current, forward, &next))
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
					current = m_pTextBoxControl->GetTextboxEndpoint();
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

bool TextBoxTextRange::IsWhiteSpace(_In_ EndPoint check)
{
	if (check.character >= m_pTextBoxControl->GetLineLength())
	{
		return true;
	}

	std::wstring line = m_pTextBoxControl->GetText();
	
	int isSpace = iswspace(line[check.character]);
	return isSpace != 0;
}

