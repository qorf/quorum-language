
class CheckBoxControl;

class CheckBoxProvider : public IRawElementProviderSimple,
	public IToggleProvider
{
public:
	CheckBoxProvider(HWND hwnd, CheckBoxControl* pButtonControl);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions * pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal);

	// IToggleProvider methods
	IFACEMETHODIMP Toggle();
	IFACEMETHODIMP get_ToggleState(_Out_ ToggleState *pRetVal);

	// Other methods
	void CheckBoxProvider::NotifyFocusGained(); // Fires a UI Automation event when this is called.

private:
	virtual ~CheckBoxProvider();
	// Ref Counter for this COM object
	ULONG m_refCount;

	CheckBoxControl* m_pButtonControl;
	HWND m_buttonControlHWnd; // The HWND for the control.
};
