#pragma once

#include "ControlT.h"

template <typename DerivedT, typename ProviderT, typename BaseT>
ControlT<DerivedT, ProviderT, BaseT>::~ControlT()
{
	if (m_provider)
	{
		LOG_IF_FAILED(UiaDisconnectProvider(m_provider.get()));
	}
}

template <typename DerivedT, typename ProviderT, typename BaseT>
const wil::com_ptr<ProviderT>& ControlT<DerivedT, ProviderT, BaseT>::GetProvider()
{
	if (!m_provider)
	{
		m_provider = wil::MakeOrThrow<ProviderT>(static_cast<DerivedT*>(this));
	}

	return m_provider;
}

template <typename DerivedT, typename ProviderT, typename BaseT>
wil::com_ptr<IRawElementProviderSimple> ControlT<DerivedT, ProviderT, BaseT>::GetProviderSimple()
{
	return GetProvider();
}

template <typename DerivedT, typename ProviderT, typename BaseT>
wil::com_ptr<IRawElementProviderFragment> ControlT<DerivedT, ProviderT, BaseT>::GetProviderFragment()
{
	return GetProvider();
}
