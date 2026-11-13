#pragma once

#include <string>
#include "TextFieldControl.h"
#include "ControlT.h"

class ComboBoxProvider;

/*
	A Quorum ComboBox for UI Automation: a combo box whose text can be typed,
	as an editable Win32 combo box. A ComboBox is a TextField in Quorum, so
	this control is a TextFieldControl too: its text, caret and selection,
	and the text field's events from Quorum, work as for a text field. Its
	provider adds the expand and collapse pattern for the list, which opens
	below it as a List with its own elements.
*/
class ComboBoxControl : public ControlT<ComboBoxControl, ComboBoxProvider, TextFieldControl>
{
public:
	ComboBoxControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	bool IsOpen();
	void Open();
	void Close();

	// Text set by a screen reader through the value pattern.
	void SetText(const std::wstring& text);

	// Called from Quorum after the list opened or closed: raises the
	// expand collapse state's change.
	void NotifyExpandCollapse(bool open);
private:
	void CallVoid(jmethodID method);

	bool m_open;
};
