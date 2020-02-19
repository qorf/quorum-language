#pragma once

#include "jni.h"
/* Header for class plugins_quorum_Libraries_Interface_AccessibilityManager */

#ifdef __cplusplus
extern "C" {
#endif

//HWND CalculateParentWindowHandle(jlong parent);

	//initialize
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InitializeAccessibilityNative(JNIEnv *, jobject, jlong, jstring);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ShutdownAccessibilityNative(JNIEnv *, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SetFocusNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_IsScreenReaderListeningNative(JNIEnv* env, jobject obj);

//Spreadsheets and TreeTables
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateSpreadsheetNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeTableNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCellNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem, jboolean isTreeCell);

//creates
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateButtonNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCheckBoxNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateGroupNative(JNIEnv*, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateRadioButtonNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jlong, jobject);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InvokeButtonNative(JNIEnv *, jobject, jlong);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateToolBarNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateDialogNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuBarNative(JNIEnv *, jobject, jlong parent, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuNative(JNIEnv*, jobject, jlong parent, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateProgressBarNative(JNIEnv*, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jboolean, jlong, jlong, jobject, jboolean);

//removes
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemovePopupMenuItemNative(JNIEnv*, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveNative(JNIEnv * env, jobject obj, jlong item);

// General properties & notifications
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NameChangedNative(JNIEnv*, jobject, jlong item, jstring name);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DescriptionChangedNative(JNIEnv*, jobject, jlong item, jstring description);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NotifyClients(JNIEnv* env, jobject obj, jlong item, jstring say, jint type);

//events
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ButtonInvoked(JNIEnv*, jobject, jlong buttonPointer);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ProgressBarValueChanged(JNIEnv*, jobject, jlong pointer, jdouble);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ToggleButtonToggled(JNIEnv*, jobject, jlong buttonPointer, jboolean toggleState);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DeselectMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuExpandedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuCollapsedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateToggleStatusNative(JNIEnv*, jobject, jlong, jboolean);

//trees
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeNative(JNIEnv *, jobject, jlong parent, jstring, jobject);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jboolean, jboolean, jlong, jlong, jobject);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveTreeItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectTreeItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeExpandedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeCollapsedNative(JNIEnv *, jobject, jlong);

//textbox
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextBoxNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextSelectionChangedNative(JNIEnv *, jobject, jlong, jstring, jint, jint);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateCaretPositionNative(JNIEnv *, jobject, jlong, jstring, jint);

JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextFieldNative(JNIEnv*, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextSelectionChangedNative(JNIEnv*, jobject, jlong, jstring, jint, jint);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NotifyTextBoxNative(JNIEnv* env, jobject obj, jlong textbox, jstring say);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextChangedNative(JNIEnv* env, jobject obj, jlong textbox, jint index, jstring added, jint removed);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextChangedNative(JNIEnv* env, jobject obj, jlong field, jint index, jstring added, jint removed);

//tabs
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabPaneNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);

//Lists
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT jlong JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateListItemNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectListItemNative(JNIEnv* env, jobject obj, jlong selectedCell);

// Spreadsheets & TreeTable Cells
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectCellNative(JNIEnv*, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TreeTableRowExpandedNative(JNIEnv*, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TreeTableRowCollapsedNative(JNIEnv*, jobject, jlong);

// RadioButtons and ButtonGroups
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectRadioButtonNative(JNIEnv*, jobject, jlong);

#ifdef __cplusplus
}
#endif
