#include "RootItemBase.h"

RootItemBase::RootItemBase(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem)
	: Item(env, std::move(controlName), std::move(controlDescription), jItem)
{
	m_root = this;
}

Item* RootItemBase::GetQuorumFocus() const noexcept
{
	return m_quorumFocus;
}

void RootItemBase::SetQuorumFocus(_In_ Item* item) noexcept
{
	m_quorumFocus = item;
}

Item* RootItemBase::GetUiaFocus() const noexcept
{
	if (!IsHostFocused())
	{
		return nullptr;
	}

	if (!m_quorumFocus)
	{
		return const_cast<RootItemBase*>(this);
	}

	const auto descendant = m_quorumFocus->GetUiaFocusDescendant();
	if (descendant)
	{
		return descendant;
	}

	return m_quorumFocus;
}

void RootItemBase::NotifyHostFocusGained()
{
	if (m_quorumFocus)
	{
		m_quorumFocus->NotifyFocusGained();
	}
}

void RootItemBase::NotifyHostFocusLost()
{
	if (m_quorumFocus)
	{
		m_quorumFocus->NotifyFocusLost();
	}
}
