#include "CalendarDayProvider.h"
#include "CalendarProvider.h"

namespace
{

// The kinds of part, for the runtime IDs.
constexpr int c_dayKind = 1;
constexpr int c_headerKind = 2;

} // anonymous namespace

// =========== A day

CalendarDayProvider::CalendarDayProvider(_In_ CalendarControl* calendar, int day)
	: CalendarPartT(calendar), m_day(day)
{
}

int CalendarDayProvider::GetPart()
{
	int index = m_day - m_calendar->GetFirstShownDay();
	if (index < 0 || index >= CalendarControl::DayCount)
	{
		return -1;
	}
	return CalendarControl::Columns + index;
}

int CalendarDayProvider::GetRow()
{
	return (m_day - m_calendar->GetFirstShownDay()) / CalendarControl::Columns;
}

int CalendarDayProvider::GetColumn()
{
	return (m_day - m_calendar->GetFirstShownDay()) % CalendarControl::Columns;
}

int CalendarDayProvider::GetKind() const noexcept
{
	return c_dayKind;
}

int CalendarDayProvider::GetKey() const noexcept
{
	return m_day;
}

CONTROLTYPEID CalendarDayProvider::GetControlType() const noexcept
{
	return UIA_DataItemControlTypeId;
}

std::wstring CalendarDayProvider::GetName()
{
	return m_calendar->GetDayText(m_day);
}

bool CalendarDayProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_GridItemPatternId || patternId == UIA_TableItemPatternId
		|| patternId == UIA_SelectionItemPatternId || patternId == UIA_InvokePatternId);
}

bool CalendarDayProvider::HasFocus()
{
	if (m_calendar->GetRoot() == nullptr || !m_calendar->HasUiaFocus())
	{
		return false;
	}
	return m_calendar->GetCurrentDay() == m_day;
}

bool CalendarDayProvider::IsFocusable() const noexcept
{
	return true;
}

bool CalendarDayProvider::IsEnabled()
{
	return m_calendar->IsDayAvailable(m_day);
}

IFACEMETHODIMP CalendarDayProvider::SetFocus() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_calendar->MoveCurrentDayTo(m_day);
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_Row(int* pRetVal) noexcept
{
	*pRetVal = 0;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	*pRetVal = GetRow();
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_Column(int* pRetVal) noexcept
{
	*pRetVal = 0;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	*pRetVal = GetColumn();
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_RowSpan(int* pRetVal) noexcept
{
	*pRetVal = 1;
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_ColumnSpan(int* pRetVal) noexcept
{
	*pRetVal = 1;
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_ContainingGrid(IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	m_calendar->GetProvider().query_to(pRetVal);
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP CalendarDayProvider::GetRowHeaderItems(SAFEARRAY** pRetVal) noexcept
{
	*pRetVal = nullptr;
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::GetColumnHeaderItems(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	const auto header = m_calendar->GetHeaderFragment(GetColumn());
	if (!header)
	{
		return S_OK;
	}
	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);
	LONG index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, header.query<IRawElementProviderSimple>().get()));
	*pRetVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

// Chooses this day as Enter does: the current day moves here, then is chosen.
HRESULT CalendarDayProvider::Choose()
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	if (!m_calendar->IsDayAvailable(m_day))
	{
		return UIA_E_ELEMENTNOTENABLED;
	}
	m_calendar->ChooseDay(m_day);
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::Select() noexcept
{
	return Choose();
}

// Only one day can be chosen, so adding is choosing when nothing else is.
IFACEMETHODIMP CalendarDayProvider::AddToSelection() noexcept
{
	if (!IsAvailable())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	int chosen = 0;
	if (m_calendar->GetChosenDay(chosen) && chosen != m_day)
	{
		return UIA_E_INVALIDOPERATION;
	}
	return Choose();
}

// A chosen day stays chosen until another is.
IFACEMETHODIMP CalendarDayProvider::RemoveFromSelection() noexcept
{
	return UIA_E_INVALIDOPERATION;
}

IFACEMETHODIMP CalendarDayProvider::get_IsSelected(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	if (!IsAvailable())
	{
		return S_OK;
	}
	int chosen = 0;
	if (m_calendar->GetChosenDay(chosen) && chosen == m_day)
	{
		*pRetVal = TRUE;
	}
	return S_OK;
}

IFACEMETHODIMP CalendarDayProvider::get_SelectionContainer(IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	m_calendar->GetProvider().query_to(pRetVal);
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP CalendarDayProvider::Invoke() noexcept
{
	return Choose();
}

// =========== A weekday's column header

CalendarHeaderProvider::CalendarHeaderProvider(_In_ CalendarControl* calendar, int column)
	: CalendarPartT(calendar), m_column(column)
{
}

int CalendarHeaderProvider::GetPart()
{
	return m_column;
}

int CalendarHeaderProvider::GetRow()
{
	return -1;
}

int CalendarHeaderProvider::GetColumn()
{
	return m_column;
}

int CalendarHeaderProvider::GetKind() const noexcept
{
	return c_headerKind;
}

int CalendarHeaderProvider::GetKey() const noexcept
{
	return m_column;
}

CONTROLTYPEID CalendarHeaderProvider::GetControlType() const noexcept
{
	return UIA_HeaderItemControlTypeId;
}

std::wstring CalendarHeaderProvider::GetName()
{
	return m_calendar->GetWeekdayName(m_column);
}

bool CalendarHeaderProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	UNREFERENCED_PARAMETER(patternId);
	return false;
}

bool CalendarHeaderProvider::HasFocus()
{
	return false;
}

bool CalendarHeaderProvider::IsFocusable() const noexcept
{
	return false;
}

bool CalendarHeaderProvider::IsEnabled()
{
	return true;
}
