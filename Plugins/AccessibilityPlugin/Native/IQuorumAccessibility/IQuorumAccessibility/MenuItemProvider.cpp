#include <windows.h>
#include <UIAutomation.h>

#include "MenuBarProvider.h"
#include "MenuBarControl.h"
#include "MenuItemProvider.h"
#include "MenuItemControl.h"
#include <string>

#include <iostream>

MenuItemProvider::MenuItemProvider(MenuItemControl * pControl) : m_refCount(1), m_pMenuItemControl(pControl)
{
	if (pControl->IsMenu())
	{
		if (pControl->HasChildren())
			m_expandCollapseState = ExpandCollapseState_Expanded;
		else
			m_expandCollapseState = ExpandCollapseState_Collapsed;
	}
	else
		m_expandCollapseState = ExpandCollapseState_LeafNode;
}

MenuItemProvider::~MenuItemProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) MenuItemProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) MenuItemProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP MenuItemProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
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
	else if (riid == __uuidof(IExpandCollapseProvider))
	{
		*ppInterface = static_cast<IExpandCollapseProvider*>(this);
	}
	else if (riid == __uuidof(IInvokeProvider))
	{
		*ppInterface = static_cast<IInvokeProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	*pRetVal = NULL;

	if (patternId == UIA_InvokePatternId && !m_pMenuItemControl->IsMenu())
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (patternId == UIA_ExpandCollapsePatternId && m_pMenuItemControl->IsMenu())
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}

	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = m_pMenuItemControl->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pMenuItemControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pMenuItemControl->GetDescription());
	}
	else if (propertyId == UIA_AcceleratorKeyPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pMenuItemControl->GetShortcut());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_MenuItemControlTypeId;
	}
	else if (propertyId == UIA_IsInvokePatternAvailablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = !m_pMenuItemControl->IsMenu() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (propertyId == UIA_IsExpandCollapsePatternAvailablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pMenuItemControl->IsMenu() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (propertyId == UIA_ExpandCollapseExpandCollapseStatePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = m_expandCollapseState;
	}
	// HasKeyboardFocus is true if the MenuBar has focus, and this MenuItem is selected.
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pMenuItemControl->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
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

// Gets the UI Automation provider for the host window. 
// Return NULL since MenuItems are not directly hosted in a window and therefore don't have an HWND
IFACEMETHODIMP MenuItemProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Enables UI Automation to locate the element in the tree.
IFACEMETHODIMP MenuItemProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	IRawElementProviderFragment* pFragment = NULL;
	Menu* pMenuControl = m_pMenuItemControl->GetParentMenuItem();
	MENUITEM_ITERATOR iter;
	MenuItemControl* pMenuItem;

	if (pMenuControl == NULL)
		pMenuControl = m_pMenuItemControl->GetParentMenuBar();

	switch (direction)
	{
		case NavigateDirection_Parent:
		{
			pFragment = static_cast<IRawElementProviderFragment*>(this->GetParentProvider());
			break;
		}
		case NavigateDirection_FirstChild:
		{
			if (m_pMenuItemControl->HasChildren())
			{
				iter = m_pMenuItemControl->GetMenuItemAt(0);
				pMenuItem = static_cast<MenuItemControl*>(*iter);
				pFragment = static_cast<IRawElementProviderFragment*>(pMenuItem->GetMenuItemProvider());

			}
			break;
		}
		case NavigateDirection_LastChild:
		{
			if (m_pMenuItemControl->HasChildren())
			{
				iter = m_pMenuItemControl->GetMenuItemAt(m_pMenuItemControl->GetCount() - 1);
				pMenuItem = static_cast<MenuItemControl*>(*iter);
				pFragment = static_cast<IRawElementProviderFragment*>(pMenuItem->GetMenuItemProvider());
			}
			break;
		}
		case NavigateDirection_NextSibling:
		{
			int myIndex = m_pMenuItemControl->GetMenuItemIndex();
			if (myIndex == pMenuControl->GetCount() - 1)
			{
				pFragment = NULL;
				break;
			}
			MENUITEM_ITERATOR nextIter = pMenuControl->GetMenuItemAt(myIndex + 1);
			MenuItemControl* pNext = (MenuItemControl*)(*nextIter);
			pFragment = pNext->GetMenuItemProvider();
			break;
		}
		case NavigateDirection_PreviousSibling:
		{
			int myIndex = m_pMenuItemControl->GetMenuItemIndex();
			if (myIndex <= 0)
			{
				pFragment = NULL;
				break;
			}
			MENUITEM_ITERATOR nextIter = pMenuControl->GetMenuItemAt(myIndex - 1);
			MenuItemControl* pPrev = static_cast<MenuItemControl*>(*nextIter);
			pFragment = pPrev->GetMenuItemProvider();
			break;
		}
	}

	*pRetVal = pFragment;

	if (pFragment != NULL)
		pFragment->AddRef();

	return S_OK;
}

// Gets the runtime identifier. This is an array consisting of UiaAppendRuntimeId, 
// which makes the ID unique among instances of the control, and the Automation Id.
IFACEMETHODIMP MenuItemProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	int id = m_pMenuItemControl->GetHashCode();
	int rId[] = { UiaAppendRuntimeId, id };
	
	HRESULT hr = S_OK;
	
	SAFEARRAY *psa = SafeArrayCreateVector(VT_I4, 0, 2);
	
	for (LONG i = 0; i < 2; i++)
	{
		hr = SafeArrayPutElement(psa, &i, &(rId[i]));
	}
	*pRetVal = psa;
	return hr;
}

