#pragma once

#include "ControlT.h"
#include "RootItemBase.h"

template <typename DerivedT, typename ProviderT, typename BaseT = RootItemBase>
class RootItemT : public ControlT<DerivedT, ProviderT, BaseT>
{
public:
	RootItemT(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem)
		: ControlT(env, std::move(controlName), std::move(controlDescription), jItem)
	{
	}

	wil::com_ptr<IRawElementProviderFragmentRoot> GetProviderFragmentRoot() override;
};

