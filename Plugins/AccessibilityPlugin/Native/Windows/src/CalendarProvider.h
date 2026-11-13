#pragma once

#include "CalendarControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum Calendar: the calendar control type,
	the grid pattern (six rows of seven days), the table pattern (the
	weekday names as column headers) and the selection pattern (the chosen
	day). Its children are the month buttons, then the weekday headers and
	the days (CalendarDayProvider.h).
*/
class CalendarProvider : public ProviderT<CalendarProvider, CalendarControl, IGridProvider, ITableProvider, ISelectionProvider>
{
public:
	CalendarProvider(CalendarControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// IRawElementProviderFragment: the first and last children include the parts.
	IFACEMETHODIMP Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override;

	// IGridProvider
	IFACEMETHODIMP get_RowCount(int* pRetVal) noexcept override;
	IFACEMETHODIMP get_ColumnCount(int* pRetVal) noexcept override;
	IFACEMETHODIMP GetItem(int row, int column, IRawElementProviderSimple** pRetVal) noexcept override;

	// ITableProvider
	IFACEMETHODIMP GetRowHeaders(SAFEARRAY** pRetVal) noexcept override;
	IFACEMETHODIMP GetColumnHeaders(SAFEARRAY** pRetVal) noexcept override;
	IFACEMETHODIMP get_RowOrColumnMajor(RowOrColumnMajor* pRetVal) noexcept override;

	// ISelectionProvider
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal) noexcept override;
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal) noexcept override;
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal) noexcept override;
};
