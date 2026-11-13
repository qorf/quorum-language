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
	Item* GetActivePart() const noexcept;
	void SetActivePart(_In_opt_ Item* part) noexcept;
	virtual bool IsHostFocused() const noexcept = 0;
	void NotifyHostFocusGained();
	void NotifyHostFocusLost();

private:
	Item* m_quorumFocus = nullptr;

	// The Quorum focus's active part (Item:GetActiveDescendant), such as a
	// combo box's highlighted row, or null. While it is set, UI Automation
	// is told the part has the focus, though Quorum's focus stays put.
	Item* m_activePart = nullptr;
};
