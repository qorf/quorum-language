#include "TreeProvider.h"
#include "TreeItemControl.h"
#include "TreeItemProvider.h"

TreeProvider::TreeProvider(_In_ TreeControl* control)
	: ProviderT(control)
{
}

bool TreeProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID TreeProvider::GetControlType() const noexcept
{
	return UIA_TreeControlTypeId;
}

// Retrieves the provider for the tree item that is selected when the control gets focus.
IFACEMETHODIMP TreeProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept
{
	*retVal = NULL;

	TreeItemControl* treeItem = m_control->GetSelectedTreeItem();
	if (treeItem)
	{
		treeItem->GetProvider().query_to(retVal);
	}

	return S_OK;
}

IFACEMETHODIMP TreeProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept try
{
	*retVal = nullptr;

	TreeItemControl* treeItem = m_control->GetSelectedTreeItem();

	if (!treeItem)
	{
		*retVal = nullptr;
		return S_OK;
	}

	const auto provider = treeItem->GetProvider();

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);

	long index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, provider.query<IUnknown>().get()));

	*retVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP TreeProvider::get_CanSelectMultiple(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP TreeProvider::get_IsSelectionRequired(_Out_ BOOL* retVal) noexcept
{
	*retVal = FALSE;
	return S_OK;
}


