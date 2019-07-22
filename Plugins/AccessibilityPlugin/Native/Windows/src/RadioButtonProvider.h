
class RadioButtonControl;

class RadioButtonProvider : public IRawElementProviderSimple,
	public ISelectionItemProvider
{
public:
	RadioButtonProvider(HWND hwnd, RadioButtonControl* pButtonControl);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions * pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal);

	// ISelectionItemProvider methods
	IFACEMETHODIMP Select();
	IFACEMETHODIMP AddToSelection();
	IFACEMETHODIMP RemoveFromSelection();
	IFACEMETHODIMP get_IsSelected(_Out_ BOOL * pRetVal);
	IFACEMETHODIMP get_SelectionContainer(_Outptr_result_maybenull_ IRawElementProviderSimple **pRetVal);

	// Other methods
	void RadioButtonProvider::NotifyFocusGained(); // Fires a UI Automation Event when called.

private:
	virtual ~RadioButtonProvider();
	// Ref Counter for this COM object
	ULONG m_refCount;

	RadioButtonControl* m_pButtonControl;
	HWND m_buttonControlHWnd; // The HWND for the control.
};
