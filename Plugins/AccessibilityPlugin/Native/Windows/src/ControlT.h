#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <wil/com.h>
#include <wil/result.h>
#include <wil/wrl.h>

#include "Item.h"

template <typename DerivedT, typename ProviderT, typename BaseT = Item>
class ControlT: public BaseT
{
public:
	using ProviderType = ProviderT;

	ControlT(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem)
		: BaseT(env, std::move(controlName), std::move(controlDescription), jItem)
	{
	}
	
	const wil::com_ptr<ProviderT>& GetProvider();
	bool IsProviderCreated() const noexcept override;
	wil::com_ptr<IRawElementProviderSimple> GetProviderSimple() override;
	wil::com_ptr<IRawElementProviderFragment> GetProviderFragment() override;

protected:
	wil::com_ptr<ProviderT> m_provider;
};

