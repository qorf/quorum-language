#include "ListItemProvider.h"
#include "ListProvider.h"

ListItemProvider::ListItemProvider(_In_ ListItemControl* control)
	: ProviderT(control)
{
}

bool ListItemProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_SelectionItemPatternId) || (patternId == UIA_ValuePatternId));
}

CONTROLTYPEID ListItemProvider::GetControlType() const noexcept
{
	return UIA_ListItemControlTypeId;
}

// Responds to the control receiving focus through a UI Automation request.
IFACEMETHODIMP ListItemProvider::SetFocus() noexcept
{
	m_control->GetParentList()->SetSelected(m_control);
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::Select() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::AddToSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::RemoveFromSelection() noexcept
{
	// NYI
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_IsSelected(_Out_ BOOL* retVal) noexcept
{
	const auto list = m_control->GetParentList();
	const bool selected = (list->GetSelected() == m_control);

	*retVal = selected ? TRUE : FALSE;
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_SelectionContainer(_Outptr_ IRawElementProviderSimple** retVal) noexcept
{
	*retVal = nullptr;

	m_control->GetParentList()->GetProvider().query_to(retVal);

	return S_OK;
}

IFACEMETHODIMP ListItemProvider::get_IsReadOnly(_Out_ BOOL* retVal) noexcept
{
	// Currently hard-coded to true -- Quorum cells are read-only in the current version.
	*retVal = TRUE;
	return S_OK;
}

IFACEMETHODIMP ListItemProvider::SetValue(LPCWSTR value) noexcept
{
	// NYI
	return UIA_E_NOTSUPPORTED;
}

IFACEMETHODIMP ListItemProvider::get_Value(_Out_ BSTR* retVal) noexcept try
{
	const auto text = m_control->GetText();
	*retVal = wil::make_bstr(text.c_str()).release();
	return S_OK;
}
CATCH_RETURN();

void ListItemProvider::NotifyElementSelected()
{
	if (m_control->IsReadyForEvents())
	{
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId));
		THROW_IF_FAILED(UiaRaiseAutomationEvent(this, UIA_SelectionItem_ElementSelectedEventId));
	}
}
