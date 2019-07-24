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
	// TODO: Replace these dummy implementations with real ones once we've gone windowless
	// and have a single root.

	IFACEMETHODIMP ElementProviderFromPoint(double x, double y, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override
	{
		// TODO: Do a hit test, probably by calling into Quorum.
		*retVal = nullptr;
		return S_OK;
	}

	IFACEMETHODIMP GetFocus(_Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override
	{
		// TODO: Once we have a single root that tracks the focus, return it.
		*retVal = nullptr;
		return S_OK;
	}
};
