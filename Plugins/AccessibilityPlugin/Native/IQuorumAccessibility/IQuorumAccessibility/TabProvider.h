#pragma once
#include "TabControl.h"
class TabControl;
class TabPaneControl;

class TabProvider : public IRawElementProviderSimple,
					public IRawElementProviderFragment,
					public ISelectionItemProvider
{
public:
	TabProvider(TabControl* pControl, TabPaneControl* parent);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions* pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal);

	// IRawElementProviderFragment methods
	IFACEMETHODIMP Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** pRetVal);
	IFACEMETHODIMP GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY** pRetVal);
	IFACEMETHODIMP get_BoundingRectangle(_Out_ UiaRect* pRetVal);
	IFACEMETHODIMP GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY** pRetVal);
	IFACEMETHODIMP SetFocus();
	IFACEMETHODIMP get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot** pRetVal);

	//ISelectionItemProvider
	//Adds the current element to the collection of selected items.
	IFACEMETHODIMP AddToSelection();
	IFACEMETHODIMP get_IsSelected(BOOL* pRetVal);
	IFACEMETHODIMP get_SelectionContainer(IRawElementProviderSimple** pRetVal);
	IFACEMETHODIMP RemoveFromSelection();
	IFACEMETHODIMP Select();
private:
	virtual ~TabProvider();

	// Ref Counter for this COM object
	ULONG referenceCount;
	TabPaneControl* parent;

	//the control
	TabControl* control;

	IRawElementProviderFragment* child; //the provider for the child
};