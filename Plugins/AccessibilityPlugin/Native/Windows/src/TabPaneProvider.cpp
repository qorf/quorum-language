#include "TabPaneProvider.h"
#include "TabControl.h"
#include "TabProvider.h"

TabPaneProvider::TabPaneProvider(_In_ TabPaneControl* control) :
	ProviderT(control)
{
}

bool TabPaneProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID TabPaneProvider::GetControlType() const noexcept
{
	return UIA_TabControlTypeId;
}

void TabPaneProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	switch (propertyId)
	{
		case UIA_OrientationPropertyId:
			retVal->boolVal = OrientationType_Horizontal;
			retVal->vt = VT_I4;
			break;
	}
}

IFACEMETHODIMP TabPaneProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept try
{
	*retVal = nullptr;

	const auto tab = m_control->GetSelectedTab();

	if (!tab)
	{
		return S_OK;
	}

	const auto provider = tab->GetProvider();

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);

	long index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, provider.query<IUnknown>().get()));

	*retVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP TabPaneProvider::get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP TabPaneProvider::get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept
{
	*retVal = TRUE;
	return S_OK;
}
