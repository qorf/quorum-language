#pragma once

#include "SpreadsheetControl.h"
#include "ProviderT.h"

/*
	UI Automation's view of a Quorum Spreadsheet: the data grid control
	type, the grid pattern (the rows and columns shown, its values and the
	blank ones after them), the table pattern (the column letters and row
	numbers as headers) and the selection pattern (the selected cells).
	Its children are its Quorum children, such as the edit field while a
	cell is edited, then the parts on screen (SpreadsheetPartProvider.h).
*/
class SpreadsheetProvider : public ProviderT<SpreadsheetProvider, SpreadsheetControl, IGridProvider, ITableProvider, ISelectionProvider>
{
public:
	SpreadsheetProvider(SpreadsheetControl* control);

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
