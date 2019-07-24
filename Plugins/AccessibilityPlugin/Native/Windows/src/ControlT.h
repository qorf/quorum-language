#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <wil/com.h>
#include <wil/result.h>
#include <wil/wrl.h>

#include "Item.h"

template <typename DerivedT, typename ProviderT>
class ControlT: public Item
{
public:
	using ProviderType = ProviderT;

	ControlT(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem)
		: Item(env, std::move(controlName), std::move(controlDescription), jItem)
	{
	}

	~ControlT();
	const wil::com_ptr<ProviderT>& GetProvider();
	wil::com_ptr<IRawElementProviderSimple> GetProviderSimple() override;
	wil::com_ptr<IRawElementProviderFragment> GetProviderFragment() override;

	// TODO: Drop this temporary function once we've gone fully windowless.
	bool CanContainWindowlessControls() const noexcept override
	{
		return true;
	}

protected:
	wil::com_ptr<ProviderT> m_provider;
};

