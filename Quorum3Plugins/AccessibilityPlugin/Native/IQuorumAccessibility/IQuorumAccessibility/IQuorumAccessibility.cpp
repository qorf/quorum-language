#include "../IQuorumAccessibility/Header/plugins_quorum_Libraries_Interface_AccessibilityManager.h"
#include "../IQuorumAccessibility/Header/jni.h"

#include <Windows.h>
#include <UIAutomation.h>

#include "ItemControl.h"
#include "ButtonControl.h"
#include "RadioButtonControl.h"
#include "CheckBoxControl.h"
#include "TextBoxControl.h"
#include "MenuBarControl.h"
#include "MenuItemControl.h"

// For Debug Output
#include <iostream>
#include <string>

// This is the handle to the main game window. It is set during initialization and must never be changed.
HWND GLFWParentWindow = NULL;

HWND GetMainWindowHandle()
{
	return GLFWParentWindow;
}

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
	UNREFERENCED_PARAMETER(instance);
	UNREFERENCED_PARAMETER(reason);
	UNREFERENCED_PARAMETER(reserved);
	return TRUE;
}


////////////////////////////////////////////////////////////////////////////
////////////				   JNI Functions
////////////
////////////////////////////////////////////////////////////////////////////

// NativeWin32InitializeAccessibility: Calls CoInitialize so that COM interface library functions are availible for use. This only ever needs to be called once. Never call this more than once.
//									   CoUninitialize must be called the same number of times as CoInitialize.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility(JNIEnv *env, jobject obj, jlong parentWindowHWND)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	CoInitializeEx(NULL, COINIT_MULTITHREADED); // COINIT_APARTMENTTHREADED COINIT_MULTITHREADED
	GLFWParentWindow = (HWND)parentWindowHWND;

	//DWORD threadID = GetCurrentThreadId();

	//std::cout << "NativeWin32InitializeAccessibility Thread ID: " << threadID << std::endl;
}

// NativeWin32ShutdownAccessibility: Closes the COM library gracefully.
// TODO: More work needs to be done in this function to ensure clean shutdown of the library.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility(JNIEnv *env, jobject obj)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	CoUninitialize();
}

// TODO: REMOVE this method from here, the JNI header file, the Java AccessibilityManager, and the Quorum AccessibilityManager.
// NativePrint: Solely used to test whether a call from Java or Quorum can make it down to C++.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativePrint(JNIEnv *env, jobject obj)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
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
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateItem(JNIEnv *env, jobject obj, jstring itemName, jstring description)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wItemName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND itemControlHandle;

	itemControlHandle = ItemControl::Create(GetModuleHandle(NULL), wItemName, wDescription);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(itemControlHandle);

}

