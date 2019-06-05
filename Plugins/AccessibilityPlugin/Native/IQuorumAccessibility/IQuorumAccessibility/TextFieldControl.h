#pragma once
#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Resources.h"
#include "TextBoxControl.h"
#include "Item.h"

class TextFieldProvider;

class TextFieldControl : public Item
{
	public:
		TextFieldControl(JNIEnv* env, _In_ WCHAR* name, _In_ WCHAR* description, jobject jItem);
		virtual ~TextFieldControl();

		static TextFieldControl* Create(JNIEnv* env, _In_ HINSTANCE instance, _In_ HWND parentWindow, _In_ WCHAR* buttonName, _In_ WCHAR* buttonDescription, jobject jItem);

		TextFieldProvider* GetTextFieldProvider();

		int GetCaretPosition();
		int GetSize();
		std::wstring GetText();
		EndPoint GetTextFieldEndpoint();
		Range GetSelectionRange();
		virtual void Focus(bool isFocused) override;

		VARIANT GetAttributeAtPoint(_In_ EndPoint start, _In_ TEXTATTRIBUTEID attribute);
		bool StepCharacter(_In_ EndPoint start, _In_ bool forward, _Out_ EndPoint* end);

	private:
		static LRESULT CALLBACK StaticTextFieldControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);
		LRESULT CALLBACK TextFieldControlWndProc(_In_ HWND hwnd, _In_ UINT message, _In_ WPARAM wParam, _In_ LPARAM lParam);

		static bool Initialize(_In_ HINSTANCE hInstance);
		void UpdateCaret();
		TextFieldProvider* textFieldProvider;
		static bool initialized;
};