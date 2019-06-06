#include "Resources.h"
#include "../IQuorumAccessibility/Header/plugins_quorum_Libraries_Interface_AccessibilityManager.h"
#include <iostream>
#include <fstream>
/**************************************************************
* Declare JNI_VERSION for use in JNI_Onload/JNI_OnUnLoad
* Change value if a Java upgrade requires it
**************************************************************/
static jint JNI_VERSION = JNI_VERSION_1_8;


// This is the handle to the main game window. It is set during initialization and must never be changed.
HWND GLFWParentWindow = NULL;

IUIAutomation* iuiAutomation = NULL;

JavaVM* jvm;
JNIEnv* thisEnv = NULL;

JClass_AccessibilityManager JavaClass_AccessibilityManager;
JClass_TextBox JavaClass_TextBox;
JClass_TextBoxSelection JavaClass_TextBoxSelection;
JClass_TextField JavaClass_TextField;
JClass_TextFieldSelection JavaClass_TextFieldSelection;
JClass_ToggleButton JavaClass_ToggleButton;
JClass_Item JavaClass_Item;

int outputCounter = 0;

void log(std::string str) {
	std::cout << str << std::endl;
	std::cout.flush();
}

/**************************************************************
* Initialize the static Java Classes and Method Id variables
* When the library is loaded in Java this method is called automatically.
**************************************************************/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
	#if LOG
		log("JNI_OnLoad Start");
	#endif

	// Obtain the JNIEnv from the VM and confirm JNI_VERSION
	JNIEnv* env;
	if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION) != JNI_OK)
	{
		return JNI_ERR;
	}

	// Temporary local reference holder
	jclass tempLocalClassRef;

	/**********************
	* AccessibilityManager
	**********************/
	#pragma region AccessibilityManager
	// Load the class id
	tempLocalClassRef = env->FindClass("plugins/quorum/Libraries/Interface/AccessibilityManager");

	// Assign the ClassId as a Global Reference
	JavaClass_AccessibilityManager.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_AccessibilityManager.WaitForUpdate = env->GetStaticMethodID(JavaClass_AccessibilityManager.me, "WaitForUpdate", "()V");
	JavaClass_AccessibilityManager.GetTreeSelectionPointer = env->GetStaticMethodID(JavaClass_AccessibilityManager.me, "GetTreeSelectionPointer", "(Lquorum/Libraries/Interface/Controls/Tree_;)J");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/*********
	* TextBox
	*********/
	#pragma region TextBox
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Controls/TextBox_");

	// Assign the ClassId as a Global Reference
	JavaClass_TextBox.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_TextBox.GetCaretLine = env->GetMethodID(JavaClass_TextBox.me, "GetCaretLine", "()I");
	JavaClass_TextBox.GetCaretPosition = env->GetMethodID(JavaClass_TextBox.me, "GetCaretPosition", "()I");
	JavaClass_TextBox.GetIndexOfLine = env->GetMethodID(JavaClass_TextBox.me, "GetIndexOfLine", "(I)I");
	JavaClass_TextBox.GetText = env->GetMethodID(JavaClass_TextBox.me, "GetText", "()Ljava/lang/String;");
	JavaClass_TextBox.GetCurrentLineText = env->GetMethodID(JavaClass_TextBox.me, "GetCurrentLineText", "()Ljava/lang/String;");
	JavaClass_TextBox.GetSelection = env->GetMethodID(JavaClass_TextBox.me, "GetSelection", "()Lquorum/Libraries/Interface/Selections/TextBoxSelection_;");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/******************
	* TextBoxSelection
	******************/
	#pragma region TextBoxSelection
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Selections/TextBoxSelection_");

	// Assign the ClassId as a Global Reference
	JavaClass_TextBoxSelection.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_TextBoxSelection.IsEmpty = env->GetMethodID(JavaClass_TextBoxSelection.me, "IsEmpty", "()Z");
	JavaClass_TextBoxSelection.GetStartIndex = env->GetMethodID(JavaClass_TextBoxSelection.me, "GetStartIndex", "()I");
	JavaClass_TextBoxSelection.GetEndIndex = env->GetMethodID(JavaClass_TextBoxSelection.me, "GetEndIndex", "()I");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/*********
	* TextField
	*********/
	#pragma region TextField
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Controls/TextField_");

	// Assign the ClassId as a Global Reference
	JavaClass_TextField.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_TextField.GetCaretPosition = env->GetMethodID(JavaClass_TextField.me, "GetCaretPosition", "()I");
	JavaClass_TextField.GetText = env->GetMethodID(JavaClass_TextField.me, "GetText", "()Ljava/lang/String;");
	JavaClass_TextField.GetSelection = env->GetMethodID(JavaClass_TextField.me, "GetSelection", "()Lquorum/Libraries/Interface/Selections/TextFieldSelection_;");
	JavaClass_TextField.GetSize = env->GetMethodID(JavaClass_TextField.me, "GetSize", "()I");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/******************
	* TextFieldSelection
	******************/
	#pragma region TextFieldSelection
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Selections/TextFieldSelection_");

	// Assign the ClassId as a Global Reference
	JavaClass_TextFieldSelection.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_TextFieldSelection.IsEmpty = env->GetMethodID(JavaClass_TextFieldSelection.me, "IsEmpty", "()Z");
	JavaClass_TextFieldSelection.GetStartIndex = env->GetMethodID(JavaClass_TextFieldSelection.me, "GetStartIndex", "()I");
	JavaClass_TextFieldSelection.GetEndIndex = env->GetMethodID(JavaClass_TextFieldSelection.me, "GetEndIndex", "()I");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/*************
	* ToggleButton
	*************/
	#pragma region ToggleButton
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Controls/ToggleButton_");

	// Assign the ClassId as a Global Reference
	JavaClass_ToggleButton.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	JavaClass_ToggleButton.SetToggleState = env->GetMethodID(JavaClass_ToggleButton.me, "SetToggleState", "(Z)V");
	JavaClass_ToggleButton.GetToggleState = env->GetMethodID(JavaClass_ToggleButton.me, "GetToggleState", "()Z");

	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	/******
	* Item
	******/
	#pragma region Item
	// Load the class id
	tempLocalClassRef = env->FindClass("quorum/Libraries/Interface/Item_");

	// Assign the ClassId as a Global Reference
	JavaClass_Item.me = (jclass)env->NewGlobalRef(tempLocalClassRef);

	// Load the method ids
	//env->GetMethodID(JavaClass_TextBox.me, "GetText", "()Ljava/lang/String;");
	JavaClass_Item.GetName = env->GetMethodID(JavaClass_Item.me, "GetName", "()Ljava/lang/String;");
	JavaClass_Item.GetDescription = env->GetMethodID(JavaClass_Item.me, "GetDescription", "()Ljava/lang/String;");
	JavaClass_Item.GetHashCode = env->GetMethodID(JavaClass_Item.me, "GetHashCode", "()I");
	// Delete local reference
	env->DeleteLocalRef(tempLocalClassRef);
	#pragma endregion

	#if LOG
		log("JNI_OnLoad End");
	#endif
	// Required to return the JNI Version
	return JNI_VERSION;
}

