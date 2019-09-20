#include "PopupMenuItemProvider.h"
#include "MenuProvider.h"
#include <string>

#include <iostream>

PopupMenuItemProvider::PopupMenuItemProvider(_In_ PopupMenuItemControl* control)
	: ProviderT(control)
{
}

bool PopupMenuItemProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_InvokePatternId) || (patternId == UIA_ExpandCollapsePatternId));
}

CONTROLTYPEID PopupMenuItemProvider::GetControlType() const noexcept
{
	return UIA_MenuItemControlTypeId;
}

void PopupMenuItemProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
		case UIA_AcceleratorKeyPropertyId:
			retVal->bstrVal = wil::make_bstr(m_control->GetShortcut().c_str()).release();
			retVal->vt = VT_BSTR;
	}
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP PopupMenuItemProvider::SetFocus() noexcept
{
	m_control->GetParentMenuBar()->SetSelectedMenuItem(m_control);
	return S_OK;
}

IFACEMETHODIMP PopupMenuItemProvider::get_ExpandCollapseState(_Out_ ExpandCollapseState* retVal) noexcept
{
	*retVal = ExpandCollapseState_LeafNode;

	if (m_control->GetFirstChild() != nullptr)
	{
		*retVal = ExpandCollapseState_Expanded;
	}
	else if (m_control->IsMenu())
	{
		*retVal = ExpandCollapseState_Collapsed;
	}

	return S_OK;
}

IFACEMETHODIMP PopupMenuItemProvider::Expand() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

IFACEMETHODIMP PopupMenuItemProvider::Collapse() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

IFACEMETHODIMP PopupMenuItemProvider::Invoke() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

// Raises a UIA Event when an item is selected.
void PopupMenuItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId));
	}
}

void PopupMenuItemProvider::NotifyElementInvoked()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_Invoke_InvokedEventId));
	}
}

void PopupMenuItemProvider::NotifyElementExpandCollapse()
{
	// Raise a UI Automation Event
	if (UiaClientsAreListening())
	{
		VARIANT oldValue, newValue;
		oldValue.vt = VT_I4;
		newValue.vt = VT_I4;

		if (m_control->GetFirstChild() != nullptr)
		{
			oldValue.lVal = ExpandCollapseState_Collapsed;
			newValue.lVal = ExpandCollapseState_Expanded;
		}
		else if (m_control->IsMenu())
		{
			oldValue.lVal = ExpandCollapseState_Expanded;
			newValue.lVal = ExpandCollapseState_Collapsed;
		}

		THROW_IF_FAILED(UiaRaiseAutomationPropertyChangedEvent(this, UIA_ExpandCollapseExpandCollapseStatePropertyId, oldValue, newValue));
	}
}
