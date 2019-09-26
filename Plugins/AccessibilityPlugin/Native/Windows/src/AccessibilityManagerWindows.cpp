#include "jni.h"
#include "plugins_quorum_Libraries_Interface_AccessibilityManager.h"

#include <Windows.h>
#include<comutil.h>
#include <UIAutomation.h>
#include <atlsafe.h>
#include "Resources.h"

#include "ItemControl.h"
#include "ButtonControl.h"
#include "RadioButtonControl.h"
#include "CheckBoxControl.h"
#include "TextBoxControl.h"
#include "MenuBarControl.h"
#include "MenuControl.h"
#include "MenuItemControl.h"
#include "PopupMenuItemControl.h"
#include "ProgressBarControl.h"
#include "TreeControl.h"
#include "TreeItemControl.h"
#include "TextFieldControl.h"
#include "TabPaneControl.h"
#include "TabControl.h"
#include "ToolBarControl.h"
#include "DialogControl.h"
#include "ListControl.h"
#include "ListItemControl.h"
#include "TableControl.h"
#include "CellControl.h"
#include "WindowRoot.h"

// For Debug Output
#include <iostream>
#include <string>

// DllMain: Entry point for dll. Nothing to do here.
BOOL WINAPI DllMain(HINSTANCE instance, DWORD reason, LPVOID reserved)
{
	UNREFERENCED_PARAMETER(instance);
	UNREFERENCED_PARAMETER(reason);
	UNREFERENCED_PARAMETER(reserved);
	return TRUE;
}

Item* GetItemFromLong(jlong itemAsLong)
{
	if (itemAsLong > 0)
	{
		return reinterpret_cast<Item*>(itemAsLong);
	}
	return nullptr;
}

jlong GetItemAsLong(_In_ Item* item)
{
	return reinterpret_cast<jlong>(item);
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_IsScreenReaderListeningNative(JNIEnv* env, jobject obj) {
	if (UiaClientsAreListening()) {
		return true;
	}
	else {
		return false;
	}
}

#pragma region Create Accessible Object

template <typename ControlT, typename... TArgs>
ControlT* Create(JNIEnv* env, _In_opt_ Item* parent, TArgs&&... args)
{
	if (!parent)
	{
		parent = GetMainWindowRoot();
	}

	const auto control = new ControlT(env, std::forward<TArgs>(args)...);
	parent->AppendChild(control);
	//std::wcout << L"Created/appended item " << control->GetName() << std::endl;
	//std::wcout.flush();
	return control;
}

// CreateItem: This is the most generic accessible object that can be created. It only contains a name and a description.
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateItemNative(JNIEnv *env, jobject obj, jlong parent, jstring itemName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);
	const auto parentItem = GetItemFromLong(parent);
	const auto pItemControl = Create<ItemControl>(env, parentItem, wItemName, wDescription, jItem);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pItemControl);

}

