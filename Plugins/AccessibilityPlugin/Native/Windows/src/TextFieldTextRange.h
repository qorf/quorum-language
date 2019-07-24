#pragma once

#include <UIAutomation.h>

#include "TextFieldControl.h"

// This uuid value is used to retrieve a unique reference to this class at runtime.
// It was generated using the GUIDGEN tool.
class __declspec(uuid("f0170999-0e2f-4baf-a90a-3c06df7cbd53"))
TextFieldTextRange : public ITextRangeProvider
{
public:

	TextFieldTextRange(_In_ TextFieldControl* control, _In_ Range range);
	virtual ~TextFieldTextRange();

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// ITextRangeProvider methods
	IFACEMETHODIMP Clone(_Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP Compare(_In_opt_ ITextRangeProvider* range, _Out_ BOOL* retVal);
	IFACEMETHODIMP CompareEndpoints(TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider* targetRange, _In_ TextPatternRangeEndpoint targetEndpoint, _Out_ int* retVal);
	IFACEMETHODIMP ExpandToEnclosingUnit(_In_ TextUnit unit);
	IFACEMETHODIMP FindAttribute(_In_ TEXTATTRIBUTEID textAttributeId, _In_ VARIANT val, _In_ BOOL searchBackward, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP FindText(_In_ BSTR text, BOOL searchBackward, BOOL ignoreCase, _Outptr_result_maybenull_ ITextRangeProvider** retVal);
	IFACEMETHODIMP GetAttributeValue(_In_ TEXTATTRIBUTEID textAttributeId, _Out_ VARIANT* retVal);
	IFACEMETHODIMP GetBoundingRectangles(_Outptr_result_maybenull_ SAFEARRAY** retVal);
	IFACEMETHODIMP GetEnclosingElement(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal);
	IFACEMETHODIMP GetText(_In_ int maxLength, _Out_ BSTR* retVal);
	IFACEMETHODIMP Move(_In_ TextUnit unit, _In_ int count, _Out_ int* retVal);
	IFACEMETHODIMP MoveEndpointByUnit(_In_ TextPatternRangeEndpoint endpoint, _In_ TextUnit unit, _In_ int count, _Out_ int* retVal);
	IFACEMETHODIMP MoveEndpointByRange(_In_ TextPatternRangeEndpoint endpoint, _In_opt_ ITextRangeProvider* targetRange, _In_ TextPatternRangeEndpoint targetEndpoint);
	IFACEMETHODIMP Select();
	IFACEMETHODIMP AddToSelection();
	IFACEMETHODIMP RemoveFromSelection();
	IFACEMETHODIMP ScrollIntoView(_In_ BOOL alignToTop);
	IFACEMETHODIMP GetChildren(_Outptr_result_maybenull_ SAFEARRAY** retVal);

private:

	// Helper functions for walking/searching
	bool CheckEndPointIsUnitEndpoint(_In_ EndPoint check, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute);
	EndPoint Walk(_In_ EndPoint start, _In_ bool forward, _In_ TextUnit unit, _In_ TEXTATTRIBUTEID specificAttribute, _In_ int count, _Out_ int* walked);
	bool IsWhiteSpace(_In_ EndPoint check);

	// Ref Counter for this COM object
	ULONG referenceCount;

	TextFieldControl* textFieldControl;		// The control object that this UIA object is referring to
	Range range;							// The range for this instance of TextAreaTextRange
};
