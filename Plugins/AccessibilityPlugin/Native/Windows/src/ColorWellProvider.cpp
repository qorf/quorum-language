#include "ColorWellProvider.h"

ColorWellProvider::ColorWellProvider(ColorWellControl* control) : ProviderT(control)
{
}

bool ColorWellProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_InvokePatternId || patternId == UIA_ValuePatternId);
}

CONTROLTYPEID ColorWellProvider::GetControlType() const noexcept
{
	return UIA_ButtonControlTypeId;
}

void ColorWellProvider::GetControlSpecificPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) const
{
	if (propertyId == UIA_LocalizedControlTypePropertyId)
	{
		retVal->bstrVal = SysAllocString(L"color well");
		retVal->vt = VT_BSTR;
	}
}

IFACEMETHODIMP ColorWellProvider::Invoke() noexcept
{
	if (m_control == nullptr || m_control->GetMe() == nullptr)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_control->Invoke();
	return S_OK;
}

IFACEMETHODIMP ColorWellProvider::get_IsReadOnly(BOOL* returnValue) noexcept
{
	*returnValue = TRUE;
	return S_OK;
}

// The color is chosen in the picker, not typed.
IFACEMETHODIMP ColorWellProvider::SetValue(LPCWSTR value) noexcept
{
	UNREFERENCED_PARAMETER(value);
	return UIA_E_ELEMENTNOTENABLED;
}

IFACEMETHODIMP ColorWellProvider::get_Value(BSTR* returnValue) noexcept
{
	std::wstring text = L"";
	if (m_control != nullptr)
	{
		text = m_control->GetColorText();
	}
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
	return S_OK;
}
