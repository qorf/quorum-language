#include "Item.h"
#include <iostream>
#include <wil/result.h>

/* static */ std::atomic<int> Item::s_nextUniqueId = 1;

Item::Item(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem) 
	: m_ControlName(std::move(controlName))
	, m_ControlDescription(std::move(controlDescription))
	, m_uniqueId(s_nextUniqueId.fetch_add(1))
	, m_root(this)
{
	javaItem = env->NewGlobalRef(jItem);
	jclass itemReference = env->GetObjectClass(javaItem);
	jmethodID method = env->GetMethodID(itemReference, "GetHashCode", "()I");

	jint hash = env->CallIntMethod(javaItem, method);
	SetHashCode(hash);
}

Item::~Item()
{
	if (m_ControlHWND)
	{
		DestroyWindow(m_ControlHWND);
		m_ControlHWND = nullptr;
	}

	// Unlink this item from the tree wherever needed.
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
	}
	if (m_previousSibling)
	{
		FAIL_FAST_IF_NULL(m_parent);
		m_previousSibling->m_nextSibling = m_nextSibling;
	}
	if (m_nextSibling)
	{
		FAIL_FAST_IF_NULL(m_parent);
		m_nextSibling->m_previousSibling = m_previousSibling;
	}
	if (m_firstChild)
	{
		FAIL_FAST_IF(this == m_root);
		for (auto child = m_firstChild; child != nullptr; child = child->m_nextSibling)
		{
			FAIL_FAST_IF(child->m_parent != this);
			child->m_parent = m_parent;
		}
	}
}

int Item::GetHashCode() {
	return objectHash;
}

void Item::SetHashCode(int hash) {
	this->objectHash = hash;
}

void Item::Focus(bool isFocused)
{
	focused = isFocused;
}

bool Item::HasFocus()
{
	return focused;
}

HWND Item::GetHWND()
{
	return m_ControlHWND;
}

void Item::SetName(_In_ std::wstring name)
{
	m_ControlName = name;
}

const WCHAR* Item::GetName()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jclass itemReference = env->GetObjectClass(javaItem);
		jmethodID method = env->GetMethodID(itemReference, "GetName", "()Ljava/lang/String;");

		jstring fullName = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, method));
		const char* nativeName = env->GetStringUTFChars(fullName, 0);
		WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeName);

		env->ReleaseStringUTFChars(fullName, nativeName);

		return wItemName;
	}

	return m_ControlName.c_str();
}

void Item::SetDescription(_In_ std::wstring description)
{
	m_ControlDescription = description;
}

const WCHAR* Item::GetDescription()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring fullDescription = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_Item.GetDescription));

		const char* nativeDescription = env->GetStringUTFChars(fullDescription, 0);
		WCHAR* wItemDescription = CreateWideStringFromUTF8Win32(nativeDescription);

		env->ReleaseStringUTFChars(fullDescription, nativeDescription);

		return wItemDescription;
	}

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

jlong Item::SetFocus()
{
	const auto hwnd = GetHWND();

	if (!hwnd)
	{
		return 0;
	}

	return reinterpret_cast<jlong>(::SetFocus(hwnd));
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

Item* Item::GetRoot() const noexcept
{
	return m_root;
}

void Item::AppendChild(Item* child) noexcept
{
	FAIL_FAST_IF(child->m_parent != nullptr);
	FAIL_FAST_IF(child->m_firstChild != nullptr);
	child->m_parent = this;
	child->m_root = m_root;
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

wil::com_ptr<IRawElementProviderFragment> Item::GetProviderFragment()
{
	FAIL_FAST();
}