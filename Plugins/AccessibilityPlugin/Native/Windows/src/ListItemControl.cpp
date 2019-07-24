#include "ListItemControl.h"
#include "ListItemProvider.h"
#include "ListControl.h"
#include "ListProvider.h"
#include "ControlTImpl.h"

ListItemControl::ListItemControl(JNIEnv* env, std::wstring&& name, _In_ ListControl* parentList, jobject jItem)
	: ControlT(env, std::move(name), L"", jItem)
	, m_parentList(parentList)
{
}

ListControl* ListItemControl::GetParentList()
{
	return m_parentList;
}

std::wstring ListItemControl::GetText()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL)
	{
		jstring fullText = reinterpret_cast<jstring>(env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetListItemText, GetMe()));

		const char* nativeFullText = env->GetStringUTFChars(fullText, 0);
		std::wstring wFullText = CreateWideStringFromUTF8Win32(nativeFullText);

		env->ReleaseStringUTFChars(fullText, nativeFullText);

		return wFullText;
	}

	return L"";
}
