#include "TableControl.h"
#include "TableProvider.h"
#include "CellControl.h"
#include "CellProvider.h"
#include "ControlTImpl.h"

TableControl::TableControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

CellControl* TableControl::GetSelected()
{
	return m_selected;
}

void TableControl::SetSelected(_In_opt_ CellControl* cell)
{
	m_selected = cell;
	if (cell != nullptr && UiaClientsAreListening())
	{
		cell->GetProvider()->NotifyElementSelected();
	}
}
