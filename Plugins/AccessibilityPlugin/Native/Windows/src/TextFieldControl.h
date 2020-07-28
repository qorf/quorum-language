#pragma once
#include <windows.h>
#include <UIAutomation.h>

#include "Resources.h"
#include "TextControlBase.h"
#include "ControlT.h"

class TextFieldProvider;

class TextFieldControl : public ControlT<TextFieldControl, TextFieldProvider, TextControlBase>
{
	public:
		TextFieldControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

		bool IsBeginningOfToken(int index) override;
		int GetIndexOfLine(int line) override;
		int GetLineIndexOfCharacter(int characterIndex) override;
		std::wstring GetText() override;
		std::wstring GetText(int startIndex, int endIndex) override;
		int GetSize() override;
		Range GetSelectionRange() override;
		void Select(const Range& range) override;
		bool IsErrorAtIndex(int index) override;

		bool IsPassword();
		void UpdatePassword(bool value);
};
