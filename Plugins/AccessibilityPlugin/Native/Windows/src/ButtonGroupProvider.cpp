#include "ButtonGroupProvider.h"
#include "RadioButtonControl.h"
#include "RadioButtonProvider.h"


ButtonGroupProvider::ButtonGroupProvider(_In_ ButtonGroupControl* control)
	: ProviderT(control)
{
}

bool ButtonGroupProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID ButtonGroupProvider::GetControlType() const noexcept
{
	return UIA_GroupControlTypeId;
}

IFACEMETHODIMP ButtonGroupProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) try
{
	*retVal = nullptr;

	RadioButtonControl* button = m_control->GetSelectedRadioButton();

	if (!button)
	{
		*retVal = nullptr;
		return S_OK;
	}

	const auto provider = button->GetProvider();

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);

	long index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, provider.query<IUnknown>().get()));

	*retVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP ButtonGroupProvider::get_CanSelectMultiple(_Out_ BOOL* retVal)
{
	*retVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP ButtonGroupProvider::get_IsSelectionRequired(_Out_ BOOL* retVal)
{
	*retVal = FALSE;
	return S_OK;
}