#pragma once

#include <UIAutomation.h>
#include <string>

#include "jni.h"

#include "Resources.h"
#include "ControlT.h"
#include "TextControlBase.h"

class TextBoxProvider;

class TextBoxControl : public ControlT<TextBoxControl, TextBoxProvider, TextControlBase>
{
	public:
		TextBoxControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

		bool IsBeginningOfToken(int index) override;
		int GetIndexOfLine(int line) override;
		int GetLineIndexOfCharacter(int characterIndex) override;
		std::wstring GetText() override;
		std::wstring GetText(int startIndex, int endIndex) override;
		int GetSize() override;
		Range GetSelectionRange() override;
		void Select(const Range& range) override;
		bool IsErrorAtIndex(int index) override;
};
