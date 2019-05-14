#pragma once
#include "TextBoxControl.h"

// Used to create Int SafeArray for RuntimeId
SAFEARRAY* BuildIntSafeArray(_In_reads_(length) const int * data, _In_ int length);

class TextBoxProvider : public IRawElementProviderSimple,
						public IRawElementProviderFragment, public IRawElementProviderFragmentRoot
{
public:
	TextBoxProvider(_In_ HWND hwnd, _In_ TextBoxControl *control);
	virtual ~TextBoxProvider();

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void**ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions * pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal);

	// IRawElementProviderFragment methods
	IFACEMETHODIMP Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal);
	IFACEMETHODIMP GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal);
	IFACEMETHODIMP get_BoundingRectangle(_Out_ UiaRect * pRetVal);
	IFACEMETHODIMP GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal);
	IFACEMETHODIMP SetFocus();
	IFACEMETHODIMP get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot ** pRetVal);

	// IRawElementProviderFragmentRoot methods
	IFACEMETHODIMP ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal);
	IFACEMETHODIMP GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal);

private:
	
	// Ref Counter for this COM object
	ULONG m_refCount;
	HWND m_textBoxControlHWND; // The HWND for the TextBox Control associated with this provider
	TextBoxControl* m_pTextBoxControl; // A pointer to the TextBox Control associated with this provider
};