#include "TreeTableProvider.h"

//reference count and the control are automatic in the parent class.
TreeTableProvider::TreeTableProvider(_In_ TreeTableControl* control)
	:SpreadsheetProvider(control)
{
}

TreeTableProvider::~TreeTableProvider()
{
}

//ISelectionProvider
IFACEMETHODIMP TreeTableProvider::get_CanSelectMultiple(BOOL* pRetVal) {
	*pRetVal = false; //should this be true for tree table? Possibly?
	return S_OK;
}

IFACEMETHODIMP TreeTableProvider::get_IsSelectionRequired(BOOL* pRetVal) {
	*pRetVal = true;
	return S_OK;
}
