#pragma once

class ButtonControl;

class ButtonProvider : public IRawElementProviderSimple,
					   public IInvokeProvider
{
public:
	ButtonProvider(ButtonControl* pButtonControl);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions * pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal);

	// IInvokeProvider methods
	IFACEMETHODIMP Invoke();

	// Other methods
	void ButtonProvider::NotifyFocusGained(); // Fires a UI Automation Event when called.

private:
	virtual ~ButtonProvider();
	// Ref Counter for this COM object
	ULONG m_refCount;

	ButtonControl* m_pButtonControl;
};
