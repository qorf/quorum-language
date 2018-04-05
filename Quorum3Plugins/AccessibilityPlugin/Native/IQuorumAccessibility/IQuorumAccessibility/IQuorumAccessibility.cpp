#include "../IQuorumAccessibility/Header/plugins_quorum_Libraries_Interface_AccessibilityManager.h"
#include "../IQuorumAccessibility/Header/jni.h"

#include <Windows.h>
#include <UIAutomation.h>

#include "Item.h"
#include "PushButtonControl.h"
#include "RadioButtonControl.h"
#include "ToggleButtonControl.h"
#include "TextBoxControl.h"

// For Debug Output
#include <iostream>
#include <string>

// CreateWideStringFromUTF8Win32: converts a const char* to a WCHAR*.
WCHAR* CreateWideStringFromUTF8Win32(const char* source)
{
	// newsize describes the length of the   
	// wchar_t string called wcstring in terms of the number   
	// of wide characters, not the number of bytes. 
	size_t newsize = strlen(source) + 1;

	// The following creates a buffer large enough to contain   
	// the exact number of characters in the original string  
	// in the new format.
	WCHAR* target = new wchar_t[newsize];

	// Convert char* string to a wchar_t* string.  
	size_t convertedChars = 0;
	mbstowcs_s(&convertedChars, target, newsize, source, _TRUNCATE);

	return target;

}

// DllMain: Entry point for dll. Nothing to do here.
BOOL WINAPI DllMain(HINSTANCE instance, DWORD reason, LPVOID reserved)
{
	return TRUE;
}


////////////////////////////////////////////////////////////////////////////
////////////				   JNI Functions
////////////
////////////////////////////////////////////////////////////////////////////

// NativeWin32InitializeAccessibility: Calls CoInitialize so that COM interface library functions are availible for use. This only ever needs to be called once. Never call this more than once.
//									   CoUninitialize must be called the same number of times as CoInitialize.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility(JNIEnv *env, jobject obj)
{
	HRESULT hr = CoInitialize(NULL);
}

// NativeWin32ShutdownAccessibility: Closes the COM library gracefully.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility(JNIEnv *env, jobject obj)
{
	CoUninitialize();
}

// TODO: REMOVE this method from here, the JNI header file, the Java AccessibilityManager, and the Quorum AccessibilityManager.
// NativePrint: Solely used to test whether a call from Java or Quorum can make it down to C++.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativePrint(JNIEnv *env, jobject obj)
{
	std::cout << "Native C++ Print" << std::endl;

}

/* ==============================
*	This section of code contains the
*	JNI methods that will create an
*	instance of the appropriate accessible
*	object for UIA to retrieve info from.
// ============================== */
#pragma region Create Accessible Object

// NativeWin32CreateItem: This is the most generic accessible object that can be created. It only contains a name and a description.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateItem(JNIEnv *env, jobject obj, jlong parentWindowHWND, jstring itemName, jstring description)
{

	HWND parentWindow;
	parentWindow = (HWND)parentWindowHWND;

	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND itemControlHandle;

	itemControlHandle = Item::Create(parentWindow, GetModuleHandle(NULL), wItemName, wDescription);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(itemControlHandle);

}

