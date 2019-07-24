#include <iostream>
#include <string>
#include <strsafe.h>

#include "TextBoxControl.h"
#include "TextBoxProvider.h"
#include "ControlTImpl.h"

TextBoxControl::TextBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}

void TextBoxControl::Focus(bool isFocused)
{
	this->focused = isFocused;
}

std::wstring TextBoxControl::GetText()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);
		std::wstring wFullText = CreateWideStringFromUTF8Win32(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);

		TextBoxProvider* eventControl = GetProvider().get();
		if (eventControl != NULL && UiaClientsAreListening())
		{
			UiaRaiseAutomationEvent(eventControl, UIA_Text_TextChangedEventId);
		}

		return wFullText;
	}

	return L"";
}

EndPoint TextBoxControl::GetTextboxEndpoint()
{
	EndPoint endOfText(0);
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

		endOfText.character = static_cast<int>(strlen(nativeFullText));

		env->ReleaseStringUTFChars(fullText, nativeFullText);
	}
	return endOfText;
}

int TextBoxControl::GetCaretLine()
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		// Wait for one frame of animation to complete
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);
		index = env->CallIntMethod(javaItem, JavaClass_TextBox.GetCaretLine);
	}

	return (int)index;
}

int TextBoxControl::GetCaretPosition()
{
	JNIEnv* env = GetJNIEnv();
	
	jint index = 0;
	if (env != NULL)
	{
		// Wait for Quorum to write
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		index = env->CallIntMethod(javaItem, JavaClass_TextBox.GetCaretPosition);
	}
	return (int)index + 1;
}

int TextBoxControl::GetIndexOfLine(int line)
{
	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		// Wait for Quorum to write
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		index = env->CallIntMethod(javaItem, JavaClass_TextBox.GetIndexOfLine, (jint)line);
	}
	return (int)index;
}

int TextBoxControl::GetLineLength()
{
	int length = 0;
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring currentLineText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetCurrentLineText));

		const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);

		length = static_cast<int>(strlen(nativeCurrentLineText));

		env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);
	}
	return length;
}

Range TextBoxControl::GetSelectionRange()
{
	JNIEnv* env = GetJNIEnv();
	Range selectionRange = { {0}, {0} };
	if (env != NULL)
	{
		jint index = 0;
		jobject JO_selection;

		// Wait for Quorum to write
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);
		
		JO_selection = env->CallObjectMethod(javaItem, JavaClass_TextBox.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetStartIndex);
		selectionRange.begin.character = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetEndIndex);
		selectionRange.end.character = (int)index;
	}

	return selectionRange;
}

void TextBoxControl::UpdateCaret()
{
	TextBoxProvider* eventControl = GetProvider().get();
	if (eventControl != NULL && UiaClientsAreListening())
	{
		HRESULT result = UiaRaiseAutomationEvent(eventControl, UIA_Text_TextSelectionChangedEventId);
	}
}

VARIANT TextBoxControl::GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute)
{
	UNREFERENCED_PARAMETER(start);
	VARIANT retval;
	VariantInit(&retval);

	// Many attributes are constant across the range, get them here
	if (attribute == UIA_AnimationStyleAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = AnimationStyle_None;
	}
	else if (attribute == UIA_BackgroundColorAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = GetSysColor(COLOR_WINDOW);
	}
	else if (attribute == UIA_BulletStyleAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = BulletStyle_None;
	}
	else if (attribute == UIA_CapStyleAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = CapStyle_None;
	}
	else if (attribute == UIA_CultureAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = GetThreadLocale();
	}
	else if (attribute == UIA_HorizontalTextAlignmentAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = HorizontalTextAlignment_Left;
	}
	else if (attribute == UIA_IndentationTrailingAttributeId)
	{
		retval.vt = VT_R8;
		retval.dblVal = 0.0;
	}
	else if (attribute == UIA_IsHiddenAttributeId)
	{
		retval.vt = VT_BOOL;
		retval.boolVal = VARIANT_FALSE;
	}
	else if (attribute == UIA_IsReadOnlyAttributeId)
	{
		// TODO: This should change depending on if the text from quorum is read-only.
		retval.vt = VT_BOOL;
		retval.boolVal = VARIANT_FALSE;
	}
	else if (attribute == UIA_IsSubscriptAttributeId)
	{
		retval.vt = VT_BOOL;
		retval.boolVal = VARIANT_FALSE;
	}
	else if (attribute == UIA_IsSuperscriptAttributeId)
	{
		retval.vt = VT_BOOL;
		retval.boolVal = VARIANT_FALSE;
	}
	else if (attribute == UIA_MarginLeadingAttributeId)
	{
		retval.vt = VT_R8;
		retval.dblVal = 0.0;
	}
	else if (attribute == UIA_MarginTrailingAttributeId)
	{
		retval.vt = VT_R8;
		retval.dblVal = 0.0;
	}
	else if (attribute == UIA_OutlineStylesAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = OutlineStyles_None;
	}
	else if (attribute == UIA_OverlineColorAttributeId)
	{
		if (SUCCEEDED(UiaGetReservedNotSupportedValue(&retval.punkVal)))
		{
			retval.vt = VT_UNKNOWN;
		}
	}
	else if (attribute == UIA_OverlineStyleAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = TextDecorationLineStyle_None;
	}
	else if (attribute == UIA_StrikethroughColorAttributeId)
	{
		if (SUCCEEDED(UiaGetReservedNotSupportedValue(&retval.punkVal)))
		{
			retval.vt = VT_UNKNOWN;
		}
	}
	else if (attribute == UIA_StrikethroughStyleAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = TextDecorationLineStyle_None;
	}
	else if (attribute == UIA_TabsAttributeId)
	{
		if (SUCCEEDED(UiaGetReservedNotSupportedValue(&retval.punkVal)))
		{
			retval.vt = VT_UNKNOWN;
		}
	}
	else if (attribute == UIA_TextFlowDirectionsAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = FlowDirections_RightToLeft;
	}
	else if (attribute == UIA_LinkAttributeId)
	{
		if (SUCCEEDED(UiaGetReservedNotSupportedValue(&retval.punkVal)))
		{
			retval.vt = VT_UNKNOWN;
		}
	}
	else if (attribute == UIA_IsActiveAttributeId)
	{
		retval.vt = VT_BOOL;
		retval.boolVal = focused ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (attribute == UIA_SelectionActiveEndAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = ActiveEnd_None;
	}
	else if (attribute == UIA_CaretPositionAttributeId)
	{
		retval.vt = VT_I4;
		if (GetCaretPosition() == 0)
		{
			retval.lVal = CaretPosition_BeginningOfLine;
		}
		else if (GetCaretPosition() == GetLineLength())
		{
			retval.lVal = CaretPosition_EndOfLine;
		}
		else
		{
			retval.lVal = CaretPosition_Unknown;
		}
	}
	else if (attribute == UIA_CaretBidiModeAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = CaretBidiMode_LTR;
	}

	return retval;
}

bool TextBoxControl::StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint * end)
{
	*end = start;
	if (forward)
	{
			end->character++;
	}
	else
	{
		if (end->character <= 0)
		{
			return false;
		}
		else
		{
			end->character--;
		}
	}
	return true;
}

void TextBoxControl::UpdateSelection(const Range& /* indices */)
{
	// TODO: Actually use the provided indices.
	UpdateCaret();
}
