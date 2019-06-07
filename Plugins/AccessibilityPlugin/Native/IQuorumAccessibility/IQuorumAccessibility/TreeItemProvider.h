#pragma once

class TreeItemControl;
class TreeControl;

class TreeItemProvider : public ISelectionItemProvider,
						 public IRawElementProviderSimple,
						 public IRawElementProviderFragment,
						 public IExpandCollapseProvider
{
public:
	TreeItemProvider(TreeItemControl* pControl);

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

	// IExpandCollapseProvider methods
	IFACEMETHODIMP get_ExpandCollapseState(_Out_ ExpandCollapseState *pRetVal);
	IFACEMETHODIMP Expand();
	IFACEMETHODIMP Collapse();

	// Various methods
	void NotifyTreeItemAdded();
	void NotifyTreeItemRemoved();
	void NotifyElementSelected();
	IUnknown* GetParentProvider();

	// Inherited via ISelectionItemProvider
	virtual HRESULT __stdcall Select(void) override;
	virtual HRESULT __stdcall AddToSelection(void) override;
	virtual HRESULT __stdcall RemoveFromSelection(void) override;
	virtual HRESULT __stdcall get_IsSelected(BOOL* pRetVal) override;
	virtual HRESULT __stdcall get_SelectionContainer(IRawElementProviderSimple** pRetVal) override;

private:
	virtual ~TreeItemProvider();

	void NotifyElementExpandCollapse();

	// Ref Counter for this COM object
	ULONG m_refCount;

	// The MenuItem
	TreeItemControl* m_pTreeItemControl;

	ExpandCollapseState m_expandCollapseState;

};