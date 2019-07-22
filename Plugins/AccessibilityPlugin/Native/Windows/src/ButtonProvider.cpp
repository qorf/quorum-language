#define INITGUID
#include <windows.h>
#include <UIAutomation.h>

#include "ButtonProvider.h"
#include "ButtonControl.h"

ButtonProvider::ButtonProvider(ButtonControl* pButtonControl) : m_refCount(1), m_pButtonControl(pButtonControl)
{
	// Nothing to do.
	#if LOG
		log("ButtonProvider::ButtonProvider");
	#endif
}

ButtonProvider::~ButtonProvider()
{
	// Nothing to do.
	#if LOG
		log("ButtonProvider::ButtonProvider");
	#endif
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ButtonProvider::AddRef()
{
	#if LOG
		log("ButtonProvider::AddRef start");
	#endif
	long val = InterlockedIncrement(&m_refCount);

	#if LOG
		log("ButtonProvider::AddRef end");
	#endif
	return val;
}

IFACEMETHODIMP_(ULONG) ButtonProvider::Release()
{
	#if LOG
		log("ButtonProvider::Release start");
	#endif
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	#if LOG
		log("ButtonProvider::Release end");
	#endif
	return val;
}

IFACEMETHODIMP ButtonProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	#if LOG
		log("ButtonProvider::QueryInterface start");
	#endif
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IRawElementProviderSimple))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else if (riid == __uuidof(IInvokeProvider))
	{
		*ppInterface = static_cast<IInvokeProvider*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	#if LOG
		log("ButtonProvider::QueryInterface end");
	#endif
	return S_OK;
}


// =========== IRawElementProviderSimple implementation

// Get provider options.
IFACEMETHODIMP ButtonProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	#if LOG
		log("ButtonProvider::get_ProviderOptions start");
	#endif
	*pRetVal = ProviderOptions_ServerSideProvider;// | ProviderOptions_UseComThreading;
	#if LOG
		log("ButtonProvider::get_ProviderOptions end");
	#endif
	return S_OK;
}

// Get the object that supports IInvokePattern.
IFACEMETHODIMP ButtonProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	#if LOG
		log("ButtonProvider::GetPatternProvider start");
	#endif

	if (patternId == UIA_InvokePatternId)
	{
		AddRef();
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
	}
	else
	{
		*pRetVal = NULL;
	}
	#if LOG
		log("ButtonProvider::GetPatternProvider end");
	#endif
	return S_OK;
}

// Gets custom properties.
IFACEMETHODIMP ButtonProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	#if LOG
		log("ButtonProvider::GetPropertyValue start");
	#endif

	if (propertyId == UIA_AutomationIdPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		ULONG Id = m_pButtonControl->GetHashCode();

		pRetVal->bstrVal = SysAllocString(std::to_wstring(Id).c_str());
	}
	else if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"button");
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pButtonControl->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pButtonControl->GetDescription());
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_ButtonControlTypeId;
	}
	else if (propertyId == UIA_IsInvokePatternAvailablePropertyId)
	{
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
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
		// Tells the screen reader that this control is capable of getting keyboard focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;

	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		// UIA_HasKeyboardFocusPropertyId is responsible for whether or not the screen reader announces that this control gained focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pButtonControl->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else
	{
		pRetVal->vt = VT_EMPTY;
		// UI Automation will attempt to get the property from the host window provider.
		// If the property is found then it will have the UI Automation defaults listed in the Microsoft Developer's Network documentation.
		// More often than not the default values are responsible for a control not functioning properly with a screen reader.
	}
	#if LOG
		log("ButtonProvider::GetPropertyValue end");
	#endif
	return S_OK;
}

// Gets the UI Automation provider for the host window. This provider supplies most properties.
IFACEMETHODIMP ButtonProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	#if LOG
		log("ButtonProvider::get_HostRawElementProvider start");
	#endif

		HRESULT face = UiaHostProviderFromHwnd(m_pButtonControl->GetHWND(), pRetVal);

	#if LOG
		log("ButtonProvider::get_HostRawElementProvider end");
	#endif
	return face;
}


// =========== IInvokeProvider implementation.

// Invoke
IFACEMETHODIMP ButtonProvider::Invoke()
{
	#if LOG
		log("ButtonProvider::Invoke start");
	#endif
	PostMessage(m_pButtonControl->GetHWND(), QUORUM_INVOKEBUTTON, NULL, NULL);
	#if LOG
		log("ButtonProvider::Invoke end");
	#endif
	return S_OK;
}

// =========== Other Methods
void ButtonProvider::NotifyFocusGained()
{
	#if LOG
		log("ButtonProvider::NotifyFocusGained start");
	#endif
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
	#if LOG
		log("ButtonProvider::NotifyFocusGained end");
	#endif
}
