#pragma once

#include "ControlT.h"

class CellProvider;
class TableControl;

class CellControl : public ControlT<CellControl, CellProvider>
{
public:
	CellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ TableControl* parent, jobject jItem, bool isTreeCell, bool isExpanded);

	TableControl* GetParentTable();
	bool IsTreeTableCell();
	bool IsExpanded();
	void SetExpanded(bool expanded);

	// Gets the text contained within the cell from Quorum.
	std::wstring GetText();

private:
	TableControl* m_parentTable;
	bool m_isTreeCell;
	bool m_isExpanded;
};
