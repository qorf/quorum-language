#include <UIAutomation.h>
#include <string>
#include "CustomMessages.h"

#ifndef TextBoxControl_HEADER
#define TextBoxControl_HEADER

struct EndPoint
{
	EndPoint() {};
	EndPoint(int l, int c) : line(l), character(c) {};
	int line;
	int character;
};

struct Range
{
	Range() {};
	Range(EndPoint b, EndPoint e) : begin(b), end(e) {};
	EndPoint begin;
	EndPoint end;
};

// The TextLine struct contains the contents of the Textline. Currently it's only a pointer to a wide string but could be 
// expanded to hold the text format, line number, and other important IDE info to be exposed to screen readers.
struct TextLine
{
	PCWSTR text;
};

/* CompareEndpointPair: Compares two Endpoints to determine whether or not one is less than, equal to, or greater than another.
*						  This function is used in several locations for walking the text line to determine various qualities about it.
*						  Less Than: To be less than an EndPoint means that a given EndPoint's line or character value is less than that of another.
*						  Greater than: To be greater than an EndPoint means that a given EndPoint's line or character value exceeds that of another.
*						  Equal To: To be equal to an EndPoint means that for a given EndPoint both its line and character values equal that of another.
*		Return: an Integer value that indicates where one EndPoint is in relation to another.
*/
inline int CompareEndpointPair(_In_ EndPoint endpoint1, _In_ EndPoint endpoint2)
{
	// First check if the two EndPoints are on the same line.
	if (endpoint1.line < endpoint2.line)
	{
		// The first EndPoint is on a line above the second EndPoint.
		// Return -2 to indicate that the lefthand EndPoint is a line above the righthand side.
		return -2;
	}
	else if (endpoint1.line > endpoint2.line)
	{
		// The first EndPoint is on a line below the second EndPoint.
		// Return 2 to indicate that the lefthand EndPoint is a line below the righthand side.
		return 2;
	}
	else
	{
		// Both EndPoints are on the same line. Now, compare whether the lefthand EndPoint's character position is to the left, right, or same as the righthand side.
		if (endpoint1.character < endpoint2.character)
		{
			// Lefthand EndPoint character position is to the left of the righthand side.
			return -1;
		}
		else if (endpoint1.character > endpoint2.character)
		{
			// Lefthand EndPoint character position is to the right of the righthand side.
			return 1;
		}
		else
		{
			// The EndPoint's are equal to one another, return 0 to indicate this.
			return 0;
		}
	}
}

class TextBoxProvider;

class TextBoxControl
{
	public:
		TextBoxControl(_In_reads_(lineCount) TextLine *lines, _In_ int lineCount, _In_ EndPoint caret);
		~TextBoxControl();

		static HWND Create(_In_ HINSTANCE instance, _In_ WCHAR* textboxName, _In_ WCHAR* textboxDescription, TextLine lines[], _In_ EndPoint caret);

		TextLine* GetLine(_In_ int line);
		void SetLineText(_In_ int line, _In_ PCWSTR newText);
		int GetLineLength(_In_ int line);
		int GetLineCount();
		EndPoint GetTextboxEndpoint();

		TextBoxProvider* GetTextBoxProvider();
		WCHAR* GetName();
		void SetName(_In_ WCHAR* name);
		VARIANT GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint *end);
		bool StepLine(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint *end);
		EndPoint GetCaretPosition();
		std::wstring GetText();

		bool HasFocus();

	private:
		static LRESULT CALLBACK StaticTextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		LRESULT CALLBACK TextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		
		static bool Initialize(_In_ HINSTANCE hInstance);
		static bool Initialized;
		
		void SetControlFocus();
		void KillControlFocus();
		void UpdateCaret(/*_In_ EndPoint* caretPosition*/);

		HWND m_TextboxHWND;
		EndPoint m_caretPosition;
		bool m_focused;
		TextLine* m_pLines;
		int m_lineCount;
		WCHAR* m_pTextboxName;
		std::wstring m_Text; // The text to be spoken aloud by the screen reader.
		TextBoxProvider* m_pTextBoxProvider;


};

#endif