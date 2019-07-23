#include "MenuBarProvider.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"

MenuBarProvider::MenuBarProvider(_In_ MenuBarControl* control)
	: ProviderT(control)
{
}

CONTROLTYPEID MenuBarProvider::GetControlType() const noexcept
{
	return UIA_MenuBarControlTypeId;
}

void MenuBarProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
		case UIA_AccessKeyPropertyId:
			retVal->bstrVal = wil::make_bstr(L"ALT").release();
			retVal->vt = VT_BSTR;
			break;
	}
}

// Retrieves the provider for the menu item that is selected when the control gets focus.
IFACEMETHODIMP MenuBarProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept
{
	*retVal = nullptr;

	const auto menuItem = m_control->GetSelectedMenuItem();
	if (menuItem)
	{
		menuItem->GetProvider().query_to(retVal);
	}

	return S_OK;
}
