#pragma once

#include "TableControl.h"
#include "ProviderT.h"

class TableProvider : public ProviderT<TableProvider, TableControl, IGridProvider, ITableProvider, ISelectionProvider>
{
public:
	TableProvider(_In_ TableControl* control);

	// ProviderT
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	//IGridProvider Methods
	IFACEMETHODIMP get_ColumnCount(int* pRetVal);
	IFACEMETHODIMP get_RowCount(int* pRetVal);
	IFACEMETHODIMP GetItem(int row, int column, IRawElementProviderSimple** pRetVal);

	//ITableProvider Methods
	IFACEMETHODIMP get_RowOrColumnMajor(RowOrColumnMajor* major);
	IFACEMETHODIMP GetColumnHeaders(SAFEARRAY** pRetVal);
	IFACEMETHODIMP GetRowHeaders(SAFEARRAY** pRetVal);

	// ISelectionProvider methods
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal) noexcept override;
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal) noexcept override;
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal) noexcept override;
};