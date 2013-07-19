//
//  MacAccessibleHandler.m
//  MacAccessibleHandler
//
//  Created by Matt Lawson on 7/3/12.
//
//	This file contains the field and function implementations for the MacAccessibleHandler class.

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// BEFORE USING THIS CLASS: You must check System Preferences -> Universal Access -> Enable access for assistive devices //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#import "MacAccessibleHandler.h"

@implementation MacAccessibleHandler

// XML tag constants
NSString* const XML_HEADER = @"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" ;
NSString* const ROOT_TAG = @"<AccessibleEvent>\n";
NSString* const ROOT_TAG_END = @"</AccessibleEvent>\n";
NSString* const CATEGORY_TAG = @"\t<Category>";
NSString* const CATEGORY_TAG_END = @"</Category>\n";
NSString* const FOCUS_TYPE_TAG = @"\t<FocusType>";
NSString* const FOCUS_TYPE_TAG_END = @"</FocusType>\n";
NSString* const KEYBOARD_TYPE_TAG = @"\t<KeyboardType>";
NSString* const KEYBOARD_TYPE_TAG_END = @"</KeyboardType>\n";
NSString* const MOUSE_TYPE_TAG = @"\t<MouseType>";
NSString* const MOUSE_TYPE_TAG_END = @"</MouseType>\n";
NSString* const WINDOW_TYPE_TAG = @"\t<WindowType>";
NSString* const WINDOW_TYPE_TAG_END = @"</WindowType>\n";
NSString* const NOTIFICATION_TYPE_TAG = @"\t<NotificationType>";
NSString* const NOTIFICATION_TYPE_TAG_END = @"</NotificationType>\n";
NSString* const MENU_TYPE_TAG = @"\t<MenuType>";
NSString* const MENU_TYPE_TAG_END = @"</MenuType>\n";
NSString* const PROPERTY_CHANGE_TYPE_TAG = @"\t<PropertyChangeType>";
NSString* const PROPERTY_CHANGE_TYPE_TAG_END = @"</PropertyChangeType>\n"; 
NSString* const COMPONENT_TAG = @"\t<Component>";
NSString* const COMPONENT_TAG_END = @"</Component>\n";
NSString* const READING_TAG = @"\t<Reading>";
NSString* const READING_TAG_END = @"</Reading>\n";
NSString* const POSITION_TAG = @"\t<Position>";
NSString* const POSITION_TAG_END = @"</Position>\n";
NSString* const MOUSE_BUTTON_TAG = @"\t<MouseButton>";
NSString* const MOUSE_BUTTON_TAG_END = @"</MouseButton>\n";
NSString* const SHORTCUT_TAG = @"\t<Shortcut>";
NSString* const SHORTCUT_TAG_END = @"</Shortcut>\n";
NSString* const CHILD_COUNT_TAG = @"\t<ChildCount>";
NSString* const CHILD_COUNT_TAG_END = @"</ChildCount>\n";
NSString* const CHILDREN_TAG = @"\t<Children>\n";
NSString* const CHILDREN_TAG_END = @"\t</Children>\n";
NSString* const CHILD_TAG = @"\t\t<Child>\n";
NSString* const CHILD_TAG_END = @"\t\t</Child>\n";
NSString* const CHILD_NAME_TAG = @"\t\t\t<ChildName>";
NSString* const CHILD_NAME_TAG_END = @"</ChildName>\n";
NSString* const CHILD_COMPONENT_TAG = @"\t\t\t<ChildComponent>";
NSString* const CHILD_COMPONENT_TAG_END = @"</ChildComponent>\n";
NSString* const CHILD_SHORTCUT_TAG = @"\t\t\t<ChildShortcut>";
NSString* const CHILD_SHORTCUT_TAG_END = @"</ChildShortcut>\n";


// This function starts the message loop thread that listens for Mac accessibility notifications
// and sends the appropriate information back to Java that will then be converted to speech.
- (void) initialize: (JNIEnv*) env object: (jobject) jobj
{
    NSLog(@"Setting Self");
    thisClass = self;
    
    hasRunningApplication = false;
    
	// Create an AutoreleasePool for local variables
	NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
	
	// Define a hashmap (dictionary) of applications for which notifications have been
	// registered
	applicationsWithNotifications = [NSMutableDictionary dictionaryWithCapacity: 10];
	[applicationsWithNotifications retain];
	
	// Get the system-level AXUIElementRef needed to get the focused application
	systemElement = AXUIElementCreateSystemWide();
	
	// Save the current running Java Virtual Machine (jvm; used to call back to Java) and
	// the MacAccessibleHandler instance that called this method (jobj; used to get the
	// callback function that is called when an event is received).
	(*env)->GetJavaVM(env, &jvm);
	jniObject = (*env)->NewGlobalRef(env, jobj);
    
	// Create and start the message loop thread with MessageThreadLoop as the main function
	loopThread = [[NSThread alloc] initWithTarget:thisClass selector:@selector(MessageThreadLoop:) object:nil];
	[loopThread start];
	
	[pool release];
}

// This function places the message loop thread in the cancelled state.  Once in this state, the
// loop cleans up memory and terminates.
- (void) terminate
{
    if (hasRunningApplication)
    {
        pid_t currPID = [focusedRunningApplication processIdentifier];
        
        // get the PID of the previously observed application if there is not a current one
        if (currPID == -1) // -1 is the return value for an application without a pid
        {
            NSNumber* currPIDInt = [NSNumber numberWithInt: (int) currPID];
            
            // Get the observer for the previous application
            AXObserverRef applicationObserver = (AXObserverRef)[applicationsWithNotifications objectForKey: currPIDInt];
            
            // Unitialize the notifications for the previous application
            [thisClass UnInitializeObserverNotifications: &applicationObserver pid: currPID];
        }
    }
    [thisClass UnInitializeKeyboardAndMouseEvents];
    
    hasRunningApplication = false;
    
	[loopThread cancel];
}

