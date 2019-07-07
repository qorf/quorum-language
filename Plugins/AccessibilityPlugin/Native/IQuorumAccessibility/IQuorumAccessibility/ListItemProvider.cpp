#include "ListItemProvider.h"

ListItemProvider::ListItemProvider(ListItemControl* pControl, ListControl* parent) : referenceCount(1), control(pControl), parent(parent)
{
}

ListItemProvider::~ListItemProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ListItemProvider::AddRef()
{
	return InterlockedIncrement(&referenceCount);
}

IFACEMETHODIMP_(ULONG) ListItemProvider::Release()
{
	long val = InterlockedDecrement(&referenceCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP ListItemProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
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
	else if (riid == __uuidof(ISelectionItemProvider))
	{
		*ppInterface = static_cast<ISelectionItemProvider*>(this);
	}
	else if (riid == __uuidof(IValueProvider))
	{
		*ppInterface = static_cast<IValueProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	switch (patternId)
	{
	case UIA_SelectionItemPatternId:
		*pRetVal = static_cast<ISelectionItemProvider*>(this);
		break;
	case UIA_ValuePatternId:
		*pRetVal = static_cast<IValueProvider*>(this);
		break;
	default:
		*pRetVal = NULL;
	}

	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}

	return S_OK;
}

IFACEMETHODIMP ListItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		ULONG Id = control->GetHashCode();

		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(control->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_ListItemControlTypeId;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE; 
	}
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsContentElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"list item");
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
	}
	return S_OK;
}

// Gets the UI Automation provider for the host window. 
// Return NULL since TreeItems are not directly hosted in a window and therefore don't have an HWND
IFACEMETHODIMP ListItemProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(control->GetHWND(), pRetVal);
}

// Enables UI Automation to locate the element in the tree.
IFACEMETHODIMP ListItemProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** pRetVal)
{
	IRawElementProviderFragment* pFragment = NULL;

	switch (direction)
	{
	case NavigateDirection_Parent:
	{
		if (parent != NULL) {
			//pFragment = static_cast<IRawElementProviderFragment*>(parent->GetProvider());
		}
		break;
	}
	case NavigateDirection_FirstChild:
	{
		if (child != NULL)
		{
			//pFragment = child;
		}
		break;
	}
	case NavigateDirection_LastChild:
	{
		if (child != NULL)
		{
			//pFragment = child;
		}
		break;
	}
	//we might want to expose these for previous/next tabs, but the UI does this anyway. Hard to say.
	case NavigateDirection_NextSibling:
	{
		break;
	}
	case NavigateDirection_PreviousSibling:
	{
		break;
	}
	}

	*pRetVal = pFragment;

	if (pFragment != NULL) {
		pFragment->AddRef();
	}
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_IsReadOnly(BOOL* returnValue)
{
	// Currently hard-coded to true -- Quorum cells are read-only in the current version.
	*returnValue = VARIANT_TRUE;

	return S_OK;
}

IFACEMETHODIMP ListItemProvider::SetValue(LPCWSTR value)
{
	// NYI

	return UIA_E_NOTSUPPORTED;
}

IFACEMETHODIMP ListItemProvider::get_Value(BSTR* returnValue)
{
	std::wstring text = control->GetText();
	*returnValue = SysAllocStringLen(text.data(), text.size());

	return S_OK;
}

// Gets the runtime identifier. This is an array consisting of UiaAppendRuntimeId, 
// which makes the ID unique among instances of the control, and the Automation Id.
IFACEMETHODIMP ListItemProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	int id = control->GetHashCode();
	int rId[] = { UiaAppendRuntimeId, id };

	HRESULT hr = S_OK;

	SAFEARRAY* psa = SafeArrayCreateVector(VT_I4, 0, 2);

	for (LONG i = 0; i < 2; i++)
	{
		hr = SafeArrayPutElement(psa, &i, &(rId[i]));
	}
	*pRetVal = psa;
	return hr;
}

// Gets the bounding rectangle of the item, in screen coordinates.
IFACEMETHODIMP ListItemProvider::get_BoundingRectangle(_Out_ UiaRect* pRetVal)
{
	// For now we aren't painting a rectangle for the provider
	// that'd require more info from Quorum.
	pRetVal->left = 0;
	pRetVal->top = 0;
	pRetVal->width = 0;
	pRetVal->height = 0;
	return S_OK;
}

// Retrieves any fragment roots that may be hosted in this element. There aren't any, so NULL is correct.
IFACEMETHODIMP ListItemProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP ListItemProvider::SetFocus()
{
	if (child != NULL) {

	}//do we need to do anything here?
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot** pRetVal)
{

	IRawElementProviderFragmentRoot* pRoot = NULL;

	if (parent != NULL)
	{
		pRoot = parent->GetProvider();
	}

	if (pRoot == NULL)
	{
		return E_FAIL;
	}
	pRoot->AddRef();
	*pRetVal = pRoot;
	return S_OK;
}

//ISelectionItemProvider
//Adds the current element to the collection of selected items.
IFACEMETHODIMP ListItemProvider::AddToSelection() {
	return S_OK;
}

//Indicates whether an item is selected.
IFACEMETHODIMP ListItemProvider::get_IsSelected(BOOL* pRetVal) {
	BOOL selected = (parent->GetSelected() == control);

	*pRetVal = selected;
	return S_OK;
}

//Specifies the provider that implements ISelectionProviderand acts as the container for the calling object.
IFACEMETHODIMP ListItemProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal) {

	*pRetVal = parent->GetProvider();
	return S_OK;
}

//Removes the current element from the collection of selected items.
IFACEMETHODIMP ListItemProvider::RemoveFromSelection() {
	return S_OK;
}

//Deselects any selected itemsand then selects the current element.
IFACEMETHODIMP ListItemProvider::Select() {
	if (!IsWindow(control->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	JNIEnv* env = GetJNIEnv();
	env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.SetTabSelection, parent->GetMe(), control->GetMe());
	return S_OK;
}

void ListItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
		UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId);
	}
}