#pragma once

#include "RadioButtonControl.h"
#include "ProviderT.h"

class RadioButtonProvider : public ProviderT<RadioButtonProvider, RadioButtonControl, ISelectionItemProvider>
{
public:
	RadioButtonProvider(RadioButtonControl* pButtonControl);

	// Overridden functions from ProviderT.
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;

	// ISelectionItemProvider methods
	IFACEMETHODIMP Select();
	IFACEMETHODIMP AddToSelection();
	IFACEMETHODIMP RemoveFromSelection();
	IFACEMETHODIMP get_IsSelected(_Out_ BOOL * pRetVal);
	IFACEMETHODIMP get_SelectionContainer(_Outptr_result_maybenull_ IRawElementProviderSimple **pRetVal);

	// Other methods.
	void NotifyElementSelected();
};
