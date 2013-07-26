// This file contains all of the implementations of the Mac OS X native JNI methods defined in 
// the MacAccessibleHandler.java file.
//
// References information for Windows accessibility programming: 
//    * http://msdn.microsoft.com/en-us/library/windows/desktop/dd317990(v=vs.85).aspx

#import <AccessibleHandlers_MacAccessibleHandler.h>
#import <MacAccessibleHandler.h>
#import <stdio.h>

MacAccessibleHandler* handler;

// This function implements the Initialize function defined in the MacAccessibleHandler.java
// It instantiates a MacAccessibleHandler class and calls this instance's initialize function.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Accessibility_MacAccessibleHandler_Initialize(JNIEnv *env, jobject jobj)
{
	handler = [MacAccessibleHandler alloc];
	[handler initialize: env object: jobj];
}

// This function implements the Terminate function defined in the MacAccessibleHandler.java
// It calls the MacAccessibleHandler instance's terminate function and releases its memory.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Accessibility_MacAccessibleHandler_Terminate(JNIEnv *env, jobject jobj)
{
	[handler terminate];
	[handler release];
}