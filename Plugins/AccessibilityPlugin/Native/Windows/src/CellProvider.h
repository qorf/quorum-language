#pragma once
#include "CellControl.h"
#include "ProviderT.h"

class CellProvider : public ProviderT<CellProvider, CellControl, ITableItemProvider, IGridItemProvider, IExpandCollapseProvider, IValueProvider, ISelectionItemProvider>
{
public:
	CellProvider(CellControl* pControl);

	// IGridItemProvider methods
	IFACEMETHODIMP get_Column(int* pRetVal);
	IFACEMETHODIMP get_ColumnSpan(int* pRetVal);
	IFACEMETHODIMP get_ContainingGrid(IRawElementProviderSimple** pRetVal);
	IFACEMETHODIMP get_Row(int* pRetVal);
	IFACEMETHODIMP get_RowSpan(int* pRetVal);

	// ITableItemProvider Methods
	IFACEMETHODIMP GetColumnHeaderItems(SAFEARRAY** pRetVal);
	IFACEMETHODIMP GetRowHeaderItems(SAFEARRAY** pRetVal);

	// IExpandCollapseProvider methods
	IFACEMETHODIMP get_ExpandCollapseState(_Out_ ExpandCollapseState* pRetVal);
	IFACEMETHODIMP Expand();
	IFACEMETHODIMP Collapse();

	// Methods from IValueProvider.
	IFACEMETHODIMP get_IsReadOnly(BOOL* returnValue);
	IFACEMETHODIMP SetValue(LPCWSTR value);
	IFACEMETHODIMP get_Value(BSTR* returnValue);

	// Inherited via ISelectionItemProvider
	IFACEMETHODIMP Select(void) override;
	IFACEMETHODIMP AddToSelection(void) override;
	IFACEMETHODIMP RemoveFromSelection(void) override;
	IFACEMETHODIMP get_IsSelected(BOOL* pRetVal) override;
	IFACEMETHODIMP get_SelectionContainer(IRawElementProviderSimple** pRetVal) override;

	// Additional methods.
	void NotifyElementSelected();

private:
	ExpandCollapseState expanded;
	// Ref Counter for this COM object
	TableControl* parent;

	void NotifyElementExpandCollapse();
};