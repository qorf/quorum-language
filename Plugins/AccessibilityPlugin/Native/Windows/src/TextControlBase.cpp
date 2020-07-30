#include <string>

#include "TextControlBase.h"

TextControlBase::TextControlBase(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : Item(env, std::move(name), std::move(description), jItem)
{
}

void TextControlBase::NotifySelectionChanged()
{
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProviderSimple().get(), UIA_Text_TextSelectionChangedEventId);
	}
}

void TextControlBase::NotifyTextChanged()
{
	if (IsReadyForEvents())
	{
		UiaRaiseAutomationEvent(GetProviderSimple().get(), UIA_Text_TextChangedEventId);
	}
}
