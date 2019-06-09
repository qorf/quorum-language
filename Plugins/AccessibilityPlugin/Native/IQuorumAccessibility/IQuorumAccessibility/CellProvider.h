#pragma once
#include "CellControl.h"
class CellControl;
class SpreadsheetControl;

class CellProvider : public IRawElementProviderSimple,
					 public ITableItemProvider,
					 public IGridItemProvider,
					 public IExpandCollapseProvider
{
public:
	CellProvider(CellControl* pControl, SpreadsheetControl* parent);

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions* pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal);

	//IGridItemProvider methods
	IFACEMETHODIMP get_Column(int* pRetVal);
	IFACEMETHODIMP get_ColumnSpan(int* pRetVal);
	IFACEMETHODIMP get_ContainingGrid(IRawElementProviderSimple** pRetVal);
	IFACEMETHODIMP get_Row(int* pRetVal);
	IFACEMETHODIMP get_RowSpan(int* pRetVal);

	//ITableItemProvider Methods
	IFACEMETHODIMP GetColumnHeaderItems(SAFEARRAY** pRetVal);
	IFACEMETHODIMP GetRowHeaderItems(SAFEARRAY** pRetVal);

	// IExpandCollapseProvider methods
	IFACEMETHODIMP get_ExpandCollapseState(_Out_ ExpandCollapseState* pRetVal);
	IFACEMETHODIMP Expand();
	IFACEMETHODIMP Collapse();
private:
	virtual ~CellProvider();
	ExpandCollapseState expanded;
	// Ref Counter for this COM object
	ULONG referenceCount;
	SpreadsheetControl* parent;

	//the control
	CellControl* control;
	void NotifyElementExpandCollapse();
};