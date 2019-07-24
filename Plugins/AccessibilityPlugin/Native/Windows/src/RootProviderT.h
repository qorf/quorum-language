#pragma once

#include "ProviderT.h"

// A class template used by all provider implementations.
template <class DerivedT, class ControlT, typename ...MoreInterfaces>
class RootProviderT : public ProviderT<DerivedT, ControlT, IRawElementProviderFragmentRoot, MoreInterfaces...>
{
public:
	RootProviderT(_In_ ControlT* control)
		: ProviderT(control)
	{
	}

	// IRawElementProviderFragmentRoot

	IFACEMETHODIMP ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override
	{
		// TODO: Do a hit test, probably by calling into Quorum.
		*retVal = nullptr;
		return S_OK;
	}

	IFACEMETHODIMP GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override
	{
		*retVal = nullptr;

		const auto item = m_control->GetUiaFocus();
		if (item && (item != m_control))
		{
			item->GetProviderFragment().query_to(retVal);
		}

		return S_OK;
	}
};
