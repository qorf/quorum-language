#include "TabProvider.h"
#include "TabPaneProvider.h"
#include "Resources.h"

TabProvider::TabProvider(_In_ TabControl* control)
	: ProviderT(control)
{
}

bool TabProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_SelectionItemPatternId);
}

CONTROLTYPEID TabProvider::GetControlType() const noexcept
{
	return UIA_TabItemControlTypeId;
}

bool TabProvider::IsKeyboardFocusable() const noexcept
{
	//tabs in our system cannot receive the focus directly, only the tab pane or the containing item
	return false;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP TabProvider::SetFocus() noexcept
{
	m_control->GetParentTabPane()->SetSelectedTab(m_control);
	return S_OK;
}

IFACEMETHODIMP TabProvider::Select() noexcept
{
	JNIEnv* env = GetJNIEnv();
	const auto parent = m_control->GetParentTabPane();
	env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.SetTabSelection, parent->GetMe(), m_control->GetMe());
	return S_OK;
}

IFACEMETHODIMP TabProvider::AddToSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TabProvider::RemoveFromSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP TabProvider::get_IsSelected(_Out_ BOOL* retVal) noexcept
{
	const auto tabPane = m_control->GetParentTabPane();
	const bool selected = (tabPane->GetSelectedTab() == m_control);

	*retVal = selected ? TRUE : FALSE;
	return S_OK;
}

IFACEMETHODIMP TabProvider::get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept
{
	*retVal = nullptr;

	m_control->GetParentTabPane()->GetProvider().query_to(retVal);

	return S_OK;
}

void TabProvider::NotifyElementSelected()
{
	if (UiaClientsAreListening())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId));
	}
}
