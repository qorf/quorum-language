#include "TreeItemProvider.h"
#include "TreeProvider.h"
#include "TreeControl.h"

TreeItemProvider::TreeItemProvider(_In_ TreeItemControl* control)
	: ProviderT(control)
{
}

bool TreeItemProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_SelectionItemPatternId) || (patternId == UIA_ExpandCollapsePatternId));
}

CONTROLTYPEID TreeItemProvider::GetControlType() const noexcept
{
	return UIA_TreeItemControlTypeId;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP TreeItemProvider::SetFocus() noexcept
{
	m_control->GetParentTree()->SetSelectedTreeItem(m_control);
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_ExpandCollapseState(_Out_ ExpandCollapseState* retVal) noexcept
{
	*retVal = ExpandCollapseState_LeafNode;

	if (m_control->IsSubtree())
	{
		if (m_control->IsExpanded())
		{
			*retVal = ExpandCollapseState_Expanded;
		}
		else
		{
			*retVal = ExpandCollapseState_Collapsed;
		}
	}

	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::Expand() noexcept
{
	// TODO: Call up to Quorum to actually make it happen. Then let Quorum fire the event.
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::Collapse() noexcept
{
	// TODO: Call up to Quorum to actually make it happen. Then let Quorum fire the event.
	return S_OK;
}

// Raises a UIA Event when an item is selected.
void TreeItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId));
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId));
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

		if (m_control->IsExpanded())
		{
			oldValue.lVal = ExpandCollapseState_Collapsed;
			newValue.lVal = ExpandCollapseState_Expanded;
		}
		else if (m_control->IsSubtree())
		{
			oldValue.lVal = ExpandCollapseState_Expanded;
			newValue.lVal = ExpandCollapseState_Collapsed;
		}

		THROW_IF_FAILED(UiaRaiseAutomationPropertyChangedEvent(this, UIA_ExpandCollapseExpandCollapseStatePropertyId, oldValue, newValue));
	}
}

IFACEMETHODIMP TreeItemProvider::Select() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::AddToSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::RemoveFromSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_IsSelected(_Out_ BOOL* retVal) noexcept
{
	const auto tree = m_control->GetParentTree();
	const bool selected = (tree->GetSelectedTreeItem() == m_control);

	*retVal = selected ? TRUE : FALSE;
	return S_OK;
}

IFACEMETHODIMP TreeItemProvider::get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept
{
	*retVal = nullptr;

	m_control->GetParentTree()->GetProvider().query_to(retVal);

	return S_OK;
}

void TreeItemProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL)
		return;

	switch (propertyId)
	{
	case UIA_PositionInSetPropertyId:
		retVal->intVal = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTreeItemSetPosition, m_control->GetMe());
		retVal->vt = VT_I4;
		break;
	case UIA_SizeOfSetPropertyId:
		retVal->intVal = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTreeItemSetSize, m_control->GetMe());
		retVal->vt = VT_I4;
		break;
	}
}
