#include <windows.h>
#include <UIAutomation.h>

#include "TreeProvider.h"
#include "TreeControl.h"
#include "TreeItemProvider.h"
#include "TreeItemControl.h"
#include <string>

#include <iostream>

TreeItemProvider::TreeItemProvider(TreeItemControl * pControl) : m_refCount(1), m_pTreeItemControl(pControl)
{
	if (pControl->IsSubtree())
	{
		if (pControl->IsExpanded())
			m_expandCollapseState = ExpandCollapseState_Expanded;
		else
			m_expandCollapseState = ExpandCollapseState_Collapsed;
	}
	else
		m_expandCollapseState = ExpandCollapseState_LeafNode;
}

TreeItemProvider::~TreeItemProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) TreeItemProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) TreeItemProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP TreeItemProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void ** ppInterface)
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
	else if (riid == __uuidof(ISelectionItemProvider))
	{
		*ppInterface = static_cast<ISelectionItemProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	*pRetVal = NULL;

	if (patternId == UIA_ExpandCollapsePatternId)
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (patternId == UIA_SelectionItemPatternId)
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}

	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (propertyId == UIA_AutomationIdPropertyId)
	{
		ULONG Id = m_pTreeItemControl->GetId();

		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pTreeItemControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pTreeItemControl->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_TreeItemControlTypeId;
	}
	else if (propertyId == UIA_IsExpandCollapsePatternAvailablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = !m_pTreeItemControl->IsSubtree() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (propertyId == UIA_ExpandCollapseExpandCollapseStatePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = m_expandCollapseState;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pTreeItemControl->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
		// HasKeyboardFocus is true if the Tree has focus, and this TreeItem is selected.
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
// Return NULL since TreeItems are not directly hosted in a window and therefore don't have an HWND
IFACEMETHODIMP TreeItemProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Enables UI Automation to locate the element in the tree.
IFACEMETHODIMP TreeItemProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	IRawElementProviderFragment* pFragment = NULL;
	Subtree* pSubtree = m_pTreeItemControl->GetParentTreeItem();
	TreeItemControl* pTreeItem;
	TREEITEM_ITERATOR iter;

	if (pSubtree == NULL)
		pSubtree = m_pTreeItemControl->GetParentTree();

	switch (direction)
	{
		case NavigateDirection_Parent:
		{
			pFragment = static_cast<IRawElementProviderFragment*>(this->GetParentProvider());
			break;
		}
		case NavigateDirection_FirstChild:
		{
			if (m_pTreeItemControl->HasChildren())
			{
				iter = m_pTreeItemControl->GetTreeItemAt(0);
				pTreeItem = static_cast<TreeItemControl*>(*iter);
				pFragment = static_cast<IRawElementProviderFragment*>(pTreeItem->GetTreeItemProvider());

			}
			break;
		}
		case NavigateDirection_LastChild:
		{
			if (m_pTreeItemControl->HasChildren())
			{
				iter = m_pTreeItemControl->GetTreeItemAt(m_pTreeItemControl->GetCount() - 1);
				pTreeItem = static_cast<TreeItemControl*>(*iter);
				pFragment = static_cast<IRawElementProviderFragment*>(pTreeItem->GetTreeItemProvider());
			}
			break;
		}
		case NavigateDirection_NextSibling:
		{
			int myIndex = m_pTreeItemControl->GetTreeItemIndex();
			if (myIndex == pSubtree->GetCount() - 1)
			{
				pFragment = NULL;
				break;
			}
			TREEITEM_ITERATOR nextIter = pSubtree->GetTreeItemAt(myIndex + 1);
			TreeItemControl* pNext = (TreeItemControl*)(*nextIter);
			pFragment = pNext->GetTreeItemProvider();
			break;
		}
		case NavigateDirection_PreviousSibling:
		{
			int myIndex = m_pTreeItemControl->GetTreeItemIndex();
			if (myIndex <= 0)
			{
				pFragment = NULL;
				break;
			}
			TREEITEM_ITERATOR nextIter = pSubtree->GetTreeItemAt(myIndex - 1);
			TreeItemControl* pPrev = static_cast<TreeItemControl*>(*nextIter);
			pFragment = pPrev->GetTreeItemProvider();
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
IFACEMETHODIMP TreeItemProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	int id = m_pTreeItemControl->GetId();
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
IFACEMETHODIMP TreeItemProvider::get_BoundingRectangle(_Out_ UiaRect * pRetVal)
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
IFACEMETHODIMP TreeItemProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP TreeItemProvider::SetFocus()
{
	m_pTreeItemControl->GetParentTree()->SetSelectedTreeItem(m_pTreeItemControl);
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot ** pRetVal)
{
	IRawElementProviderFragmentRoot* pRoot = NULL;
	if (m_pTreeItemControl == NULL) {
		return E_FAIL;
	}

	if (m_pTreeItemControl->GetParentTree() != NULL)
	{
		TreeControl* pTree = m_pTreeItemControl->GetParentTree();
		if (pTree == NULL) {
			return E_FAIL;
		}
		pRoot = pTree->GetTreeProvider();
		if (pRoot == NULL)
		{
			return E_FAIL;
		}
		pRoot->AddRef();
		*pRetVal = pRoot;
		return S_OK;
	}
	return E_FAIL;
}



IFACEMETHODIMP TreeItemProvider::get_ExpandCollapseState(_Out_ ExpandCollapseState * pRetVal)
{
	*pRetVal = m_expandCollapseState;
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::Expand()
{
	m_expandCollapseState = ExpandCollapseState_Expanded;
	NotifyElementExpandCollapse();
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::Collapse()
{
	m_expandCollapseState = ExpandCollapseState_Collapsed;
	NotifyElementExpandCollapse();
	return S_OK;
}

// Raises a UIA Event when an item is added to the Subtree collection
void TreeItemProvider::NotifyTreeItemAdded()
{
	if (UiaClientsAreListening())
		UiaRaiseStructureChangedEvent(this, StructureChangeType_ChildAdded, NULL, 0);
}

// Raises an event when an item is removed from the list.
// StructureType_ChildRemoved is unusual in that it is raised on the parent provider,
// since the child provider may not exist anymore, but it uses the child's runtime ID.
void TreeItemProvider::NotifyTreeItemRemoved()
{
	if (UiaClientsAreListening())
	{
		IRawElementProviderSimple* parentProvider = static_cast<IRawElementProviderSimple*>(this->GetParentProvider());
		parentProvider->AddRef();

		// Construct the runtime ID for the removed child
		int id = m_pTreeItemControl->GetId();
		int rId[] = { UiaAppendRuntimeId, id };

		UiaRaiseStructureChangedEvent(parentProvider, StructureChangeType_ChildRemoved, rId, 2);

	}
}

// Raises a UIA Event when an item is selected.
void TreeItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
		UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId);
	}
}

void TreeItemProvider::NotifyElementExpandCollapse()
{
	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{
		VARIANT oldValue, newValue;
		oldValue.vt = VT_I4;
		newValue.vt = VT_I4;

		if (m_expandCollapseState == ExpandCollapseState_Expanded)
		{
			oldValue.lVal = ExpandCollapseState_Collapsed;
			newValue.lVal = ExpandCollapseState_Expanded;
		}
		else if (m_expandCollapseState == ExpandCollapseState_Collapsed)
		{
			oldValue.lVal = ExpandCollapseState_Expanded;
			newValue.lVal = ExpandCollapseState_Collapsed;
		}

		UiaRaiseAutomationPropertyChangedEvent(this, UIA_ExpandCollapseExpandCollapseStatePropertyId, oldValue, newValue);
	}
}

IFACEMETHODIMP TreeItemProvider::Select(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::AddToSelection(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::RemoveFromSelection(void)
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_IsSelected(BOOL* pRetVal)
{
	TreeControl* tree = m_pTreeItemControl->GetParentTree();
	BOOL selected = (tree->GetSelectedTreeItem() == m_pTreeItemControl);

	*pRetVal = selected;
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal)
{
	*pRetVal = static_cast<IRawElementProviderSimple*>(this->GetParentProvider());

	return S_OK;
}

IUnknown* TreeItemProvider::GetParentProvider()
{
	IRawElementProviderSimple* parentProvider;

	TreeItemControl* pParentTreeItem = m_pTreeItemControl->GetParentTreeItem();
	if (pParentTreeItem != NULL)
	{
		parentProvider = pParentTreeItem->GetTreeItemProvider();
	}
	else
	{
		TreeControl* pTree = m_pTreeItemControl->GetParentTree();
		parentProvider = pTree->GetTreeProvider();
	}

	return static_cast<IUnknown*>(parentProvider);
}