// This function serves as a message loop that listens for accessiblity notifications and
// focused application changes.
- (void) MessageThreadLoop: (id) argument
{
	// Configure an AutoreleasePool for the local variables
	NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
	
	// Attach the new thread to the Java Virtual Machine instance that called the Mac native code
	(*jvm)->AttachCurrentThread(jvm, (void**) &jniEnv, NULL);    
    
	AXUIElementRef focusedApplication;
	
    NSLog(@"right before it should do something");
    // Start keyboard and mouse events
    //[thisClass InitializeKeyboardAndMouseEvents];
    
	while (true)
	{
		// Get the current focused application
		if (AXUIElementCopyAttributeValue(systemElement, kAXFocusedApplicationAttribute, (CFTypeRef*) &focusedApplication) == kAXErrorSuccess)
		{
            NSString* name = nil;
            AXUIElementCopyAttributeValue(focusedApplication, kAXTitleAttribute, (CFTypeRef*) &name);
            if (name)
            {
                if ([name rangeOfString:@"java"].location == NSNotFound)
                {
                    // Get the process ID of the focused application
                    pid_t newPID = nil;
                    if (AXUIElementGetPid(focusedApplication, &newPID) == kAXErrorSuccess)
                    {     
                        // Create an NSRunningApplication instance for the running application
                        NSRunningApplication* newRunningApplication = [NSRunningApplication runningApplicationWithProcessIdentifier: newPID];
                        
                        // Check if the focused application has changed (true if it has changed)
                        if (![newRunningApplication isEqual: focusedRunningApplication])
                        {                        
                            // Get an NSNumber instance of the new process ID (used as a key in a hashmap)
                            NSNumber* newPIDInt = [NSNumber numberWithInt: (int) newPID];
                            
                            // If the hashmap of applications for which notifications have been configured does not contain
                            // the new application
                            if ([applicationsWithNotifications objectForKey: newPIDInt] == nil)
                            {
                                // Create a new accessibility observer for the new application and initialize the notifications
                                AXObserverRef applicationObserver = [thisClass InitializeObserverNotifications: newPID];					
                                
                                // Add the new application to the hashmap
                                [applicationsWithNotifications setObject:(id)applicationObserver forKey:newPIDInt];
                                
                                // If the previous focused application has been terminated
                                if (focusedRunningApplication != nil)
                                {
                                    // Get the previous application's process ID
                                    pid_t oldPID = [focusedRunningApplication processIdentifier];
                                    NSNumber* oldPIDInt = [NSNumber numberWithInt: (int) oldPID];
                                    
                                    // Get the observer for the previous application
                                    AXObserverRef applicationObserverOld = (AXObserverRef)[applicationsWithNotifications objectForKey: oldPIDInt];
                                    
                                    // Unitialize the notifications for the previous application
                                    [thisClass UnInitializeObserverNotifications: &applicationObserverOld pid: oldPID];
                                    
                                    // Remove the application from the hashmap
                                    [applicationsWithNotifications removeObjectForKey: oldPIDInt];
                                }
                            }
                            
                            [thisClass SendEventInfo: [thisClass CreateXML:@"Focus" : @"Component" : @"Window" : name : nil : nil]];
                            //focusedRunningApplication = newRunningApplication;
                            
                            // Set the focused application to the new application
                            focusedRunningApplication = newRunningApplication;	
                            hasRunningApplication = true;
                        }  
                    }
                    else
                    {
                        NSLog(@"break");
                        break;
                    }//end of name if
                }
            }
		}
        
		// Check if the thread has been cancelled (the handler has been terminated)
		if ([loopThread isCancelled])
		{
            //NSLog(@"thread was cancelled");
			// Delete the reference to the MacAccessiblityHandler java object
			(*jniEnv)->DeleteGlobalRef(jniEnv, jniObject);
            
			// Unitialize all of the notifications for all of the applications for which notifications
			// have been configured
			for (NSNumber* key in applicationsWithNotifications)
			{
				AXObserverRef observer = (AXObserverRef) [applicationsWithNotifications objectForKey: key];
				[thisClass UnInitializeObserverNotifications: &observer pid: (pid_t) [key integerValue]];
			}
			
			// Release the memory for the application hashmap
			[applicationsWithNotifications release];
			
			[pool release];
			return;
		}
	}
}

// This function sends the information stored in string parameter to Java by calling the
// ReceiveEvent function in AccessibleHandler.java.
- (void) SendEventInfo: (NSString*) eventInfo
{      
    (*jvm)->AttachCurrentThread(jvm, (void**) &jniEnv, NULL);
    
    // Get the methodID of the ReceiveEvent function from 
    jclass className = (*jniEnv)->GetObjectClass(jniEnv, jniObject);
	methodID = (*jniEnv)->GetMethodID(jniEnv, className, "ReceiveEvent", "(Ljava/lang/String;)V");
    
	// Create a new Java string using the C string (UTF8String) representation of the eventInfo NSString
	jvalue param;
	jstring paramString = (*jniEnv)->NewStringUTF(jniEnv, [eventInfo UTF8String]);
	param.l = paramString;    
    
	// Call the ReceiveEvent function with the eventInfo Java string as the parameter
	(*jniEnv)->CallVoidMethodA(jniEnv, jniObject, methodID, &param);
}

// This function initializes global mouse and keyboard events.
-(void) InitializeKeyboardAndMouseEvents;
{
    // Keyboard Hooks //
    
    // Define the mask(type of events) for keyboard events.
    CGEventMask keyboardMask = CGEventMaskBit(kCGEventKeyDown) | CGEventMaskBit(NSKeyUp) | CGEventMaskBit(NSFlagsChanged);
    
    // Create keyboard event hooks.
    CFMachPortRef mMachPortRef = CGEventTapCreate(kCGAnnotatedSessionEventTap, kCGHeadInsertEventTap, kCGEventTapOptionListenOnly, keyboardMask, (CGEventTapCallBack)KeyboardEventTapCallBack, nil);
    
    if (mMachPortRef == NULL)
        printf("Error initializing keyboard notifications.\n");
    else
    {
        CFRelease(mMachPortRef);
    }
    
    // Add the events to the main loop so they will be processed.
    keyboardEventLoopSource = CFMachPortCreateRunLoopSource(kCFAllocatorDefault, mMachPortRef, 0);
    CFRunLoopAddSource(CFRunLoopGetMain(), keyboardEventLoopSource, kCFRunLoopDefaultMode);
    
    
    // Mouse Hooks //
    
    // Define the mask(type of events) for mouse events.
    CGEventMask mouseMask = CGEventMaskBit(kCGEventLeftMouseDown) | CGEventMaskBit(kCGEventLeftMouseUp) | CGEventMaskBit(kCGEventMouseMoved) | CGEventMaskBit(kCGEventRightMouseDown) | CGEventMaskBit(kCGEventRightMouseUp) | CGEventMaskBit(kCGEventOtherMouseDown) | CGEventMaskBit(kCGEventOtherMouseUp) | CGEventMaskBit(kCGEventScrollWheel);
    
    // Create mouse event hooks.
    mMachPortRef = CGEventTapCreate(kCGHIDEventTap, kCGHeadInsertEventTap, kCGEventTapOptionListenOnly, mouseMask, (CGEventTapCallBack)MouseEventTapCallBack, nil);
    
    if (mMachPortRef == NULL)
    {
        printf("Error initializing mouse notifications.\n");
        return;
    }
    
    // Add the events to the main loop so they will be processed.
    mouseEventLoopSource = CFMachPortCreateRunLoopSource(kCFAllocatorDefault, mMachPortRef, 0);
    CFRunLoopAddSource(CFRunLoopGetMain(), mouseEventLoopSource, kCFRunLoopDefaultMode);
}

// This function removes the keyboard and mouse events from the main run loop
-(void) UnInitializeKeyboardAndMouseEvents
{
    CFRunLoopRemoveSource(CFRunLoopGetMain(), keyboardEventLoopSource, kCFRunLoopDefaultMode);
    CFRunLoopRemoveSource(CFRunLoopGetMain(), mouseEventLoopSource, kCFRunLoopDefaultMode);
}

