#include <UIAutomation.h>
#include <string>

#include "CustomMessages.h"
#include "Item.h"

#ifndef TextBoxControl_HEADER
#define TextBoxControl_HEADER

/* The EndPoint structure where the caret is in terms of line and character.
*  For now, the line will not be included since we are storing the full
*  text as a string instead of breaking it into lines.
*  Note: This implementation is not tested and so it may be necessary to implement
*        a multi-line solution.
*/
struct EndPoint
{
	EndPoint() : character(0) {};
	EndPoint(int c) : character(c) {};
	int character;
};

/* This structure stores the range that the caret encompasses in the text.
*  If the caret doesn't have a range, meaning it is only an insertion point for text,
*  then for the purposes of accessibility this is called the degenerate text range.
*  A degenerate text range is a range the begins and ends at the same EndPoint.
*/
struct Range
{
	Range() {};
	Range(EndPoint b, EndPoint e) : begin(b), end(e) {};
	EndPoint begin;
	EndPoint end;
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
	// Since the EndPoint will always reside on line zero we only need to compare whether the lefthand
	// EndPoint's character position is to the left, right, or same as the righthand side.
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

class TextBoxProvider;

class TextBoxControl : public Item
{
	public:
		TextBoxControl(_In_ const char* lines, _In_ int caretIndex);

		static TextBoxControl* Create(_In_ HINSTANCE instance, _In_ WCHAR* textboxName, _In_ WCHAR* textboxDescription, _In_ const char* lines, _In_ int caretIndex);

		int GetLineLength();

		const char* GetLine();


		int GetLineCount();
		EndPoint GetTextboxEndpoint();

		TextBoxProvider* GetTextBoxProvider();

		EndPoint GetCaretPosition();

		VARIANT GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint *end);


		bool HasFocus();

	private:
		static LRESULT CALLBACK StaticTextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		LRESULT CALLBACK TextBoxControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		
		static bool Initialize(_In_ HINSTANCE hInstance);
		static bool Initialized;
		
		void SetControlFocus(_In_ bool focused);

		void UpdateCaret(_In_ EndPoint caretPosition);

		EndPoint m_caretPosition;
		bool m_focused;

		/* This is the text of the entire Textbox control. From this we will pull out the information
		*  we need to report to the screen readers using Ranges and Endpoints.
		*/
		const char * m_fullText;

		/* With the current approach to handling text from the textbox we are holding a
		 * string that contains the entire contents of the textbox instead of using the TextLine struct.
		 * So, m_lineCount should always be 1 since as far as the rest of the control is concerned there is
		 * only one line.
		 */
		const int m_lineCount = 1;
		
		TextBoxProvider* m_pTextBoxProvider;


};

#endif