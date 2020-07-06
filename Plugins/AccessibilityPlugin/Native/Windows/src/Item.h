#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <string>
#include <atomic>
#include <wil/com.h>

#include "jni.h"
#include "Resources.h"

class RootItemBase;

class Item
{
public:
	Item(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);
	virtual ~Item();

	bool HasQuorumFocus() const noexcept;
	bool HasUiaFocus() const noexcept;
	void SetQuorumFocus();

	// These methods are only called on items that can receive the Quorum focus, e.g. a menu bar
	// but not the menu items. However, they may be called in cases other than Quorum focus changes,
	// e.g. when the host window gains or loses focus.
	virtual void NotifyFocusGained();
	virtual void NotifyFocusLost();

	// If the focus that we expose to UIA is a descendant of the Quorum focus, e.g. an item
	// in a list or tree, return that descendant here. Otherwise, return null.
	virtual Item* GetUiaFocusDescendant() const noexcept;

	void SetName(_In_ std::wstring name);
	const WCHAR* GetName();
	void SetDescription(_In_ std::wstring description);
	const WCHAR* GetDescription();
	jobject GetMe();
	int GetUniqueId() const noexcept;

	Item* GetParent() const noexcept;
	Item* GetFirstChild() const noexcept;
	Item* GetLastChild() const noexcept;
	Item* GetPreviousSibling() const noexcept;
	Item* GetNextSibling() const noexcept;
	int GetChildCount() const noexcept;
	RootItemBase* GetRoot() const noexcept;
	void AppendChild(_In_ Item* child);
	void RemoveFromParent();

	virtual bool IsReadyForNotifications();

	virtual wil::com_ptr<IRawElementProviderSimple> GetProviderSimple() = 0;
	virtual wil::com_ptr<IRawElementProviderFragment> GetProviderFragment() = 0;

protected:
	std::wstring m_ControlName;
	std::wstring m_ControlDescription;
	jobject javaItem = nullptr;
	RootItemBase* m_root = nullptr;

private:
	void SetRootRecursive(_In_ RootItemBase* root) noexcept;
	void NotifyChildAdded();
	void RemoveFromParentInternal() noexcept;
	void RemoveAllChildren() noexcept;

	static std::atomic<int> s_nextUniqueId;

	int m_uniqueId;

	Item* m_parent = nullptr;
	Item* m_firstChild = nullptr;
	Item* m_lastChild = nullptr;
	Item* m_previousSibling = nullptr;
	Item* m_nextSibling = nullptr;
	int m_childCount = 0;
};
