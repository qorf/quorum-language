#include "MenuItemControl.h"
#include "MenuItemProvider.h"
#include "MenuBarControl.h"
#include "MenuBarProvider.h"
#include "ControlTImpl.h"

MenuItemControl::MenuItemControl(JNIEnv* env, std::wstring&& menuItemName, std::wstring&& menuItemShortcut, _In_ bool isMenu, _In_ MenuBarControl* parentMenuBar, jobject jItem)
	: ControlT(env, std::move(menuItemName), L"", jItem)
	, m_shortcut(std::move(menuItemShortcut))
	, m_parentMenuBar(parentMenuBar)
	, m_isMenu(isMenu)
{
}

MenuBarControl * MenuItemControl::GetParentMenuBar()
{
	return m_parentMenuBar;
}

bool MenuItemControl::IsMenu()
{
	return m_isMenu;
}

void MenuItemControl::SetShortcut(std::wstring&& shortcut)
{
	m_shortcut = std::move(shortcut);
}

const std::wstring& MenuItemControl::GetShortcut()
{
	return m_shortcut;
}

void MenuItemControl::NotifyExpandCollapse()
{
	GetProvider()->NotifyElementExpandCollapse();
}

std::wstring MenuItemControl::GetMnemonic()
{
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		jstring jText = reinterpret_cast<jstring>(env->CallObjectMethod(javaItem, JavaClass_MenuItem.GetMnemonic));

		if (jText != NULL)
		{
			const char* nativeText = env->GetStringUTFChars(jText, 0);
			std::wstring wText = CreateWideStringFromUTF8Win32(nativeText);

			env->ReleaseStringUTFChars(jText, nativeText);

			return wText;
		}
	}

	return L"";
}