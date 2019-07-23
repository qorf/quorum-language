#include "ListProvider.h"
#include "ListItemControl.h"
#include "ListItemProvider.h"

ListProvider::ListProvider(_In_ ListControl* control) :
	ProviderT(control)
{
}

bool ListProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID ListProvider::GetControlType() const noexcept
{
	return UIA_ListControlTypeId;
}

// Retrieves the provider for the tree item that is selected when the control gets focus.
IFACEMETHODIMP ListProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept
{
	*retVal = nullptr;

	const auto listItem = m_control->GetSelected();
	if (listItem)
	{
		listItem->GetProvider().query_to(retVal);
	}

	return S_OK;
}

IFACEMETHODIMP ListProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept try
{
	*retVal = nullptr;

	const auto listItem = m_control->GetSelected();

	if (!listItem)
	{
		return S_OK;
	}

	const auto provider = listItem->GetProvider();

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);

	long index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, provider.query<IUnknown>().get()));

	*retVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP ListProvider::get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP ListProvider::get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept
{
	*retVal = TRUE;
	return S_OK;
}
