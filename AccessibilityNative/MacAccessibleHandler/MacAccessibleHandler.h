//
//  MacAccessibleHandler.h
//  MacAccessibleHandler
//
//  Created by Matt Lawson on 7/3/12.
//
//	This file contains the field and function definitions for the MacAccessibleHandler class.
//  The descriptions of the fields and functions are contained in MacAccessibleHandler.m.

#import <Cocoa/Cocoa.h>
#import <Carbon/Carbon.h>
#import <JavaVM/jni.h>

id thisClass;
@interface MacAccessibleHandler : NSObject {
	JavaVM *jvm;
	JNIEnv *jniEnv;
	jobject jniObject;
	NSThread* loopThread;
	NSMutableDictionary* applicationsWithNotifications;
	
	jmethodID methodID;
    
	AXUIElementRef systemElement;
	NSRunningApplication* focusedRunningApplication;
    bool hasRunningApplication;
    CFRunLoopSourceRef keyboardEventLoopSource;
    CFRunLoopSourceRef mouseEventLoopSource;
}

- (void) initialize: (JNIEnv*) env object: (jobject) jobj;
- (void) terminate;
- (void) MessageThreadLoop: (id) argument;
- (void) SendEventInfo: (NSString*) eventInfo;
- (AXObserverRef) InitializeObserverNotifications: (pid_t) elementProcessID;
- (void) InitializeKeyboardAndMouseEvents;
- (void) UnInitializeObserverNotifications: (AXObserverRef*) observer pid: (pid_t) oldPID;
- (void) UnInitializeKeyboardAndMouseEvents;
- (void) CreateEventList: (NSMutableArray*) events;
- (NSString *)descriptionOfChildren:(AXUIElementRef)uiElement : (NSString*)notification;
- (void) getWinEventNames: (NSString*) event : (NSString**) category : (NSString**) type : (NSString**) component;
- (NSString*) renameComponent:(NSString*)roleValue;
- (NSString*) getKeyName:(CGKeyCode)keycode;

// XML functions
- (NSString*) CreateXML:(NSString*)category :(NSString*)type :(NSString*)component :(NSString*)name  :(NSString*) shortcut : (NSString*) childXML;
- (NSString*) CreateKeyboardXML: (NSString*) type : (NSString*) key;
- (NSString*) CreateMouseXML: (NSString*) type : (NSString*) position : (NSString*) button;
- (NSString*) CreateChildXML: (NSString*) name : (NSString*) component : (NSString*) shortcut;
- (NSString*) removeInvalidXMLCharacters: (NSString*) xmlValue;

// Callback functions
void ProcessNotification(AXObserverRef observer, AXUIElementRef element, CFStringRef notification, void* refcon);
CGEventRef KeyboardEventTapCallBack (CGEventTapProxy proxy, CGEventType type, CGEventRef event, void *refcon);
CGEventRef MouseEventTapCallBack (CGEventTapProxy proxy,CGEventType type,CGEventRef event,void *refcon);

@end
