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
