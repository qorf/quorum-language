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

IFACEMETHODIMP TreeTableProvider::GetSelection(SAFEARRAY** pRetVal) {
	//JNIEnv* env = GetJNIEnv();
	//long selectionPointer = env->CallStaticLongMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetTabPaneSelectionPointer, control->GetMe());

	//if (selectionPointer == 0)
	//{
	//	*pRetVal = NULL;
	//	return S_OK;
	//}

	//ListItemControl* theControl = reinterpret_cast<ListItemControl*>(selectionPointer);
	//control->SetSelected(theControl);
	//ListItemProvider* theProvider = theControl->GetProvider();

	//HRESULT hr = S_OK;

	//*pRetVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	//if (*pRetVal == NULL)
	//{
	//	hr = E_OUTOFMEMORY;
	//}
	//else
	//{
	//	long index = 0;
	//	hr = SafeArrayPutElement(*pRetVal, &index, theProvider);
	//	if (FAILED(hr))
	//	{
	//		SafeArrayDestroy(*pRetVal);
	//		*pRetVal = NULL;
	//	}
	//	else
	//	{
	//		// Since the provider is being passed out of our domain, we need to increment its reference counter.
	//		theProvider->AddRef();
	//	}
	//}

	//return hr;
	return S_OK;
}
