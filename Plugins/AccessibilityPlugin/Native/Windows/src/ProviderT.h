#pragma once

#include <UIAutomation.h>
#include <wil/com.h>
#include <wil/resource.h>
#include <wil/result.h>
#include <wrl.h>

#include "RootItemBase.h"
#include "Resources.h"

namespace wrl = Microsoft::WRL;

using unique_safearray = wil::unique_any<SAFEARRAY*, decltype(&::SafeArrayDestroy), ::SafeArrayDestroy>;

// A class template used by all provider implementations.
template <class DerivedT, class ControlT, typename ...MoreInterfaces>
class ProviderT : public wrl::RuntimeClass<
	wrl::RuntimeClassFlags<wrl::RuntimeClassType::ClassicCom>,
	IRawElementProviderSimple,
	IRawElementProviderFragment,
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
		*retVal = ProviderOptions_ServerSideProvider | ProviderOptions_UseComThreading;
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
				SetBstrPropertyValueIfNotEmpty(retVal, m_control->GetName());
				break;

			case UIA_HelpTextPropertyId:
				SetBstrPropertyValueIfNotEmpty(retVal, m_control->GetDescription());
				break;

			case UIA_ControlTypePropertyId:
				retVal->lVal = static_cast<DerivedT*>(this)->GetControlType();
				retVal->vt = VT_I4;
				break;

			case UIA_LocalizedControlTypePropertyId:
			{
				auto roleDescription = m_control->GetRoleDescription();
				SetBstrPropertyValueIfNotEmpty(retVal, roleDescription.c_str());
				break;
			}

			case UIA_HasKeyboardFocusPropertyId:
				retVal->boolVal = m_control->HasUiaFocus() ? VARIANT_TRUE : VARIANT_FALSE;
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
		return S_OK;
	}

	// IRawElementProviderFragment

	IFACEMETHODIMP Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override try
	{
		*retVal = nullptr;
		Item* destination = nullptr;

		const auto value = m_control->GetName();

		switch (direction)
		{
			case NavigateDirection_Parent:
				destination = m_control->GetParent();
				break;

			case NavigateDirection_FirstChild:
				destination = m_control->GetFirstChild();
				break;

			case NavigateDirection_LastChild:
				destination = m_control->GetLastChild();
				break;

			case NavigateDirection_PreviousSibling:
				destination = m_control->GetPreviousSibling();
				break;

			case NavigateDirection_NextSibling:
				destination = m_control->GetNextSibling();
				break;
		}

		if (destination)
		{
			*retVal = destination->GetProviderFragment().detach();
		}

		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override try
	{
		int id = m_control->GetUniqueId();
		int rid[] = { UiaAppendRuntimeId, id };

		unique_safearray sa{ SafeArrayCreateVector(VT_I4, 0, ARRAYSIZE(rid)) };
		THROW_IF_NULL_ALLOC(sa);

		for (LONG i = 0; i < ARRAYSIZE(rid); i++)
		{
			THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &i, &(rid[i])));
		}

		*retVal = sa.release();
		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP get_BoundingRectangle(_Out_ UiaRect* retVal) noexcept override
	{
		JNIEnv* env = GetJNIEnv();

		if (env == NULL)
		{
			// If the JNI environment isn't available, we can't call up to it.
			*retVal = {};
		}
		else if (m_control == NULL || m_control->GetMe() == NULL)
		{
			// If there's no control to find, we don't try to reference its Quorum values.
			*retVal = {};
		}
		else
		{
			jdoubleArray valuesArray = (jdoubleArray)(env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetBoundingRectangle, m_control->GetMe()));

			jdouble* values = env->GetDoubleArrayElements(valuesArray, 0);

			*retVal = { values[0], values[1], values[2], values[3] };
		}

		return S_OK;
	}

	IFACEMETHODIMP GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override
	{
		*retVal = nullptr;
		return S_OK;
	}

	IFACEMETHODIMP SetFocus() noexcept override
	{
		// TODO: Call Item.SetFocus
		return S_OK;
	}

	IFACEMETHODIMP get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot** retVal) noexcept override try
	{
		*retVal = nullptr;
		if (m_control == nullptr || m_control->GetRoot() == nullptr)
			return S_OK;

		m_control->GetRoot()->GetProviderFragmentRoot().query_to(retVal);
		return S_OK;
	}
	CATCH_RETURN();

protected:
	bool IsPatternSupported(PATTERNID /* patternId */) const noexcept
	{
		return false;
	}

	void SetBstrPropertyValueIfNotEmpty(_Out_ VARIANT* retVal, const wchar_t* string)
	{
		if (string && *string)
		{
			retVal->bstrVal = wil::make_bstr(string).release();
			retVal->vt = VT_BSTR;
		}
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
