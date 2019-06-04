#include <string>
#include <windows.h>
#include <iostream>

#include "TextFieldControl.h"
#include "TextFieldProvider.h"

bool TextFieldControl::initialized = false;

/**** Button methods ***/

// ButtonControl: Constructor. Sets the default values for the button.
TextFieldControl::TextFieldControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem) : Item(env, name, description, jItem), textFieldProvider(NULL), focused(false)
{
}

// ~ButtonControl: Release the reference to the ButtonProvider if there is one.
TextFieldControl::~TextFieldControl()
{
	if (textFieldProvider != NULL)
	{
		textFieldProvider->Release();
		textFieldProvider = NULL;
	}
}

// RegisterButtonControl: Registers the ButtonControl with Windows API so that it can used
bool TextFieldControl::Initialize(_In_ HINSTANCE hInstance)
{
	#if LOG
		log("TextFieldControl::Initialize Start");
	#endif

	WNDCLASSEXW wc;

	ZeroMemory(&wc, sizeof(wc));
	wc.cbSize = sizeof(wc);
	wc.style = CS_HREDRAW | CS_VREDRAW;
	wc.lpfnWndProc = StaticTextFieldControlWndProc;
	wc.hInstance = hInstance;
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.lpszClassName = L"QUORUM_TEXTFIELD";


	if (RegisterClassExW(&wc) == 0)
	{
		// An error occured. Output this error so it can be seen from Quorum.
		DWORD errorMessageID = ::GetLastError();

		LPSTR messageBuffer = nullptr;
		size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
			NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)& messageBuffer, 0, NULL);

		std::string message(messageBuffer, size);
		//std::cout << "RegisterTextFieldControl Error " << errorMessageID << ": " << message << std::endl;
		fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	#if LOG
	log("TextFieldControl::Initialize Finished");
	#endif

	return true;
}

TextFieldControl* TextFieldControl::Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* controlName, _In_ WCHAR* controlDescription, jobject jItem)
{
	#if LOG
	log("TextFieldControl::Create Start");
	#endif

	if (!initialized)
	{
		initialized = Initialize(instance);
	}

	if (initialized)
	{
		TextFieldControl* control = new TextFieldControl(env, controlName, controlDescription, jItem);

		CreateWindowExW(WS_EX_WINDOWEDGE,
			L"QUORUM_TEXTFIELD",
			controlName,
			WS_VISIBLE | WS_CHILD,
			-1,
			-1,
			1,
			1,
			parentWindow,
			NULL,
			instance,
			static_cast<PVOID>(control));

		if (control->m_ControlHWND == NULL)
		{
			DWORD errorMessageID = ::GetLastError();

			LPSTR messageBuffer = nullptr;
			size_t size = FormatMessageA(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
				NULL, errorMessageID, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), (LPSTR)& messageBuffer, 0, NULL);

			std::string message(messageBuffer, size);
			//std::cout << "Native Code - CreateWindowExW Error " << errorMessageID << ": " << message;
			fflush(stdout);

			//Free the buffer.
			LocalFree(messageBuffer);
		}
		else
		{
			if (UiaClientsAreListening())
				control->GetTextFieldProvider();

			#if LOG
			log("TextFieldControl::Initialize Finished Successfully");
			#endif

			return control;
		}
	}

	#if LOG
	log("TextFieldControl::Initialize Finished (RETURNED NULL)");
	#endif

	return NULL; // Indicates failure to create window.
}

TextFieldProvider* TextFieldControl::GetTextFieldProvider()
{
	#if LOG
	log("TextFieldControl::GetTextFieldProvider Start");
	#endif

	if (textFieldProvider == NULL)
	{
		textFieldProvider = new TextFieldProvider(this);
		//UiaRaiseAutomationEvent(textFieldProvider, UIA_Window_WindowOpenedEventId);
	}

	#if LOG
	log("TextFieldControl::GetTextFieldProvider Finished");
	#endif

	return textFieldProvider;
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
		// Wait for Quorum to write
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

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
		// Wait for Quorum to write
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		length = env->CallIntMethod(GetMe(), JavaClass_TextField.GetSize);
	}
	return (int)length;
}