// This function initializes the accessiblity notification hooks for the process with
// the parameterized process ID.
- (AXObserverRef) InitializeObserverNotifications: (int) elementProcessID 
{
	AXError er;
    
    // Get the application accessiblity object using the process ID. 
	AXUIElementRef element = AXUIElementCreateApplication(elementProcessID);
	
	// Create an accessiblity observer that listens to notifications for
	// the application accessiblity object
	AXObserverRef applicationObserver;
	er = AXObserverCreate(elementProcessID, ProcessNotification, &applicationObserver);
	
    if (er == kAXErrorSuccess)
    {
        //Add notifications
        NSMutableArray* eventList = [[NSMutableArray alloc] init]; 
        [thisClass CreateEventList:eventList];
        
        // Add notifications
        for(int i = 0; i < [eventList count]; i++)
        {
            NSString* eventName = [eventList objectAtIndex:i];
            er = AXObserverAddNotification(applicationObserver, element, (CFStringRef)eventName, nil);
            if (er != kAXErrorSuccess)
            {
                printf("Error initializing %s notifications.\n", CFStringGetCStringPtr((CFStringRef)eventName, CFStringGetSystemEncoding()));
            }
        }
    }
    
	// Add the observer's run loop to the current main run loop
	CFRunLoopAddSource(CFRunLoopGetMain(), AXObserverGetRunLoopSource(applicationObserver), kCFRunLoopDefaultMode);
	
	return applicationObserver;
}

// This function removes all observer notifications.
- (void) UnInitializeObserverNotifications: (AXObserverRef*)observer pid: (pid_t)oldPID
{
	// Remove the notification hooks from the observer parameter
    NSMutableArray* eventList = [[NSMutableArray alloc] init]; 
    [thisClass CreateEventList:eventList];
    
    for(int i = 0; i < [eventList count]; i++)
    {
        NSString* eventName = [eventList objectAtIndex:i];
        AXObserverRemoveNotification(*observer, AXUIElementCreateApplication(oldPID), (CFStringRef)eventName);
    }
	
	// Remove the observer's run loop from the main run loop
	CFRunLoopRemoveSource(CFRunLoopGetMain(), AXObserverGetRunLoopSource(*observer), kCFRunLoopDefaultMode);
    
    //[loopThread cancel];
}

// This function creates an array of events to be initialized for the system.
- (void) CreateEventList: (NSMutableArray*) events
{
    [events addObject:(NSString*)kAXMainWindowChangedNotification];
    [events addObject:(NSString*)kAXFocusedWindowChangedNotification];
    [events addObject:(NSString*)kAXFocusedUIElementChangedNotification];
    [events addObject:(NSString*)kAXApplicationActivatedNotification];
    //[events addObject:(NSString*)kAXApplicationDeactivatedNotification];
    //[events addObject:(NSString*)kAXApplicationHiddenNotification];
    [events addObject:(NSString*)kAXApplicationShownNotification];
    [events addObject:(NSString*)kAXWindowCreatedNotification]; 
    [events addObject:(NSString*)kAXWindowMovedNotification];
    [events addObject:(NSString*)kAXWindowResizedNotification];
    [events addObject:(NSString*)kAXWindowMiniaturizedNotification];
    [events addObject:(NSString*)kAXWindowDeminiaturizedNotification];
    [events addObject:(NSString*)kAXDrawerCreatedNotification];
    [events addObject:(NSString*)kAXSheetCreatedNotification];
    [events addObject:(NSString*)kAXHelpTagCreatedNotification];
    //[events addObject:(NSString*)kAXValueChangedNotification];
    [events addObject:(NSString*)kAXUIElementDestroyedNotification];
    [events addObject:(NSString*)kAXMenuOpenedNotification];
    [events addObject:(NSString*)kAXMenuClosedNotification];
    [events addObject:(NSString*)kAXMenuItemSelectedNotification];
    [events addObject:(NSString*)kAXRowCountChangedNotification];
    [events addObject:(NSString*)kAXRowExpandedNotification];
    [events addObject:(NSString*)kAXRowCollapsedNotification];
    [events addObject:(NSString*)kAXSelectedCellsChangedNotification];
    [events addObject:(NSString*)kAXUnitsChangedNotification];
    [events addObject:(NSString*)kAXSelectedChildrenMovedNotification];
    [events addObject:(NSString*)kAXSelectedChildrenChangedNotification];
    [events addObject:(NSString*)kAXResizedNotification];
    [events addObject:(NSString*)kAXMovedNotification];
    [events addObject:(NSString*)kAXCreatedNotification];
    [events addObject:(NSString*)kAXSelectedRowsChangedNotification];
    [events addObject:(NSString*)kAXSelectedColumnsChangedNotification];
    //[events addObject:(NSString*)kAXSelectedTextChangedNotification];
    [events addObject:(NSString*)kAXTitleChangedNotification];
    
    return;
}

////////////////////////
// Callback Functions //
////////////////////////

// This function serves as the callback function that is called when a registered Mac 
// notification is triggered. It creates the xml according to the specification and 
// passes it to Java.
void ProcessNotification(AXObserverRef observer, AXUIElementRef element, CFStringRef notification, void* refcon)
{         
	// Get the active instance of the MacAccessibleHandler (the refcon parameter)
	//MacAccessibleHandler* handler = (MacAccessibleHandler*) refcon;
    
    AXUIElementRef uiElement = element;
    
	// Get the application title from the application AXUIElementRef (element)
	NSString* sendString = @"";
    NSString* name = @"";
    NSString* shortcut = nil;
    NSString* children;
    
	//AXUIElementCopyAttributeValue(element, kAXTitleAttribute, (CFTypeRef*) &name);
    NSString * cat, * ty, * comp;        
    cat  = [[NSString alloc] init];
    ty   = [[NSString alloc] init];
    comp = [[NSString alloc] init];
    //comp = nil;
    
    [thisClass getWinEventNames:(NSString*)notification : &cat : &ty : &comp];
    
    //NSLog((NSString*)notification);
    
    CFArrayRef selected = nil;
    CFIndex	count;
    
    // if more information is needed about the component, get the role attribute from the element
    if ( [comp isEqualToString:@"unknown"] )
    {
        AXUIElementCopyAttributeValue(uiElement, kAXRoleAttribute, (CFTypeRef*) &comp);
        if (comp)
            comp = [thisClass renameComponent:(NSString*)comp];
    }
    
    // Get the name of the element
    AXUIElementCopyAttributeValue(uiElement, kAXTitleAttribute, (CFTypeRef*) &name);
    
    if (!name)
    {
        AXUIElementCopyAttributeValue(uiElement, kAXValueAttribute, (CFTypeRef*) &name);
        if (!name)
        {
            AXUIElementCopyAttributeValue(uiElement, kAXDescriptionAttribute, (CFTypeRef*) &name);
        }
    }
    
    // Get the keyboard shortcut if it exists
    CFStringRef titleRef = nil;
    AXUIElementCopyAttributeValue(uiElement, kAXMenuItemCmdCharAttribute, (const void**)&titleRef);
    if (titleRef)
    {
        NSString* keys = @"";
        CFTypeRef theValue;
        AXUIElementCopyAttributeValue(uiElement, kAXMenuItemCmdModifiersAttribute, &theValue);
        if (theValue)
        {
            int mod = [[(id)theValue description] intValue];
            if (mod & 4) { keys = [keys stringByAppendingString:@"ctrl + "]; }
            if (mod & 2) { keys = [keys stringByAppendingString:@"alt + "]; }
            if (mod & 1) { keys = [keys stringByAppendingString:@"shift + "]; }
        }
        shortcut = @"";
        shortcut = [shortcut stringByAppendingFormat:@"cmd + %@%@", keys, (NSString*)titleRef];
    }
    
    // Get the children (in XML format) if they exist
    children = [thisClass descriptionOfChildren:uiElement :(NSString*)notification]; 
//    if ([(NSString*)notification isEqualToString:(NSString*)kAXSelectedChildrenChangedNotification] || [(NSString*)notification isEqualToString:(NSString*)kAXSelectedChildrenMovedNotification] || [(NSString*)notification isEqualToString:(NSString*)kAXSelectedCellsChangedNotification] || [(NSString*)notification isEqualToString:(NSString*)kAXSelectedRowsChangedNotification] || [(NSString*)notification isEqualToString:(NSString*)kAXSelectedColumnsChangedNotification])
//        children = [thisClass descriptionOfChildren:uiElement :true]; 
//    else
//        children = [thisClass descriptionOfChildren:uiElement :false]; 
    
    //NSLog((NSString*)notification);
    sendString = [thisClass CreateXML:cat : ty : comp : name : shortcut : children];
    
    //NSLog(sendString);
	[thisClass SendEventInfo: sendString];
    
    return;
}

