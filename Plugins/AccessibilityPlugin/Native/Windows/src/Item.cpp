#include "Item.h"
#include <iostream>
#include <wil/result.h>

Item::Item(JNIEnv* env, std::wstring controlName, std::wstring controlDescription, jobject jItem) 
	: m_ControlName(controlName), m_ControlDescription(controlDescription), m_ControlHWND(NULL)
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
	for (auto child = m_firstChild; child != nullptr; child = child->m_nextSibling)
	{
		FAIL_FAST_IF(child->m_parent != this);
		child->m_parent = m_parent;
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

jlong Item::SetFocus()
{
	const auto hwnd = GetHWND();

	if (!hwnd)
	{
		return 0;
	}

	return reinterpret_cast<jlong>(::SetFocus(hwnd));
}

void Item::AppendChild(Item* child) noexcept
{
	FAIL_FAST_IF(child->m_parent != nullptr);
	child->m_parent = this;
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
