#include <string>
#include <windows.h>
#include <iostream>

#include "TextFieldControl.h"
#include "TextFieldProvider.h"
#include "TextBoxProvider.h"
#include "ControlTImpl.h"

TextFieldControl::TextFieldControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
	m_text = L"";
}

int TextFieldControl::GetCaretPosition()
{
	#if LOG
	log("TextFieldControl::GetCaretPosition Start");
	#endif

	JNIEnv* env = GetJNIEnv();

	jint index = 0;
	if (env != NULL)
	{
		index = env->CallIntMethod(GetMe(), JavaClass_TextField.GetCaretPosition);
	}

	#if LOG
	log("TextFieldControl::GetCaretPosition Finished");
	#endif

	return (int)index;
}

int TextFieldControl::GetSize()
{
	#if LOG
	log("TextFieldControl::GetTextFieldProvider Start");
	#endif

	JNIEnv* env = GetJNIEnv();

	jint length = 0;
	if (env != NULL)
	{
		length = env->CallIntMethod(GetMe(), JavaClass_TextField.GetSize);
	}
	return (int)length;
}

std::wstring TextFieldControl::GetText()
{
	return m_text;
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

void TextFieldControl::UpdateCaret()
{
	TextFieldProvider* eventControl = GetProvider().get();
	if (eventControl != NULL && UiaClientsAreListening())
	{
		HRESULT result = UiaRaiseAutomationEvent(eventControl, UIA_Text_TextSelectionChangedEventId);
	}
}

VARIANT TextFieldControl::GetAttributeAtPoint(_In_ int start, _In_ TEXTATTRIBUTEID attribute)
{
	#if LOG
	log("TextFieldControl::GetAttributeAtPoint Start");
	#endif

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
		else if (GetCaretPosition() == GetSize())
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

	#if LOG
	log("TextFieldControl::GetAttributeAtPoint Finished");
	#endif

	return retval;
}

bool TextFieldControl::StepCharacter(_In_ int start, _In_ bool forward, _Out_ int* end)
{
	*end = start;
	if (forward)
	{
		if (*end >= GetSize())
		{
			return false;
		}

		(*end)++;
	}
	else
	{
		if (*end <= 0)
		{
			return false;
		}
		else
		{
			(*end)--;
		}
	}

	return true;
}

int TextFieldControl::GetTextFieldEndpoint()
{
	int endOfText = 0;
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(GetMe(), JavaClass_TextField.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

		endOfText = static_cast<int>(strlen(nativeFullText));

		env->ReleaseStringUTFChars(fullText, nativeFullText);
	}

	return endOfText;
}

Range TextFieldControl::GetSelectionRange()
{
	JNIEnv* env = GetJNIEnv();
	Range selectionRange = { {0}, {0} };

	if (env != NULL)
	{
		jint index = 0;
		jobject JO_selection;

		JO_selection = env->CallObjectMethod(GetMe(), JavaClass_TextField.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetStartIndex);
		selectionRange.begin = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetEndIndex);
		selectionRange.end = (int)index;

	}

	return selectionRange;
}

void TextFieldControl::UpdateSelection(const Range& /* indices */)
{
	// TODO: Actually use the provided indices.
	UpdateCaret();
}

void TextFieldControl::UpdatePassword(bool value)
{
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

void TextFieldControl::UpdateText(int index, std::wstring added, int removed)
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