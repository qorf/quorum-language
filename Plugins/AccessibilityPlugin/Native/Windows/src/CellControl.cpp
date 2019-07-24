#include "CellControl.h"
#include "CellProvider.h"
#include "TableControl.h"
#include "TableProvider.h"
#include "ControlTImpl.h"

CellControl::CellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ TableControl* parent, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
	, m_parentTable(parent)
{
}

TableControl* CellControl::GetParentTable()
{
	return m_parentTable;
}

std::wstring CellControl::GetText()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring fullText = reinterpret_cast<jstring>(env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCellText, GetMe()));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);
		std::wstring wFullText = CreateWideStringFromUTF8Win32(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);

		return wFullText;
	}

	return L"";
}