std::wstring TextFieldControl::GetText()
{
	#if LOG
	log("TextFieldControl::GetText Start");
	#endif

	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(GetMe(), JavaClass_TextField.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);
		std::wstring wFullText = CreateWideStringFromUTF8Win32(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);

		TextFieldProvider* eventControl = new TextFieldProvider(this);
		if (eventControl != NULL && UiaClientsAreListening())
		{
			UiaRaiseAutomationEvent(eventControl, UIA_Text_TextChangedEventId);
		}


		#if LOG
		log("TextFieldControl::GetText Finished Successfully");
		#endif

		return wFullText;
	}

	#if LOG
	log("TextFieldControl::GetText Finished (EMPTY STRING)");
	#endif

	return L"";
}

void TextFieldControl::SetControlFocus(_In_ bool focus)
{
	#if LOG
	log("TextFieldControl::SetControlFocus Start");
	#endif

	focused = focus;
	if (focused)
		textFieldProvider->NotifyFocusGained();

	#if LOG
	log("TextFieldControl::SetControlFocus Finished");
	#endif
}

LRESULT CALLBACK TextFieldControl::StaticTextFieldControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	#if LOG
	log("TextFieldControl::StaticTextFieldControlWndProc Start");
	#endif

	TextFieldControl* pThis = reinterpret_cast<TextFieldControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT* createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<TextFieldControl*>(createStruct->lpCreateParams);
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
		#if LOG
		log("TextFieldControl::StaticTextFieldControlWndProc Finished (via Non-Static Proc)");
		#endif
		return pThis->TextFieldControlWndProc(hwnd, message, wParam, lParam);
	}

	#if LOG
	log("TextFieldControl::StaticTextFieldControlWndProc Finished");
	#endif

	return DefWindowProc(hwnd, message, wParam, lParam);
}

// Control window procedure.
LRESULT CALLBACK TextFieldControl::TextFieldControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{
	#if LOG
	log("TextFieldControl::TextFieldControlWndProc Start");
	#endif

	LRESULT lResult = 0;

	switch (message)
	{
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		{
			// Register with UI Automation.
			IRawElementProviderSimple* provider = new TextFieldProvider(this);
			if (provider != NULL)
			{
				lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, provider);
			}

			//lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, this->GetTextBoxProvider());
		}
		break;
	}
	case WM_DESTROY:
	{
		IRawElementProviderSimple* provider = this->GetTextFieldProvider();
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

		//UpdateCaret();

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

	#if LOG
	log("TextFieldControl::TextFieldControlWndProc Finished");
	#endif

	return lResult;
}

VARIANT TextFieldControl::GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute)
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
		retval.boolVal = HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
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

bool TextFieldControl::StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint* end)
{
	#if LOG
	log("TextFieldControl::StepCharacter Start");
	#endif

	*end = start;
	if (forward)
	{
		if (end->character >= GetSize())
		{
			#if LOG
			log("TextFieldControl::GetAttributeAtPoint Finished (returned FALSE, >= case");
			#endif
			return false;
		}

		end->character++;
	}
	else
	{
		if (end->character <= 0)
		{
			#if LOG
			log("TextFieldControl::GetAttributeAtPoint Finished (returned FALSE, <= case)");
			#endif
			return false;
		}
		else
		{
			end->character--;
		}
	}

	#if LOG
	log("TextFieldControl::GetAttributeAtPoint Finished (returned TRUE)");
	#endif

	return true;
}

EndPoint TextFieldControl::GetTextFieldEndpoint()
{
	#if LOG
	log("TextFieldControl::GetTextFieldEndpoint Start");
	#endif

	EndPoint endOfText(0);
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		jstring fullText = reinterpret_cast<jstring>(env->CallObjectMethod(GetMe(), JavaClass_TextField.GetText));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

		endOfText.character = strlen(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);
	}

	#if LOG
	log("TextFieldControl::GetTextFieldEndpoint Finished");
	#endif

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

		// Wait for Quorum to write
		env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.WaitForUpdate);

		JO_selection = env->CallObjectMethod(GetMe(), JavaClass_TextField.GetSelection);

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetStartIndex);
		selectionRange.begin.character = (int)index;

		index = env->CallIntMethod(JO_selection, JavaClass_TextFieldSelection.GetEndIndex);
		selectionRange.end.character = (int)index;

	}

	return selectionRange;
}