// NativeWin32CreateButton: Creates a window that contains the accessible information for a PushButton that was passed into this function.
//		Returns: jlong which is the HWND for the window. This is used to further interact with the button after creation. i.e., to rename the button later should the name be changed. Also, used to keep track of it in java.
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateButton(JNIEnv *env, jobject obj, jstring buttonName, jstring description)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeButtonName = env->GetStringUTFChars(buttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wButtonName = CreateWideStringFromUTF8Win32(nativeButtonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);


	HWND buttonControlHandle;

	buttonControlHandle = ButtonControl::Create(GetModuleHandle(NULL), wButtonName, wDescription);

	env->ReleaseStringUTFChars(buttonName, nativeButtonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(buttonControlHandle);

}

// NativeWin32CreateCheckBox: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateCheckBox(JNIEnv *env, jobject obj, jstring togglebuttonName, jstring description)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeTogglebuttonName = env->GetStringUTFChars(togglebuttonName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wTogglebuttonName = CreateWideStringFromUTF8Win32(nativeTogglebuttonName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND checkBoxControlHandle;

	checkBoxControlHandle = CheckBoxControl::Create(GetModuleHandle(NULL), wTogglebuttonName, wDescription);

	env->ReleaseStringUTFChars(togglebuttonName, nativeTogglebuttonName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(checkBoxControlHandle);

}

// NativeWin32CreateRadioButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateRadioButton(JNIEnv *env, jobject obj, jstring itemName, jstring description)
{
	UNREFERENCED_PARAMETER(obj);
	const char *nativeItemName = env->GetStringUTFChars(itemName, 0);
	const char *nativeDescription = env->GetStringUTFChars(description, 0);

	WCHAR* wRadiobuttonName = CreateWideStringFromUTF8Win32(nativeItemName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);

	HWND radiobuttonControlHandle;

	radiobuttonControlHandle = RadioButtonControl::Create(GetModuleHandle(NULL), wRadiobuttonName, wDescription);

	env->ReleaseStringUTFChars(itemName, nativeItemName);
	env->ReleaseStringUTFChars(description, nativeDescription);

	return PtrToLong(radiobuttonControlHandle);

}

// NativeWin32CreateTextBox: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateTextBox(JNIEnv *env, jobject obj, jstring textboxName, jstring description, jstring fullText, jint caretIndex)
{
	UNREFERENCED_PARAMETER(obj);
	const char* nativeTextboxName = env->GetStringUTFChars(textboxName, 0);
	const char* nativeDescription = env->GetStringUTFChars(description, 0);
	const char* nativeFullText = env->GetStringUTFChars(fullText, 0);

	WCHAR* wTextboxName = CreateWideStringFromUTF8Win32(nativeTextboxName);
	WCHAR* wDescription = CreateWideStringFromUTF8Win32(nativeDescription);
	//WCHAR* wCurrentLineText = CreateWideStringFromUTF8Win32(nativeFullText);

	//EndPoint caret = EndPoint(caretLine, caretCharacter);
	
	//TextLine line[] = { wCurrentLineText };
	//TextLine line[] = { { L"Hello world!", 12 }, };

	//DWORD threadID = GetCurrentThreadId();

	//std::cout << "NativeWin32CreateTextBox Thread ID: " << threadID << std::endl;

	HWND textboxControlHandle = TextBoxControl::Create(GetModuleHandle(NULL), wTextboxName, wDescription, nativeFullText, (int)caretIndex);

	env->ReleaseStringUTFChars(textboxName, nativeTextboxName);
	env->ReleaseStringUTFChars(description, nativeDescription);
	env->ReleaseStringUTFChars(fullText, nativeFullText);

	return PtrToLong(textboxControlHandle);

}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateMenuBar(JNIEnv * env, jobject obj, jstring menuBarName)
{
	const char* nativeMenuBarName = env->GetStringUTFChars(menuBarName, 0);
	WCHAR* wMenuBarName = CreateWideStringFromUTF8Win32(nativeMenuBarName);

	HWND menuBarControl = MenuBarControl::Create(GetModuleHandle(NULL), wMenuBarName);

	env->ReleaseStringUTFChars(menuBarName, nativeMenuBarName);

	return PtrToLong(menuBarControl);
}

JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32CreateMenuItem(JNIEnv * env, jobject obj, jstring menuItemName, jstring menuShortcut, jlong parentMenu, jlong parentMenuBar)
{
	const char* nativeMenuItemName = env->GetStringUTFChars(menuItemName, 0);
	const char* nativeMenuShortcut = env->GetStringUTFChars(menuShortcut, 0);

	WCHAR* wMenuItemName = CreateWideStringFromUTF8Win32(nativeMenuItemName);
	WCHAR* wMenuShortcut = CreateWideStringFromUTF8Win32(nativeMenuShortcut);

	HWND menuBar = (HWND)parentMenuBar;
	
	MenuItemControl* parentMenuItem = NULL;
	
	if ((long)parentMenu != 0)
		parentMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)parentMenu));
	
	MenuItemControl* menuItemControl = new MenuItemControl(wMenuItemName, wMenuShortcut, parentMenuItem);

	SendMessage(menuBar, QUORUM_ADDMENUITEM, 0, (LPARAM)menuItemControl);

	env->ReleaseStringUTFChars(menuItemName, nativeMenuItemName);
	env->ReleaseStringUTFChars(menuShortcut, nativeMenuShortcut);

	return PtrToLong(menuItemControl);
}

#pragma endregion