// This function serves as the Keyboard Callback function that is called whenever a keyboard event 
// occurs on the system. It creates the xml according to the specification and passes it to Java.
CGEventRef KeyboardEventTapCallBack (CGEventTapProxy proxy, CGEventType type, CGEventRef event, void *refcon)
{
    // Get the active instance of the MacAccessibleHandler (the refcon parameter)
    MacAccessibleHandler* handler = (MacAccessibleHandler*) refcon;
    
    NSString *sendString, *ty, *key;
    
    if (type == kCGEventKeyDown) 
    {
        ty = @"KeyPress";
        CGKeyCode keycode = CGEventGetIntegerValueField(event, kCGKeyboardEventKeycode);
        key = [handler getKeyName:keycode];
    }
    else if (type == NSKeyUp)
    {
        ty = @"KeyRelease";
        CGKeyCode keycode = CGEventGetIntegerValueField(event, kCGKeyboardEventKeycode);
        key = [handler getKeyName:keycode];
    }
    else if (type == NSFlagsChanged)
    {
        
        CGKeyCode keycode = CGEventGetIntegerValueField(event, kCGKeyboardEventKeycode);
        key = [handler getKeyName:keycode];
        
        CGEventFlags flagsP;
        flagsP=CGEventGetFlags(event);
        
        if ([key isEqualToString:@"Function"])
        {
            if((flagsP & kCGEventFlagMaskSecondaryFn) == kCGEventFlagMaskSecondaryFn)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        else if ([key isEqualToString:@"Control"])
        {
            if((flagsP & kCGEventFlagMaskControl) == kCGEventFlagMaskControl)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        
        else if ([key isEqualToString:@"Shift"])
        {
            if((flagsP & kCGEventFlagMaskShift) == kCGEventFlagMaskShift)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        else if ([key isEqualToString:@"Alt"])
        {
            if((flagsP & kCGEventFlagMaskAlternate) == kCGEventFlagMaskAlternate)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        else if ([key isEqualToString:@"CapsLock"])
        {
            if((flagsP & kCGEventFlagMaskAlphaShift) == kCGEventFlagMaskAlphaShift)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        else if ([key isEqualToString:@"Command"])
        {
            if((flagsP & kCGEventFlagMaskCommand) == kCGEventFlagMaskCommand)
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
        else
        {
            if( ((flagsP & kCGEventFlagMaskNumericPad) == kCGEventFlagMaskNumericPad) || ((flagsP & kCGEventFlagMaskNonCoalesced) == kCGEventFlagMaskNonCoalesced) || ((flagsP & kCGEventFlagMaskHelp) == kCGEventFlagMaskHelp) )
                ty = @"KeyPress";
            else
                ty = @"KeyRelease";
        }
    }
    
    sendString = [handler CreateKeyboardXML:ty: key];
    sendString = [sendString stringByAppendingString:@"\n"];
    
    [handler SendEventInfo: sendString];
    
    return event;
}

// This function serves as the Mouse Callback function that is called whenever a mouse event 
// occurs on the system. It creates the xml according to the specification and passes it to Java.
CGEventRef MouseEventTapCallBack (CGEventTapProxy proxy, CGEventType type, CGEventRef event, void *refcon)
{    
    // Get the active instance of the MacAccessibleHandler (the refcon parameter)
    MacAccessibleHandler* handler = (MacAccessibleHandler*) refcon;
    
    NSPoint point = CGEventGetLocation(event);
    NSString* pos;
    pos = [NSString stringWithFormat:@"%d,%d",(int)point.x, (int)point.y];
    
    NSString *button, *ty;
    button = NULL;
    
    switch(type)
    {
        case (kCGEventMouseMoved):      { ty = @"Move"; break; }
		case (kCGEventLeftMouseDown):   { ty = @"Click"; button = @"Left"; break; }
		case (kCGEventLeftMouseUp):     { ty = @"Release"; button = @"Left"; break; }
		case (kCGEventRightMouseDown):  { ty = @"Click"; button = @"Right"; break; }
		case (kCGEventRightMouseUp):    { ty = @"Release"; button = @"Right"; break; }
		case (kCGEventOtherMouseDown):  { ty = @"Click"; button = @"X"; break; }
		case (kCGEventOtherMouseUp):    { ty = @"Release"; button = @"X"; break; }
		case (kCGEventScrollWheel):     { ty = @"Wheel"; pos = NULL; break; }    
    }
    
    NSString* sendString;
    sendString = [handler CreateMouseXML:ty: pos: button];
    sendString = [sendString stringByAppendingString:@"\n"];
    
    [handler SendEventInfo: sendString];
    
    return event;
}

// This function get the children of the event and puts them into xml format.    
- (NSString *)descriptionOfChildren:(AXUIElementRef)uiElement : (NSString*)notification {
    
    NSString *	theValueDescString	= NULL;
    CFTypeRef	theValue;
    CFIndex	count;
    CFArrayRef children = nil;
    
    AXUIElementRef winRef;
    CFStringRef titleRef,componentRef, shortcutRef;
    NSString *shortcut, *comp;
    
    NSString* childType;
    if ([notification isEqualToString:(NSString*)kAXSelectedChildrenChangedNotification] || [notification isEqualToString:(NSString*)kAXSelectedChildrenMovedNotification])
        childType = NSAccessibilitySelectedChildrenAttribute;
    else if ([notification isEqualToString:(NSString*)kAXSelectedRowsChangedNotification])
        childType = NSAccessibilitySelectedRowsAttribute;
    else if ([notification isEqualToString:(NSString*)kAXSelectedColumnsChangedNotification])
        childType = NSAccessibilitySelectedColumnsAttribute; // check options, there might be something to get the headers
    else if ([notification isEqualToString:(NSString*)kAXSelectedCellsChangedNotification])
        childType = NSAccessibilitySelectedCellsAttribute;
    else
        childType = NSAccessibilityChildrenAttribute;
    
    if (AXUIElementCopyAttributeValue(uiElement, (CFStringRef)NSAccessibilitySelectedRowsAttribute, &children) == kAXErrorSuccess && children)
    {
        count = CFArrayGetCount(children);
        //NSLog(@"children: %ld", count);
        if (count > 0)
        {
            theValueDescString = [@"" stringByAppendingFormat:@"%@%ld%@%@",CHILD_COUNT_TAG, count, CHILD_COUNT_TAG_END, CHILDREN_TAG];
            
            for (int i = 0; i <= count -1; i++)
            {
                winRef = (AXUIElementRef)CFArrayGetValueAtIndex(children, i);
                
                titleRef = NULL;
                AXUIElementCopyAttributeValue(winRef, kAXTitleAttribute, (const void**)&titleRef);
                
                componentRef = NULL;
                AXUIElementCopyAttributeValue(winRef, kAXRoleAttribute, (const void**)&componentRef);
                if (componentRef)
                    comp = [thisClass renameComponent:(NSString*)componentRef];
                else
                    comp = (NSString*)componentRef;
                
                if ([(NSString*)componentRef isEqualToString:kAXRowRole])
                {
                    CFArrayRef rowChildren = nil;
                    if (AXUIElementCopyAttributeValue(winRef, (CFStringRef)NSAccessibilityChildrenAttribute, &rowChildren) == kAXErrorSuccess && rowChildren)
                    {
                        AXUIElementRef rowRef = (AXUIElementRef)CFArrayGetValueAtIndex(rowChildren, 0);
                        AXUIElementCopyAttributeValue(rowRef, kAXValueAttribute, &titleRef);
                    }
                }
                
                shortcut = NULL;
                shortcutRef = NULL;
                AXUIElementCopyAttributeValue(winRef, kAXMenuItemCmdCharAttribute, (const void**)&shortcutRef);
                if (shortcutRef)
                {
                    NSString* keys = @"";
                    CFTypeRef theValue;
                    AXUIElementCopyAttributeValue(winRef, kAXMenuItemCmdModifiersAttribute, &theValue);
                    if (theValue)
                    {
                        int mod = [[(id)theValue description] intValue];
                        if (mod & 4) { keys = [keys stringByAppendingString:@"ctrl + "]; }
                        if (mod & 2) { keys = [keys stringByAppendingString:@"alt + "]; }
                        if (mod & 1) { keys = [keys stringByAppendingString:@"shift + "]; }
                    }
                    shortcut = [@"cmd + " stringByAppendingFormat:@"%@%@", keys, (NSString*)shortcutRef];
                }
                
                if (componentRef)
                    componentRef = [thisClass renameComponent:(NSString*)componentRef];
                
                theValueDescString = [theValueDescString stringByAppendingString:[thisClass CreateChildXML:(NSString*)titleRef :(NSString*)componentRef : shortcut]];
            }
            theValueDescString = [theValueDescString stringByAppendingString:CHILDREN_TAG_END];
        }
    }
    
    return theValueDescString;
}  

///////////////////
// XML Functions //
///////////////////

// This fuction creates a string of xml according to the specification.
- (NSString*) CreateXML:(NSString*)category :(NSString*)type :(NSString*)component :(NSString*)name  :(NSString*) shortcut : (NSString*) childXML
{    
    category =  [thisClass removeInvalidXMLCharacters:category];
    type =      [thisClass removeInvalidXMLCharacters:type];
    component = [thisClass removeInvalidXMLCharacters:component];
    name =      [thisClass removeInvalidXMLCharacters:name];
    shortcut =  [thisClass removeInvalidXMLCharacters:shortcut];
    NSString* xmlString;
    
    xmlString = [XML_HEADER stringByAppendingFormat:@"\n%@", ROOT_TAG];
    
    if (category)
        xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", CATEGORY_TAG, category, CATEGORY_TAG_END];
    
    if (type)
    {
        // Choose which type of event it is so that the proper type tag is added
        if ( [category isEqualToString:@"Focus"] ) // Focus Events
            xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", FOCUS_TYPE_TAG, type, FOCUS_TYPE_TAG_END];
        else if ( [category isEqualToString:@"Window"] ) // Window Events
            xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", WINDOW_TYPE_TAG, type, WINDOW_TYPE_TAG_END];
        else if ( [category isEqualToString:@"Notification"] ) // Notification Events
            xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", NOTIFICATION_TYPE_TAG, type, NOTIFICATION_TYPE_TAG_END];
        else if ( [category isEqualToString:@"Menu"] ) // Menu Events
            xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", MENU_TYPE_TAG, type, MENU_TYPE_TAG_END];
        else if ( [category isEqualToString:@"PropertyChange"] ) // Property Change Events
            xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", PROPERTY_CHANGE_TYPE_TAG, type, PROPERTY_CHANGE_TYPE_TAG_END];
    }
    
    if (component)
        xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", COMPONENT_TAG, component, COMPONENT_TAG_END];
    
    if (name)
        xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", READING_TAG, name, READING_TAG_END];
    
    if (shortcut)
        xmlString = [xmlString stringByAppendingFormat:@"%@%@%@", SHORTCUT_TAG, shortcut, SHORTCUT_TAG_END];
    
    if (childXML)
        xmlString = [xmlString stringByAppendingString:childXML];
    
    xmlString = [xmlString stringByAppendingString:ROOT_TAG_END];
    
    return xmlString;
}

// This function creates a string of xml according to the specification for keyboard events.
- (NSString*) CreateKeyboardXML: (NSString*) type : (NSString*) key
{
    type =  [thisClass removeInvalidXMLCharacters:type];
    key =   [thisClass removeInvalidXMLCharacters:key];
    NSString* xmlString;
    
    xmlString = XML_HEADER;
    xmlString = [xmlString stringByAppendingString:@"\n"];
    xmlString = [xmlString stringByAppendingString:ROOT_TAG];
    
    xmlString = [xmlString stringByAppendingString:CATEGORY_TAG];
    xmlString = [xmlString stringByAppendingString:@"Keyboard"];
    xmlString = [xmlString stringByAppendingString:CATEGORY_TAG_END];
    
    xmlString = [xmlString stringByAppendingString:KEYBOARD_TYPE_TAG];
    xmlString = [xmlString stringByAppendingString:type];
    xmlString = [xmlString stringByAppendingString:KEYBOARD_TYPE_TAG_END];
    
    xmlString = [xmlString stringByAppendingString:READING_TAG];
    xmlString = [xmlString stringByAppendingString:key];
    xmlString = [xmlString stringByAppendingString:READING_TAG_END];
    
    xmlString = [xmlString stringByAppendingString:ROOT_TAG_END];
    
    return xmlString;
}

// This fuction creates a string of xml according to the specification for mouse events.
- (NSString*) CreateMouseXML: (NSString*) type : (NSString*) position : (NSString*) button
{
    type =      [thisClass removeInvalidXMLCharacters:type];
    position =  [thisClass removeInvalidXMLCharacters:position];
    button =    [thisClass removeInvalidXMLCharacters:button];
    //assert((position != nil) && (type != nil) && (button != nil));
    NSString* xmlString;
    
    xmlString = XML_HEADER;
    xmlString = [xmlString stringByAppendingString:@"\n"];
    xmlString = [xmlString stringByAppendingString:ROOT_TAG];
    
    xmlString = [xmlString stringByAppendingString:CATEGORY_TAG];
    xmlString = [xmlString stringByAppendingString:@"Mouse"];
    xmlString = [xmlString stringByAppendingString:CATEGORY_TAG_END];
    
    xmlString = [xmlString stringByAppendingString:MOUSE_TYPE_TAG];
    xmlString = [xmlString stringByAppendingString:type];
    xmlString = [xmlString stringByAppendingString:MOUSE_TYPE_TAG_END];
    
    if (position != NULL)
    {
        xmlString = [xmlString stringByAppendingString:POSITION_TAG];
        xmlString = [xmlString stringByAppendingString:position];
        xmlString = [xmlString stringByAppendingString:POSITION_TAG_END];
    }
    
    if (button != NULL)
    {
        xmlString = [xmlString stringByAppendingString:MOUSE_BUTTON_TAG];
        xmlString = [xmlString stringByAppendingString:button];
        xmlString = [xmlString stringByAppendingString:MOUSE_BUTTON_TAG_END];
    }
    
    xmlString = [xmlString stringByAppendingString:ROOT_TAG_END];
    
    return xmlString;
}

// This function creates a string of xml according to the specification for a child element.
- (NSString*) CreateChildXML: (NSString*) name : (NSString*) component : (NSString*) shortcut
{
    name =      [thisClass removeInvalidXMLCharacters:name];
    component = [thisClass removeInvalidXMLCharacters:component];
    shortcut =  [thisClass removeInvalidXMLCharacters:shortcut];
    NSString* xmlString;
    
    xmlString = CHILD_TAG;
    
    xmlString = [xmlString stringByAppendingString: CHILD_NAME_TAG];
    if (name)
        xmlString = [xmlString stringByAppendingString:name];
    xmlString = [xmlString stringByAppendingString: CHILD_NAME_TAG_END];
    
    xmlString = xmlString = [xmlString stringByAppendingString: CHILD_COMPONENT_TAG];
    if (component)
        xmlString = [xmlString stringByAppendingString:component];
    xmlString = [xmlString stringByAppendingString: CHILD_COMPONENT_TAG_END];
    
    xmlString = xmlString = [xmlString stringByAppendingString: CHILD_SHORTCUT_TAG];
    if (shortcut)
        xmlString = [xmlString stringByAppendingString:shortcut];
    xmlString = [xmlString stringByAppendingString: CHILD_SHORTCUT_TAG_END];
    
    xmlString = [xmlString stringByAppendingString: CHILD_TAG_END];
    
    return xmlString;
}

// This function gets the names of the category, type, and component of the event to
// be used in the xml.
- (void) getWinEventNames: (NSString*) event : (NSString**) category : (NSString**) type : (NSString**) component
{    
    if ([event isEqualToString:(NSString*)kAXMainWindowChangedNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXFocusedWindowChangedNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXFocusedUIElementChangedNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"unknown";    }
    else if ([event isEqualToString: (NSString*)kAXApplicationActivatedNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"Window";   }
    //    else if ([event isEqualToString:(NSString*)kAXApplicationDeactivatedNotification])
    //    {   return NULL;    } //category = @"Focus"; type = @"Component"; component = @"Window"; }
    //    else if ([event isEqualToString:(NSString*)kAXApplicationHiddenNotification])
    //    {   return NULL;    } //category = @"Window"; type = @"HideComponent"; component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXApplicationShownNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXWindowCreatedNotification])
    {   *category = @"Window"; *type = @"CreateComponent"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXWindowMovedNotification])
    {   *category = @"Window"; *type = @"MoveOrResize"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXWindowResizedNotification])
    {   *category = @"Window"; *type = @"MoveOrResize"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXWindowMiniaturizedNotification])
    {   *category = @"Window"; *type = @"Minimize"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXWindowDeminiaturizedNotification])
    {   *category = @"Window"; *type = @"RestoreMinimized"; *component = @"Window";   }
    else if ([event isEqualToString:(NSString*)kAXDrawerCreatedNotification])
    {   *category = @"Window"; *type = @"CreateComponent"; *component = @"Drawer";   }
    else if ([event isEqualToString:(NSString*)kAXSheetCreatedNotification])
    {   *category = @"Window"; *type = @"CreateComponent"; *component = @"Sheet";   }
    else if ([event isEqualToString:(NSString*)kAXHelpTagCreatedNotification])
    {   *category = @"Window"; *type = @"CreateComponent"; *component = @"ToolTip";   } 
    else if ([event isEqualToString:(NSString*)kAXValueChangedNotification])
    {   *category = @"PropertyChange"; *type = @"Value"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXUIElementDestroyedNotification])
    {   *category = @"Window"; *type = @"DestroyComponent"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXSelectedChildrenChangedNotification])
    {   *category = @"PropertyChange"; *type = @"Selection"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXSelectedTextChangedNotification])
    {   *category = @"PropertyChange"; *type = @"TextSelection"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXRowCountChangedNotification])
    {   *category = @"PropertyChange"; *type = @"State"; *component = @"Table";   } // not sure about windows conv
    else if ([event isEqualToString:(NSString*)kAXSelectedColumnsChangedNotification])
    {   *category = @"PropertyChange"; *type = @"Selection"; *component = @"Table";   }
    else if ([event isEqualToString:(NSString*)kAXSelectedRowsChangedNotification])
    {   *category = @"PropertyChange"; *type = @"Selection"; *component = @"Table";   }
    else if ([event isEqualToString:(NSString*)kAXRowExpandedNotification])
    {   *category = @"'Focus"; *type = @"unknown"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXRowCollapsedNotification])
    {   *category = @"'Focus"; *type = @"unknown"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXSelectedCellsChangedNotification])
    {   *category = @"'PropertyChange"; *type = @"unknown"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXUnitsChangedNotification])
    {   *category = @"'Focus"; *type = @"unknown"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXSelectedChildrenMovedNotification])
    {   *category = @"'Focus"; *type = @"Selection"; *component = @"Table";   }
    else if ([event isEqualToString:(NSString*)kAXTitleChangedNotification])
    {   *category = @"'Focus"; *type = @"unknown"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXCreatedNotification])
    {   *category = @"Window"; *type = @"CreateComponent"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXMovedNotification])
    {   *category = @"Window"; *type = @"MoveOrResize"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXResizedNotification])
    {   *category = @"Window"; *type = @"MoveOrResize"; *component = @"unknown";   }
    else if ([event isEqualToString:(NSString*)kAXMenuClosedNotification])
    {   *category = @"Menu"; *type = @"MenuClose"; *component = @"Menu";   }
    else if ([event isEqualToString:(NSString*)kAXMenuItemSelectedNotification])
    {   *category = @"Focus"; *type = @"Component"; *component = @"MenuItem";   }
    else if ([event isEqualToString:(NSString*)kAXMenuOpenedNotification])
    {   *category = @"Menu"; *type = @"MenuOpen"; *component = @"Menu";   }
    else
    {   *category = @"unknown"; *type = @"unknown"; *component = @"unknown";    }
}

