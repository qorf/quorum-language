#pragma once

#include <UIAutomation.h>
#include <wil/com.h>
#include <wil/resource.h>
#include <wil/result.h>
#include <wrl.h>

namespace wrl = Microsoft::WRL;

// A class template used by all provider implementations.
template <class DerivedT, class ControlT, typename ...MoreInterfaces>
class ProviderT : public wrl::RuntimeClass<
	wrl::RuntimeClassFlags<wrl::RuntimeClassType::ClassicCom>,
	IRawElementProviderSimple,
	MoreInterfaces...>
{
public:
	using ControlType = ControlT;

	ProviderT(_In_ ControlT* control)
		: m_control(control)
	{
	}

	// IRawElementProviderSimple

	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions* retVal) noexcept override
	{
		// TODO: Reconsider UseComThreading when we no longer need to wait on animation frames.
		*retVal = ProviderOptions_ServerSideProvider;
		return S_OK;
	}

	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** retVal) noexcept override try
	{
		*retVal = nullptr;

		if (static_cast<DerivedT*>(this)->IsPatternSupported(patternId))
		{
			wil::com_query_to(this, retVal);
		}

		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) noexcept override try
	{
		VariantInit(retVal);

		switch (propertyId)
		{
			case UIA_FrameworkIdPropertyId:
				retVal->bstrVal = wil::make_bstr(L"Quorum").release();
				retVal->vt = VT_BSTR;
				break;

			case UIA_NamePropertyId:
				retVal->bstrVal = wil::make_bstr(m_control->GetName()).release();
				retVal->vt = VT_BSTR;
				break;

			case UIA_HelpTextPropertyId:
				retVal->bstrVal = wil::make_bstr(m_control->GetDescription()).release();
				retVal->vt = VT_BSTR;
				break;

			case UIA_ControlTypePropertyId:
				retVal->lVal = static_cast<DerivedT*>(this)->GetControlType();
				retVal->vt = VT_I4;
				break;

			case UIA_HasKeyboardFocusPropertyId:
				retVal->boolVal = m_control->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsControlElementPropertyId:
				retVal->boolVal = static_cast<DerivedT*>(this)->IsControlElement() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsContentElementPropertyId:
				retVal->boolVal = static_cast<DerivedT*>(this)->IsContentElement() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsEnabledPropertyId:
				retVal->boolVal = static_cast<DerivedT*>(this)->IsEnabled() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsKeyboardFocusablePropertyId:
				retVal->boolVal = static_cast<DerivedT*>(this)->IsKeyboardFocusable() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			default:
				static_cast<DerivedT*>(this)->GetControlSpecificPropertyValue(propertyId, retVal);
				break;
		}

		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal) noexcept override
	{
		*retVal = nullptr;

		// Temporary: Remove this from the base ProviderT once the controls are windowless.
		const auto hwnd = m_control->GetHWND();
		if (hwnd)
		{
			return UiaHostProviderFromHwnd(hwnd, retVal);
		}

		return S_OK;
	}

protected:
	bool IsPatternSupported(PATTERNID /* patternId */) const noexcept
	{
		return false;
	}

	CONTROLTYPEID GetControlType() const noexcept
	{
		return UIA_CustomControlTypeId;
	}

	bool IsControlElement() const noexcept
	{
		return true;
	}

	bool IsContentElement() const noexcept
	{
		return true;
	}

	bool IsEnabled() const noexcept
	{
		return true;
	}

	bool IsKeyboardFocusable() const noexcept
	{
		// IsKeyboardFocusable doesn't have to match IsControlElement, but it very often does.
		return static_cast<const DerivedT*>(this)->IsControlElement();
	}

	void GetControlSpecificPropertyValue(PROPERTYID /* propertyId */, _Out_ VARIANT* /* retVal */) const
	{
	}

	ControlT* m_control;
};
