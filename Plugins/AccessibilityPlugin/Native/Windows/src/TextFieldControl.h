#pragma once
#include <windows.h>
#include <UIAutomation.h>

#include "Resources.h"
#include "TextBoxControl.h"
#include "Item.h"

class TextFieldProvider;

class TextFieldControl : public ControlT<TextFieldControl, TextFieldProvider>
{
	public:
		TextFieldControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

		int GetCaretPosition();
		int GetSize();
		std::wstring GetText();
		EndPoint GetTextFieldEndpoint();
		Range GetSelectionRange();

		VARIANT GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint* end);

		void UpdateSelection(const Range& indices);

		// Used to update the TextBox's contents to match with Quorum.
		void UpdateText(int index, std::wstring added, int removed);

	private:
		void UpdateCaret();

		std::wstring m_text;
};
