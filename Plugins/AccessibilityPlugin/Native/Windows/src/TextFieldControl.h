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
		virtual void Focus(bool isFocused) override;

		void UpdateSelection(const Range& indices);

	private:
		void UpdateCaret();
};
