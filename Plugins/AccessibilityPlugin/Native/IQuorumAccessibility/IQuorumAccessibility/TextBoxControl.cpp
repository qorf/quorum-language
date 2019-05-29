#include <iostream>
#include <string>
#include <strsafe.h>

#include "TextBoxControl.h"
#include "TextBoxTextAreaProvider.h"
#include "TextBoxProvider.h"

bool TextBoxControl::Initialized = false;

TextBoxControl::TextBoxControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, _In_ jobject me)
	: Item(env, name, description, me), m_focused(false), m_pTextBoxProvider(NULL), m_JO_me(me)
{
}

TextBoxControl::~TextBoxControl()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
		env->DeleteGlobalRef(m_JO_me);
}

bool TextBoxControl::Initialize(_In_ HINSTANCE hInstance)
{
	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticTextBoxControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_TEXTBOX";

	if (RegisterClassExW(&wc) == 0)
	{
		// An error occured. Output this error so it can be seen from Quorum.
		DWORD errorMessageID = ::GetLastError();

		LPSTR messageBuffer = nullptr;
		size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

		//std::string message(messageBuffer, size);
		//std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		//fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}

TextBoxControl* TextBoxControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* textboxName, _In_ WCHAR* textboxDescription, _In_ jobject me)
{

	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		TextBoxControl * control = new TextBoxControl(env, textboxName, textboxDescription, me);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_TEXTBOX",
			textboxName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parentWindow,
			NULL,
			instance,
			static_cast<PVOID>(control)
		);

		if (control->m_ControlHWND == NULL)
		{
			DWORD errorMessageID = ::GetLastError();

			LPSTR messageBuffer = nullptr;
			size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
				NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)&messageBuffer, 0, NULL);

			std::string message(messageBuffer, size);
			std::cout << "Native Code - CreateWindowExW Error " << errorMessageID << ": " << message;
			fflush(stdout);

			//Free the buffer.
			LocalFree(messageBuffer);
		}
		else
		{
			if (UiaClientsAreListening())
			{
				control->GetTextBoxProvider();
			}

			return control;
		}
	}

	return NULL; // Indicates failure to create window.

}

TextBoxProvider* TextBoxControl::GetTextBoxProvider()
{
	if (m_pTextBoxProvider == NULL)
	{
		m_pTextBoxProvider = new TextBoxProvider(GetHWND(), this);
	}
	return m_pTextBoxProvider;
}

void TextBoxControl::SetControlFocus(_In_ bool focused)
{
	m_focused = focused;
	if (focused)
		NotifyFocusGained(GetHWND(), this);
}

bool TextBoxControl::HasFocus()
{
	return m_focused;
}

std::wstring TextBoxControl::GetText()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		//env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);
		std::wstring wFullText = CreateWideStringFromUTF8Win32(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);

		TextBoxTextAreaProvider *eventControl = new TextBoxTextAreaProvider(GetHWND(), this);
		if (eventControl != NULL && UiaClientsAreListening())
		{
			UiaRaiseAutomationEvent(eventControl, UIA_Text_TextChangedEventId);
			eventControl->Release();
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

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

		endOfText.character = strlen(nativeFullText);

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
		// Wait for Quorum to write
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);
		index = env->CallIntMethod(m_JO_me, JavaClass_TextBox.GetCaretLine);
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

		index = env->CallIntMethod(m_JO_me, JavaClass_TextBox.GetCaretPosition);
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

		index = env->CallIntMethod(m_JO_me, JavaClass_TextBox.GetIndexOfLine, (jint)line);
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

		jstring currentLineText = reinterpret_cast<jstring>(env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetCurrentLineText));

		const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);

		length = strlen(nativeCurrentLineText);

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
		
		JO_selection = env->CallObjectMethod(m_JO_me, JavaClass_TextBox.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetStartIndex);
		selectionRange.begin.character = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextBoxSelection.GetEndIndex);
		selectionRange.end.character = (int)index;
	}

	return selectionRange;
}

void TextBoxControl::UpdateCaret()
{
	NotifyCaretPositionChanged(GetHWND(), this);
}

jobject TextBoxControl::GetMe()
{
	return m_JO_me;
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
		retval.boolVal = m_focused ? VARIANT_TRUE : VARIANT_FALSE;
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

LRESULT TextBoxControl::StaticTextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{

	TextBoxControl * pThis = reinterpret_cast<TextBoxControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<TextBoxControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
		pThis->m_ControlHWND = hwnd;
	}

	if (message == WM_NCDESTROY)
	{
		pThis = NULL;
		SetWindowLongPtr(hwnd, GWLP_USERDATA, NULL);
	}

	if (pThis != NULL)
	{
		return pThis->TextBoxControlWndProc(hwnd, message, wParam, lParam);
	}

	return DefWindowProc(hwnd, message, wParam, lParam);
}


LRESULT CALLBACK TextBoxControl::TextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			IRawElementProviderSimple* provider = new TextBoxProvider(hwnd, this);
			if (provider != NULL)
			{
				lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, provider);
				provider->Release();
			}

			//lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetTextBoxProvider());
		}
		break;
	}
	case WM_DESTROY:
	{
		IRawElementProviderSimple* provider = this->GetTextBoxProvider();
		if (provider != NULL)
		{
			HRESULT hr = UiaDisconnectProvider(provider);
			if (FAILED(hr))
			{
				// An error occurred while trying to disconnect the provider. For now, print the error message.
				std::cout << "UiaDisconnectProvider failed: UiaDisconnectProvider returned HRESULT 0x" << hr << std::endl;
			}
		}
	}
	case WM_SETFOCUS:
	{
		SetControlFocus(true);
		break;
	}
	case WM_KILLFOCUS:
	{
		SetControlFocus(false);
		break;
	}
	case QUORUM_UPDATESELECTION:
	{
		
		//Range indices = *(Range*)(lParam);
		//m_caretPosition = indices;

		UpdateCaret();

		break;
	}
	case QUORUM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
		break;
	}
	case QUORUM_SETTEXT:
	{
		//m_fullText = (WCHAR*)lParam;
		break;
	}
	default:

		lResult = ForwardMessage(hwnd, message, wParam, lParam);
		break;

	}

	return lResult;
}