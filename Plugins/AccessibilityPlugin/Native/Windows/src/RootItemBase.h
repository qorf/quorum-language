#pragma once

#include "Item.h"

class RootItemBase : public Item
{
public:
	RootItemBase(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	virtual wil::com_ptr<IRawElementProviderFragmentRoot> GetProviderFragmentRoot() = 0;

	Item* GetQuorumFocus() const noexcept;
	void SetQuorumFocus(_In_ Item* item) noexcept;
	Item* GetUiaFocus() const noexcept;
	virtual bool IsHostFocused() const noexcept = 0;
	void NotifyHostFocusGained();
	void NotifyHostFocusLost();

private:
	Item* m_quorumFocus = nullptr;
};