// NativeWin32CreatePushButton: Creates a window that contains the accessible information for a PushButton that was passed into this function.
//		Returns: jlong which is the HWND for the window. This is used to further interact with the button after creation. i.e., to rename the button later should the name be changed. Also, used to keep track of it in java.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreatePushButton(JNIEnv *env, jobject obj, jlong parentWindowHWND, jstring buttonName, jstring description)
{

	HWND parentWindow;
	parentWindow = (HWND)parentWindowHWND;

	const char *nativeButtonName = env->GetStringUTFChars(buttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wButtonName = CreateWideStringFromUTF8Win32(nativeButtonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);


	HWND pushbuttonControlHandle;

	pushbuttonControlHandle = PushButtonControl::Create(parentWindow, GetModuleHandle(NULL), wButtonName, wDescription);

	env->ReleaseStringUTFChars(buttonName, nativeButtonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(pushbuttonControlHandle);

}

// NativeWin32CreateToggleButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateToggleButton(JNIEnv *env, jobject obj, jlong parentWindowHWND, jstring togglebuttonName, jstring description)
{

	HWND parentWindow;
	parentWindow = (HWND)parentWindowHWND;

	const char *nativeTogglebuttonName = env->GetStringUTFChars(togglebuttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTogglebuttonName = CreateWideStringFromUTF8Win32(nativeTogglebuttonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND togglebuttonControlHandle;

	togglebuttonControlHandle = ToggleButtonControl::Create(parentWindow, GetModuleHandle(NULL), wTogglebuttonName, wDescription);

	env->ReleaseStringUTFChars(togglebuttonName, nativeTogglebuttonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(togglebuttonControlHandle);

}

// NativeWin32CreateRadioButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateRadioButton(JNIEnv *env, jobject obj, jlong parentWindowHWND, jstring itemName, jstring description)
{

	HWND parentWindow;
	parentWindow = (HWND)parentWindowHWND;

	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wRadiobuttonName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND radiobuttonControlHandle;

	radiobuttonControlHandle = RadioButtonControl::Create(parentWindow, GetModuleHandle(NULL), wRadiobuttonName, wDescription);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(radiobuttonControlHandle);

}

// NativeWin32CreateTextBox: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTextBox(JNIEnv *env, jobject obj, jlong parentWindowHWND, jstring textboxName, jstring description, jstring currentLineText, jint caretLine, jint caretCharacter)
{

	HWND parentWindow;
	parentWindow = (HWND)parentWindowHWND;

	const char *nativeTextboxName = env->GetStringUTFChars(textboxName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);
	const char *nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextboxName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);
	WCHAR* wCurrentLineText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	HWND textboxControlHandle;

	EndPoint caret;
	caret.character = (int)caretCharacter;
	caret.line = (int)caretLine;
	//TextLine line[] = { wCurrentLineText };
	TextLine line[] = { { L"Hello world!" }, };

	textboxControlHandle = TextBoxControl::Create(parentWindow, GetModuleHandle(NULL), wTextboxName, wDescription, line, caret);

	env->ReleaseStringUTFChars(textboxName, nativeTextboxName);
	env->ReleaseStringUTFChars(description, nativeDescription);
	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);


	return PtrToLong(textboxControlHandle);

}

#pragma endregion


/* ==============================
*	This section of code contains the
*   JNI methods that will send a message
*	to the control to raise UIA Events or update
*	a control's info to match the Quorum object.
// ============================== */
#pragma region Send A Message

// NativeWin32SetFocus: This function will send a message to the window procedure for the given jlongHWND to set focus. Each window procedure may handle that message differently but in general a window procedure will raise
//						a UI Automation Focus Changed event that triggers the screen reader to announce that the focus has changed. This function does not and should not change the keyboard focus to the given jlongHWND. If it does
//						then it will take keyboard control away from the main GLFW window that Quorum uses to get keyboard events from. There is no known way to give it back to the main GLFW window once the keyboard focus has been moved from it.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SetFocus(JNIEnv *env, jobject obj, jlong jlongHWND)
{
	HWND GLFW_HWND;
	GLFW_HWND = (HWND)jlongHWND;

	// The send message parameters are:
	//	GLFW_HWND: The HWND of the control that we want to send a message to
	//	CUSTOM_SETFOCUS: The message to that control's window procedure which it will respond to. Custom messages are allowed and are kept in the CustomMessages.h file for consistency throughout the library.
	//	The last two parameters wParam and lParam are basically arbitrary and in no way need to be the values chosen here. By convention wParam is used to send integers and lParam is used to send pointers.
	SendMessage(GLFW_HWND, CUSTOM_SETFOCUS, 0, 0);

	return PtrToLong(GLFW_HWND);
}

// NativeWin32TextBoxTextSelectionChanged: This method will fire the appropriate UIA Event for when the text selection has changed. The selection can change as a result of the caret moving or text being added to the currentLineText.
// TODO: Update the currentLineText from what is given by Quorum. That way the line down here can stay in sync with Quorum.
JNIEXPORT void Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32TextBoxTextSelectionChanged(JNIEnv *env, jobject obj, jlong textboxHWND, jstring currentLineText, jint caretLine, jint caretCharacter)
{
	const char *nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wCurrentLineText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	EndPoint caret;
	caret.line = (int)caretLine;
	caret.character = (int)caretCharacter;

	//SendMessage((HWND)textboxHWND, CUSTOM_SETTEXT, 0, (LPARAM)wCurrentLineText);
	SendMessage((HWND)textboxHWND, CUSTOM_UPDATECARET, 0, (LPARAM)&caret);

	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);
}

// NativeWin32UpdateCaretPosition:
JNIEXPORT void Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateCaretPosition(JNIEnv *env, jobject obj, jlong textboxHWND, jstring adjacentCharacter)
{
	const char *nativeAdjacentCharacter = env->GetStringUTFChars(adjacentCharacter, 0);
	std::wstring wAdjacentCharacter = CreateWideStringFromUTF8Win32(nativeAdjacentCharacter);


	SendMessage((HWND)textboxHWND, CUSTOM_UPDATECARET, 0, (LPARAM)&wAdjacentCharacter);
	//SendMessage((HWND)textboxHWND, CUSTOM_UPDATECARET, 0, (LPARAM)&caret);

	env->ReleaseStringUTFChars(adjacentCharacter, nativeAdjacentCharacter);
}

// NativeWin32InvokeButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InvokeButton(JNIEnv *env, jobject obj, jlong jlongHWND)
{
	HWND GLFW_HWND;
	GLFW_HWND = (HWND)jlongHWND;

	SendMessage(GLFW_HWND, CUSTOM_INVOKEBUTTON, 0, 0);

	return PtrToLong(GLFW_HWND);
}

// NativeWin32UpdateToggleStatus: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateToggleStatus(JNIEnv *env, jobject obj, jlong jlongHWND, jboolean selected)
{
	HWND GLFW_HWND;
	GLFW_HWND = (HWND)jlongHWND;

	bool nativeSelected = (bool)selected;

	if (nativeSelected)
	{
		SendMessage(GLFW_HWND, CUSTOM_INVOKEBUTTON, true, 0);
		return true;
	}
	else
	{
		SendMessage(GLFW_HWND, CUSTOM_INVOKEBUTTON, false, 0);
		return true;
	}

	return true;

}

#pragma endregion





