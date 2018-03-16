#include <iostream>
#include <string>
#include <strsafe.h>

#include "TextBoxControl.h"
#include "TextBoxTextAreaProvider.h"
#include "TextBoxProvider.h"

bool TextBoxControl::Initialized = false;

TextBoxControl::TextBoxControl(_In_reads_(lineCount) TextLine * lines, _In_ int lineCount, _In_ EndPoint caret)
{
	TextBoxControl::lines = lines;
	TextBoxControl::lineCount = lineCount;
	m_caretPosition.line = caret.line;
	m_caretPosition.character = caret.character;
	isActive = false;
}

// RegisterButtonControl: Registers the TextControl with Windows API so that it can used and later be registered with UI Automation
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

		std::string message(messageBuffer, size);
		std::cout << "RegisterButtonControl Error " << errorMessageID << ": " << message << std::endl;
		fflush(stdout);

		//Free the buffer.
		LocalFree(messageBuffer);

		return false;
	}

	return true;
}

HWND TextBoxControl::Create(_In_ HWND parent, _In_ HINSTANCE instance, _In_ WCHAR* textboxName, _In_ WCHAR* textboxDescription, TextLine quorumLines[], _In_ EndPoint caret)
{

	if (!Initialized)
	{
		Initialized = Initialize(instance);
	}

	if (Initialized)
	{
		TextBoxControl * control = new TextBoxControl(quorumLines, _ARRAYSIZE(quorumLines), caret);

		// TODO: Debug. Remove this section
		PCWSTR sampleText;
		sampleText = L"Create method entered.";

		TextLine* textline = control->GetLine(0);
		std::wcout << "WCOUT: " << sampleText << std::endl;
		std::wcout << "Textline->Text: " << textline->text << std::endl;
		std::cout << "Size of the text line: " << control->GetLineLength(0) << std::endl;
		std::cout << "Number of lines: " << _ARRAYSIZE(quorumLines) << std::endl;
		// =====

		control->m_TextboxHWND = CreateWindowExW(WS_EX_WINDOWEDGE,
												 L"QUORUM_TEXTBOX",
												 textboxName,
												 WS_VISIBLE | WS_CHILD,
												 -1,
												 -1,
												 1,
												 1,
												 parent, // Parent window
												 NULL,
												 instance,
												 static_cast<PVOID>(control)
												 );

		if (control->m_TextboxHWND == 0)
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
			control->SetName(textboxName);

			if (UiaClientsAreListening())
			{
				control->GetTextBoxProvider();
			}

			return control->m_TextboxHWND;
		}
	}

	return 0; // Indicates failure to create window.

}

TextLine * TextBoxControl::GetLine(_In_ int line)
{
	if (line < 0 || line >= lineCount)
	{
		return NULL;
	}
	return &lines[line];
}

int TextBoxControl::GetLineLength(_In_ int line)
{
	size_t strLength;
	if (FAILED(StringCchLength(lines[line].text, 10000, &strLength)))
	{
		strLength = 0;
	}
	return static_cast<int>(strLength);
}

int TextBoxControl::GetLineCount()
{
	return lineCount;
}

EndPoint TextBoxControl::GetEndOfText()
{
	EndPoint endOfText = { lineCount - 1, 0 };
	endOfText.character = GetLineLength(endOfText.line);
	return endOfText;
}

VARIANT TextBoxControl::GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute)
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
		retval.vt = VT_BOOL;
		retval.boolVal = VARIANT_TRUE;
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
		retval.boolVal = isActive ? VARIANT_TRUE : VARIANT_FALSE;
	}
	else if (attribute == UIA_SelectionActiveEndAttributeId)
	{
		retval.vt = VT_I4;
		retval.lVal = ActiveEnd_None;
	}
	else if (attribute == UIA_CaretPositionAttributeId)
	{
		retval.vt = VT_I4;
		if (m_caretPosition.character == 0)
		{
			retval.lVal = CaretPosition_BeginningOfLine;
		}
		else if (m_caretPosition.character == GetLineLength(m_caretPosition.line))
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
		if (end->character >= GetLineLength(end->line))
		{
			if (end->line + 1 >= GetLineCount())
			{
				return false;
			}
			end->line++;
			end->character = 0;
		}
		else
		{
			end->character++;
		}
	}
	else
	{
		if (end->character <= 0)
		{
			if (end->line <= 0)
			{
				return false;
			}
			end->line--;
			end->character = GetLineLength(end->line);
		}
		else
		{
			end->character--;
		}
	}
	return true;
}

// This moves forward or backward by line. It targets the end of lines, so if
// in the middle of a line, it moves to the end, and if it's at the end of a line
// it moves to the end of the next line.