// This function renames the component value to match the xml specification.
- (NSString*) renameComponent:(NSString*)roleValue
{
    NSString* component = @"";
    
    if ([roleValue isEqualToString:NSAccessibilityApplicationRole] || [roleValue isEqualToString:NSAccessibilityWindowRole] || [roleValue isEqualToString:NSAccessibilityBrowserRole])
        component = @"Window";
    else if ([roleValue isEqualToString:NSAccessibilityButtonRole] || [roleValue isEqualToString:NSAccessibilityPopUpButtonRole] /*|| [roleValue isEqualToString:NSAccessibilitySortButtonRole]*/)
        component = @"Button";
    else if ([roleValue isEqualToString:NSAccessibilityCheckBoxRole])
        component = @"CheckBox";
    else if ([roleValue isEqualToString:NSAccessibilityImageRole])
        component = @"Picture";
    else if ([roleValue isEqualToString:NSAccessibilityListRole] || [roleValue isEqualToString:NSAccessibilityGroupRole] || [roleValue isEqualToString:NSAccessibilityRadioGroupRole] || [roleValue isEqualToString:NSAccessibilitySplitGroupRole] || [roleValue isEqualToString:NSAccessibilityTabGroupRole])
        component = @"List";
    else if ([roleValue isEqualToString:NSAccessibilityMenuBarRole] || [roleValue isEqualToString:NSAccessibilityMenuRole])
        component = @"Menu";
    else if ([roleValue isEqualToString:NSAccessibilityMenuItemRole])
        component = @"MenuItem";
    else if ([roleValue isEqualToString:NSAccessibilityRadioButtonRole]) // not sure what to name this
        component = @"RadioButton";
    else if ([roleValue isEqualToString:NSAccessibilityScrollBarRole])
        component = @"Scrollbar";
    else if ([roleValue isEqualToString:NSAccessibilitySliderRole] || [roleValue isEqualToString:NSAccessibilityIncrementorRole])
        component = @"Slider";
    else if ([roleValue isEqualToString:NSAccessibilityStaticTextRole])
        component = @"Label";
    else if ([roleValue isEqualToString:NSAccessibilityTableRole])
        component = @"Table";
    else if ([roleValue isEqualToString:NSAccessibilityTextAreaRole] || [roleValue isEqualToString:NSAccessibilityTextFieldRole] ||[roleValue isEqualToString:NSAccessibilityComboBoxRole])
        component = @"Text";
    else if ([roleValue isEqualToString:NSAccessibilityToolbarRole])
        component = @"Toolbar";    
    else if ([roleValue isEqualToString:NSAccessibilityBusyIndicatorRole] || [roleValue isEqualToString:NSAccessibilityLevelIndicatorRole] || [roleValue isEqualToString:NSAccessibilityProgressIndicatorRole] || [roleValue isEqualToString:NSAccessibilityRelevanceIndicatorRole] || [roleValue isEqualToString:NSAccessibilityValueIndicatorRole])
        component = @"Indicator";
    else if ([roleValue isEqualToString:NSAccessibilityHelpTagRole])
        component = @"ToolTip";
    
    // the following do not conform to XML specification
    else if ([roleValue isEqualToString:NSAccessibilityCellRole])
        component = @"cell";
    else if ([roleValue isEqualToString:NSAccessibilityColorWellRole])
        component = @"color well";
    else if ([roleValue isEqualToString:NSAccessibilityColumnRole])
        component = @"column";
    else if ([roleValue isEqualToString:NSAccessibilityDisclosureTriangleRole])
        component = @"disclosure triangle";
    else if ([roleValue isEqualToString:NSAccessibilityDrawerRole])
        component = @"drawer";
    else if ([roleValue isEqualToString:NSAccessibilityGridRole])
        component = @"grind";
    else if ([roleValue isEqualToString:NSAccessibilityGrowAreaRole])
        component = @"grow area";
    else if ([roleValue isEqualToString:NSAccessibilityHandleRole])
        component = @"handle";
    
    else if ([roleValue isEqualToString:NSAccessibilityLayoutAreaRole])
        component = @"layout area";
    else if ([roleValue isEqualToString:NSAccessibilityLayoutItemRole])
        component = @"layout item";
    else if ([roleValue isEqualToString:NSAccessibilityLinkRole])
        component = @"link";
    else if ([roleValue isEqualToString:NSAccessibilityMatteRole])
        component = @"matte";
    else if ([roleValue isEqualToString:NSAccessibilityMenuButtonRole])
        component = @"menu button";
    else if ([roleValue isEqualToString:NSAccessibilityOutlineRole])
        component = @"outline";
    else if ([roleValue isEqualToString:NSAccessibilityRowRole])
        component = @"row";
    else if ([roleValue isEqualToString:NSAccessibilityRulerMarkerRole])
        component = @"ruler marker";
    else if ([roleValue isEqualToString:NSAccessibilityRulerRole])
        component = @"ruler";
    else if ([roleValue isEqualToString:NSAccessibilityScrollAreaRole])
        component = @"scroll area";
    else if ([roleValue isEqualToString:NSAccessibilitySheetRole])
        component = @"sheet";
    else if ([roleValue isEqualToString:NSAccessibilitySplitterRole])
        component = @"splitter";    
    else if ([roleValue isEqualToString:NSAccessibilitySystemWideRole])
        component = @"system wide";
    else if ([roleValue isEqualToString:NSAccessibilityUnknownRole])
        component = @"unknown role";
    
    else
        component = nil;
    
    return component;
}

