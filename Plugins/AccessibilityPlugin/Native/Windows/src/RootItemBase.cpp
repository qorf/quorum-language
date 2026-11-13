#include "RootItemBase.h"
#include <iostream>

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
	// An active part belongs to the item that had the focus, so a new focus
	// starts without one.
	if (item != m_quorumFocus)
	{
		m_activePart = nullptr;
	}
	m_quorumFocus = item;
}

Item* RootItemBase::GetActivePart() const noexcept
{
	return m_activePart;
}

void RootItemBase::SetActivePart(_In_opt_ Item* part) noexcept
{
	m_activePart = part;
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

	if (m_activePart)
	{
		return m_activePart;
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