// CreateButton: Creates a window that contains the accessible information for a PushButton that was passed into this function.
//		Returns: jlong which is the HWND for the window. This is used to further interact with the button after creation. i.e., to rename the button later should the name be changed. Also, used to keep track of it in java.
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateButtonNative(JNIEnv *env, jobject obj, jlong parent, jstring buttonName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeButtonName = env->GetStringUTFChars(buttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wButtonName = CreateWideStringFromUTF8Win32(nativeButtonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	const auto parentItem = GetItemFromLong(parent);
	const auto pButtonControl = Create<ButtonControl>(env, parentItem, wButtonName, wDescription, jItem);

	env->ReleaseStringUTFChars(buttonName, nativeButtonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pButtonControl);

}

// CreateCheckBox: 
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCheckBoxNative(JNIEnv *env, jobject obj, jlong parent, jstring togglebuttonName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeTogglebuttonName = env->GetStringUTFChars(togglebuttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTogglebuttonName = CreateWideStringFromUTF8Win32(nativeTogglebuttonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	const auto parentItem = GetItemFromLong(parent);
	const auto pCheckBoxControl = Create<CheckBoxControl>(env, parentItem, wTogglebuttonName, wDescription, jItem);

	env->ReleaseStringUTFChars(togglebuttonName, nativeTogglebuttonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pCheckBoxControl);

}

// CreateRadioButton: 
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateRadioButtonNative(JNIEnv *env, jobject obj, jlong parent, jstring itemName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wRadiobuttonName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	const auto parentItem = GetItemFromLong(parent);
	const auto pRadiobuttonControl = Create<RadioButtonControl>(env, parentItem, wRadiobuttonName, wDescription, jItem);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pRadiobuttonControl);

}

// CreateTextBox: 
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextBoxNative(JNIEnv *env, jobject obj, jlong parent, jstring textboxName, jstring description, jobject self)
{
	UNREFERENCED_PARAMETER(obj);
	const char* nativeTextboxName = env->GetStringUTFChars(textboxName, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextboxName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	jobject nativeSelf = env->NewGlobalRef(self);

	const auto parentItem = GetItemFromLong(parent);
	const auto pTextboxControl = Create<TextBoxControl>(env, parentItem, wTextboxName, wDescription, nativeSelf);

	env->ReleaseStringUTFChars(textboxName, nativeTextboxName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pTextboxControl);

}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextFieldNative(JNIEnv* env, jobject obj, jlong parent, jstring textFieldName, jstring description, jobject self)
{
	UNREFERENCED_PARAMETER(obj);
	const char* nativeTextFieldName = env->GetStringUTFChars(textFieldName, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextFieldName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	jobject nativeSelf = env->NewGlobalRef(self);

	const auto parentItem = GetItemFromLong(parent);
	const auto textFieldControl = Create<TextFieldControl>(env, parentItem, wTextboxName, wDescription, nativeSelf);

	env->ReleaseStringUTFChars(textFieldName, nativeTextFieldName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(textFieldControl);

}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuBarNative(JNIEnv * env, jobject obj, jlong parent, jstring menuBarName, jobject jItem)
{
	const char* nativeMenuBarName = env->GetStringUTFChars(menuBarName, 0);
	WCHAR* wMenuBarName = CreateWideStringFromUTF8Win32(nativeMenuBarName);

	const auto parentItem = GetItemFromLong(parent);
	const auto pMenuBarControl = Create<MenuBarControl>(env, parentItem, wMenuBarName, jItem);

	env->ReleaseStringUTFChars(menuBarName, nativeMenuBarName);

	return GetItemAsLong(pMenuBarControl);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuNative(JNIEnv* env, jobject obj, jlong parent, jstring menuBarName, jobject jItem) {
	const char* nativeMenuBarName = env->GetStringUTFChars(menuBarName, 0);
	WCHAR* wMenuBarName = CreateWideStringFromUTF8Win32(nativeMenuBarName);

	const auto parentItem = GetItemFromLong(parent);
	const auto pMenuBarControl = Create<MenuControl>(env, parentItem, wMenuBarName, jItem);

	env->ReleaseStringUTFChars(menuBarName, nativeMenuBarName);

	return GetItemAsLong(pMenuBarControl);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateProgressBarNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jstring description, jobject jItem) {
	const char* nativeName = env->GetStringUTFChars(name, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	const auto parentItem = GetItemFromLong(parent);
	const auto pControl = Create<ProgressBarControl>(env, parentItem, wName, wDescription, jItem);

	env->ReleaseStringUTFChars(name, nativeName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return GetItemAsLong(pControl);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuItemNative(JNIEnv * env, jobject obj, jlong parent, jstring menuItemName, jstring menuShortcut, jboolean isMenu, jlong parentMenu, jlong parentMenuBar, jobject jItem, jboolean isPopupMenu)
{
	const char* nativeMenuItemName = env->GetStringUTFChars(menuItemName, 0);
	const char* nativeMenuShortcut = env->GetStringUTFChars(menuShortcut, 0);

	WCHAR* wMenuItemName = CreateWideStringFromUTF8Win32(nativeMenuItemName);
	WCHAR* wMenuShortcut = CreateWideStringFromUTF8Win32(nativeMenuShortcut);

	if (isPopupMenu) {
		MenuControl* pMenuBar = static_cast<MenuControl*>(GetItemFromLong(parentMenuBar));

		Item* parentItem = nullptr;

		if (parentMenu)
		{
			parentItem = static_cast<MenuItemControl*>(GetItemFromLong(parentMenu));
		}
		else
		{
			parentItem = pMenuBar;
		}

		const auto menuItemControl = new PopupMenuItemControl(env, wMenuItemName, wMenuShortcut, (bool)isMenu, pMenuBar, jItem);

		parentItem->AppendChild(menuItemControl);

		env->ReleaseStringUTFChars(menuItemName, nativeMenuItemName);
		env->ReleaseStringUTFChars(menuShortcut, nativeMenuShortcut);

		return GetItemAsLong(menuItemControl);
	}
	else {
		MenuBarControl* pMenuBar = static_cast<MenuBarControl*>(GetItemFromLong(parentMenuBar));

		Item* parentItem = nullptr;

		if (parentMenu)
		{
			parentItem = static_cast<MenuItemControl*>(GetItemFromLong(parentMenu));
		}
		else
		{
			parentItem = pMenuBar;
		}

		const auto menuItemControl = new MenuItemControl(env, wMenuItemName, wMenuShortcut, (bool)isMenu, pMenuBar, jItem);

		parentItem->AppendChild(menuItemControl);

		env->ReleaseStringUTFChars(menuItemName, nativeMenuItemName);
		env->ReleaseStringUTFChars(menuShortcut, nativeMenuShortcut);

		return GetItemAsLong(menuItemControl);
	}
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabPaneNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	const auto pane = Create<TabPaneControl>(env, parentItem, wName, jItem);
	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(pane);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto tabPaneControl = static_cast<TabPaneControl*>(GetItemFromLong(parent));
	const auto tab = Create<TabControl>(env, tabPaneControl, wName, tabPaneControl, jItem);
	env->ReleaseStringUTFChars(name, nativeName);
	return GetItemAsLong(tab);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	const auto list = Create<ListControl>(env, parentItem, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(list);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListItemNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto listControl = static_cast<ListControl*>(GetItemFromLong(parent));
	const auto listItem = new ListItemControl(env, wName, listControl, jItem);
	listControl->AppendChild(listItem);
	env->ReleaseStringUTFChars(name, nativeName);
	return GetItemAsLong(listItem);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateToolBarNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	const auto toolBar = Create<ToolBarControl>(env, parentItem, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(toolBar);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateSpreadsheetNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	// Currently we pass the empty string for the description -- this needs to be instead retrieved from Quorum.
	const auto table = Create<TableControl>(env, parentItem, wName, L"", jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(table);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeTableNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	// Currently we pass the empty string for the description -- this needs to be instead retrieved from Quorum.
	const auto table = Create<TableControl>(env, parentItem, wName, L"", jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(table);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCellNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	TableControl* parentControl = static_cast<TableControl*>(GetItemFromLong(parent));
	// Currently we pass the empty string for the description -- this needs to be instead retrieved from Quorum.
	const auto cell = Create<CellControl>(env, parentControl, wName, L"", parentControl, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(cell);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateDialogNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	const auto parentItem = GetItemFromLong(parent);
	// Currently we pass the empty string for the description -- this needs to be instead retrieved from Quorum.
	const auto dialog = Create<DialogControl>(env, parentItem, wName, L"", jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return GetItemAsLong(dialog);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeNative(JNIEnv* env, jobject obj, jlong parent, jstring treeName, jobject jItem)
{
	const char* nativeTreeName = env->GetStringUTFChars(treeName, 0);
	WCHAR* wTreeName = CreateWideStringFromUTF8Win32(nativeTreeName);

	const auto parentItem = GetItemFromLong(parent);
	const auto pTreeControl = Create<TreeControl>(env, parentItem, wTreeName, jItem);

	env->ReleaseStringUTFChars(treeName, nativeTreeName);

	return GetItemAsLong(pTreeControl);
}

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeItemNative(JNIEnv* env, jobject obj, 
	jlong parent, jstring treeItemName, jstring treeItemDescription, jboolean isSubtree, jboolean isExpanded, 
	jlong parentSubtree, jlong parentTree, jobject jItem)
{
	const char* nativeTreeItemName = env->GetStringUTFChars(treeItemName, 0);
	const char* nativeTreeItemDescription = env->GetStringUTFChars(treeItemDescription, 0);

	WCHAR* wTreeItemName = CreateWideStringFromUTF8Win32(nativeTreeItemName);
	WCHAR* wTreeItemDescription = CreateWideStringFromUTF8Win32(nativeTreeItemDescription);

	TreeControl* pTree = static_cast<TreeControl*>(GetItemFromLong(parentTree));

	Item* parentItem = nullptr;

	if (parentSubtree) {
		parentItem = static_cast<TreeItemControl*>(GetItemFromLong(parentSubtree));
	} else {
		parentItem = pTree;
	}

	const auto treeItemControl = new TreeItemControl(env, wTreeItemName, wTreeItemDescription, (bool)isSubtree, (bool)isExpanded, pTree, jItem);
	parentItem->AppendChild(treeItemControl);

	env->ReleaseStringUTFChars(treeItemName, nativeTreeItemName);
	env->ReleaseStringUTFChars(treeItemDescription, nativeTreeItemDescription);

	return GetItemAsLong(treeItemControl);
}

#pragma endregion

#pragma region Remove Accessible Object

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveNative(JNIEnv * env, jobject obj, jlong item)
{
	Item* itemToRemove = GetItemFromLong(item);

	const auto windowRoot = GetMainWindowRoot();

	// If we're removing the focus from the system, make sure the Quorum focus reflects that.
	if (windowRoot->GetQuorumFocus() == itemToRemove)
		windowRoot->SetQuorumFocus(nullptr);

	delete itemToRemove;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveMenuItemNative(JNIEnv * env, jobject obj, jlong menuItem)
{
	
	MenuItemControl* menuItemToRemove = static_cast<MenuItemControl*>(GetItemFromLong(menuItem));

	const auto parentMenuBar = menuItemToRemove->GetParentMenuBar();
	if (parentMenuBar->GetSelectedMenuItem() == menuItemToRemove)
	{
		parentMenuBar->SetSelectedMenuItem(nullptr);
	}

	menuItemToRemove->RemoveFromParent();

	delete menuItemToRemove;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemovePopupMenuItemNative(JNIEnv* env, jobject obj, jlong menuItem)
{

	PopupMenuItemControl* menuItemToRemove = static_cast<PopupMenuItemControl*>(GetItemFromLong(menuItem));

	const auto parentMenuBar = menuItemToRemove->GetParentMenuBar();
	if (parentMenuBar->GetSelectedMenuItem() == menuItemToRemove)
	{
		parentMenuBar->SetSelectedMenuItem(nullptr);
	}

	menuItemToRemove->RemoveFromParent();

	delete menuItemToRemove;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveTreeItemNative(JNIEnv * env, jobject obj, jlong treeItem)
{

	TreeItemControl* treeItemToRemove = static_cast<TreeItemControl*>(GetItemFromLong(treeItem));

	const auto parentTree = treeItemToRemove->GetParentTree();
	if (parentTree->GetSelectedTreeItem() == treeItemToRemove)
	{
		parentTree->SetSelectedTreeItem(nullptr);
	}

	treeItemToRemove->RemoveFromParent();

	delete treeItemToRemove;

	return true;

}


#pragma endregion
#pragma region Send A Message

// SetFocus: This function will send a message to the window procedure for the control to change focus to that control. This function can only be called
//						on controls that have an HWND. MenuItems, for example, don't have an HWND and receive focus through selection.
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SetFocusNative(JNIEnv *env, jobject obj, jlong control)
{
	#if LOG
		log("SetFocus Start\n");
	#endif

	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	Item* pControl = GetItemFromLong(control);
	
	if (pControl)
	{
		pControl->SetQuorumFocus();
		return 1; // success
	}

	#if LOG
		log("SetFocus End\n");
	#endif

	return 0; // failure
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NameChangedNative(JNIEnv* env, jobject obj, jlong itemPointer, jstring name)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);

	Item* item = GetItemFromLong(itemPointer);
	WCHAR* newName = CreateWideStringFromUTF8Win32(nativeName);

	item->SetName(newName);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DescriptionChangedNative(JNIEnv* env, jobject obj, jlong itemPointer, jstring description)
{
	const char* nativeDescription = env->GetStringUTFChars(description, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeDescription);

	Item* item = GetItemFromLong(itemPointer);
	item->SetDescription(wText);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ButtonInvoked(JNIEnv*, jobject, jlong buttonPointer)
{
	ButtonControl* button = static_cast<ButtonControl*>(GetItemFromLong(buttonPointer));
	button->NotifyInvoked();

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ProgressBarValueChanged(JNIEnv*, jobject, jlong pointer, jdouble value)
{
	ProgressBarControl* progress = static_cast<ProgressBarControl*>(GetItemFromLong(pointer));
	progress->SetValue(value);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ToggleButtonToggled(JNIEnv*, jobject, jlong buttonPointer, jboolean toggleState)
{
	// Currently, standard toggle buttons use the same providers as check boxes.
	CheckBoxControl* checkBox = static_cast<CheckBoxControl*>(GetItemFromLong(buttonPointer));
	checkBox->UpdateToggleState(toggleState == JNI_TRUE ? ToggleState_On : ToggleState_Off);

	return true;
}

// TextBoxTextSelectionChanged: This method will fire the appropriate UIA Event for when the text selection has changed. The selection can change as a result of the caret moving or text being added to the currentLineText.
// TODO: Update the currentLineText from what is given by Quorum. That way the line down here can stay in sync with Quorum.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextSelectionChangedNative(JNIEnv *env, jobject obj, jlong textbox, jstring currentLineText, jint startIndex, jint endIndex)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	TextBoxControl* pTextBox = static_cast<TextBoxControl*>(GetItemFromLong(textbox));
	Range indices((int)startIndex, (int)endIndex);

	pTextBox->UpdateSelection(indices);

	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NotifyTextBoxNative(JNIEnv* env, jobject obj, jlong textbox, jstring say)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(say, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);
	BSTR resultString = _com_util::ConvertStringToBSTR(nativeCurrentLineText);
	const char* custom = "Custom Announcement";
	BSTR customBSTR = _com_util::ConvertStringToBSTR(custom);

	TextBoxControl* pTextBox = static_cast<TextBoxControl*>(GetItemFromLong(textbox));
	IRawElementProviderSimple* provider = ((IRawElementProviderSimple*)pTextBox->GetProvider().get());

	enum NotificationKind kind = NotificationKind_ActionAborted;
	enum NotificationProcessing processing = NotificationProcessing_MostRecent;
	
	UiaRaiseNotificationEvent(provider, kind, processing, resultString, customBSTR);

	SysFreeString(resultString);
	SysFreeString(customBSTR);
	env->ReleaseStringUTFChars(say, nativeCurrentLineText);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextChangedNative(JNIEnv* env, jobject obj, jlong textbox, jint index, jstring added, jint removed)
{
	TextBoxControl* control = static_cast<TextBoxControl*>(GetItemFromLong(textbox));

	std::wstring addedText = CreateWideStringFromUTF8Win32(env->GetStringUTFChars(added, 0));
	control->UpdateText(index, addedText, removed);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextChangedNative(JNIEnv* env, jobject obj, jlong field, jint index, jstring added, jint removed)
{
	TextFieldControl* control = static_cast<TextFieldControl*>(GetItemFromLong(field));

	std::wstring addedText = CreateWideStringFromUTF8Win32(env->GetStringUTFChars(added, 0));
	control->UpdateText(index, addedText, removed);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextSelectionChangedNative(JNIEnv* env, jobject obj, jlong textField, jstring currentLineText, jint startIndex, jint endIndex)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	TextFieldControl* textFieldControl = static_cast<TextFieldControl*>(GetItemFromLong(textField));
	Range indices((int)startIndex, (int)endIndex);

	textFieldControl->UpdateSelection(indices);

	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);
}

// UpdateCaretPosition:
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateCaretPositionNative(JNIEnv *env, jobject obj, jlong textboxHWND, jstring fullText, jint caretIndex)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	UNREFERENCED_PARAMETER(textboxHWND);
	UNREFERENCED_PARAMETER(fullText);
	UNREFERENCED_PARAMETER(caretIndex);
}

// InvokeButton: 
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InvokeButtonNative(JNIEnv *env, jobject obj, jlong control)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	UNREFERENCED_PARAMETER(control);

	// This is apparently never called and should probably be removed from the Java class.

	return false;
}

// UpdateToggleStatus: 
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateToggleStatusNative(JNIEnv *env, jobject obj, jlong control, jboolean selected)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	UNREFERENCED_PARAMETER(control);
	UNREFERENCED_PARAMETER(selected);

	// This is apparently never called and should probably be removed from the Java class.

	return false;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectMenuItemNative(JNIEnv * env, jobject obj, jlong selectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(GetItemFromLong(selectedMenuItem));
	MenuBarControl* pMenuBar = pMenuItem->GetParentMenuBar();

	if (!pMenuBar->HasQuorumFocus())
		pMenuBar->SetQuorumFocus();

	pMenuBar->SetSelectedMenuItem(pMenuItem);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DeselectMenuItemNative(JNIEnv * env, jobject obj, jlong deselectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(GetItemFromLong(deselectedMenuItem));

	pMenuItem->GetParentMenuBar()->SetSelectedMenuItem(nullptr);
	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectTreeItemNative(JNIEnv * env, jobject obj, jlong selectedTreeItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(GetItemFromLong(selectedTreeItem));
	TreeControl* pTree = pTreeItem->GetParentTree();

	if (!pTree->HasQuorumFocus())
		pTree->SetQuorumFocus();

	pTree->SetSelectedTreeItem(pTreeItem);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectCellNative(JNIEnv* env, jobject obj, jlong selectedCell)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	CellControl* cellControl = static_cast<CellControl*>(GetItemFromLong(selectedCell));
	TableControl* spreadsheetControl = cellControl->GetParentTable();

	if (!spreadsheetControl->HasQuorumFocus())
		spreadsheetControl->SetQuorumFocus();

	spreadsheetControl->SetSelected(cellControl);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectListItemNative(JNIEnv* env, jobject obj, jlong selectedCell)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	ListItemControl* listItemControl = static_cast<ListItemControl*>(GetItemFromLong(selectedCell));
	ListControl* listControl = listItemControl->GetParentList();

	if (!listControl->HasQuorumFocus()) {
		listControl->SetQuorumFocus();
	}
	listControl->SetSelected(listItemControl);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuExpandedNative(JNIEnv *env, jobject obj, jlong menuItem)
{
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(GetItemFromLong(menuItem));

	if (pMenuItem != NULL)
		pMenuItem->NotifyExpandCollapse();
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuCollapsedNative(JNIEnv *env, jobject obj, jlong menuItem)
{
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(GetItemFromLong(menuItem));

	if (pMenuItem != NULL)
		pMenuItem->NotifyExpandCollapse();
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeExpandedNative(JNIEnv *env, jobject obj, jlong treeItem)
{
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(GetItemFromLong(treeItem));

	if (pTreeItem != NULL)
		pTreeItem->SetExpanded(true);
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeCollapsedNative(JNIEnv *env, jobject obj, jlong treeItem)
{
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(GetItemFromLong(treeItem));

	if (pTreeItem != NULL)
		pTreeItem->SetExpanded(false);
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TreeTableRowExpandedNative(JNIEnv* env, jobject obj, jlong cell)
{
	/*
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(GetItemFromLong(treeItem));

	if (pTreeItem != NULL)
		pTreeItem->SetExpanded(true);
	else
		return false;
	*/
	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TreeTableRowCollapsedNative(JNIEnv* env, jobject obj, jlong cell)
{
	/*
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(GetItemFromLong(treeItem));

	if (pTreeItem != NULL)
		pTreeItem->SetExpanded(false);
	else
		return false;
	*/
	return true;
}

#pragma endregion