// This function gets the name of the key that created the event.
- (NSString*) getKeyName:(CGKeyCode)keycode
{
    NSString* keyPressed;
	switch(keycode)
	{
		case (kVK_Tab):             { keyPressed = @"Tab"; break; }
		case (kVK_Return):          { keyPressed = @"Return"; break; }
		case (kVK_CapsLock):        { keyPressed = @"CapsLock"; break; }
		case (kVK_Escape):          { keyPressed = @"Esc"; break; }
		case (kVK_Space):           { keyPressed = @"Space"; break; }
		case (kVK_PageUp):          { keyPressed = @"PageUp"; break; }
		case (kVK_PageDown):        { keyPressed = @"PageDown"; break; }
        case (kVK_ForwardDelete):	{ keyPressed = @"ForwardDelete"; break; }
		case (kVK_End):             { keyPressed = @"End"; break; }
		case (kVK_Home):			{ keyPressed = @"Home"; break; }
		case (kVK_LeftArrow):       { keyPressed = @"Left"; break; }
		case (kVK_UpArrow):			{ keyPressed = @"Up"; break; }
		case (kVK_RightArrow):		{ keyPressed = @"Right"; break; }
		case (kVK_DownArrow):		{ keyPressed = @"Down"; break; }
		case (kVK_Delete):          { keyPressed = @"Delete"; break; }
		case (kVK_Help):			{ keyPressed = @"Help"; break; }
        case (kVK_Command):         { keyPressed = @"Command"; break; }
        case (kVK_Shift):           { keyPressed = @"Shift"; break; }
        case (kVK_Option):          { keyPressed = @"Alt"; break; }
        case (kVK_Control):         { keyPressed = @"Control"; break; }
        case (kVK_RightShift):      { keyPressed = @"Shift"; break; }
        case (kVK_RightOption):     { keyPressed = @"Alt"; break; }
        case (kVK_RightControl):    { keyPressed = @"Control"; break; }
        case (kVK_Function):        { keyPressed = @"Function"; break; }
		case (kVK_ANSI_0): { keyPressed = @"0"; break; }
		case (kVK_ANSI_1): { keyPressed = @"1"; break; }
		case (kVK_ANSI_2): { keyPressed = @"2"; break; }
		case (kVK_ANSI_3): { keyPressed = @"3"; break; }
		case (kVK_ANSI_4): { keyPressed = @"4"; break; }
		case (kVK_ANSI_5): { keyPressed = @"5"; break; }
		case (kVK_ANSI_6): { keyPressed = @"6"; break; }
		case (kVK_ANSI_7): { keyPressed = @"7"; break; }
		case (kVK_ANSI_8): { keyPressed = @"8"; break; }
		case (kVK_ANSI_9): { keyPressed = @"9"; break; }
		case (kVK_ANSI_A): { keyPressed = @"A"; break; }
		case (kVK_ANSI_B): { keyPressed = @"B"; break; }
		case (kVK_ANSI_C): { keyPressed = @"C"; break; }
		case (kVK_ANSI_D): { keyPressed = @"D"; break; }
		case (kVK_ANSI_E): { keyPressed = @"E"; break; }
		case (kVK_ANSI_F): { keyPressed = @"F"; break; }
		case (kVK_ANSI_G): { keyPressed = @"G"; break; }
		case (kVK_ANSI_H): { keyPressed = @"H"; break; }
		case (kVK_ANSI_I): { keyPressed = @"I"; break; }
		case (kVK_ANSI_J): { keyPressed = @"J"; break; }
		case (kVK_ANSI_K): { keyPressed = @"K"; break; }
		case (kVK_ANSI_L): { keyPressed = @"L"; break; }
		case (kVK_ANSI_M): { keyPressed = @"M"; break; }
		case (kVK_ANSI_N): { keyPressed = @"N"; break; }
		case (kVK_ANSI_O): { keyPressed = @"O"; break; }
		case (kVK_ANSI_P): { keyPressed = @"P"; break; }
		case (kVK_ANSI_Q): { keyPressed = @"Q"; break; }
		case (kVK_ANSI_R): { keyPressed = @"R"; break; }
		case (kVK_ANSI_S): { keyPressed = @"S"; break; }
		case (kVK_ANSI_T): { keyPressed = @"T"; break; }
		case (kVK_ANSI_U): { keyPressed = @"U"; break; }
		case (kVK_ANSI_V): { keyPressed = @"V"; break; }
		case (kVK_ANSI_W): { keyPressed = @"W"; break; }
		case (kVK_ANSI_X): { keyPressed = @"X"; break; }
		case (kVK_ANSI_Y): { keyPressed = @"Y"; break; }
		case (kVK_ANSI_Z): { keyPressed = @"Z"; break; }
		case (kVK_ANSI_Keypad0):		{ keyPressed = @"NumPad0"; break; }
		case (kVK_ANSI_Keypad1):		{ keyPressed = @"NumPad1"; break; } 
		case (kVK_ANSI_Keypad2):		{ keyPressed = @"NumPad2"; break; }
		case (kVK_ANSI_Keypad3):		{ keyPressed = @"NumPad3"; break; }
		case (kVK_ANSI_Keypad4):		{ keyPressed = @"NumPad4"; break; }
		case (kVK_ANSI_Keypad5):		{ keyPressed = @"NumPad5"; break; } 
		case (kVK_ANSI_Keypad6):		{ keyPressed = @"NumPad6"; break; }
		case (kVK_ANSI_Keypad7):		{ keyPressed = @"NumPad7"; break; }
		case (kVK_ANSI_Keypad8):		{ keyPressed = @"NumPad8"; break; }
		case (kVK_ANSI_Keypad9):		{ keyPressed = @"NumPad9"; break; }
		case (kVK_ANSI_KeypadMultiply):	{ keyPressed = @"Multiply"; break; }
		case (kVK_ANSI_KeypadPlus):		{ keyPressed = @"Add"; break; } 
        case (kVK_ANSI_KeypadEnter):	{ keyPressed = @"Enter"; break; }
		case (kVK_ANSI_KeypadMinus):	{ keyPressed = @"Subtract"; break; }
		case (kVK_ANSI_KeypadDecimal):	{ keyPressed = @"Decimal"; break; }
		case (kVK_ANSI_KeypadDivide):	{ keyPressed = @"Divide"; break; }
        case (kVK_ANSI_KeypadEquals):	{ keyPressed = @"Equal"; break; }
        case (kVK_ANSI_KeypadClear):	{ keyPressed = @"Clear"; break; }
		case (kVK_F1):	{ keyPressed = @"F1"; break; }
		case (kVK_F2):	{ keyPressed = @"F2"; break; } 
		case (kVK_F3):	{ keyPressed = @"F3"; break; }
		case (kVK_F4):	{ keyPressed = @"F4"; break; }
		case (kVK_F5):	{ keyPressed = @"F5"; break; }
		case (kVK_F6):	{ keyPressed = @"F6"; break; } 
		case (kVK_F7):	{ keyPressed = @"F7"; break; }
		case (kVK_F8):	{ keyPressed = @"F8"; break; }
		case (kVK_F9):	{ keyPressed = @"F9"; break; }
		case (kVK_F10):	{ keyPressed = @"F10"; break; }
		case (kVK_F11):	{ keyPressed = @"F11"; break; }
		case (kVK_F12):	{ keyPressed = @"F12"; break; } 
		case (kVK_F13):	{ keyPressed = @"F13"; break; }
		case (kVK_F14):	{ keyPressed = @"F14"; break; }
		case (kVK_F15):	{ keyPressed = @"F15"; break; }
		case (kVK_F16):	{ keyPressed = @"F16"; break; } 
		case (kVK_F17):	{ keyPressed = @"F17"; break; }
		case (kVK_F18):	{ keyPressed = @"F18"; break; }
		case (kVK_F19):	{ keyPressed = @"F19"; break; }
		case (kVK_F20):	{ keyPressed = @"F20"; break; }
            // multimedia keyboard keys 
		case (kVK_Mute):                { keyPressed = @"VolumeMute"; break; }
		case (kVK_VolumeDown):			{ keyPressed = @"VolumeDown"; break; }
		case (kVK_VolumeUp):			{ keyPressed = @"VolumeUp"; break; }
            // symbol keys
		case (kVK_ANSI_Equal):          { keyPressed = @"=+"; break; }
		case (kVK_ANSI_Comma):          { keyPressed = @",<"; break; }
		case (kVK_ANSI_Minus):          { keyPressed = @"-_"; break; }
		case (kVK_ANSI_Period):         { keyPressed = @".>"; break; }
		case (kVK_ANSI_RightBracket):   { keyPressed = @"[{"; break; }
		case (kVK_ANSI_LeftBracket):    { keyPressed = @"]}"; break; }
		case (kVK_ANSI_Quote):          { keyPressed = @"'\""; break; }
		case (kVK_ANSI_Semicolon):		{ keyPressed = @";:"; break; }
		case (kVK_ANSI_Backslash):		{ keyPressed = @"\\|"; break; }
		case (kVK_ANSI_Slash):          { keyPressed = @"/?"; break; }
		case (kVK_ANSI_Grave):          { keyPressed = @"`~"; break; }
        default:                        { keyPressed = @"Command"; break; } // right command is not mapped to any value
	} // end switch
    
    return keyPressed;
}

// This function removes all of the invalid characters in xml, these characters are <>"'&.
- (NSString*) removeInvalidXMLCharacters: (NSString*) xmlValue
{
    NSString* newXML = xmlValue;
    
    newXML = [newXML stringByReplacingOccurrencesOfString:@"&" withString:@"&amp;"];
    newXML = [newXML stringByReplacingOccurrencesOfString:@"<" withString:@"&lt;"];
    newXML = [newXML stringByReplacingOccurrencesOfString:@">" withString:@"&gt;"];
    newXML = [newXML stringByReplacingOccurrencesOfString:@"\"" withString:@"&quot;"];
    newXML = [newXML stringByReplacingOccurrencesOfString:@"\'" withString:@"&apos;"];
    
    return newXML;
}

@end

