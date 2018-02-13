/*************************************************************************************************
* Description: Implementation of the Provider class, which implements IRawElementProviderSimple
* and IInvokeProvider for a simple custom control.
*
*  Copyright (C) Microsoft Corporation.  All rights reserved.
*
* This source code is intended only as a supplement to Microsoft
* Development Tools and/or on-line documentation.  See these other
* materials for detailed information regarding Microsoft code samples.
*
* THIS CODE AND INFORMATION ARE PROVIDED AS IS WITHOUT WARRANTY OF ANY
* KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
* PARTICULAR PURPOSE.
*
*************************************************************************************************/

#define INITGUID
#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include <ole2.h>
#include <UIAutomation.h>

#include "ItemProvider.h"
#include "Item.h"

ItemProvider::ItemProvider(HWND hwnd, Item* pItem) : m_refCount(1), m_controlHWnd(hwnd), m_pItem(pItem)
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
	*pRetVal = ProviderOptions_ServerSideProvider;
	return S_OK;
}

// Get the object that supports IInvokePattern.
IFACEMETHODIMP ItemProvider::GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** pRetVal)
{
	*pRetVal = NULL;
	if (patternId == UIA_InvokePatternId)
	{
		*pRetVal = static_cast<IRawElementProviderSimple*>(this);
		AddRef();
	}

	return S_OK;
}

// Gets custom properties.
IFACEMETHODIMP ItemProvider::GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* pRetVal)
{
	if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"Item");
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
	else if (propertyId == UIA_ProviderDescriptionPropertyId)
	{
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(L"An item created within Quorum.");
	}
	else if (propertyId == UIA_IsKeyboardFocusablePropertyId)
	{
		// Tells the screen reader that this control is capable of getting keyboard focus.
		// This isn't enough for the screen reader to announce the control's existence to the user when it gains focus in Quorum.
		// UIA_HasKeyboardFocusPropertyId is respondsible for whether or not the screen reader announces that this control gained focus.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
	}
	else if (propertyId == UIA_HasKeyboardFocusPropertyId)
	{
		// This tells the screen reader whether or not this control has Keyboard focus. Normally, only one control/window is allowed to have keyboard focus at a time
		// but by lying and having every instance of this control report that it has keyboard focus then we don't have to mantain what has focus on the native level.
		pRetVal->vt = VT_BOOL;
		pRetVal->boolVal = VARIANT_TRUE;
		//pRetVal->boolVal = m_pItem->GetFocus() ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (propertyId == UIA_NamePropertyId)
	{
		// The name of this item as given from Quorum.
		pRetVal->vt = VT_BSTR;
		pRetVal->bstrVal = SysAllocString(m_pItem->GetName());
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
	if (m_controlHWnd == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	return UiaHostProviderFromHwnd(m_controlHWnd, pRetVal);
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