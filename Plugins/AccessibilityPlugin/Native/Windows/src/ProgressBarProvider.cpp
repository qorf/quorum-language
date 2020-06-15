#include "ProgressBarProvider.h"

ProgressBarProvider::ProgressBarProvider(ProgressBarControl* control) : ProviderT(control)
{
}

bool ProgressBarProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_RangeValuePatternId || patternId == UIA_ValuePatternId);
}

CONTROLTYPEID ProgressBarProvider::GetControlType() const noexcept
{
	return UIA_ProgressBarControlTypeId;
}

// Invoke
IFACEMETHODIMP ProgressBarProvider::get_IsReadOnly(BOOL* pRetVal) noexcept
{
	*pRetVal = TRUE;
	return S_OK;
}

//Weirdly, screen readers seem to ignore this value. It feels like they shouldn't, but 
//even windows controls put in values that sure seem wrong.
IFACEMETHODIMP ProgressBarProvider::get_LargeChange(double* pRetVal) noexcept
{
	*pRetVal = 0.1;
	return S_OK;
}
IFACEMETHODIMP ProgressBarProvider::get_Maximum(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetMaximum();
	return S_OK;
}
IFACEMETHODIMP ProgressBarProvider::get_Minimum(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetMinimum();
	return S_OK;
}
IFACEMETHODIMP ProgressBarProvider::get_SmallChange(double* pRetVal) noexcept
{
	*pRetVal = 0.001;
	return S_OK;
}
IFACEMETHODIMP ProgressBarProvider::get_Value(double* pRetVal) noexcept
{
	*pRetVal = m_control->GetValue();
	return S_OK;
}
IFACEMETHODIMP ProgressBarProvider::SetValue(double val) noexcept
{
	m_control->SetValue(val);
	return S_OK;
}

IFACEMETHODIMP ProgressBarProvider::SetValue(LPCWSTR value)
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

IFACEMETHODIMP ProgressBarProvider::get_Value(BSTR* returnValue)
{
#if LOG
	log("TextFieldProvider::get_Value Start");
#endif

#if LOG
	log("TextFieldProvider::get_Value Finish");
#endif

	double value = m_control->GetPercent(m_control->GetValue());
	int truncated = (int)value;
	std::wstring text = L"" + std::to_wstring(truncated) + L"%";
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
	return S_OK;
}
