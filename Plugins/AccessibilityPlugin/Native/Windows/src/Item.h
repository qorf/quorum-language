#pragma once

#include <windows.h>
#include <UIAutomation.h>
#include <string>
#include <atomic>

#include "jni.h"
#include "Resources.h"

class Item
{
public:
	Item(JNIEnv* env, std::wstring controlName, std::wstring controlDescription, jobject jItem);
	virtual ~Item();

	virtual void Focus(bool isFocused);
	virtual bool HasFocus();

	HWND GetHWND();
	void SetName(_In_ std::wstring name);
	const WCHAR* GetName();
	void SetDescription(_In_ std::wstring description);
	const WCHAR* GetDescription();
	jobject GetMe();
	int GetHashCode();
	void SetHashCode(int hash);
	int GetUniqueId() const noexcept;

	// TODO: Change the return type to Item* once we're rid of the HWNDs.
	jlong SetFocus();

	void AppendChild(Item* child) noexcept;
protected:
	std::wstring m_ControlName;
	std::wstring m_ControlDescription;
	HWND m_ControlHWND = nullptr;
	bool focused = false;
	int objectHash;
	jobject javaItem;

private:
	static std::atomic<int> s_nextUniqueId;

	int m_uniqueId;

	Item* m_parent = nullptr;
	Item* m_firstChild = nullptr;
	Item* m_lastChild = nullptr;
	Item* m_previousSibling = nullptr;
	Item* m_nextSibling = nullptr;
};
