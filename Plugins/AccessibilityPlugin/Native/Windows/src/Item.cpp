#include <iostream>
#include <wil/result.h>

#include "Item.h"
#include "RootItemBase.h"

/* static */ std::atomic<int> Item::s_nextUniqueId = 1;

Item::Item(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem) 
	: m_ControlName(std::move(controlName))
	, m_ControlDescription(std::move(controlDescription))
	, m_uniqueId(s_nextUniqueId.fetch_add(1))
{
	// Some native items, like the window root, have no corresponding Quorum item. In those cases,
	// the subclass will pass null for env and jItem.
	if (env && jItem)
	{
		javaItem = env->NewGlobalRef(jItem);
	}
}

Item::~Item()
{
	RemoveFromParentInternal();
	RemoveAllChildren();
}

bool Item::HasQuorumFocus() const noexcept
{
	return (m_root->GetQuorumFocus() == this);
}

bool Item::HasUiaFocus() const noexcept
{
	return (m_root->GetUiaFocus() == this);
}

void Item::SetQuorumFocus()
{
	const auto oldFocus = m_root->GetQuorumFocus();

	if (oldFocus == this)
	{
		return;
	}


	m_root->SetQuorumFocus(this);
	NotifyFocusGained();

	if (oldFocus)
	{
		oldFocus->NotifyFocusLost();
	}
}

void Item::NotifyFocusGained()
{
	const auto uiaFocusItem = m_root->GetUiaFocus();
	if (uiaFocusItem && UiaClientsAreListening())
	{
		const auto provider = uiaFocusItem->GetProviderSimple();
		UiaRaiseAutomationEvent(provider.get(), UIA_AutomationFocusChangedEventId);
	}
}

Item* Item::GetUiaFocusDescendant() const noexcept
{
	return nullptr;
}

void Item::NotifyFocusLost()
{
}

void Item::SetName(_In_ std::wstring name)
{
	VARIANT oldName, newName;
	oldName.vt = VT_BSTR;
	newName.vt = VT_BSTR;
	oldName.bstrVal = wil::make_bstr(m_ControlName.c_str()).release();
	newName.bstrVal = wil::make_bstr(name.c_str()).release();

	m_ControlName = name;

	const auto provider = GetProviderSimple();
	UiaRaiseAutomationPropertyChangedEvent(provider.get(), UIA_NamePropertyId, oldName, newName);
}

const WCHAR* Item::GetName()
{
	return m_ControlName.c_str();
}

void Item::SetDescription(_In_ std::wstring description)
{
	VARIANT oldDescription, newDescription;
	oldDescription.vt = VT_BSTR;
	newDescription.vt = VT_BSTR;
	oldDescription.bstrVal = wil::make_bstr(m_ControlDescription.c_str()).release();
	newDescription.bstrVal = wil::make_bstr(description.c_str()).release();

	m_ControlDescription = description;

	const auto provider = GetProviderSimple();
	UiaRaiseAutomationPropertyChangedEvent(provider.get(), UIA_HelpTextPropertyId, oldDescription, newDescription);
}

const WCHAR* Item::GetDescription()
{
	return m_ControlDescription.c_str();
}

jobject Item::GetMe()
{
	return javaItem;
}

int Item::GetUniqueId() const noexcept
{
	return m_uniqueId;
}

Item* Item::GetParent() const noexcept
{
	return m_parent;
}

Item* Item::GetFirstChild() const noexcept
{
	return m_firstChild;
}

Item* Item::GetLastChild() const noexcept
{
	return m_lastChild;
}

Item* Item::GetPreviousSibling() const noexcept
{
	return m_previousSibling;
}

Item* Item::GetNextSibling() const noexcept
{
	return m_nextSibling;
}

int Item::GetChildCount() const noexcept
{
	return m_childCount;
}

RootItemBase* Item::GetRoot() const noexcept
{
	return m_root;
}

void Item::SetRootRecursive(_In_ RootItemBase* root) noexcept
{
	m_root = root;
	for (auto child = m_firstChild; child != nullptr; child = child->m_nextSibling)
	{
		child->SetRootRecursive(root);
	}
}

void Item::NotifyChildAdded()
{
	if (UiaClientsAreListening())
	{
		const auto provider = GetProviderSimple();
		THROW_IF_FAILED(UiaRaiseStructureChangedEvent(
			provider.get(),
			StructureChangeType_ChildAdded,
			nullptr /* pRuntimeId */,
			0 /* cRuntimeIdLen */));
	}
}

void Item::AppendChild(_In_ Item* child)
{
	FAIL_FAST_IF(child->m_parent != nullptr);
	FAIL_FAST_IF(child->m_firstChild != nullptr);
	child->m_parent = this;
	child->SetRootRecursive(m_root);
	m_childCount++;

	if (m_lastChild)
	{
		m_lastChild->m_nextSibling = child;
		child->m_previousSibling = m_lastChild;
		m_lastChild = child;
	}
	else
	{
		m_firstChild = child;
		m_lastChild = child;
	}
}

void Item::RemoveFromParent()
{
	if (!m_parent)
	{
		return;
	}

	wil::com_ptr<IRawElementProviderSimple> parentProvider;
	if (UiaClientsAreListening())
	{
		parentProvider = m_parent->GetProviderSimple();
	}

	RemoveFromParentInternal();

	if (UiaClientsAreListening())
	{
		int rid[] = { UiaAppendRuntimeId, m_uniqueId };
		THROW_IF_FAILED(UiaRaiseStructureChangedEvent(
			parentProvider.get(),
			StructureChangeType_ChildRemoved,
			rid,
			ARRAYSIZE(rid)));
	}
}

void Item::RemoveFromParentInternal() noexcept
{
	if (m_parent)
	{
		if (this == m_parent->m_firstChild)
		{
			m_parent->m_firstChild = m_nextSibling;
		}
		if (this == m_parent->m_lastChild)
		{
			m_parent->m_lastChild = m_previousSibling;
		}
		m_parent->m_childCount--;
		m_parent = nullptr;
	}

	if (m_previousSibling)
		m_previousSibling->m_nextSibling = m_nextSibling;

	if (m_nextSibling)
		m_nextSibling->m_previousSibling = m_previousSibling;

	m_previousSibling = nullptr;
	m_nextSibling = nullptr;

	if (m_root)
	{
		// If this is enabled, this can cause crashing when closing Menus/Trees. Theoretically, this shouldn't be necessary, as the accessibility manager in Quorum always removes all children before the parent item.
		//SetRootRecursive(nullptr);
	}
}

void Item::RemoveAllChildren() noexcept
{
	if (m_firstChild)
	{
		for (auto child = m_firstChild; child != nullptr; child = child->m_nextSibling)
		{
			FAIL_FAST_IF(child->m_parent != this);
			child->m_parent = nullptr;
			if (this == child->m_root)
			{
				child->SetRootRecursive(nullptr);
			}
		}
		m_firstChild = nullptr;
		m_lastChild = nullptr;
		m_childCount = 0;
	}
}
