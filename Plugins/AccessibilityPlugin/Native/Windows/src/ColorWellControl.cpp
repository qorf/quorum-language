#include "ColorWellControl.h"
#include "ColorWellProvider.h"
#include "ControlTImpl.h"

ColorWellControl::ColorWellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	currentText = GetColorText();
}

void ColorWellControl::Invoke()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && GetMe() != nullptr)
	{
		env->CallVoidMethod(GetMe(), JavaClass_Button.Activate);
	}
}

std::wstring ColorWellControl::GetColorText()
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_ColorWell.GetColorText == nullptr)
	{
		return currentText;
	}
	jstring value = (jstring)env->CallObjectMethod(GetMe(), JavaClass_ColorWell.GetColorText);
	if (value == nullptr)
	{
		return L"";
	}
	const jchar* characters = env->GetStringChars(value, NULL);
	jsize length = env->GetStringLength(value);
	std::wstring result(reinterpret_cast<const wchar_t*>(characters), length);
	env->ReleaseStringChars(value, characters);
	env->DeleteLocalRef(value);
	return result;
}

void ColorWellControl::NotifyColorChanged()
{
	std::wstring text = GetColorText();
	if (text == currentText)
	{
		return;
	}
	std::wstring oldText = currentText;
	currentText = text;

	if (IsReadyForEvents())
	{
		VARIANT oldWords, newWords;
		VariantInit(&oldWords);
		VariantInit(&newWords);
		oldWords.vt = VT_BSTR;
		oldWords.bstrVal = SysAllocStringLen(oldText.data(), static_cast<UINT>(oldText.size()));
		newWords.vt = VT_BSTR;
		newWords.bstrVal = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_ValueValuePropertyId, oldWords, newWords);
		VariantClear(&oldWords);
		VariantClear(&newWords);
	}
}

// Generate ControlT's members here, so other files can link to them (the
// quorum-natives build, WindowsDllMacBuild.md fix 2).
template class ControlT<ColorWellControl, ColorWellProvider>;