// When moving backwards it still targets the end of the line, moving to the end of
// the previous line, except when on the first line, where it will move to the
// beginning of the line, as there isn't a previous line.

// This is done so whether we walk forward or backwards, there is a consistent
// span given, from the end of one line, to the end of the next
bool TextBoxControl::StepLine( _In_ EndPoint start, _In_ bool forward, _Out_ EndPoint * end)
{
	*end = start;
	if (forward)
	{
		if (end->character >= GetLineLength(end->line))
		{
			if (end->line + 1 >= GetLineCount())
			{
				return false;
			}
			end->line++;
			end->character = GetLineLength(end->line);
		}
		else
		{
			end->character = GetLineLength(end->line);
		}
	}
	else
	{
		if (end->line <= 0)
		{
			if (end->character <= 0)
			{
				return false;
			}
			end->character = 0;
		}
		else
		{
			end->line--;
			end->character = GetLineLength(end->line);
		}
	}
	return true;
}

TextBoxTextAreaProvider* TextBoxControl::GetTextBoxProvider()
{
	if (m_pTextBoxProvider == NULL)
	{
		m_pTextBoxProvider = new TextBoxTextAreaProvider(this->m_TextboxHWND, this);
	}
	return m_pTextBoxProvider;
}

bool TextBoxControl::IsActive()
{
	return isActive;
}

EndPoint TextBoxControl::GetCaretPosition()
{
	return m_caretPosition;
}

WCHAR* TextBoxControl::GetName()
{
	return m_pTextboxName;
}

void TextBoxControl::SetName(_In_ WCHAR* name)
{
	m_pTextboxName = name;
}

LRESULT TextBoxControl::StaticTextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam)
{

	TextBoxControl * pThis = reinterpret_cast<TextBoxControl*>(GetWindowLongPtr(hwnd, GWLP_USERDATA));
	if (message == WM_NCCREATE)
	{
		CREATESTRUCT *createStruct = reinterpret_cast<CREATESTRUCT*>(lParam);
		pThis = reinterpret_cast<TextBoxControl*>(createStruct->lpCreateParams);
		SetWindowLongPtr(hwnd, GWLP_USERDATA, reinterpret_cast<LONG_PTR>(pThis));
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
	// Register with UI Automation.
	case WM_GETOBJECT:
	{
		// If the lParam matches the RootObjectId, send back the RawElementProvider
		//if (static_cast<long>(lParam) == static_cast<long>(UiaRootObjectId))
		//{
			IRawElementProviderSimple * provider = new TextBoxProvider(hwnd, this);
			if (provider != NULL)
			{
				lResult = UiaReturnRawElementProvider(hwnd, wParam, lParam, provider);
				provider->Release();
			}
		//}
		break;
	}
	case CUSTOM_SETFOCUS:
	{
		SetFocus();
		break;
	}
	case WM_KILLFOCUS:
	{
		KillFocus();
		break;
	}
	case CUSTOM_UPDATECARET:
	{
		// TODO: Debug. Remove this section
		PCWSTR sampleText = L"CUSTOM_UPDATECARET message being responded to.";

		TextLine* textline = this->GetLine(0);
		std::wcout << "WCOUT: " << sampleText << std::endl;
		std::wcout << "Textline->Text: " << textline->text << std::endl;
		std::cout << "Size of the text line: " << this->GetLineLength(0) << std::endl;
		fflush(stdout);
		//=====

		UpdateCaret((EndPoint*)lParam);
		break;
	}
	case CUSTOM_SETNAME:
	{
		this->SetName((WCHAR*)lParam);
		break;
	}
	case CUSTOM_SETTEXT:
	{
		// Set the text for the current text line.
		// Currently the textbox only maintains one textline at a time but
		// can and likely will need to be able to hold multiple lines.
		//this->lines->text = (WCHAR*)lParam;
		break;
	}
	default:
		lResult = DefWindowProc(hwnd, message, wParam, lParam);
		break;
	}

	return lResult;
}

void TextBoxControl::SetFocus()
{
	NotifyFocusGained(this->m_TextboxHWND, this);
	isActive = true;
}

void TextBoxControl::KillFocus()
{
	isActive = false;
}

void TextBoxControl::UpdateCaret( _In_ EndPoint* caretPosition)
{

	// TODO: Debug. Remove this section
	/*PCWSTR sampleText;
	sampleText = L"UpdateCaret method entered.";

	TextLine* textline = this->GetLine(0);
	std::wcout << "WCOUT: " << sampleText << std::endl;
	std::wcout << "Textline->Text: " << textline->text << std::endl;
	std::cout << "Size of the text line: " << this->GetLineLength(0) << std::endl;
	fflush(stdout);*/
	//=====

	m_caretPosition = *caretPosition;
	NotifyCaretPositionChanged(this->m_TextboxHWND, this);
}