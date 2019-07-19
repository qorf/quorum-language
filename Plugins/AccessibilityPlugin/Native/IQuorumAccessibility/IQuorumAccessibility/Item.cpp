#include "Item.h"
#include <iostream>

Item::Item(JNIEnv* env, std::wstring controlName, std::wstring controlDescription, jobject jItem) 
	: m_ControlName(controlName), m_ControlDescription(controlDescription), m_ControlHWND(NULL)
{
	javaItem = env->NewGlobalRef(jItem);
	jclass itemReference = env->GetObjectClass(javaItem);
	jmethodID method = env->GetMethodID(itemReference, "GetHashCode", "()I");

	jint hash = env->CallIntMethod(javaItem, method);
	SetHashCode(hash);
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
