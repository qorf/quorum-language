class TreeControl;

class TreeProvider :	public ISelectionProvider,
						public IRawElementProviderSimple,
						public IRawElementProviderFragment,
						public IRawElementProviderFragmentRoot
{
public:
	TreeProvider(_In_ TreeControl* pMenuBarControl);
	
	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

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

	// ISelectionProvider methods
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal) override;
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal) override;
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal) override;

private:
	virtual ~TreeProvider();
	ULONG m_refCount;

	TreeControl* m_pTreeControl;

};
