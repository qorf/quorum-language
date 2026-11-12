#include "SliderProvider.h"
#include <cwchar>

SliderProvider::SliderProvider(SliderControl* control) : ProviderT(control)
{
}

bool SliderProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_RangeValuePatternId || patternId == UIA_ValuePatternId);
}

CONTROLTYPEID SliderProvider::GetControlType() const noexcept
{
	return UIA_SliderControlTypeId;
}

IFACEMETHODIMP SliderProvider::get_IsReadOnly(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_LargeChange(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetLargeChange();
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_Maximum(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetMaximum();
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_Minimum(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetMinimum();
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_SmallChange(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetSmallChange();
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_Value(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetValue();
	return S_OK;
}

// A screen reader sets the number: kept within the range by the slider.
IFACEMETHODIMP SliderProvider::SetValue(double val) noexcept
{
	if (val < m_control->GetMinimum() || val > m_control->GetMaximum())
	{
		return E_INVALIDARG;
	}
	m_control->ChangeValue(val);
	return S_OK;
}

// A screen reader sets the value as text: taken if it starts with a number.
IFACEMETHODIMP SliderProvider::SetValue(LPCWSTR value) noexcept
{
	if (value == nullptr)
	{
		return E_INVALIDARG;
	}
	wchar_t* end = nullptr;
	double number = std::wcstod(value, &end);
	if (end == value)
	{
		return E_INVALIDARG;
	}
	m_control->ChangeValue(number);
	return S_OK;
}

IFACEMETHODIMP SliderProvider::get_Value(BSTR* returnValue) noexcept
{
	std::wstring text = m_control->GetValueText();
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
	return S_OK;
}
