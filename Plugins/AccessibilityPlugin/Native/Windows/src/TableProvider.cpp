#include "TableProvider.h"
#include "CellProvider.h"

TableProvider::TableProvider(_In_ TableControl* control) : ProviderT(control)
{
}

bool TableProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_GridPatternId) || (patternId == UIA_TablePatternId) || (patternId == UIA_SelectionPatternId));
}

CONTROLTYPEID TableProvider::GetControlType() const noexcept
{
	return UIA_DataGridControlTypeId;
}

//IGridProvider Methods
IFACEMETHODIMP TableProvider::get_ColumnCount(int* pRetVal) {

	JNIEnv* env = GetJNIEnv();

	jint count = 0;
	if (env != NULL)
	{
		count = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTableColumnsSize, m_control->GetMe());
		*pRetVal = (int)count;
	}
	*pRetVal = count;
	return S_OK;
}

IFACEMETHODIMP TableProvider::get_RowCount(int* pRetVal) {
	JNIEnv* env = GetJNIEnv();

	jint count = 0;
	if (env != NULL)
	{
		count = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTableRowsSize, m_control->GetMe());
		*pRetVal = (int)count;
	}
	*pRetVal = count;
	return S_OK;
}
IFACEMETHODIMP TableProvider::GetItem(int row, int column, IRawElementProviderSimple** pRetVal) {
	return S_OK;
}

//ITableProvider Methods
IFACEMETHODIMP TableProvider::get_RowOrColumnMajor(RowOrColumnMajor* major) {
	return S_OK;
}
IFACEMETHODIMP TableProvider::GetColumnHeaders(SAFEARRAY** pRetVal) {
	return S_OK;
}
IFACEMETHODIMP TableProvider::GetRowHeaders(SAFEARRAY** pRetVal) {
	return S_OK;
}

IFACEMETHODIMP TableProvider::GetSelection(SAFEARRAY** pRetVal)
{
	CellControl* cellControl = m_control->GetSelected();

	if (!cellControl)
	{
		*pRetVal = NULL;
		return S_OK;
	}

	CellProvider* cellProvider = cellControl->GetProvider().get();

	HRESULT hr = S_OK;

	*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	if (*pRetVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		long index = 0;
		hr = SafeArrayPutElement(*pRetVal, &index, cellProvider);
		if (FAILED(hr))
		{
			SafeArrayDestroy(*pRetVal);
			*pRetVal = NULL;
		}
		else
		{
			// Since the provider is being passed out of our domain, we need to increment its reference counter.
			cellProvider->AddRef();
		}
	}

	return hr;
}

IFACEMETHODIMP TableProvider::get_CanSelectMultiple(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}

IFACEMETHODIMP TableProvider::get_IsSelectionRequired(BOOL* pRetVal)
{
	*pRetVal = false;
	return S_OK;
}
