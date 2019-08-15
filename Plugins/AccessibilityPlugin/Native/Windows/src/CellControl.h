#pragma once

#include "ControlT.h"

class CellProvider;
class TableControl;

class CellControl : public ControlT<CellControl, CellProvider>
{
public:
	CellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ TableControl* parent, jobject jItem);

	TableControl* GetParentTable();

	// Gets the text contained within the cell from Quorum.
	std::wstring GetText();

private:
	TableControl* m_parentTable;
};
