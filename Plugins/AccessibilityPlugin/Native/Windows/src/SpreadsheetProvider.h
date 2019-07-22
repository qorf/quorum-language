#pragma once
#include "SpreadsheetControl.h"

class SpreadsheetControl;

class SpreadsheetProvider : public IRawElementProviderSimple,
							public IGridProvider,
							public ITableProvider,
							public ISelectionProvider
{
public:
	SpreadsheetProvider(_In_ SpreadsheetControl* control);
	virtual ~SpreadsheetProvider();

	// IUnknown methods
	IFACEMETHODIMP_(ULONG) AddRef();
	IFACEMETHODIMP_(ULONG) Release();
	IFACEMETHODIMP QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface);

	// IRawElementProviderSimple methods
	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions* pRetVal);
	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal);
	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal);
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal);

	//IGridProvider Methods
	IFACEMETHODIMP get_ColumnCount(int* pRetVal);
	IFACEMETHODIMP get_RowCount(int* pRetVal);
	IFACEMETHODIMP GetItem(int row, int column, IRawElementProviderSimple** pRetVal);

	//ITableProvider Methods
	IFACEMETHODIMP get_RowOrColumnMajor(RowOrColumnMajor* major);
	IFACEMETHODIMP GetColumnHeaders(SAFEARRAY** pRetVal);
	IFACEMETHODIMP GetRowHeaders(SAFEARRAY** pRetVal);

	// ISelectionProvider methods
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal) override;
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal) override;
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal) override;

protected:
	
	ULONG m_refCount;

	SpreadsheetControl* control;
};