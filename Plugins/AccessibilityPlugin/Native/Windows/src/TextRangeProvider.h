#pragma once

#include <UIAutomation.h>

#include "TextControlBase.h"

class __declspec(uuid("c5e2bb94-1a47-41eb-ac0b-e48594676489"))
TextRangeProvider: public ITextRangeProvider
{
public:

	TextRangeProvider(_In_ TextControlBase* control, _In_ Range range);
	virtual ~TextRangeProvider();

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// ITextRangeProvider methods
	IFACEMETHODIMP Clone(_Outptr_result_maybenull_ ITextRangeProvider ** retVal);
	IFACEMETHODIMP Compare(_In_opt_ ITextRangeProvider * range, _Out_ BOOL *retVal);
	IFACEMETHODIMP CompareEndpoints(TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider *targetRange, _In_ TextPatternRangeEndpoint targetEndpoint, _Out_ int *retVal);
	IFACEMETHODIMP ExpandToEnclosingUnit(_In_ TextUnit unit);
	IFACEMETHODIMP FindAttribute(_In_ TEXTATTRIBUTEID textAttributeId, _In_ VARIANT val, _In_ BOOL searchBackward, _Outptr_result_maybenull_ ITextRangeProvider **retVal);
	IFACEMETHODIMP FindText(_In_ BSTR text, BOOL searchBackward, BOOL ignoreCase, _Outptr_result_maybenull_ ITextRangeProvider **retVal);
	IFACEMETHODIMP GetAttributeValue(_In_ TEXTATTRIBUTEID textAttributeId, _Out_ VARIANT *retVal);
	IFACEMETHODIMP GetBoundingRectangles(_Outptr_result_maybenull_ SAFEARRAY ** retVal);
	IFACEMETHODIMP GetEnclosingElement(_Outptr_result_maybenull_ IRawElementProviderSimple **retVal);
	IFACEMETHODIMP GetText(int maxLength, _Out_ BSTR* retVal) noexcept;
	IFACEMETHODIMP Move(_In_ TextUnit unit, _In_ int count, _Out_ int *retVal);
	IFACEMETHODIMP MoveEndpointByUnit(_In_ TextPatternRangeEndpoint endpoint, _In_ TextUnit unit, _In_ int count, _Out_ int *retVal);
	IFACEMETHODIMP MoveEndpointByRange(_In_ TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider *targetRange, _In_ TextPatternRangeEndpoint targetEndpoint);
	IFACEMETHODIMP Select();
	IFACEMETHODIMP AddToSelection();
	IFACEMETHODIMP RemoveFromSelection();
	IFACEMETHODIMP ScrollIntoView(_In_ BOOL alignToTop);
	IFACEMETHODIMP GetChildren(_Outptr_result_maybenull_ SAFEARRAY ** retVal);

private:

	// Helper functions for walking/searching
	bool StepCharacter(int start, bool forward, _Out_ int* end);
	bool CheckEndpointIsUnitEndpoint(_In_ int check, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute);
	int Walk(_In_ int start, _In_ bool forward, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute, _In_ int count, _Out_ int *walked);
	wil::unique_variant GetAttributeAtPoint(int start, TEXTATTRIBUTEID attribute);

	// Ref Counter for this COM object
	ULONG m_refCount;

	TextControlBase* m_control;
	Range m_range;
};
