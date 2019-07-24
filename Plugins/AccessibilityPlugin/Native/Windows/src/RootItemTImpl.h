#pragma once

#include "ControlTImpl.h"
#include "RootItemT.h"

template <typename DerivedT, typename ProviderT, typename BaseT>
wil::com_ptr<IRawElementProviderFragmentRoot> RootItemT<DerivedT, ProviderT, BaseT>::GetProviderFragmentRoot()
{
	return GetProvider();
}
