#include "TextFieldProvider.h"
#include "TextFieldControl.h"
#include "TextFieldTextRange.h"

TextFieldProvider::TextFieldProvider(TextFieldControl* parentControl) : referenceCount(1), textFieldControl(parentControl)
{
	// No further work necessary -- we've already set the fields we care about in the constructor header above.
}

TextFieldProvider::~TextFieldProvider()
{
}

HRESULT __stdcall TextFieldProvider::get_ProviderOptions(ProviderOptions* pRetVal)
{
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

HRESULT __stdcall TextFieldProvider::GetPatternProvider(PATTERNID patternId, IUnknown** pRetVal)
{
	switch (patternId)
	{
		case UIA_TextPatternId:
		case UIA_ValuePatternId:
			*pRetVal = static_cast<IRawElementProviderSimple*>(this);
			break;
		default:
			*pRetVal = NULL;
	}

	return S_OK;
}

HRESULT __stdcall TextFieldProvider::GetPropertyValue(PROPERTYID propertyId, VARIANT* pRetVal)
{
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"text field");
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(textFieldControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(textFieldControl->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_EditControlTypeId;
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
		// This tells the screen reader whether or not the control can be interacted with.
		// Hardcoded to true but this property could be dynamic depending on the needs of the Quorum GUI.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = textFieldControl->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (propertyId == UIA_IsPasswordPropertyId)
	{
		/*
		Identifies the IsPassword property, which is a Boolean value that indicates whether
		the automation element contains protected content or a password.

		When the IsPassword property is TRUE and the element has the keyboard focus, a client
		application should disable keyboard echoing or keyboard input feedback that may expose
		the user's protected information. Attempting to access the Value property of the protected
		element (edit control) may cause an error to occur.

		For now, this will be hardcoded as FALSE but in the future this can be maintained in Quorum and
		updated down here when textboxes contain protected info.
		*/

		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_FALSE;
	}
	//else if (propertyId == UIA_LabeledByPropertyId)
	//{
	/*
	If there is a static text label associated with this control, then this property must expose
	a reference to this control. If the TextBox is a subcomponent of another control,
	it will not have a LabeledBy property set. E.g., it's contained within a tab.
	*/

	//}
	else
	{
		pRetVal->vt = VT_EMPTY;
		// UI Automation will attempt to get the property from the host window provider.
		// If the property is found then it will have the UI Automation defaults listed in the Microsoft Developer's Network documentation.
		// More often than not the default values are responsible for a control not functioning properly with a screen reader.
	}

	return S_OK;
}

HRESULT __stdcall TextFieldProvider::get_HostRawElementProvider(IRawElementProviderSimple** pRetVal)
{

	HRESULT face = UiaHostProviderFromHwnd(textFieldControl->GetHWND(), pRetVal);

	return face;
}

void TextFieldProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}

IFACEMETHODIMP_(ULONG) TextFieldProvider::AddRef()
{
	long val = InterlockedIncrement(&referenceCount);

	return val;
}

IFACEMETHODIMP_(ULONG) TextFieldProvider::Release()
{
	long val = InterlockedDecrement(&referenceCount);
	if (val == 0)
	{
		delete this;
	}

	return val;
}

IFACEMETHODIMP TextFieldProvider::QueryInterface(REFIID riid, void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IValueProvider))
	{
		*ppInterface = static_cast<IValueProvider*>(this);
	}
	else if (riid == __uuidof(ITextProvider))
	{
		*ppInterface = static_cast<ITextProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::GetSelection(SAFEARRAY** retVal)
{
	// NYI
	return NULL;
}

IFACEMETHODIMP TextFieldProvider::GetVisibleRanges(SAFEARRAY** retVal)
{
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// Not Implemented yet.
	*retVal = NULL;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromChild(IRawElementProviderSimple* childElement, ITextRangeProvider** retVal)
{
	UNREFERENCED_PARAMETER(childElement);
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// There are no children of this text control
	*retVal = NULL;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromPoint(UiaPoint screenLocation, ITextRangeProvider** retVal)
{
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	/*
	*	Since the textfield control is a 1x1 pixel box the given UiaPoint isn't going to be correct. So if UIA calls this
	*	function then we'll report the caret position as the closest RangeFromPoint since Quorum handles mouse events.
	*/
	UNREFERENCED_PARAMETER(screenLocation); // This will never be used. Instead we get the point from Quorum.


	int caretPosition = textFieldControl->GetCaretPosition();
	Range closestRange(caretPosition, caretPosition);

	*retVal = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, closestRange);
	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_DocumentRange(ITextRangeProvider** retVal)
{
	// NYI
	return NULL;
}

IFACEMETHODIMP TextFieldProvider::get_SupportedTextSelection(SupportedTextSelection* retVal)
{
	if (!IsWindow(textFieldControl->GetHWND()))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*retVal = SupportedTextSelection_Single;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_IsReadOnly(BOOL* returnValue)
{
	// Currently hard-coded to false -- Quorum text fields can't be read-only in the current version.
	*returnValue = VARIANT_TRUE;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::SetValue(LPCWSTR value)
{
	// NYI
	return E_NOTIMPL;
}

IFACEMETHODIMP TextFieldProvider::get_Value(BSTR* returnValue)
{
	// NYI
	return E_NOTIMPL;
}
