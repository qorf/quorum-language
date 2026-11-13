#include "CalendarProvider.h"

CalendarProvider::CalendarProvider(CalendarControl* control) : ProviderT(control)
{
}

bool CalendarProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_GridPatternId || patternId == UIA_TablePatternId || patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID CalendarProvider::GetControlType() const noexcept
{
	return UIA_CalendarControlTypeId;
}

IFACEMETHODIMP CalendarProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept try
{
	*retVal = nullptr;
	if (direction == NavigateDirection_FirstChild)
	{
		Item* first = m_control->GetFirstChild();
		if (first != nullptr)
		{
			*retVal = first->GetProviderFragment().detach();
		}
		else if (m_control->IsAvailableForQueries())
		{
			*retVal = m_control->GetPartFragment(0).detach();
		}
		return S_OK;
	}
	if (direction == NavigateDirection_LastChild)
	{
		if (m_control->IsAvailableForQueries())
		{
			*retVal = m_control->GetPartFragment(CalendarControl::PartCount - 1).detach();
		}
		return S_OK;
	}
	return ProviderT::Navigate(direction, retVal);
}
CATCH_RETURN();

IFACEMETHODIMP CalendarProvider::get_RowCount(int* pRetVal) noexcept
{
	*pRetVal = CalendarControl::Rows;
	return S_OK;
}

IFACEMETHODIMP CalendarProvider::get_ColumnCount(int* pRetVal) noexcept
{
	*pRetVal = CalendarControl::Columns;
	return S_OK;
}

IFACEMETHODIMP CalendarProvider::GetItem(int row, int column, IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	if (row < 0 || row >= CalendarControl::Rows || column < 0 || column >= CalendarControl::Columns)
	{
		return E_INVALIDARG;
	}
	if (!m_control->IsAvailableForQueries())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	const auto fragment = m_control->GetPartFragment(CalendarControl::Columns + row * CalendarControl::Columns + column);
	if (fragment)
	{
		fragment.query_to(pRetVal);
	}
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP CalendarProvider::GetRowHeaders(SAFEARRAY** pRetVal) noexcept
{
	*pRetVal = nullptr;
	return S_OK;
}

IFACEMETHODIMP CalendarProvider::GetColumnHeaders(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, CalendarControl::Columns) };
	THROW_IF_NULL_ALLOC(sa);
	for (LONG column = 0; column < CalendarControl::Columns; column++)
	{
		const auto header = m_control->GetHeaderFragment(column);
		THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &column, header.query<IRawElementProviderSimple>().get()));
	}
	*pRetVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP CalendarProvider::get_RowOrColumnMajor(RowOrColumnMajor* pRetVal) noexcept
{
	*pRetVal = RowOrColumnMajor_RowMajor;
	return S_OK;
}

// The chosen day, when it is shown; otherwise none.
IFACEMETHODIMP CalendarProvider::GetSelection(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	int chosen = 0;
	bool hasChosen = false;
	if (m_control->IsAvailableForQueries())
	{
		hasChosen = m_control->GetChosenDay(chosen) && m_control->IsDayShown(chosen);
	}

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, hasChosen ? 1 : 0) };
	THROW_IF_NULL_ALLOC(sa);
	if (hasChosen)
	{
		const auto day = m_control->GetDayFragment(chosen);
		LONG index = 0;
		THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, day.query<IRawElementProviderSimple>().get()));
	}
	*pRetVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP CalendarProvider::get_CanSelectMultiple(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP CalendarProvider::get_IsSelectionRequired(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	return S_OK;
}
