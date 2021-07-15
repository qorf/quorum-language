#include <stdio.h>
#include "jni.h"
#import <Foundation/Foundation.h>

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_Accessibility_MacAccessibility_InitializeAccessibilityNative
  (JNIEnv* env, jobject thisObject, jlong window, jstring title) {
    printf("Hi");
}


