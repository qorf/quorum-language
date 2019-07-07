#include "../IQuorumAccessibility/Header/plugins_quorum_Libraries_Interface_AccessibilityManager.h"
#include "../IQuorumAccessibility/Header/jni.h"

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
#include "MenuItemControl.h"
#include "TreeControl.h"
#include "TreeItemControl.h"
#include "TextfieldControl.h"
#include "TabPaneControl.h"
#include "ToolBarControl.h"
#include "DialogControl.h"
#include "ListControl.h"
#include "ListItemControl.h"
#include "SpreadsheetControl.h"
#include "TreeTableControl.h"
#include "CellControl.h"

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

HWND CalculateParentWindowHandle(jlong parent) {
	HWND handle = GetMainWindowHandle();
	if (parent != -1) {
		Item* theParent = static_cast<Item*>(LongToPtr((long)parent));
		handle = theParent->GetHWND();
	}
	return handle;
}

Item* GetItemPointer(jlong item) {
	if (item > 0) {
		Item* theParent = static_cast<Item*>(LongToPtr((long)item));
		return theParent;
	}
	return 0;
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

// CreateItem: This is the most generic accessible object that can be created. It only contains a name and a description.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateItemNative(JNIEnv *env, jobject obj, jlong parent, jstring itemName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);
	HWND handle = CalculateParentWindowHandle(parent);
	ItemControl* pItemControl = ItemControl::Create(env, GetModuleHandle(NULL), handle, wItemName, wDescription, jItem);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pItemControl);

}

// CreateButton: Creates a window that contains the accessible information for a PushButton that was passed into this function.
//		Returns: jlong which is the HWND for the window. This is used to further interact with the button after creation. i.e., to rename the button later should the name be changed. Also, used to keep track of it in java.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateButtonNative(JNIEnv *env, jobject obj, jlong parent, jstring buttonName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeButtonName = env->GetStringUTFChars(buttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wButtonName = CreateWideStringFromUTF8Win32(nativeButtonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);
	ButtonControl* pButtonControl;

	HWND handle = CalculateParentWindowHandle(parent);
	pButtonControl = ButtonControl::Create(env, GetModuleHandle(NULL), handle, wButtonName, wDescription, jItem);

	env->ReleaseStringUTFChars(buttonName, nativeButtonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pButtonControl);

}

// CreateCheckBox: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCheckBoxNative(JNIEnv *env, jobject obj, jlong parent, jstring togglebuttonName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeTogglebuttonName = env->GetStringUTFChars(togglebuttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTogglebuttonName = CreateWideStringFromUTF8Win32(nativeTogglebuttonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND handle = CalculateParentWindowHandle(parent);
	CheckBoxControl* pCheckBoxControl = CheckBoxControl::Create(env, GetModuleHandle(NULL), handle, wTogglebuttonName, wDescription, jItem);

	env->ReleaseStringUTFChars(togglebuttonName, nativeTogglebuttonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pCheckBoxControl);

}

// CreateRadioButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateRadioButtonNative(JNIEnv *env, jobject obj, jlong parent, jstring itemName, jstring description, jobject jItem)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wRadiobuttonName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND handle = CalculateParentWindowHandle(parent);
	RadioButtonControl* pRadiobuttonControl = RadioButtonControl::Create(env, GetModuleHandle(NULL), handle, wRadiobuttonName, wDescription, jItem);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pRadiobuttonControl);

}