#pragma region Remove Accessible Object

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32RemoveMenuItem(JNIEnv * env, jobject obj, jlong menuItem)
{
	
	MenuItemControl* menuItemToRemove = static_cast<MenuItemControl*>(LongToPtr((long)menuItem));

	MenuControl* menuControl = menuItemToRemove->GetMenuControl();

	return menuControl->RemoveMenuItem(menuItemToRemove->GetMenuItemIndex());

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
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	HWND control = (HWND)jlongHWND;

	// Sends the appropriate messages to all windows.
	SetFocus(control);


	return PtrToLong(control);
}

// NativeWin32TextBoxTextSelectionChanged: This method will fire the appropriate UIA Event for when the text selection has changed. The selection can change as a result of the caret moving or text being added to the currentLineText.
// TODO: Update the currentLineText from what is given by Quorum. That way the line down here can stay in sync with Quorum.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32TextBoxTextSelectionChanged(JNIEnv *env, jobject obj, jlong textboxHWND, jstring currentLineText, jint caretLine, jint caretCharacter)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	UNREFERENCED_PARAMETER(textboxHWND);
	UNREFERENCED_PARAMETER(currentLineText);
	UNREFERENCED_PARAMETER(caretLine);
	UNREFERENCED_PARAMETER(caretCharacter);
	/*const char *nativeCurrentLineText = env->GetStringUTFChars(currentLineText, 0);
	WCHAR* wCurrentLineText = CreateWideStringFromUTF8Win32(nativeCurrentLineText);

	EndPoint caret;
	caret.line = (int)caretLine;
	caret.character = (int)caretCharacter;

	SendMessage((HWND)textboxHWND, QUORUM_UPDATECARET, 0, (LPARAM)&caret);

	env->ReleaseStringUTFChars(currentLineText, nativeCurrentLineText);*/
	std::cout << "NativeWin32TextBoxTextSelectionChanged is being reworked. Use NativeWin32UpdateCaretPosition instead." << std::endl;
}

// NativeWin32UpdateCaretPosition:
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateCaretPosition(JNIEnv *env, jobject obj, jlong textboxHWND, jstring fullText, jint caretIndex)
{
	UNREFERENCED_PARAMETER(obj);
	UNREFERENCED_PARAMETER(textboxHWND);
	UNREFERENCED_PARAMETER(fullText);
	UNREFERENCED_PARAMETER(caretIndex);

	HWND control = (HWND)textboxHWND;

	const char *nativeFullText = env->GetStringUTFChars(fullText, 0);

	SendMessage(control, QUORUM_UPDATECARET, (int)caretIndex, (LPARAM)nativeFullText);

	env->ReleaseStringUTFChars(fullText, nativeFullText);
}

// NativeWin32InvokeButton: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InvokeButton(JNIEnv *env, jobject obj, jlong jlongHWND)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	HWND control = (HWND)jlongHWND;

	SendMessage(control, QUORUM_INVOKEBUTTON, 0, 0);

	return PtrToLong(control);
}

// NativeWin32UpdateToggleStatus: 
JNIEXPORT long JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32UpdateToggleStatus(JNIEnv *env, jobject obj, jlong jlongHWND, jboolean selected)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	HWND control = (HWND)jlongHWND;

	bool nativeSelected = (bool)selected;

	if (nativeSelected)
	{
		SendMessage(control, QUORUM_INVOKEBUTTON, true, 0);
		return true;
	}
	else
	{
		SendMessage(control, QUORUM_INVOKEBUTTON, false, 0);
		return true;
	}

}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32SelectMenuItem(JNIEnv * env, jobject obj, jlong selectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	
	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)selectedMenuItem));
	
	pMenuItem->GetParentMenuBar()->SetSelectedMenuItem(pMenuItem);
	return true;
}

JNIEXPORT bool JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32DeselectMenuItem(JNIEnv * env, jobject obj, jlong deselectedMenuItem)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);

	MenuItemControl* pMenuItem = static_cast<MenuItemControl*>(LongToPtr((long)deselectedMenuItem));

	pMenuItem->GetParentMenuBar()->SetSelectedMenuItem(NULL);
	return true;
}

#pragma endregion