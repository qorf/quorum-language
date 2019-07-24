#pragma once

#include "ControlT.h"

class TableProvider;
class CellControl;

class TableControl : public ControlT<TableControl, TableProvider>
{
public:
	TableControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem);

	CellControl* GetSelected();
	void SetSelected(CellControl* selected);

private:
	CellControl* m_selected = nullptr;
};
