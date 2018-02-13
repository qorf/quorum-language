#include <UIAutomation.h>
#include "CustomMessages.h"

#ifndef TextBoxControl_HEADER
#define TextBoxControl_HEADER

struct EndPoint
{
	int line;
	int character;
};

struct Range
{
	EndPoint begin;
	EndPoint end;
};

struct TextLine
{
	PCWSTR text;
};

inline int QuickCompareEndpoints(_In_ EndPoint endpoint1, _In_ EndPoint endpoint2)
{
	if (endpoint1.line < endpoint2.line)
	{
		return -2;
	}
	else if (endpoint1.line > endpoint2.line)
	{
		return 2;
	}
	else
	{
		if (endpoint1.character < endpoint2.character)
		{
			return -1;
		}
		else if (endpoint1.character > endpoint2.character)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}

class TextBoxControl
{
	public:
		TextBoxControl(_In_reads_(lineCount) TextLine *lines, _In_ int lineCount, _In_ EndPoint caret);
		static void RegisterTextControl(HINSTANCE hInstance);


		TextLine *GetLine(_In_ int line);
		int GetLineLength(_In_ int line);
		int GetLineCount();
		EndPoint GetEnd();

		VARIANT GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint *end);
		bool StepLine(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint *end);
		bool IsActive();
		EndPoint GetCaretPosition();

	private:
		static LRESULT CALLBACK StaticTextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		LRESULT CALLBACK TextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		LRESULT OnSetFocus(HWND hwnd);
		LRESULT OnKillFocus();
		LRESULT UpdateCaret(HWND hwnd, LPARAM caretPosition);

		EndPoint m_caretPosition;
		bool isActive;
		TextLine *lines;
		int lineCount;
		//TextBoxProvider* m_TextBoxProvider; // Necessary?


};

#endif