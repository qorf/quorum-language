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
	#if LOG
	log("TextFieldProvider::get_ProviderOptions Start");
	#endif

	*pRetVal = ProviderOptions_ServerSideProvider;

	#if LOG
	log("TextFieldProvider::get_ProviderOptions Finished");
	#endif

	return S_OK;
}

HRESULT __stdcall TextFieldProvider::GetPatternProvider(PATTERNID patternId, IUnknown** pRetVal)
{
	#if LOG
	log("TextFieldProvider::GetPatternProvider Start");
	#endif

	switch (patternId)
	{
		case UIA_TextPatternId:
			*pRetVal = static_cast<ITextProvider*>(this);
			break;
		case UIA_ValuePatternId:
			*pRetVal = static_cast<IValueProvider*>(this);
			break;
		default:
			*pRetVal = NULL;
	}

	if (*pRetVal != NULL) {
		(static_cast<IUnknown*>(*pRetVal))->AddRef();
	}

	#if LOG
	log("TextFieldProvider::GetPatternProvider Finish");
	#endif

	return S_OK;
}

HRESULT __stdcall TextFieldProvider::GetPropertyValue(PROPERTYID propertyId, VARIANT* pRetVal)
{
	#if LOG
	log("TextFieldProvider::GetPropertyValue Start" + propertyId);
	#endif

	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = textFieldControl->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"text_field");
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(textFieldControl->GetName());
	}
	//else if (propertyId == UIA_ValueValuePropertyId)
	//{
	//	pRetVal->vt = VT_BSTR;
	//	BSTR r;
	//	get_Value(&r);
	//	pRetVal->bstrVal = r;
	//}
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

	#if LOG
	log("TextFieldProvider::GetPropertyValue Finish");
	#endif

	return S_OK;
}

HRESULT __stdcall TextFieldProvider::get_HostRawElementProvider(IRawElementProviderSimple** pRetVal)
{
	#if LOG
	log("TextFieldProvider::get_HostRawElementProvider Start");
	#endif

	HRESULT face = UiaHostProviderFromHwnd(textFieldControl->GetHWND(), pRetVal);

	#if LOG
	log("TextFieldProvider::get_HostRawElementProvider Finish");
	#endif

	return face;
}

void TextFieldProvider::NotifyFocusGained()
{
	#if LOG
	log("TextFieldProvider::NotifyFocusGained Start");
	#endif

	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}

	#if LOG
	log("TextFieldProvider::NotifyFocusGained Finish");
	#endif
}

IFACEMETHODIMP_(ULONG) TextFieldProvider::AddRef()
{
	#if LOG
	log("TextFieldProvider::AddRef Start");
	#endif

	long val = InterlockedIncrement(&referenceCount);

	#if LOG
	log("TextFieldProvider::AddRef Finish");
	#endif

	return val;
}

IFACEMETHODIMP_(ULONG) TextFieldProvider::Release()
{
	#if LOG
	log("TextFieldProvider::Release Start");
	#endif

	long val = InterlockedDecrement(&referenceCount);
	if (val == 0)
	{
		delete this;
	}

	#if LOG
	log("TextFieldProvider::Release Finish: ");
	#endif

	return val;
}

