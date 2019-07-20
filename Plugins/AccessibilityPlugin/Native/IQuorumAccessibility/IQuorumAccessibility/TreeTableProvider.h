#pragma once
#include "TreeTableControl.h"

class TreeTableControl;

class TreeTableProvider : public SpreadsheetProvider
{
public:
	TreeTableProvider(_In_ TreeTableControl* control);
	virtual ~TreeTableProvider();

	//ISelectionProvider overrides
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal);
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal);
private:
};
