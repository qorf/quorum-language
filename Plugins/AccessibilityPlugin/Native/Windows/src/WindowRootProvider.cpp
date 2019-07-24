#include "WindowRootProvider.h"

WindowRootProvider::WindowRootProvider(_In_ WindowRoot* control)
	: ProviderT(control)
{
}

CONTROLTYPEID WindowRootProvider::GetControlType() const noexcept
{
	return UIA_WindowControlTypeId;
}

IFACEMETHODIMP WindowRootProvider::get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal) noexcept
{
	return UiaHostProviderFromHwnd(m_control->GetHwnd(), retVal);
}
