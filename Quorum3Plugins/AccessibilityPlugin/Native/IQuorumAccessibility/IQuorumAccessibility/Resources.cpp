#include "Resources.h"
#include "../IQuorumAccessibility/Header/plugins_quorum_Libraries_Interface_AccessibilityManager.h"
/**************************************************************
* Declare JNI_VERSION for use in JNI_Onload/JNI_OnUnLoad
* Change value if a Java upgrade requires it
**************************************************************/
static jint JNI_VERSION = JNI_VERSION_1_8;


// This is the handle to the main game window. It is set during initialization and must never be changed.
HWND GLFWParentWindow = NULL;

JavaVM* jvm;
JNIEnv* thisEnv = NULL;

JClass_AccessibilityManager JavaClass_AccessibilityManager;
JClass_TextBox JavaClass_TextBox;
JClass_TextBoxSelection JavaClass_TextBoxSelection;


/**************************************************************
* Initialize the static Java Classes and Method Id variables
* When the library is loaded in Java this method is called automatically.
**************************************************************/
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
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
	JavaClass_TextBox.GetCaretIndex = env->GetMethodID(JavaClass_TextBox.me, "GetCaretIndex", "()I");
	JavaClass_TextBox.GetText = env->GetMethodID(JavaClass_TextBox.me, "GetText", "()Ljava/lang/String;");
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

	// Required to return the JNI Version
	return JNI_VERSION;
}

/**************************************************************
* Destroy the global static Class Id variables
* When the library is unloaded in Java this method is called automatically.
**************************************************************/
void JNI_OnUnload(JavaVM *vm, void *reserved)
{

	// Obtain the JNIEnv from the VM
	JNIEnv* env;
	vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION);

	// Destroy the global references
	env->DeleteGlobalRef(JavaClass_AccessibilityManager.me);
	env->DeleteGlobalRef(JavaClass_TextBox.me);

}

// NativeWin32InitializeAccessibility: Calls CoInitialize so that COM interface library functions are availible for use. This only ever needs to be called once. Never call this more than once.
//									   CoUninitialize must be called the same number of times as CoInitialize.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32InitializeAccessibility(JNIEnv *env, jobject obj, jlong parentWindowHWND)
{
	UNREFERENCED_PARAMETER(obj);
	CoInitializeEx(NULL, COINIT_MULTITHREADED); // COINIT_APARTMENTTHREADED COINIT_MULTITHREADED
	GLFWParentWindow = (HWND)parentWindowHWND;
	env->GetJavaVM(&jvm);
}

// NativeWin32ShutdownAccessibility: Closes the COM library gracefully.
// TODO: More work needs to be done in this function to ensure clean shutdown of the library. MSDN has a clean shutdown example that details what needs to be done.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_AccessibilityManager_NativeWin32ShutdownAccessibility(JNIEnv *env, jobject obj)
{
	UNREFERENCED_PARAMETER(env);
	UNREFERENCED_PARAMETER(obj);
	CoUninitialize();
}

HWND GetMainWindowHandle()
{
	return GLFWParentWindow;
}

JNIEnv* GetJNIEnv()
{
	// double check it's all ok
	int getEnvStat = jvm->GetEnv((void **)&thisEnv, JNI_VERSION_1_6);
	if (getEnvStat == JNI_EDETACHED)
	{
		/*std::cout << "GetEnv: not attached" << std::endl;*/
		if (jvm->AttachCurrentThread((void **)&thisEnv, NULL) != 0)
			/*std::cout << "Failed to attach" << std::endl*/;

	}
	else if (getEnvStat == JNI_OK)
	{
		return thisEnv;
	}
	else if (getEnvStat == JNI_EVERSION)
	{
		/*std::cout << "GetEnv: version not supported" << std::endl*/;
	}

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