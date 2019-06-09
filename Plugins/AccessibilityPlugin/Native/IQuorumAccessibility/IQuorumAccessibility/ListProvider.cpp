#include "ListProvider.h"

ListProvider::ListProvider(_In_ ListControl* control) : m_refCount(1), control(control)
{
}

ListProvider::~ListProvider()
{
}
// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ListProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) ListProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP ListProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderFragment))
	{
		*ppInterface = static_cast<IRawElementProviderFragment*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderFragmentRoot))
	{
		*ppInterface = static_cast<IRawElementProviderFragmentRoot*>(this);
	}
	else if (riid == __uuidof(ISelectionProvider))
	{
		*ppInterface = static_cast<ISelectionProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	if (*ppInterface != NULL) {
		(static_cast<IUnknown*>(*ppInterface))->AddRef();
	}
	return S_OK;
}

// Gets UI Automation provider options.
IFACEMETHODIMP ListProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// The Tree doesn't support any patterns so NULL is correct.
IFACEMETHODIMP ListProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	switch (patternId)
	{
	case UIA_SelectionPatternId:
		*pRetVal = static_cast<ISelectionProvider*>(this);
		break;
	default:
		*pRetVal = NULL;
	}

	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}
	return S_OK;
}

// Gets the custom properties for this control.
IFACEMETHODIMP ListProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = control->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->control->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_ListControlTypeId;
	}
	else if (propertyId == UIA_IsContentElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
		// This tells the screen reader whether or not the control can be interacted with.
		// Hardcoded to true but this property could be dynamic depending on the needs of the Quorum GUI.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_LabeledByPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->control->GetName());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"list");
	}
	else if (propertyId == UIA_OrientationPropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->boolVal = OrientationType_Horizontal;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
	}

	return S_OK;
}

IFACEMETHODIMP ListProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
}

// Enables UI Automation to locate the element in the tree.
// Navigation to the parent is handled by the host window provider.
IFACEMETHODIMP ListProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	/*TreeControl* pTreeControl = this->m_pTreeControl;
	TreeItemControl* pTreeItem = NULL;
	IRawElementProviderFragment* pFragment = NULL;
	TREEITEM_ITERATOR iter;

	switch (direction)
	{
	case NavigateDirection_FirstChild:
	{
		if (pTreeControl->HasChildren())
		{
			iter = pTreeControl->GetTreeItemAt(0);
			pTreeItem = static_cast<TreeItemControl*>(*iter);
			pFragment = (IRawElementProviderFragment*)pTreeItem->GetTreeItemProvider();
		}
		break;
	}
	case NavigateDirection_LastChild:
	{
		if (pTreeControl->HasChildren())
		{
			iter = pTreeControl->GetTreeItemAt(pTreeControl->GetCount() - 1);
			pTreeItem = static_cast<TreeItemControl*>(*iter);
			pFragment = (IRawElementProviderFragment*)pTreeItem->GetTreeItemProvider();
		}
		break;
	}
	}
	if (pFragment != NULL)
	{
		pFragment->AddRef();
	}
	*pRetVal = pFragment;*/
	* pRetVal = NULL;
	return S_OK;
}

// UI Automation gets this value from the host window provider, so NULL is correct here.
IFACEMETHODIMP ListProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

IFACEMETHODIMP ListProvider::get_BoundingRectangle(_Out_ UiaRect* pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// For now we aren't painting a rectangle for the provider
	// that'd require more info from Quorum.
	pRetVal->left = 0;
	pRetVal->top = 0;
	pRetVal->width = 0;
	pRetVal->height = 0;
	return S_OK;
}

// Retreives other fragment roots that may be hosted in this one.
IFACEMETHODIMP ListProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
// For HWND-based controls, this is handled by the host window provider.
IFACEMETHODIMP ListProvider::SetFocus()
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	return S_OK;
}

// Retrieves the root element of this fragment.
IFACEMETHODIMP ListProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// A provider for a fragment root should return a pointer to its own implementation.
	*pRetVal = static_cast<IRawElementProviderFragmentRoot*>(this);
	AddRef();
	return S_OK;
}

// Retrieves the IRawElementProviderFragment interface for the item at the specified 
// point (in client coordinates).
IFACEMETHODIMP ListProvider::ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// Since the accessible objects are 1x1 pixel boxes hidden in the corner of the application we'd need quorum to
	// give us the client coordinates.
	// Not implemented yet
	*pRetVal = NULL;
	return S_OK;
}

// Retrieves the provider for the tree item that is selected when the control gets focus.
IFACEMETHODIMP ListProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** pRetVal)
{
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;

	ListItemControl* tab = control->GetSelected();
	if (tab != nullptr)
	{
		// Get Provider
		IRawElementProviderFragment* provider = (IRawElementProviderFragment*)(tab->GetProvider());

		*pRetVal = provider;
	}

	return S_OK;
}

//ISelectionProvider
IFACEMETHODIMP ListProvider::get_CanSelectMultiple(BOOL* pRetVal) {
	*pRetVal = false;
	return S_OK;
}

IFACEMETHODIMP ListProvider::get_IsSelectionRequired(BOOL* pRetVal) {
	*pRetVal = true;
	return S_OK;
}

IFACEMETHODIMP ListProvider::GetSelection(SAFEARRAY** pRetVal) {
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	//JNIEnv* env = GetJNIEnv();
	//long selectionPointer = env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTabPaneSelectionPointer, control->GetMe());

	//if (selectionPointer == 0)
	//{
	//	*pRetVal = NULL;
	//	return S_OK;
	//}

	//ListItemControl* theControl = static_cast<ListItemControl*>(LongToPtr((long)selectionPointer));
	//control->SetSelected(theControl);
	//ListItemProvider* theProvider = theControl->GetProvider();

	//HRESULT hr = S_OK;

	//*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	//if (*pRetVal == NULL)
	//{
	//	hr = E_OUTOFMEMORY;
	//}
	//else
	//{
	//	long index = 0;
	//	hr = SafeArrayPutElement(*pRetVal, &index, theProvider);
	//	if (FAILED(hr))
	//	{
	//		SafeArrayDestroy(*pRetVal);
	//		*pRetVal = NULL;
	//	}
	//	else
	//	{
	//		// Since the provider is being passed out of our domain, we need to increment its reference counter.
	//		theProvider->AddRef();
	//	}
	//}

	//return hr;
	return S_OK;
}