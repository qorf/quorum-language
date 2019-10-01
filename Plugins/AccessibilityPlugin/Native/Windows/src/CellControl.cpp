#include "CellControl.h"
#include "CellProvider.h"
#include "TableControl.h"
#include "TableProvider.h"
#include "ControlTImpl.h"

CellControl::CellControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, _In_ TableControl* parent, jobject jItem, bool isTreeCell, bool isExpanded)
	: ControlT(env, std::move(name), std::move(description), jItem)
	, m_parentTable(parent)
	, m_isTreeCell(isTreeCell)
	, m_isExpanded(isExpanded)
{
}

TableControl* CellControl::GetParentTable()
{
	return m_parentTable;
}

bool CellControl::IsTreeTableCell()
{
	return m_isTreeCell;
}

bool CellControl::IsExpanded()
{
	return m_isExpanded;
}

void CellControl::SetExpanded(bool expanded)
{
	m_isExpanded = expanded;
	GetProvider()->NotifyElementExpandCollapse();
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



