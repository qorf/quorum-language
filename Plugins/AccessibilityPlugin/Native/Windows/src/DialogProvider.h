#pragma once
#include "DialogControl.h"
#include "ProviderT.h"

class DialogProvider : public ProviderT<DialogProvider, DialogControl, IWindowProvider>
{
public:
	DialogProvider(_In_ DialogControl* control);
	
	// Overridden functions from ProviderT.
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	IFACEMETHODIMP Close() noexcept;
	IFACEMETHODIMP get_CanMaximize(_Out_ BOOL* retVal) noexcept;
	IFACEMETHODIMP get_CanMinimize(_Out_ BOOL* retVal) noexcept;
	IFACEMETHODIMP get_IsModal(_Out_ BOOL* retVal) noexcept;
	IFACEMETHODIMP get_IsTopmost(_Out_ BOOL* retVal) noexcept;
	IFACEMETHODIMP get_WindowInteractionState(_Out_ WindowInteractionState* retVal) noexcept;
	IFACEMETHODIMP get_WindowVisualState(_Out_ WindowVisualState* retVal) noexcept;
	IFACEMETHODIMP SetVisualState(_In_ WindowVisualState retVal) noexcept;
	IFACEMETHODIMP WaitForInputIdle(_Out_ int  milliseconds, _Out_ BOOL* pRetVal) noexcept;
	void GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const;
};