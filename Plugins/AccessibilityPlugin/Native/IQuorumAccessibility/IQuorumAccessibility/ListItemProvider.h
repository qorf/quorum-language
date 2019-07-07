#pragma once
#include "ListItemControl.h"
#include "ListControl.h"
class ListItemControl;
class ListControl;

class ListItemProvider : public IRawElementProviderSimple,
					public IRawElementProviderFragment,
					public ISelectionItemProvider,
					public IValueProvider
{
public:
	ListItemProvider(ListItemControl* pControl, ListControl* parent);

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

	// Methods from IValueProvider.
	IFACEMETHODIMP get_IsReadOnly(BOOL* returnValue);
	IFACEMETHODIMP SetValue(LPCWSTR value);
	IFACEMETHODIMP get_Value(BSTR* returnValue);

	void NotifyElementSelected();
private:
	virtual ~ListItemProvider();

	// Ref Counter for this COM object
	ULONG referenceCount;
	ListControl* parent;

	//the control
	ListItemControl* control;

	IRawElementProviderFragment* child; //the provider for the child
};