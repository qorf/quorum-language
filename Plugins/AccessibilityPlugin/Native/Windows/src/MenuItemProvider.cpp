#include "MenuItemProvider.h"
#include "MenuBarProvider.h"
#include <string>

#include <iostream>

MenuItemProvider::MenuItemProvider(_In_ MenuItemControl* control)
	: ProviderT(control)
{
}

bool MenuItemProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_InvokePatternId) || (patternId == UIA_ExpandCollapsePatternId));
}

CONTROLTYPEID MenuItemProvider::GetControlType() const noexcept
{
	return UIA_MenuItemControlTypeId;
}

void MenuItemProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	JNIEnv* env;

	switch (propertyId)
	{
		case UIA_AcceleratorKeyPropertyId:
			retVal->bstrVal = wil::make_bstr(m_control->GetShortcut().c_str()).release();
			retVal->vt = VT_BSTR;
			break;
		case UIA_PositionInSetPropertyId:
			env = GetJNIEnv();
			if (env == NULL)
				break;

			retVal->intVal = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetMenuItemSetPosition, m_control->GetMe());
			retVal->vt = VT_I4;
			break;
		case UIA_SizeOfSetPropertyId:
			env = GetJNIEnv();
			if (env == NULL)
				break;

			retVal->intVal = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetMenuItemSetSize, m_control->GetMe());
			retVal->vt = VT_I4;
			break;
	}
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP MenuItemProvider::SetFocus() noexcept
{
	m_control->GetParentMenuBar()->SetSelectedMenuItem(m_control);
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::get_ExpandCollapseState(_Out_ ExpandCollapseState* retVal) noexcept
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

IFACEMETHODIMP MenuItemProvider::Expand() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::Collapse() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

IFACEMETHODIMP MenuItemProvider::Invoke() noexcept
{
	// TODO: Call into Quorum to actually make it happen, then let Quorum fire the event.
	return S_OK;
}

// Raises a UIA Event when an item is selected.
void MenuItemProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId));
	}
}

void MenuItemProvider::NotifyElementInvoked()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_Invoke_InvokedEventId));
	}
}

void MenuItemProvider::NotifyElementExpandCollapse()
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
