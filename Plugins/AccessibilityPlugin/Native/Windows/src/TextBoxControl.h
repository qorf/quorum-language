#pragma once

#include <UIAutomation.h>
#include <string>

#include "jni.h"

#include "Resources.h"
#include "ControlT.h"

/* The EndPoint structure where the caret is in terms of line and character.
*  For now, the line will not be included since we are storing the full
*  text as a string instead of breaking it into lines.
*  Note: This implementation is not tested and so it may be necessary to implement
*        a multi-line solution.
*
struct EndPoint
{
	EndPoint() : character(0) {};
	EndPoint(int c) : character(c) {};
	int character;
};
*/

/* This structure stores the range that the caret encompasses in the text.
*  If the caret doesn't have a range, meaning it is only an insertion point for text,
*  then for the purposes of accessibility this is called the degenerate text range.
*  A degenerate text range is a range the begins and ends at the same EndPoint.
*/
struct Range
{
	Range() {};
	Range(int b, int e) : begin(b), end(e) {};
	int begin;
	int end;
};

/* CompareEndpointPair: Compares two Endpoints to determine whether or not one is less than, equal to, or greater than another.
*						  This function is used in several locations for walking the text line to determine various qualities about it.
*						  Less Than: To be less than an EndPoint means that a given EndPoint's line or character value is less than that of another.
*						  Greater than: To be greater than an EndPoint means that a given EndPoint's line or character value exceeds that of another.
*						  Equal To: To be equal to an EndPoint means that for a given EndPoint both its line and character values equal that of another.
*		Return: an Integer value that indicates where one EndPoint is in relation to another.
*/
inline int CompareEndpointPair(_In_ int endpoint1, _In_ int endpoint2)
{
	// Since the EndPoint will always reside on line zero we only need to compare whether the lefthand
	// EndPoint's character position is to the left, right, or same as the righthand side.
	if (endpoint1 < endpoint2)
	{
		// Lefthand EndPoint character position is to the left of the righthand side.
		return -1;
	}
	else if (endpoint1 > endpoint2)
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

class TextBoxControl : public ControlT<TextBoxControl, TextBoxProvider>
{
	public:
		TextBoxControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

		int GetCaretLine();
		int GetIndexOfLine(int line);
		int GetLineLength();
		std::wstring GetText();
		int GetSize();
		Range GetSelectionRange();

		VARIANT GetAttributeAtPoint(_In_ int start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ int start, _In_ bool forward, _Out_ int *end);

		void NotifySelectionChanged();

		// Used to update the TextBox's contents to match with Quorum.
		void UpdateText(int index, std::wstring added, int removed);

	private:
		std::wstring m_text;
};
