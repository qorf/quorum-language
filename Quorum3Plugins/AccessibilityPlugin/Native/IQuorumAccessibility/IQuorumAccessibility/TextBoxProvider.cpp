#include "TextBoxProvider.h"
#include "TextBoxTextAreaProvider.h"

// Used to create Int SafeArray for RuntimeId
SAFEARRAY* BuildIntSafeArray(_In_reads_(length) const int * data, _In_ int length)
{
	SAFEARRAY *intSafeArray = SafeArrayCreateVector(VT_I4, 0, length);

	if (intSafeArray == NULL)
		return NULL;

	for (long i = 0; i < length; i++)
	{
		if (FAILED(SafeArrayPutElement(intSafeArray, &i, (void *)&(data[i]))))
		{
			SafeArrayDestroy(intSafeArray);
			intSafeArray = NULL;
			break;
		}
	}

	return intSafeArray;
}

TextBoxProvider::TextBoxProvider(_In_ HWND hwnd, _In_ TextBoxControl *control) : m_refCount(1), m_pTextBoxControl(control), m_textBoxControlHWND(hwnd)
{

}

TextBoxProvider::~TextBoxProvider()
{
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) TextBoxProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) TextBoxProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP TextBoxProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void**ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = (IUnknown*)((IRawElementProviderSimple*)this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = (IRawElementProviderSimple*)this;
	}
	else if (riid == __uuidof(IRawElementProviderFragment))
	{
		*ppInterface = (IRawElementProviderFragment*)this;
	}
	else if (riid == __uuidof(IRawElementProviderFragmentRoot))
	{
		*ppInterface = (IRawElementProviderFragmentRoot*)this;
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}

// =========== IRawElementProviderSimple implementation.

IFACEMETHODIMP TextBoxProvider::get_ProviderOptions(_Out_ ProviderOptions * pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = ProviderOptions_ServerSideProvider | ProviderOptions_UseComThreading;
	return S_OK;
}

// TextBoxProvider doesn't support any patterns so NULL is correct
IFACEMETHODIMP TextBoxProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown ** pRetVal)
{
	UNREFERENCED_PARAMETER(patternId);
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}


IFACEMETHODIMP TextBoxProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT * pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_PaneControlTypeId;
	}
	/*else if (propertyId == UIA_NamePropertyId)
	{
	pRetVal->vt = VT_BSTR;
	pRetVal->bstrVal = SysAllocString(L"TextBox Provider");
	}*/
	else if (propertyId == UIA_IsControlElementPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_FALSE;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_FALSE;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
		// UI Automation will attempt to get the property from the host window provider.
		// If the property is found then it will have the UI Automation defaults listed in the Microsoft Developer's Network documentation.
		// More often than not the default values are responsible for a control not functioning properly with a screen reader.
	}


	return S_OK;
}


IFACEMETHODIMP TextBoxProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple ** pRetVal)
{
	return UiaHostProviderFromHwnd(m_textBoxControlHWND, pRetVal);
}


// =========== IRawElementProviderFragment implementation.


IFACEMETHODIMP TextBoxProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;

	if (direction == NavigateDirection_FirstChild || direction == NavigateDirection_LastChild)
	{
		*pRetVal = new TextBoxTextAreaProvider(m_textBoxControlHWND, m_pTextBoxControl);
		if (*pRetVal == NULL)
		{
			return E_OUTOFMEMORY;
		}
	}

	// For the other directions (parent, next, previous) the default of NULL is correct
	// However, in the future if a textbox is embedded into something else, like a tab, then
	// parent will likely need to be implemented.
	return S_OK;
}

IFACEMETHODIMP TextBoxProvider::GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// This is the top level element. So return NULL
	*pRetVal = NULL;
	return S_OK;
}

IFACEMETHODIMP TextBoxProvider::get_BoundingRectangle(_Out_ UiaRect * pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// For now we aren't painting a rectangle for the provider
	pRetVal->left = 0;
	pRetVal->top = 0;
	pRetVal->width = 0;
	pRetVal->height = 0;
	return S_OK;
}

// Retreives other fragment roots that may be hosted in this one.
IFACEMETHODIMP TextBoxProvider::GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY ** pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	*pRetVal = NULL;
	return S_OK;
}

// Responds to the control receiving focus through a UI Automation request.
// For HWND-based controls, this is handled by the host window provider.
// So doing nothing here is fine.
IFACEMETHODIMP TextBoxProvider::SetFocus()
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	return S_OK;
}

// Retrieves the root element of this fragment.
IFACEMETHODIMP TextBoxProvider::get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot ** pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// A provider for a fragment root should return a pointer to its own implementation.
	*pRetVal = static_cast<IRawElementProviderFragmentRoot*>(this);
	AddRef();
	return S_OK;
}

// =========== IRawElementProviderFragmenRoot implementation.

// Retrieves the IRawElementProviderFragment interface for the item at the specified 
// point (in client coordinates).
IFACEMETHODIMP TextBoxProvider::ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	UNREFERENCED_PARAMETER(x);
	UNREFERENCED_PARAMETER(y);
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	
	// Since the accessible objects are 1x1 pixel boxes hidden in the corner of the application we'd need quorum to
	// give us the client coordinates.
	// Not implemented yet.
	*pRetVal = NULL;
	return S_OK;
}

IFACEMETHODIMP TextBoxProvider::GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment ** pRetVal)
{
	if (!IsWindow(m_textBoxControlHWND))
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	// The pane shouldn't be able to receive focus. 
	// It doesn't exist as far as the UI is concerned.
	*pRetVal = NULL;
	return S_OK;
}
