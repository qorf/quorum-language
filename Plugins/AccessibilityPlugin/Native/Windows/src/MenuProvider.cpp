#include "MenuProvider.h"
#include "MenuItemControl.h"
#include "MenuItemProvider.h"

MenuProvider::MenuProvider(_In_ MenuControl* control)
	: ProviderT(control)
{
}

CONTROLTYPEID MenuProvider::GetControlType() const noexcept
{
	return UIA_MenuControlTypeId;
}

void MenuProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
		case UIA_AccessKeyPropertyId:
			retVal->bstrVal = wil::make_bstr(L"ALT").release();
			retVal->vt = VT_BSTR;
			break;
	}
}
