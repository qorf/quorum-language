#pragma once

#include "ControlT.h"

template <typename DerivedT, typename ProviderT>
ControlT<DerivedT, ProviderT>::~ControlT()
{
	if (m_provider)
	{
		LOG_IF_FAILED(UiaDisconnectProvider(m_provider.query<IRawElementProviderSimple>().get()));
	}
}

template <typename DerivedT, typename ProviderT>
const wil::com_ptr<ProviderT>& ControlT<DerivedT, ProviderT>::GetProvider()
{
	if (!m_provider)
	{
		m_provider = wil::MakeOrThrow<ProviderT>(static_cast<DerivedT*>(this));
	}

	return m_provider;
}

template <typename DerivedT, typename ProviderT>
wil::com_ptr<IRawElementProviderFragment> ControlT<DerivedT, ProviderT>::GetProviderFragment()
{
	return GetProvider().query<IRawElementProviderFragment>();
}