// CreateTextBox: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextBoxNative(JNIEnv *env, jobject obj, jlong parent, jstring textboxName, jstring description, jobject self)
{
	UNREFERENCED_PARAMETER(obj);
	const char* nativeTextboxName = env->GetStringUTFChars(textboxName, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextboxName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	jobject nativeSelf = env->NewGlobalRef(self);

	HWND handle = CalculateParentWindowHandle(parent);
	TextBoxControl* pTextboxControl = TextBoxControl::Create(env, GetModuleHandle(NULL), handle, wTextboxName, wDescription, nativeSelf);

	env->ReleaseStringUTFChars(textboxName, nativeTextboxName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pTextboxControl);

}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextFieldNative(JNIEnv* env, jobject obj, jlong parent, jstring textFieldName, jstring description, jobject self)
{
	UNREFERENCED_PARAMETER(obj);
	const char* nativeTextFieldName = env->GetStringUTFChars(textFieldName, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextFieldName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	jobject nativeSelf = env->NewGlobalRef(self);

	HWND handle = CalculateParentWindowHandle(parent);
	TextFieldControl* textFieldControl = TextFieldControl::Create(env, GetModuleHandle(NULL), handle, wTextboxName, wDescription, nativeSelf);

	env->ReleaseStringUTFChars(textFieldName, nativeTextFieldName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(textFieldControl);

}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuBarNative(JNIEnv * env, jobject obj, jlong parent, jstring menuBarName, jobject jItem)
{
	const char* nativeMenuBarName = env->GetStringUTFChars(menuBarName, 0);
	WCHAR* wMenuBarName = CreateWideStringFromUTF8Win32(nativeMenuBarName);

	HWND handle = CalculateParentWindowHandle(parent);
	MenuBarControl* pMenuBarControl = MenuBarControl::Create(env, GetModuleHandle(NULL), handle, wMenuBarName, jItem);

	env->ReleaseStringUTFChars(menuBarName, nativeMenuBarName);

	return PtrToLong(pMenuBarControl);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuItemNative(JNIEnv * env, jobject obj, jlong parent, jstring menuItemName, jstring menuShortcut, jboolean isMenu, jlong parentMenu, jlong parentMenuBar, jobject jItem)
{
	const char* nativeMenuItemName = env->GetStringUTFChars(menuItemName, 0);
	const char* nativeMenuShortcut = env->GetStringUTFChars(menuShortcut, 0);

	WCHAR* wMenuItemName = CreateWideStringFromUTF8Win32(nativeMenuItemName);
	WCHAR* wMenuShortcut = CreateWideStringFromUTF8Win32(nativeMenuShortcut);

	MenuBarControl* pMenuBar = static_cast<MenuBarControl*>(LongToPtr((long)parentMenuBar));
	
	MenuItemControl* parentMenuItem = NULL;
	
	if ((long)parentMenu != NULL)
		parentMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)parentMenu));
	
	MenuItemControl* menuItemControl = new MenuItemControl(env, wMenuItemName, wMenuShortcut, (bool)isMenu, pMenuBar->CreateUniqueId(), parentMenuItem, pMenuBar, jItem);

	SendMessage(pMenuBar->GetHWND(), QUORUM_ADDMENUITEM, 0, (LPARAM)menuItemControl);

	env->ReleaseStringUTFChars(menuItemName, nativeMenuItemName);
	env->ReleaseStringUTFChars(menuShortcut, nativeMenuShortcut);

	return PtrToLong(menuItemControl);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabPaneNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	TabPaneControl* pane = TabPaneControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);
	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	TabPaneControl* tabPaneControl = static_cast<TabPaneControl*>(GetItemPointer(parent));
	TabControl* pane = TabControl::Create(env, GetModuleHandle(NULL), handle, tabPaneControl, wName, jItem);
	env->ReleaseStringUTFChars(name, nativeName);
	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	ListControl* pane = ListControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListItemNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	ListControl* listControl = static_cast<ListControl*>(GetItemPointer(parent));
	ListItemControl* pane = ListItemControl::Create(env, GetModuleHandle(NULL), handle, listControl, wName, jItem);
	env->ReleaseStringUTFChars(name, nativeName);
	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateToolBarNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	ToolBarControl* pane = ToolBarControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateSpreadsheetNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	SpreadsheetControl* pane = SpreadsheetControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeTableNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	TreeTableControl* pane = TreeTableControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCellNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	SpreadsheetControl* parentControl = static_cast<SpreadsheetControl*>(GetItemPointer(parent));
	CellControl* pane = CellControl::Create(env, GetModuleHandle(NULL), handle, parentControl, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateDialogNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem)
{
	const char* nativeName = env->GetStringUTFChars(name, 0);
	WCHAR* wName = CreateWideStringFromUTF8Win32(nativeName);

	HWND handle = CalculateParentWindowHandle(parent);
	DialogControl* pane = DialogControl::Create(env, GetModuleHandle(NULL), handle, wName, jItem);

	env->ReleaseStringUTFChars(name, nativeName);

	return PtrToLong(pane);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeNative(JNIEnv* env, jobject obj, jlong parent, jstring treeName, jobject jItem)
{
	const char* nativeTreeName = env->GetStringUTFChars(treeName, 0);
	WCHAR* wTreeName = CreateWideStringFromUTF8Win32(nativeTreeName);

	HWND handle = CalculateParentWindowHandle(parent);
	TreeControl* pTreeControl = TreeControl::Create(env, GetModuleHandle(NULL), handle, wTreeName, jItem);

	env->ReleaseStringUTFChars(treeName, nativeTreeName);

	return PtrToLong(pTreeControl);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeItemNative(JNIEnv* env, jobject obj, 
	jlong parent, jstring treeItemName, jstring treeItemDescription, jboolean isSubtree, jboolean isExpanded, 
	jlong parentSubtree, jlong parentTree, jobject jItem)
{
	const char* nativeTreeItemName = env->GetStringUTFChars(treeItemName, 0);
	const char* nativeTreeItemDescription = env->GetStringUTFChars(treeItemDescription, 0);

	WCHAR* wTreeItemName = CreateWideStringFromUTF8Win32(nativeTreeItemName);
	WCHAR* wTreeItemDescription = CreateWideStringFromUTF8Win32(nativeTreeItemDescription);

	TreeControl* pTree = static_cast<TreeControl*>(LongToPtr((long)parentTree));

	TreeItemControl* parentTreeItem = NULL;

	if ((long)parentSubtree != NULL) {
		parentTreeItem = static_cast<TreeItemControl*>(LongToPtr((long)parentSubtree));
	}

	if (env != NULL)
	{
		jclass itemReference = env->GetObjectClass(jItem);
		jmethodID method = env->GetMethodID(itemReference, "GetName", "()Ljava/lang/String;");

		jstring fullName = reinterpret_cast<jstring>(env->CallObjectMethod(jItem, method));
		const char* nativeName = env->GetStringUTFChars(fullName, 0);
		WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeName);

		env->ReleaseStringUTFChars(fullName, nativeName);

	}

	TreeItemControl* treeItemControl = new TreeItemControl(env, wTreeItemName, wTreeItemDescription, (bool)isSubtree, (bool)isExpanded, pTree->CreateUniqueId(), parentTreeItem, pTree, jItem);

	SendMessage(pTree->GetHWND(), QUORUM_ADDTREEITEM, 0, (LPARAM)treeItemControl);

	env->ReleaseStringUTFChars(treeItemName, nativeTreeItemName);
	env->ReleaseStringUTFChars(treeItemDescription, nativeTreeItemDescription);

	return PtrToLong(treeItemControl);
}

#pragma endregion

#pragma region Remove Accessible Object

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveNative(JNIEnv * env, jobject obj, jlong item)
{
	Item* itemToRemove = static_cast<Item*>(LongToPtr((long)item));
		
	DestroyWindow(itemToRemove->GetHWND());

	// The pointer is still in use until DestroyWindow has returned.
	// So delete the pointer here.
	delete itemToRemove;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveMenuItemNative(JNIEnv * env, jobject obj, jlong menuItem)
{
	
	MenuItemControl* menuItemToRemove = static_cast<MenuItemControl*>(LongToPtr((long)menuItem));

	HWND hwnd = menuItemToRemove->GetParentMenuBar()->GetHWND();

	SendMessage(hwnd, QUORUM_REMOVEMENUITEM, 0, (LPARAM)menuItemToRemove);

	return true;

}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveTreeItemNative(JNIEnv * env, jobject obj, jlong treeItem)
{

	TreeItemControl* treeItemToRemove = static_cast<TreeItemControl*>(LongToPtr((long)treeItem));

	HWND hwnd = treeItemToRemove->GetParentTree()->GetHWND();

	SendMessage(hwnd, QUORUM_REMOVETREEITEM, 0, (LPARAM)treeItemToRemove);

	return true;

}


#pragma endregion
#pragma region Send A Message

// SetFocus: This function will send a message to the window procedure for the control to change focus to that control. This function can only be called
//						on controls that have an HWND. MenuItems, for example, don't have an HWND and receive focus through selection.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SetFocusNative(JNIEnv *env, jobject obj, jlong control)
{
	#if LOG
		log("SetFocus Start\n");
	#endif

	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	Item* pControl = static_cast<Item*>(LongToPtr((long)control));
	
	if (pControl != NULL && pControl->GetHWND() != NULL)
	{
		// Sends the appropriate messages to all windows.
		HWND prevFocus = SetFocus(pControl->GetHWND());
		#if LOG
				log("SetFocus Finish 1\n");
		#endif

		return PtrToLong(prevFocus);
	}

	#if LOG
		log("SetFocus End\n");
	#endif

	return NULL;
}

// TextBoxTextSelectionChanged: This method will fire the appropriate UIA Event for when the text selection has changed. The selection can change as a result of the caret moving or text being added to the currentLineText.
// TODO: Update the currentLineText from what is given by Quorum. That way the line down here can stay in sync with Quorum.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextSelectionChangedNative(JNIEnv *env, jobject obj, jlong textbox, jstring currentLineText, jint startIndex, jint endIndex)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	TextBoxControl* pTextBox = static_cast<TextBoxControl*>(LongToPtr((long)textbox));
	Range indices((int)startIndex, (int)endIndex);

	//SendMessage(pTextBox->GetHWND(), QUORUM_SETTEXT, 0, (LPARAM)wText);
	SendMessage(pTextBox->GetHWND(), QUORUM_UPDATESELECTION, 0, (LPARAM)&indices);

	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NotifyTextBoxNative(JNIEnv* env, jobject obj, jlong textbox, jstring say)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(say, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);
	BSTR resultString = _com_util::ConvertStringToBSTR(nativeCurrentLineText);
	const char* custom = "Custom Announcement";
	BSTR customBSTR = _com_util::ConvertStringToBSTR(custom);

	TextBoxControl* pTextBox = static_cast<TextBoxControl*>(LongToPtr((long)textbox));
	IRawElementProviderSimple* provider = ((IRawElementProviderSimple*)pTextBox->GetTextBoxProvider());

	enum NotificationKind kind = NotificationKind_ActionAborted;
	enum NotificationProcessing processing = NotificationProcessing_MostRecent;
	
	UiaRaiseNotificationEvent(provider, kind, processing, resultString, customBSTR);

	SysFreeString(resultString);
	SysFreeString(customBSTR);
	env->ReleaseStringUTFChars(say, nativeCurrentLineText);
}

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextChangedNative(JNIEnv* env, jobject obj, jlong textbox, jstring change)
{
	//const char* nativeCurrentLineText = env->GetStringUTFChars(change, 0);
	//BSTR resultString = _com_util::ConvertStringToBSTR(nativeCurrentLineText);

	//TextBoxControl* pTextBox = static_cast<TextBoxControl*>(LongToPtr((long)textbox));
	//IRawElementProviderSimple* provider = ((IRawElementProviderSimple*)pTextBox->GetTextBoxProvider());

	//size_t length = strlen(nativeCurrentLineText);

	//SAFEARRAYBOUND saBound;
	//saBound.lLbound = 0;
	//saBound.cElements = length;
	//SAFEARRAY* psa = SafeArrayCreateVector(VT_VARIANT, 0, 1);//&saBound);
	//VARIANT* param;
	//HRESULT hr = SafeArrayAccessData(psa, (LPVOID*)& param);
	//if (SUCCEEDED(hr)) {
	//	long index = 0;

	//	param->vt = VT_BSTR;
	//	param->bstrVal = resultString;
	//	//SafeArrayPutElement(psa, &index, param);

	//	enum TextEditChangeType type = TextEditChangeType_CompositionFinalized;
	//	HRESULT result = UiaRaiseTextEditTextChangedEvent(provider, type, psa);

	//	SafeArrayUnlock(psa);
	//}
	//SafeArrayDestroy(psa);

	//SysFreeString(resultString);
	//env->ReleaseStringUTFChars(change, nativeCurrentLineText);
}


JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextSelectionChangedNative(JNIEnv* env, jobject obj, jlong textField, jstring currentLineText, jint startIndex, jint endIndex)
{
	const char* nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	TextFieldControl* textFieldControl = static_cast<TextFieldControl*>(LongToPtr((long)textField));
	Range indices((int)startIndex, (int)endIndex);

	//SendMessage(textFieldControl->GetHWND(), QUORUM_SETTEXT, 0, (LPARAM)wText);
	SendMessage(textFieldControl->GetHWND(), QUORUM_UPDATESELECTION, 0, (LPARAM)& indices);

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

	int a = 5;
	/*HWND control = (HWND)textboxHWND;

	const char *nativeFullText = env->GetStringUTFChars(fullText, 0);

	SendMessage(control, QUORUM_UPDATECARET, (int)caretIndex, (LPARAM)nativeFullText);

	env->ReleaseStringUTFChars(fullText, nativeFullText);*/
	//std::cout << "Textbox must be reworked. This function does nothing for now." << std::endl;
}

// InvokeButton: 
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InvokeButtonNative(JNIEnv *env, jobject obj, jlong control)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	
	Item* pControl = static_cast<Item*>(LongToPtr((long)control));

	if (pControl != NULL && pControl->GetHWND() != NULL)
	{
		SendMessage(pControl->GetHWND(), QUORUM_INVOKEBUTTON, 0, 0);
		return true;
	}
	
	return false;
}

// UpdateToggleStatus: 
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateToggleStatusNative(JNIEnv *env, jobject obj, jlong control, jboolean selected)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	Item* pControl = static_cast<Item*>(LongToPtr((long)control));

	bool nativeSelected = (bool)selected;

	if (pControl != NULL && pControl->GetHWND() != NULL)
	{
		if (nativeSelected)
			SendMessage(pControl->GetHWND(), QUORUM_INVOKEBUTTON, true, 0);
		else
			SendMessage(pControl->GetHWND(), QUORUM_INVOKEBUTTON, false, 0);
		return true;
	}
	
	return false;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectMenuItemNative(JNIEnv * env, jobject obj, jlong selectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)selectedMenuItem));
	MenuBarControl* pMenuBar = pMenuItem->GetParentMenuBar();

	if (!pMenuBar->HasFocus())
		SetFocus(pMenuBar->GetHWND());

	SendMessage(pMenuBar->GetHWND(), QUORUM_SELECTMENUITEM, 0, (LPARAM)pMenuItem);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DeselectMenuItemNative(JNIEnv * env, jobject obj, jlong deselectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)deselectedMenuItem));

	pMenuItem->GetParentMenuBar()->SetSelectedMenuItem(nullptr);
	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectTreeItemNative(JNIEnv * env, jobject obj, jlong selectedTreeItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(LongToPtr((long)selectedTreeItem));
	TreeControl* pTree = pTreeItem->GetParentTree();

	if (!pTree->HasFocus())
		SetFocus(pTree->GetHWND());

	SendMessage(pTree->GetHWND(), QUORUM_SELECTTREEITEM, 0, (LPARAM)pTreeItem);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectCellNative(JNIEnv* env, jobject obj, jlong selectedCell)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	CellControl* cellControl = static_cast<CellControl*>(LongToPtr((long)selectedCell));
	SpreadsheetControl* spreadsheetControl = cellControl->GetParent();

	if (!spreadsheetControl->HasFocus())
		SetFocus(spreadsheetControl->GetHWND());

	SendMessage(spreadsheetControl->GetHWND(), QUORUM_SELECTTREEITEM, 0, (LPARAM)cellControl);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectListItemNative(JNIEnv* env, jobject obj, jlong selectedCell)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	ListItemControl* listItemControl = static_cast<ListItemControl*>(LongToPtr((long)selectedCell));
	ListControl* listControl = listItemControl->GetParent();

	if (!listControl->HasFocus()) {
		SetFocus(listControl->GetHWND());
	}
	SendMessage(listControl->GetHWND(), QUORUM_SELECT_LIST_ITEM, 0, (LPARAM)listItemControl);

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuExpandedNative(JNIEnv *env, jobject obj, jlong menuItem)
{
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)menuItem));

	if (pMenuItem != NULL)
		pMenuItem->Expand();
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuCollapsedNative(JNIEnv *env, jobject obj, jlong menuItem)
{
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)menuItem));

	if (pMenuItem != NULL)
		pMenuItem->Collapse();
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeExpandedNative(JNIEnv *env, jobject obj, jlong treeItem)
{
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(LongToPtr((long)treeItem));

	if (pTreeItem != NULL)
		pTreeItem->Expand();
	else
		return false;

	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeCollapsedNative(JNIEnv *env, jobject obj, jlong treeItem)
{
	TreeItemControl* pTreeItem = static_cast<TreeItemControl*>(LongToPtr((long)treeItem));

	if (pTreeItem != NULL)
		pTreeItem->Collapse();
	else
		return false;

	return true;
}

#pragma endregion
