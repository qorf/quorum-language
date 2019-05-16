#include <windows.h>
#include <UIAutomation.h>

class TextFieldControl;

class TextFieldProvider : public IRawElementProviderSimple, public ITextProvider//, public IValueProvider
{
	public:
		TextFieldProvider(TextFieldControl* parentControl);
		void NotifyFocusGained();

		// Methods from IUnknown.
		IFACEMETHODIMP_(ULONG) AddRef();
		IFACEMETHODIMP_(ULONG) Release();
		IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

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

		// Inherited via IRawElementProviderSimple
		virtual HRESULT __stdcall get_ProviderOptions(ProviderOptions* pRetVal) override;
		virtual HRESULT __stdcall GetPatternProvider(PATTERNID patternId, IUnknown** pRetVal) override;
		virtual HRESULT __stdcall GetPropertyValue(PROPERTYID propertyId, VARIANT* pRetVal) override;
		virtual HRESULT __stdcall get_HostRawElementProvider(IRawElementProviderSimple** pRetVal) override;
	private:
		virtual ~TextFieldProvider();
		// Reference Counter for this COM object
		ULONG referenceCount;

		TextFieldControl* textFieldControl;
};
