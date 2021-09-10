#include "TableControl.h"
#include "TableProvider.h"
#include "CellControl.h"
#include "CellProvider.h"
#include "ControlTImpl.h"
#include <iostream>

TableControl::TableControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

CellControl* TableControl::GetSelected() const noexcept
{
	return m_selected;
}

Item* TableControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelected();
}

void TableControl::SetSelected(_In_opt_ CellControl* cell)
{
	m_selected = cell;
	if (cell != nullptr && IsReadyForEvents())
	{
		cell->GetProvider()->NotifyElementSelected();
	}
}
