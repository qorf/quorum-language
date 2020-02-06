#include <iostream>
#include <string>
#include <strsafe.h>

#include "TextBoxControl.h"
#include "TextBoxProvider.h"
#include "TextBoxTextRange.h"
#include "ControlTImpl.h"

TextBoxControl::TextBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
	m_text = L"";
}

std::wstring TextBoxControl::GetText()
{
	return m_text;
}

int TextBoxControl::GetTextboxEndpoint()
{
	int endOfText = 0;
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_TextBox.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

		endOfText = static_cast<int>(strlen(nativeFullText));

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
		
		JO_selection = env->CallObjectMethod(javaItem, JavaClass_TextBox.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetStartIndex);
		selectionRange.begin = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetEndIndex);
		selectionRange.end = (int)index;
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

VARIANT TextBoxControl::GetAttributeAtPoint(_In_ int start, _In_ TEXTATTRIBUTEID attribute)
{
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
		retval.boolVal = HasQuorumFocus() ? VARIANT_TRUE : VARIANT_FALSE;
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
	else if (attribute == UIA_AnnotationTypesAttributeId)
	{
		JNIEnv* env = GetJNIEnv();
		if (env != NULL)
		{
			bool hasErrors = env->CallStaticBooleanMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.IsErrorAtIndex, javaItem, start);
			if (hasErrors)
			{
				retval.vt = VT_I4;
				retval.lVal = AnnotationType_SpellingError;
			}
		}
	}
	else if (attribute == UIA_AnnotationObjectsAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = NULL;
	}
	else if (attribute == UIA_CaretBidiModeAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = CaretBidiMode_LTR;
	}

	return retval;
}

bool TextBoxControl::StepCharacter(_In_ int start, _In_ bool forward, _Out_ int * end)
{
	*end = start;
	if (forward)
	{
		if (*end > GetTextboxEndpoint())
			return false;
		else
			(*end)++;
	}
	else
	{
		if (*end <= 0)
			return false;
		else
			(*end)--;
	}
	return true;
}

void TextBoxControl::UpdateSelection(const Range& /* indices */)
{
	// TODO: Actually use the provided indices.
	UpdateCaret();
}

void TextBoxControl::UpdateText(int index, std::wstring added, int removed)
{
	VARIANT oldText, newText;
	oldText.vt = VT_BSTR;
	newText.vt = VT_BSTR;
	oldText.bstrVal = wil::make_bstr(m_text.c_str()).release();

	std::wstring preText;

	if (index == 0)
		preText = L"";
	else
		preText = m_text.substr(0, index);

	std::wstring postText;
	if (index + removed >= m_text.length())
		postText = L"";
	else
		postText = m_text.substr(index + removed, std::wstring::npos);

	m_text = preText + added + postText;

	newText.bstrVal = wil::make_bstr(m_text.c_str()).release();

	const auto provider = GetProvider();
	UiaRaiseAutomationPropertyChangedEvent(provider.get(), UIA_ValueValuePropertyId, oldText, newText);
}
