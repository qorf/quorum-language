#include "../Header/jni.h"
/* Header for class plugins_quorum_Libraries_Interface_AccessibilityManager */

#ifndef _Included_plugins_quorum_Libraries_Interface_AccessibilityManager
#define _Included_plugins_quorum_Libraries_Interface_AccessibilityManager
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     plugins_quorum_Libraries_Interface_AccessibilityManager
 * Method:    print
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativePrint(JNIEnv *, jobject);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility(JNIEnv *, jobject, jlong);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility(JNIEnv *, jobject);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SetFocus(JNIEnv *, jobject, jlong);

//
// ===== Item
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateItem(JNIEnv *, jobject, jstring, jstring, jobject);

//
// ===== Buttons
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateButton(JNIEnv *, jobject, jstring, jstring, jobject);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateCheckBox(JNIEnv *, jobject, jstring, jstring, jobject);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateRadioButton(JNIEnv *, jobject, jstring, jstring, jobject);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InvokeButton(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateToggleStatus(JNIEnv *, jobject, jlong, jboolean);

//
// ===== Menu Bars and Menu Items
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateMenuBar(JNIEnv *, jobject, jstring, jobject);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateMenuItem(JNIEnv *, jobject, jstring, jstring, jboolean, jlong, jlong, jobject);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32RemoveMenuItem(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32Remove(JNIEnv * env, jobject obj, jlong item);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SelectMenuItem(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32DeselectMenuItem(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32MenuExpanded(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32MenuCollapsed(JNIEnv *, jobject, jlong);

//
// ===== Tree and Tree Item
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTree(JNIEnv *, jobject, jstring, jobject);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTreeItem(JNIEnv *, jobject, jstring, jstring, jboolean, jboolean, jlong, jlong, jobject);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32RemoveTreeItem(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SelectTreeItem(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SubtreeExpanded(JNIEnv *, jobject, jlong);

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SubtreeCollapsed(JNIEnv *, jobject, jlong);

//
// ===== Textbox
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTextBox(JNIEnv *, jobject, jstring, jstring, jobject);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32TextBoxTextSelectionChanged(JNIEnv *, jobject, jlong, jstring, jint, jint);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateCaretPosition(JNIEnv *, jobject, jlong, jstring, jint);

//
// ===== TextField
//

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTextField(JNIEnv*, jobject, jstring, jstring, jobject);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32TextFieldTextSelectionChanged(JNIEnv*, jobject, jlong, jstring, jint, jint);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32NotifyTextBox(JNIEnv* env, jobject obj, jlong textbox, jstring say);

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32TextBoxTextChanged(JNIEnv* env, jobject obj, jlong textbox, jstring change);


//tabs
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTabPane(JNIEnv* env, jobject obj, jstring name, jobject jItem);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTab(JNIEnv* env, jobject obj, jlong parentHandle, jstring name, jobject jItem);

#ifdef __cplusplus
}
#endif
#endif
