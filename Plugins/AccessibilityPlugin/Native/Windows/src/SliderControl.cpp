#include "SliderControl.h"
#include "SliderProvider.h"
#include "ControlTImpl.h"

SliderControl::SliderControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	currentValue = 0;
	if (env != NULL && jItem != nullptr)
	{
		currentValue = env->CallDoubleMethod(jItem, JavaClass_Slider.GetValue);
	}
}

double SliderControl::CallDouble(jmethodID method, double fallback)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && GetMe() != nullptr)
	{
		return env->CallDoubleMethod(GetMe(), method);
	}
	return fallback;
}

double SliderControl::GetValue()
{
	return CallDouble(JavaClass_Slider.GetValue, currentValue);
}

double SliderControl::GetMinimum()
{
	return CallDouble(JavaClass_Slider.GetMinimum, 0);
}

double SliderControl::GetMaximum()
{
	return CallDouble(JavaClass_Slider.GetMaximum, 100);
}

double SliderControl::GetSmallChange()
{
	return CallDouble(JavaClass_Slider.GetStep, 1);
}

double SliderControl::GetLargeChange()
{
	return CallDouble(JavaClass_Slider.GetPageStep, 10);
}

std::wstring SliderControl::GetValueText()
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr)
	{
		return currentText;
	}
	jstring value = (jstring)env->CallObjectMethod(GetMe(), JavaClass_Slider.GetValueText);
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

void SliderControl::ChangeValue(double value)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && GetMe() != nullptr)
	{
		env->CallVoidMethod(GetMe(), JavaClass_Slider.ChangeValue, (jdouble)value, (jboolean)JNI_TRUE);
	}
}

void SliderControl::NotifyValueChanged(double value)
{
	std::wstring text = GetValueText();
	if (currentValue == value && currentText == text)
	{
		return;
	}
	double oldValue = currentValue;
	std::wstring oldText = currentText;
	currentValue = value;
	currentText = text;

	if (IsReadyForEvents())
	{
		VARIANT oldNumber, newNumber;
		VariantInit(&oldNumber);
		VariantInit(&newNumber);
		oldNumber.vt = VT_R8;
		oldNumber.dblVal = oldValue;
		newNumber.vt = VT_R8;
		newNumber.dblVal = value;
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_RangeValueValuePropertyId, oldNumber, newNumber);

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
template class ControlT<SliderControl, SliderProvider>;
