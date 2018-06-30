#include "../IQuorumAccessibility/Header/jni.h"
#include <UIAnimation.h>

#ifndef Resources_HEADER
#define Resources_HEADER

// This returns the main game engine window handle for Quorum which messages must be forwarded to.
// If new windows can be created by Quorum then messages will need to be forwarded to those instead.
// Its defined in IQuorumAccessibility.cpp
HWND GetMainWindowHandle();

JNIEnv* GetJNIEnv();

WCHAR* CreateWideStringFromUTF8Win32(const char* source);

/**************************************************************
* Static Global Variables to cache Java Class and Method IDs
*
**************************************************************/

struct JClass_AccessibilityManager
{
	jclass me;
	jmethodID WaitForUpdate;

};
struct JClass_TextBox
{
	jclass me;
	jmethodID GetCaretLine;
	jmethodID GetCaretIndex;
	jmethodID GetIndexOfLine;
	jmethodID GetText;
	jmethodID GetCurrentLineText;
	jmethodID GetSelection;
};
struct JClass_TextBoxSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};

extern JClass_AccessibilityManager JavaClass_AccessibilityManager;
extern JClass_TextBox JavaClass_TextBox;
extern JClass_TextBoxSelection JavaClass_TextBoxSelection;








#endif