#include <stdio.h>
#include "jni.h"
#import <Foundation/Foundation.h>
#import <AppKit/AppKit.h>

JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Interface_Accessibility_MacAccessibility_InitializeAccessibilityNative
  (JNIEnv* env, jobject thisObject, jlong window, jstring title) {
    NSWindow* root = (NSWindow*) window;
    NSView* view = root.contentView;
    
//    NSMenu* app = [[NSApplication sharedApplication] mainMenu] ;
//    NSMenuItem *fileMenuItem = [[NSMenuItem alloc] initWithTitle: @"Bob"];
//    [app addItem: fileMenuItem];
    
    NSMenu *fileMenu = [[NSMenu alloc] initWithTitle:@"Quorum Studio"];
    NSMenuItem *newMenu = [[NSMenuItem alloc] initWithTitle:@"Bob" action:NULL keyEquivalent:@""];
    NSMenuItem *openMenu = [[NSMenuItem alloc] initWithTitle:@"Open" action:NULL keyEquivalent:@""];
    NSMenuItem *saveMenu = [[NSMenuItem alloc] initWithTitle:@"Save" action:NULL keyEquivalent:@""];
    
    [fileMenu addItem: newMenu];
    [fileMenu addItem: openMenu];
    [fileMenu addItem: saveMenu];
    
    NSMenuItem* java = [[NSApp mainMenu] itemAtIndex: 0];
    //java.setTitle(@"Quorum Studio");
    
    [[[NSApp mainMenu] itemAtIndex: 0] setTitle: @"Quorum Studio"];
    NSMenuItem *fileMenuItem = [[NSMenuItem alloc] initWithTitle:@"Monkey" action:NULL keyEquivalent:@""];
    [fileMenuItem setSubmenu: fileMenu]; // was setMenu:
    //[[NSApp mainMenu] removeItemAtIndex: 0];
    //[[NSApp mainMenu] insertItem: fileMenuItem atIndex: 0];
    [fileMenuItem release];
    //app.addItem(item);
}


