#include "ToolBarProvider.h"

ToolBarProvider::ToolBarProvider(_In_ ToolBarControl* control)
	: ProviderT(control)
{
}

CONTROLTYPEID ToolBarProvider::GetControlType() const noexcept
{
	return UIA_ToolBarControlTypeId;
}

bool ToolBarProvider::IsKeyboardFocusable() const noexcept
{
	return false;
}

void ToolBarProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
		case UIA_OrientationPropertyId:
			retVal->boolVal = OrientationType_Horizontal;
			retVal->vt = VT_I4;
			break;
	}
}
