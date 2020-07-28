#include "TableProvider.h"
#include "CellProvider.h"

TableProvider::TableProvider(_In_ TableControl* control) : ProviderT(control)
{
}

bool TableProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return ((patternId == UIA_GridPatternId) || (patternId == UIA_SelectionPatternId));
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
	JNIEnv* env = GetJNIEnv();

	jlong pointer = 0;
	if (env != NULL)
	{
		std::cout << row << ", " << column << std::endl;
		pointer = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTableItem,
			m_control->GetMe(), row, column);
		if (pointer == 0) {
			return S_OK; //nothing was found, but ok.
		}
		CellControl* control = (CellControl*)pointer;
		CellProvider* cellProvider = control->GetProvider().get();
		*pRetVal = cellProvider;
	}
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

IFACEMETHODIMP TableProvider::GetSelection(SAFEARRAY** retVal) noexcept try
{
	const auto cellControl = m_control->GetSelected();

	if (!cellControl)
	{
		*retVal = NULL;
		return S_OK;
	}

	const auto provider = cellControl->GetProvider();

	HRESULT hr = S_OK;

	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, 1) };
	THROW_IF_NULL_ALLOC(sa);

	long index = 0;
	THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &index, provider.query<IUnknown>().get()));

	*retVal = sa.release();
	return S_OK;
}
CATCH_RETURN();

IFACEMETHODIMP TableProvider::get_CanSelectMultiple(BOOL* pRetVal) noexcept
{
	*pRetVal = false;
	return S_OK;
}

IFACEMETHODIMP TableProvider::get_IsSelectionRequired(BOOL* pRetVal) noexcept
{
	*pRetVal = false;
	return S_OK;
}