IFACEMETHODIMP TextFieldProvider::QueryInterface(REFIID riid, void** ppInterface)
{
	#if LOG
	log("TextFieldProvider::QueryInterface Start");
	#endif

	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
#if LOG
		log("TextFieldProvider::QueryInterface Finish (IRawElementProviderSimple Unkown Case)");
#endif
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
#if LOG
		log("TextFieldProvider::QueryInterface Finish (IRawElementProviderSimple)");
#endif
		
	}
	else if (riid == __uuidof(IValueProvider))
	{
		*ppInterface = static_cast<IValueProvider*>(this);
	#if LOG
		log("TextFieldProvider::QueryInterface Finish (IValueProvider)");
	#endif
	}
	else if (riid == __uuidof(ITextProvider))
	{
		*ppInterface = static_cast<ITextProvider*>(this);
		#if LOG
			log("TextFieldProvider::QueryInterface Finish (TextProvider)");
		#endif
	}
	else
	{
		#if LOG
		log("TextFieldProvider::QueryInterface Finish (E_NOINTERFACE)");
		#endif
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();

	#if LOG
	log("TextFieldProvider::QueryInterface Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal)
{
	#if LOG
	log("TextFieldProvider::GetSelection Start");
	#endif

	Range caretRange = textFieldControl->GetSelectionRange();


	ITextRangeProvider* selectionRangeProvider = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, caretRange);
	HRESULT hr = S_OK;
	if (selectionRangeProvider == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		*retVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
		if (*retVal == NULL)
		{
			hr = E_OUTOFMEMORY;
		}
		else
		{
			long index = 0;
			hr = SafeArrayPutElement(*retVal, &index, selectionRangeProvider);
			if (FAILED(hr))
			{
				SafeArrayDestroy(*retVal);
				*retVal = NULL;
			}
		}
		selectionRangeProvider->Release();
	}

	#if LOG
	log("TextFieldProvider::GetSelection Finish");
	#endif

	return hr;
}

IFACEMETHODIMP TextFieldProvider::GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal)
{
	#if LOG
	log("TextFieldProvider::GetVisibleRanges Start");
	#endif

	// Not Implemented yet.
	*retVal = NULL;

	#if LOG
	log("TextFieldProvider::GetVisibleRanges Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::RangeFromChild Start");
	#endif

	UNREFERENCED_PARAMETER(childElement);

	// There are no children of this text control
	*retVal = NULL;

	#if LOG
	log("TextFieldProvider::RangeFromChild Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::RangeFromPoint Start");
	#endif

	/*
	*	Since the textfield control is a 1x1 pixel box the given UiaPoint isn't going to be correct. So if UIA calls this
	*	function then we'll report the caret position as the closest RangeFromPoint since Quorum handles mouse events.
	*/
	UNREFERENCED_PARAMETER(screenLocation); // This will never be used. Instead we get the point from Quorum.


	int caretPosition = textFieldControl->GetCaretPosition();
	Range closestRange(caretPosition, caretPosition);

	*retVal = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, closestRange);

	#if LOG
	log("TextFieldProvider::RangeFromPoint Finish");
	#endif

	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal)
{
	#if LOG
	log("TextFieldProvider::get_DocumentRange Start");
	#endif

	// Get the full text range that encompasses the document. From the first character on the first line
	// all the way to the last character on the last line.
	Range fullDocumentRange = { { 0 }, textFieldControl->GetTextFieldEndpoint() };

	*retVal = new TextFieldTextRange(textFieldControl->GetHWND(), textFieldControl, fullDocumentRange);

	#if LOG
	log("TextFieldProvider::get_DocumentRange Finish");
	#endif

	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal)
{
	#if LOG
	log("TextFieldProvider::get_SupportedTextSelection Start");
	#endif

	#if LOG
	log("TextFieldProvider::get_SupportedTextSelection Finish");
	#endif

	*retVal = SupportedTextSelection_Single;
	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::get_IsReadOnly(BOOL* returnValue)
{
	#if LOG
	log("TextFieldProvider::get_IsReadOnly Start");
	#endif

	// Currently hard-coded to false -- Quorum text fields can't be read-only in the current version.
	*returnValue = VARIANT_FALSE;

	#if LOG
	log("TextFieldProvider::get_IsReadOnly Finish");
	#endif

	return S_OK;
}

IFACEMETHODIMP TextFieldProvider::SetValue(LPCWSTR value)
{
	#if LOG
	log("TextFieldProvider::SetValue Start");
	#endif

	// NYI
	
	#if LOG
	log("TextFieldProvider::SetValue Finish");
	#endif

	return UIA_E_NOTSUPPORTED;
}

IFACEMETHODIMP TextFieldProvider::get_Value(BSTR* returnValue)
{
	#if LOG
	log("TextFieldProvider::get_Value Start");
	#endif

	std::wstring text = textFieldControl->GetText();
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));

	#if LOG
	log("TextFieldProvider::get_Value Finish");
	#endif

	return S_OK;
}
