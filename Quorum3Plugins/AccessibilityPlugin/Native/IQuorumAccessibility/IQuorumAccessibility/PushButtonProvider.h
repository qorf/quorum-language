
class PushButtonControl;

class PushButtonProvider : public IRawElementProviderSimple,
	public IInvokeProvider
{
public:
	PushButtonProvider(HWND hwnd, PushButtonControl* pButtonControl);

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
	void PushButtonProvider::NotifyFocusGained(); // Fires a UI Automation Event when called.

private:
	virtual ~PushButtonProvider();
	// Ref Counter for this COM object
	ULONG m_refCount;

	PushButtonControl* m_pButtonControl;
	HWND m_buttonControlHWnd; // The HWND for the control.
};
