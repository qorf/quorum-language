#pragma once

class ItemControl;

class ItemProvider : public IRawElementProviderSimple
{
public:
	ItemProvider(ItemControl* pItem);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions * pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal);

	// Other methods
	void ItemProvider::NotifyFocusGained(); // Fires a UI Automation event when called.

private:
	virtual ~ItemProvider();
	ULONG m_refCount; // Ref Counter for this COM object
	
	ItemControl* m_pItem;
	
};
