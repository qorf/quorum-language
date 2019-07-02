#include <windows.h>
#include <UIAutomation.h>

#include "TreeProvider.h"
#include "TreeControl.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"

TreeProvider::TreeProvider(_In_ TreeControl * pTreeControl) : m_refCount(1), m_pTreeControl(pTreeControl)
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) TreeProvider::AddRef()
{
	//std::cout << "Add: " << m_refCount << std::endl;
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) TreeProvider::Release()
{
	//std::cout << "Remove: " << m_refCount << std::endl;
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP TreeProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
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
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

// Gets UI Automation provider options.
IFACEMETHODIMP TreeProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// The Tree doesn't support any patterns so NULL is correct.
IFACEMETHODIMP TreeProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

// Gets the custom properties for this control.
IFACEMETHODIMP TreeProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(this->m_pTreeControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pTreeControl->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_TreeControlTypeId;
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
	else
	{
		pRetVal->vt = VT_EMPTY;
	}

	return S_OK;
}

IFACEMETHODIMP TreeProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	return UiaHostProviderFromHwnd(m_pTreeControl->GetHWND(), pRetVal);
}

// Enables UI Automation to locate the element in the tree.
// Navigation to the parent is handled by the host window provider.
IFACEMETHODIMP TreeProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	TreeControl* pTreeControl = this->m_pTreeControl;
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
				if (pTreeItem == NULL) {
					*pRetVal = pFragment;
					return S_OK;
				}
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
				if (pTreeItem == NULL) {
					*pRetVal = pFragment;
					return S_OK;
				}
				pFragment = (IRawElementProviderFragment*)pTreeItem->GetTreeItemProvider();
			}
			break;
		}
	}
	if (pFragment != NULL)
	{
		pFragment->AddRef();
	}
	*pRetVal = pFragment;
	return S_OK;
}

// UI Automation gets this value from the host window provider, so NULL is correct here.
IFACEMETHODIMP TreeProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

IFACEMETHODIMP TreeProvider::get_BoundingRectangle(_Out_ UiaRect * pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
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
IFACEMETHODIMP TreeProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
// For HWND-based controls, this is handled by the host window provider.
IFACEMETHODIMP TreeProvider::SetFocus()
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	return S_OK;
}

// Retrieves the root element of this fragment.
IFACEMETHODIMP TreeProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
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
IFACEMETHODIMP TreeProvider::ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
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
IFACEMETHODIMP TreeProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;

	TreeItemControl* pTreeItem = m_pTreeControl->GetSelectedTreeItem();
	if (pTreeItem != nullptr)
	{
		// Get Provider
		IRawElementProviderFragment* provider = (IRawElementProviderFragment*)(pTreeItem->GetTreeItemProvider());

		*pRetVal = provider;
	}

	return S_OK;
}



TreeProvider::~TreeProvider()
{
}

IFACEMETHODIMP TreeProvider::GetSelection(SAFEARRAY** pRetVal)
{
	if (!IsWindow(m_pTreeControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	JNIEnv* env = GetJNIEnv();
	long selectionPointer = env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTreeSelectionPointer, m_pTreeControl->GetMe());

	if (selectionPointer == 0)
	{
		*pRetVal = NULL;
		return S_OK;
	}

	TreeItemControl* treeItemControl = static_cast<TreeItemControl*>(LongToPtr((long)selectionPointer));
	TreeItemProvider* treeItemProvider = treeItemControl->GetTreeItemProvider();

	HRESULT hr = S_OK;

	*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		long index = 0;
		hr = SafeArrayPutElement(*pRetVal, &index, treeItemProvider);
		if (FAILED(hr))
		{
			SafeArrayDestroy(*pRetVal);
			*pRetVal = NULL;
		}
		else
		{
			// Since the provider is being passed out of our domain, we need to increment its reference counter.
			treeItemProvider->AddRef();
		}
	}

	return hr;
}

IFACEMETHODIMP TreeProvider::get_CanSelectMultiple(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}

IFACEMETHODIMP TreeProvider::get_IsSelectionRequired(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}