// Gets the bounding rectangle of the item, in screen coordinates.
IFACEMETHODIMP MenuItemProvider::get_BoundingRectangle(_Out_ UiaRect * pRetVal)
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
IFACEMETHODIMP MenuItemProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP MenuItemProvider::SetFocus()
{
	m_pMenuItemControl->GetParentMenuBar()->SetSelectedMenuItem(m_pMenuItemControl);
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot ** pRetVal)
{

	IRawElementProviderFragmentRoot* pRoot = NULL;
	
	if (m_pMenuItemControl->GetParentMenuBar() != NULL)
	{
		MenuBarControl* pMenuBar = m_pMenuItemControl->GetParentMenuBar();
		pRoot = pMenuBar->GetMenuBarProvider();
	}


	if (pRoot == NULL)
	{
		return E_FAIL;
	}
	pRoot->AddRef();
	*pRetVal = pRoot;
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::get_ExpandCollapseState(ExpandCollapseState * pRetVal)
{
	*pRetVal = m_expandCollapseState;
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::Expand()
{
	m_expandCollapseState = ExpandCollapseState_Expanded;
	NotifyElementExpandCollapse();
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::Collapse()
{
	m_expandCollapseState = ExpandCollapseState_Collapsed;
	NotifyElementExpandCollapse();
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::Invoke()
{
	NotifyElementInvoked();
	return S_OK;
}

// Raises a UIA Event when an item is added to the Menu collection
void MenuItemProvider::NotifyMenuItemAdded()
{
	if (UiaClientsAreListening())
		UiaRaiseStructureChangedEvent(this, StructureChangeType_ChildAdded, NULL, 0);
}

// Raises an event when an item is removed from the list.
// StructureType_ChildRemoved is unusual in that it is raised on the parent provider,
// since the child provider may not exist anymore, but it uses the child's runtime ID.
void MenuItemProvider::NotifyMenuItemRemoved()
{
	if (UiaClientsAreListening())
	{
		IRawElementProviderSimple* parentProvider = static_cast<IRawElementProviderSimple*>(this->GetParentProvider());
		parentProvider->AddRef();

		// Construct the runtime ID for the removed child
		int id = m_pMenuItemControl->GetHashCode();
		int rId[] = { UiaAppendRuntimeId, id };

		UiaRaiseStructureChangedEvent(parentProvider, StructureChangeType_ChildRemoved, rId, (sizeof(rId) / sizeof(rId[0])));

	}
}

// Raises a UIA Event when an item is selected.
void MenuItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
		UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId);
	}
}

void MenuItemProvider::NotifyElementInvoked()
{
	if (UiaClientsAreListening())
		UiaRaiseAutomationEvent(this, UIA_Invoke_InvokedEventId);
}

void MenuItemProvider::NotifyElementExpandCollapse()
{
	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationPropertyChangedEventId);
		if (m_expandCollapseState == ExpandCollapseState_Expanded)
			UiaRaiseAutomationEvent(this, UIA_MenuOpenedEventId);
		else if (m_expandCollapseState == ExpandCollapseState_Collapsed)
			UiaRaiseAutomationEvent(this, UIA_MenuClosedEventId);

	}
}

IUnknown* MenuItemProvider::GetParentProvider()
{
	IRawElementProviderSimple* parentProvider;

	MenuItemControl* pParentMenuItem = m_pMenuItemControl->GetParentMenuItem();
	if (pParentMenuItem != NULL)
	{
		parentProvider = pParentMenuItem->GetMenuItemProvider();
	}
	else
	{
		MenuBarControl* pMenuBar = m_pMenuItemControl->GetParentMenuBar();
		parentProvider = pMenuBar->GetMenuBarProvider();
	}

	return static_cast<IUnknown*>(parentProvider);
}
