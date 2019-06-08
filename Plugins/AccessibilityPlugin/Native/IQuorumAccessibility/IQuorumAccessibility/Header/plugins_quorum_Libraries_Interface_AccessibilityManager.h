#include "../Header/jni.h"
/* Header for class plugins_quorum_Libraries_Interface_AccessibilityManager */

#ifndef _Included_plugins_quorum_Libraries_Interface_AccessibilityManager
#define _Included_plugins_quorum_Libraries_Interface_AccessibilityManager
#ifdef __cplusplus
extern "C" {
#endif

//HWND CalculateParentWindowHandle(jlong parent);

	//initialize
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InitializeAccessibilityNative(JNIEnv *, jobject, jlong);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_ShutdownAccessibilityNative(JNIEnv *, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SetFocusNative(JNIEnv *, jobject, jlong);


//creates
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateButtonNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateCheckBoxNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateRadioButtonNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_InvokeButtonNative(JNIEnv *, jobject, jlong);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateToolBarNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateDialogNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuBarNative(JNIEnv *, jobject, jlong parent, jstring, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateMenuItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jboolean, jlong, jlong, jobject);


//removes
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveNative(JNIEnv * env, jobject obj, jlong item);

//events
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_DeselectMenuItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuExpandedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_MenuCollapsedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateToggleStatusNative(JNIEnv*, jobject, jlong, jboolean);

//trees
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeNative(JNIEnv *, jobject, jlong parent, jstring, jobject);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTreeItemNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jboolean, jboolean, jlong, jlong, jobject);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_RemoveTreeItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SelectTreeItemNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeExpandedNative(JNIEnv *, jobject, jlong);
JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_SubtreeCollapsedNative(JNIEnv *, jobject, jlong);

//textbox
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextBoxNative(JNIEnv *, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextSelectionChangedNative(JNIEnv *, jobject, jlong, jstring, jint, jint);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_UpdateCaretPositionNative(JNIEnv *, jobject, jlong, jstring, jint);

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTextFieldNative(JNIEnv*, jobject, jlong parent, jstring, jstring, jobject);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextFieldTextSelectionChangedNative(JNIEnv*, jobject, jlong, jstring, jint, jint);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NotifyTextBoxNative(JNIEnv* env, jobject obj, jlong textbox, jstring say);
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_TextBoxTextChangedNative(JNIEnv* env, jobject obj, jlong textbox, jstring change);

//tabs
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabPaneNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_CreateTabNative(JNIEnv* env, jobject obj, jlong parent, jstring name, jobject jItem);
#ifdef __cplusplus
}
#endif
#endif
