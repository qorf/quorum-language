#include <iostream>
#include <string>
#include <strsafe.h>

#include "TextBoxControl.h"
#include "TextBoxProvider.h"
#include "ControlTImpl.h"

TextBoxControl::TextBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

std::wstring TextBoxControl::GetText()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		jstring jText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetText));

		const char* nativeText = env->GetStringUTFChars(jText, 0);
		std::wstring wText = CreateWideStringFromUTF8Win32(nativeText);

		env->ReleaseStringUTFChars(jText, nativeText);

		return wText;
	}

	return L"";
}

std::wstring TextBoxControl::GetText(int startIndex, int endIndex)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		jstring jText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetPartialText, startIndex, endIndex));

		const char* nativeText = env->GetStringUTFChars(jText, 0);
		std::wstring wText = CreateWideStringFromUTF8Win32(nativeText);

		env->ReleaseStringUTFChars(jText, nativeText);

		return wText;
	}

	return L"";
}

int TextBoxControl::GetSize()
{
	JNIEnv* env = GetJNIEnv();

	jint size = 0;
	if (env != NULL && javaItem != nullptr)
	{
		size = env->CallIntMethod(javaItem, JavaClass_TextBox.GetSize);
	}

	return static_cast<int>(size);
}

int TextBoxControl::GetIndexOfLine(int line)
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL && javaItem != nullptr)
	{
		index = env->CallIntMethod(javaItem, JavaClass_TextBox.GetIndexOfLine, (jint)line);
	}
	return (int)index;
}

bool TextBoxControl::IsBeginningOfToken(int index)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		if (env->CallBooleanMethod(javaItem, JavaClass_TextBox.IsBeginningOfToken, index))
		{
			return true;
		}
	}

	return false;
}

int TextBoxControl::GetLineIndexOfCharacter(int characterIndex)
{
	JNIEnv* env = GetJNIEnv();

	jint lineIndex = 0;
	if (env != NULL && javaItem != nullptr)
	{
		lineIndex = env->CallIntMethod(javaItem, JavaClass_TextBox.GetLineIndexOfCharacter, characterIndex);
	}
	return lineIndex;
}

Range TextBoxControl::GetSelectionRange()
{
	JNIEnv* env = GetJNIEnv();
	Range selectionRange = { {0}, {0} };
	if (env != NULL && javaItem != nullptr)
	{
		jint index = 0;
		jobject JO_selection;
		
		JO_selection = env->CallObjectMethod(javaItem, JavaClass_TextBox.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetStartIndex);
		selectionRange.begin = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetEndIndex);
		selectionRange.end = (int)index;
	}

	return selectionRange;
}

void TextBoxControl::Select(const Range& range)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		env->CallVoidMethod(javaItem, JavaClass_TextBox.Select, range.begin, range.end);
	}
}

bool TextBoxControl::IsErrorAtIndex(int index)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		if (env->CallStaticBooleanMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.IsErrorAtIndex, javaItem, index))
		{
			return true;
		}
	}

	return false;
}
