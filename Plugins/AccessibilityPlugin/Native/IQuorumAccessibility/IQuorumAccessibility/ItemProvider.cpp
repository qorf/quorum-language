#include <windows.h>
#include <UIAutomation.h>
#include <iostream>
#include "ItemProvider.h"
#include "ItemControl.h"
#include "Item.h"

ItemProvider::ItemProvider(ItemControl* pItem) : m_refCount(1), m_pItem(pItem)
{
	// Nothing to do.
}

ItemProvider::~ItemProvider()
{
	// Nothing to do.
}

// =========== IUnknown implementation.

IFACEMETHODIMP_(ULONG) ItemProvider::AddRef()
{
	return InterlockedIncrement(&m_refCount);
}

IFACEMETHODIMP_(ULONG) ItemProvider::Release()
{
	long val = InterlockedDecrement(&m_refCount);
	if (val == 0)
	{
		delete this;
	}
	return val;
}

IFACEMETHODIMP ItemProvider::QueryInterface(_In_ REFIID riid, _Outptr_ void** ppInterface)
{
	if (riid == __uuidof(IUnknown))
	{
		*ppInterface = static_cast<IRawElementProviderSimple*>(this);
	}
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	(static_cast<IUnknown*>(*ppInterface))->AddRef();
	return S_OK;
}


// =========== IRawElementProviderSimple implementation

// Get provider options.
IFACEMETHODIMP ItemProvider::get_ProviderOptions(_Out_ ProviderOptions* pRetVal)
{
	*pRetVal = ProviderOptions_ServerSideProvider | ProviderOptions_UseComThreading;
	return S_OK;
}


IFACEMETHODIMP ItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	UNREFERENCED_PARAMETER(patternId);
	// ItemControl doesn't implement any control patterns so NULL is correct.
	*pRetVal = NULL;
	return S_OK;
}

// Gets custom properties.
IFACEMETHODIMP ItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"item");
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		// The name of this item as given from Quorum.
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pItem->GetName());
	}
	else if (propertyId == UIA_HelpTextPropertyId)
	{
		// This is read aloud each time the control gains focus.
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pItem->GetDescription());
	}
	else if (propertyId == UIA_ProviderDescriptionPropertyId)
	{
		// This is not read aloud by screen readers.
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"An item created within Quorum.");
	}
	else if (propertyId == UIA_ControlTypePropertyId)
	{
		pRetVal->vt = VT_I4;
		pRetVal->lVal = UIA_CustomControlTypeId;
	}
	else if (propertyId == UIA_IsEnabledPropertyId)
	{
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
		// UIA_HasKeyboardFocusPropertyId is respondsible for whether or not the screen reader announces that this control gained focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = m_pItem->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
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

// Gets the UI Automation provider for the host window. This provider supplies most properties.
IFACEMETHODIMP ItemProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** pRetVal)
{
	return UiaHostProviderFromHwnd(m_pItem->GetHWND(), pRetVal);
}

// =========== Other Methods

// Raises an event when an item is selected.
//
void ItemProvider::NotifyFocusGained()
{
	if (UiaClientsAreListening())
	{
		UiaRaiseAutomationEvent(this, UIA_AutomationFocusChangedEventId);
	}
}
