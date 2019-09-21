#include "ProgressBarProvider.h"

ProgressBarProvider::ProgressBarProvider(ProgressBarControl* control) : ProviderT(control)
{
}

bool ProgressBarProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_RangeValuePatternId);
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

IFACEMETHODIMP ProgressBarProvider::get_LargeChange(double* pRetVal) noexcept
{
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
