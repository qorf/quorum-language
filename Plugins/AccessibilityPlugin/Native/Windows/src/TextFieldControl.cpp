#include <string>
#include <windows.h>
#include <iostream>

#include "TextFieldControl.h"
#include "TextFieldProvider.h"
#include "ControlTImpl.h"

namespace
{

constexpr wchar_t c_passwordPlaceholder = L'*';

} // anonymous namespace

TextFieldControl::TextFieldControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

std::wstring TextFieldControl::GetText()
{
	return GetText(0, GetSize());
}

std::wstring TextFieldControl::GetText(int startIndex, int endIndex)
{
	if (IsPassword())
	{
		return std::wstring(static_cast<size_t>(endIndex - startIndex), c_passwordPlaceholder);
	}

	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring jText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextField.GetPartialText, startIndex, endIndex));

		const char* nativeText = env->GetStringUTFChars(jText, 0);
		std::wstring wText = CreateWideStringFromUTF8Win32(nativeText);

		env->ReleaseStringUTFChars(jText, nativeText);

		return wText;
	}

	return L"";
}

int TextFieldControl::GetSize()
{
	JNIEnv* env = GetJNIEnv();

	jint size = 0;
	if (env != NULL)
	{
		size = env->CallIntMethod(javaItem, JavaClass_TextField.GetSize);
	}

	return static_cast<int>(size);
}

int TextFieldControl::GetIndexOfLine(int /* line */)
{
	return 0;
}

bool TextFieldControl::IsBeginningOfToken(int index)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		if (env->CallBooleanMethod(javaItem, JavaClass_TextField.IsBeginningOfToken, index))
		{
			return true;
		}
	}

	return false;
}

int TextFieldControl::GetLineIndexOfCharacter(int /* characterIndex */)
{
	return 0;
}

Range TextFieldControl::GetSelectionRange()
{
	JNIEnv* env = GetJNIEnv();
	Range selectionRange = { {0}, {0} };
	if (env != NULL)
	{
		jint index = 0;
		jobject JO_selection;
		
		JO_selection = env->CallObjectMethod(javaItem, JavaClass_TextField.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetStartIndex);
		selectionRange.begin = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetEndIndex);
		selectionRange.end = (int)index;
	}

	return selectionRange;
}

void TextFieldControl::Select(const Range& range)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		env->CallVoidMethod(javaItem, JavaClass_TextField.Select, range.begin, range.end);
	}
}

bool TextFieldControl::IsErrorAtIndex(int /* index */)
{
	return false;
}

bool TextFieldControl::IsPassword()
{
	JNIEnv* env = GetJNIEnv();

	if (env == NULL)
	{
		// If the JNI environment isn't available, we can't call up to it.
		return false;
	}
	jboolean value = (jboolean)(env->CallBooleanMethod(this->GetMe(), JavaClass_TextField.IsPassword));
	return (bool)value;
}

void TextFieldControl::UpdatePassword(bool value)
{
	if (!IsReadyForEvents())
	{
		return;
	}

	VARIANT oldPassword, newPassword;
	oldPassword.vt = VT_BOOL;
	if (IsPassword()) {
		oldPassword.boolVal = VARIANT_TRUE;
	}
	else {
		oldPassword.boolVal = VARIANT_FALSE;
	}
	
	newPassword.vt = VT_BOOL;

	if (value) {
		newPassword.boolVal = VARIANT_TRUE;
	}
	else {
		newPassword.boolVal = VARIANT_FALSE;
	}
	const auto provider = GetProvider();
	UiaRaiseAutomationPropertyChangedEvent(provider.get(), UIA_IsPasswordPropertyId, oldPassword, newPassword);
}