/**************************************************************
* Destroy the global static Class Id variables
* When the library is unloaded in Java this method is called automatically.
**************************************************************/
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved)
{
	#if LOG
		log("JNI_OnUnload Start");
	#endif
	// Obtain the JNIEnv from the VM
	JNIEnv* env;
	vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION);

	// Destroy the global references
	env->DeleteGlobalRef(JavaClass_AccessibilityManager.me);
	env->DeleteGlobalRef(JavaClass_TextBox.me);
	#if LOG
		log("JNI_OnUnload Start");
	#endif
}

// NativeWin32InitializeAccessibility: Calls CoInitialize so that COM interface library functions are availible for use. This only ever needs to be called once. Never call this more than once.
//									   CoUninitialize must be called the same number of times as CoInitialize.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility(JNIEnv *env, jobject obj, jlong parentWindowHWND)
{
	#if LOG
		log("Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility Start");
	#endif
	UNREFERENCED_PARAMETER(obj);
	CoInitializeEx(NULL, COINIT_MULTITHREADED); // COINIT_APARTMENTTHREADED COINIT_MULTITHREADED
	GLFWParentWindow = (HWND)parentWindowHWND;
	
	// Used to initialize the iuiAutomation variable in Resources.h, which is used to access IUIAutomation methods.
	HRESULT result = CoCreateInstance(__uuidof(CUIAutomation), NULL, CLSCTX_INPROC_SERVER, __uuidof(IUIAutomation), (void**)&iuiAutomation);
	//std::cout << "Initialized iuiAutomation variable? " << SUCCEEDED(result) << std::endl;
	//std::cout.flush();
	env->GetJavaVM(&jvm);
	#if LOG
		log("Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility End");
	#endif
}

// NativeWin32ShutdownAccessibility: Closes the COM library gracefully.
// TODO: More work needs to be done in this function to ensure clean shutdown of the library. MSDN has a clean shutdown example that details what needs to be done.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility(JNIEnv *env, jobject obj)
{
	#if LOG
		log("Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility Start");
	#endif
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	UiaDisconnectAllProviders();
	CoUninitialize();
	#if LOG
		log("Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility End");
	#endif
}

HWND GetMainWindowHandle()
{
	#if LOG
		log("Resources.cpp GetMainWindowHandle");
	#endif
	return GLFWParentWindow;
}

IUIAutomation* GetIUIAutomation()
{
	#if LOG
		log("Resources.cpp GetIUIAutomation");
	#endif
	return iuiAutomation;
}

JNIEnv* GetJNIEnv()
{
	#if LOG
		log("Resources.cpp GetJNIEnv Start");
	#endif
	// double check it's all ok
	int getEnvStat = jvm->GetEnv((void **)&thisEnv, JNI_VERSION_1_6);
	if (getEnvStat == JNI_EDETACHED)
	{
		if (jvm->AttachCurrentThread((void **)&thisEnv, NULL) != 0)
			std::cout << "Failed to attach (GetJNIEnv)\n";//	OutputToFile("Failed to attach");

	}
	else if (getEnvStat == JNI_OK)
	{
		#if LOG
				log("Resources.cpp GetJNIEnv End (JNI_OK)");
		#endif
		return thisEnv;
	}
	else if (getEnvStat == JNI_EVERSION)
	{
		//OutputToFile("GetEnv: version not supported");
	}

	#if LOG
		log("Resources.cpp GetJNIEnv End (Returned NULL)");
	#endif

	return NULL;
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
