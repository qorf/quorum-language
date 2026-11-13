#include "ComboBoxControl.h"
#include "ComboBoxProvider.h"
#include "TextFieldProvider.h"
#include "ControlTImpl.h"

ComboBoxControl::ComboBoxControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT<ComboBoxControl, ComboBoxProvider, TextFieldControl>(env, std::move(name), std::move(description), jItem)
{
	m_open = false;
}

bool ComboBoxControl::IsOpen()
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_ComboBox.IsOpen == nullptr)
	{
		return m_open;
	}
	return env->CallBooleanMethod(GetMe(), JavaClass_ComboBox.IsOpen) == JNI_TRUE;
}

void ComboBoxControl::CallVoid(jmethodID method)
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && GetMe() != nullptr && method != nullptr)
	{
		env->CallVoidMethod(GetMe(), method);
	}
}

void ComboBoxControl::Open()
{
	CallVoid(JavaClass_ComboBox.Open);
}

void ComboBoxControl::Close()
{
	CallVoid(JavaClass_ComboBox.Close);
}

void ComboBoxControl::SetText(const std::wstring& text)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_ComboBox.SetText == nullptr)
	{
		return;
	}
	jstring value = env->NewString(reinterpret_cast<const jchar*>(text.data()), static_cast<jsize>(text.size()));
	if (value == nullptr)
	{
		return;
	}
	env->CallVoidMethod(GetMe(), JavaClass_ComboBox.SetText, value);
	env->DeleteLocalRef(value);
}

void ComboBoxControl::NotifyExpandCollapse(bool open)
{
	if (m_open == open)
	{
		return;
	}
	m_open = open;

	if (IsReadyForEvents())
	{
		VARIANT oldState, newState;
		VariantInit(&oldState);
		VariantInit(&newState);
		oldState.vt = VT_I4;
		newState.vt = VT_I4;
		oldState.lVal = open ? ExpandCollapseState_Collapsed : ExpandCollapseState_Expanded;
		newState.lVal = open ? ExpandCollapseState_Expanded : ExpandCollapseState_Collapsed;
		UiaRaiseAutomationPropertyChangedEvent(GetProvider().get(), UIA_ExpandCollapseExpandCollapseStatePropertyId, oldState, newState);
	}
}

// Generate ControlT's members here, so other files can link to them (the
// quorum-natives build, WindowsDllMacBuild.md fix 2).
template class ControlT<ComboBoxControl, ComboBoxProvider, TextFieldControl>;
