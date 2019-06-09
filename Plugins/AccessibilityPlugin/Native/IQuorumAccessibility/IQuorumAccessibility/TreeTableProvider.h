#pragma once
#include "TreeTableControl.h"

class TreeTableControl;

class TreeTableProvider : public SpreadsheetProvider,
						  public ISelectionProvider
{
public:
	TreeTableProvider(_In_ TreeTableControl* control);
	virtual ~TreeTableProvider();

	//ISelectionProvider
	IFACEMETHODIMP get_CanSelectMultiple(BOOL* pRetVal);
	IFACEMETHODIMP get_IsSelectionRequired(BOOL* pRetVal);
	IFACEMETHODIMP GetSelection(SAFEARRAY** pRetVal);
private:
};