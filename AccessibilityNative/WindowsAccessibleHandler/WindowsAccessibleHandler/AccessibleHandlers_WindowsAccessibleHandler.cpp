// This file contains all of the Windows-specific code that registers the running application for
// Windows accessibility events and passes the appropriate information back to Java using JNI.
//
// References information for Windows accessibility programming: 
//    * http://msdn.microsoft.com/en-us/library/windows/desktop/dd317990(v=vs.85).aspx

#pragma once

#include "AccessibleHandlers_WindowsAccessibleHandler.h"
#include "UIAutomationHandler.h"
#include <comdef.h>
#include <Windows.h>
#include <OleAcc.h>
#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <oleauto.h>
#include <UIAutomationClient.h>
#include <atlstr.h>

using namespace std;

///////////////////////////
// Function Declarations //
///////////////////////////

DWORD WINAPI MessageLoop( LPVOID lpParam );
void SetEventHooks(vector<DWORD> eventList);
vector<DWORD> CreateEventList();

// Callback functions
void CALLBACK WinEventCallback(HWINEVENTHOOK hook, DWORD eventID, HWND hwnd, LONG idObject, LONG idChild, DWORD dwEventThread, DWORD dwmsEventTime);
LRESULT CALLBACK KeyboardEventCallback(int id, WPARAM wparam, LPARAM lparam);
LRESULT CALLBACK MouseEventCallback(int id, WPARAM wparam, LPARAM lparam);

// XML functions
string createKeyboardXML(DWORD eventID, string key);
string createMouseXML(DWORD eventID, string name, string position, string button);
string createXML(DWORD eventID, string category, string type, string component, string name, string shortcut, long numChildren, string childInfo);
string createChildXML(string childName, string childRole, string childShortcut);
string getKeyPressedName(int code);
void getMouseEventNames(int code, string &button, string &type);
void getWinEventNames(int code, string &category, string &type);
string getComponentName(VARIANT role);

////////////////////////////////////
// Global Constants and Variables //
////////////////////////////////////

// XML constants
const string XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" ;
const string ROOT_TAG = "AccessibleEvent";
const string CATEGORY_TAG = "Category";
const string FOCUS_TYPE_TAG = "FocusType";
const string KEYBOARD_TYPE_TAG = "KeyboardType";
const string MOUSE_TYPE_TAG = "MouseType";
const string WINDOW_TYPE_TAG = "WindowType";
const string NOTIFICATION_TYPE_TAG = "NotificationType";
const string MENU_TYPE_TAG = "MenuType";
const string PROPERTY_CHANGE_TYPE_TAG = "PropertyChangeType";
const string COMPONENT_TAG = "Component";
const string READING_TAG = "Reading";
const string POSITION_TAG = "Position";
const string MOUSE_BUTTON_TAG = "MouseButton";
const string SHORTCUT_TAG = "Shortcut";
const string CHILD_COUNT_TAG = "ChildCount";
const string CHILDREN_TAG = "Children";
const string CHILD_TAG = "Child";
const string CHILD_NAME_TAG = "ChildName";
const string CHILD_COMPONENT_TAG = "ChildComponent";
const string CHILD_SHORTCUT_TAG = "ChildShortcut";

// Global Variables
vector<HWINEVENTHOOK> winEventHookIDs;
HHOOK keyEventHookID;
HHOOK mouseHookID;
HANDLE messageLoopThread;
JavaVM *jvm = 0;
JNIEnv *jniEnv = 0;
jobject jniObject;
jmethodID methodID;
int lastKey = NULL;
UIAHandler *uia = NULL;
int zero = 0; // simplest way I have found to get rid of extra symbols at the end of xml string.

//////////////////////////
// Function Definitions //
//////////////////////////

// This function starts the message loop thread that listens for Windows accessibility events
// and sends the appropriate information back to Java that will then be converted to speech.
// It implements the Initialize function defined in the WindowsAccessibleHandler.java
// file.
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Accessibility_WindowsAccessibleHandler_Initialize
  (JNIEnv *env, jobject jobj)
{
	// Initialize COM to get the text values from Accessibility objects.
	CoInitialize(NULL);
	
	// Save the current running Java Virtual Machine (jvm; used to call back to Java) and
	// the WindowsAccessibleHandler instance that called this method (jobj; used to get the
	// callback function that is called when an event is received).
	env->GetJavaVM(&jvm);
	jniObject = env->NewGlobalRef(jobj);

	// Start the message loop thread using the MessageLoop function.
	messageLoopThread = CreateThread(NULL, 0, MessageLoop, 0, 0, 0);
	if (messageLoopThread == NULL)
	{
		cout << "Error creating message loop thread." << endl;
	}

	// Create a UIA event handler.
	uia = new UIAHandler();
	if (uia == NULL)
	{
		cout << "Error creating UIA Handler." << endl;
	}	
}

// This function stops the message loop thread and unhooks all of the Windows events
JNIEXPORT void JNICALL Java_plugins_quorum_Libraries_Accessibility_WindowsAccessibleHandler_Terminate
  (JNIEnv *env, jobject jobj)
{
	// Stop the message loop thread
	TerminateThread(messageLoopThread, 0);
	CloseHandle(messageLoopThread);

	// Unhook the keyboard and mouse event hook.
	UnhookWindowsHookEx(keyEventHookID);
	UnhookWindowsHookEx(mouseHookID);

	// Unhook all of the windows event hooks.
	for each (HWINEVENTHOOK handle in winEventHookIDs)
	{
		UnhookWinEvent(handle);
	}
	winEventHookIDs.clear();

	if (uia != NULL)
	{
		uia ->StopEventHandler();
		uia->Release();
		uia = NULL;
	}

	//Unintialize COM
	CoUninitialize();

	// Delete the reference to the Java WindowsAccessibleHandler object.
	env->DeleteGlobalRef(jniObject);
}

// This function serves as a message loop that listens for Windows accessibility events
// and sends the appropriate information back to Java using a callback function.
DWORD WINAPI MessageLoop( LPVOID lpParam )
{
	// Attach the new current thread to the Java Virtual Machine that entered this JNI code.
	// Attaching is necessary as it allows this thread to call Java functions.
	jvm->AttachCurrentThread((void**) &jniEnv, NULL);

	// Get the Java method ID for the ReceiveEvent function from the Java AccessibilityHandler class.
	// This method will serve as the callback function with the Windows event information
	jclass className = jniEnv->GetObjectClass(jniObject);
	methodID = jniEnv->GetMethodID(className, "ReceiveEvent", "(Ljava/lang/String;)V");

	uia->SetJavaInteraction(jniObject, methodID,jniEnv, messageLoopThread); 

	// Set the Windows event hooks that allow this application to listen for Windows events.
	SetEventHooks(CreateEventList());

	// Listen for messages
	MSG msg;

	while(GetMessageA(&msg, NULL, 0, 0))
	{ 
		TranslateMessage(&msg); 
		DispatchMessage(&msg); 
	}
	return 0;
}

// This function sets a Windows event hook for each
// event constant stored in the eventList vector.
void SetEventHooks(vector<DWORD> eventList)
{
	// Set the keyboard event hook using the KeyboardEventCallback as the callback function.
	keyEventHookID = SetWindowsHookEx(WH_KEYBOARD_LL, (HOOKPROC) KeyboardEventCallback, NULL, 0);
	if (keyEventHookID == NULL)
		cout << "Initialize: could not register callback for event: keyboardHook" << endl;

	// Set the mouse event hook using the MouseEventCallback as the callback function.
	mouseHookID = SetWindowsHookEx(WH_MOUSE_LL, (HOOKPROC) MouseEventCallback, NULL, 0);
	if (mouseHookID == NULL)
		cout << "Initialize: could not register callback for event: mouseHook" << endl;

	for (int i = 0; i < eventList.size(); i++)
	{
		// Set the Windows event hook for the event constant using the WinEventCallBack function as the callback function.
		HWINEVENTHOOK hookID = SetWinEventHook(eventList.at(i), eventList.at(i), NULL, WinEventCallback, 0, 0, WINEVENT_OUTOFCONTEXT);
	
		if (hookID != 0)
			winEventHookIDs.push_back(hookID);
		else
			cout << "Initialize: could not register callback for event: " << eventList[i] << endl;
	}

	
}

// This function sets a Windows event hook for each 
// event constant stored in the eventList vector.
vector<DWORD> CreateEventList()
{
	vector<DWORD> eventList;

	eventList.push_back(EVENT_SYSTEM_SOUND);
	eventList.push_back(EVENT_SYSTEM_ALERT);
	eventList.push_back(EVENT_SYSTEM_FOREGROUND); // works as should
	eventList.push_back(EVENT_SYSTEM_MENUSTART); // works as should // will be followed by MENUPOPUPSTART
	eventList.push_back(EVENT_SYSTEM_MENUEND);
	eventList.push_back(EVENT_SYSTEM_MENUPOPUPSTART);
	eventList.push_back(EVENT_SYSTEM_MENUPOPUPEND);
	eventList.push_back(EVENT_SYSTEM_CAPTURESTART); // anytime you unclick
	eventList.push_back(EVENT_SYSTEM_CAPTUREEND); // anytime you start clicking
	eventList.push_back(EVENT_SYSTEM_MOVESIZESTART);
	eventList.push_back(EVENT_SYSTEM_MOVESIZEEND);
	eventList.push_back(EVENT_SYSTEM_CONTEXTHELPSTART);
	eventList.push_back(EVENT_SYSTEM_CONTEXTHELPEND);
	eventList.push_back(EVENT_SYSTEM_DRAGDROPSTART);
	eventList.push_back(EVENT_SYSTEM_DRAGDROPEND);
	eventList.push_back(EVENT_SYSTEM_DIALOGSTART);
	eventList.push_back(EVENT_SYSTEM_DIALOGEND);
	eventList.push_back(EVENT_SYSTEM_SCROLLINGSTART);
	eventList.push_back(EVENT_SYSTEM_SCROLLINGEND);
	eventList.push_back(EVENT_SYSTEM_SWITCHSTART);
	eventList.push_back(EVENT_SYSTEM_SWITCHEND);
	eventList.push_back(EVENT_SYSTEM_MINIMIZESTART);
	eventList.push_back(EVENT_SYSTEM_MINIMIZEEND);	
	eventList.push_back(EVENT_SYSTEM_DESKTOPSWITCH);
	eventList.push_back(EVENT_SYSTEM_END);
	eventList.push_back(EVENT_SYSTEM_ARRANGMENTPREVIEW); // NOT SURE: creates a lot of events at once?

	//eventList.push_back(EVENT_CONSOLE_CARET);
	//eventList.push_back(EVENT_CONSOLE_UPDATE_REGION);
	//eventList.push_back(EVENT_CONSOLE_UPDATE_SIMPLE);
	//eventList.push_back(EVENT_CONSOLE_UPDATE_SCROLL);
	//eventList.push_back(EVENT_CONSOLE_LAYOUT);
	//eventList.push_back(EVENT_CONSOLE_START_APPLICATION);
	//eventList.push_back(EVENT_CONSOLE_END_APPLICATION);

	//eventList.push_back(EVENT_OBJECT_CREATE);
	//eventList.push_back(EVENT_OBJECT_DESTROY);
	//eventList.push_back(EVENT_OBJECT_SHOW); // this seems to trigger 2 events every second with UIAHandler
	//eventList.push_back(EVENT_OBJECT_HIDE);
	//eventList.push_back(EVENT_OBJECT_REORDER);
	eventList.push_back(EVENT_OBJECT_FOCUS);
	eventList.push_back(EVENT_OBJECT_SELECTION);
	eventList.push_back(EVENT_OBJECT_SELECTIONADD);
	eventList.push_back(EVENT_OBJECT_SELECTIONREMOVE);
	eventList.push_back(EVENT_OBJECT_SELECTIONWITHIN);
	eventList.push_back(EVENT_OBJECT_STATECHANGE);
	eventList.push_back(EVENT_OBJECT_LOCATIONCHANGE); // whenever the mouse moves // always triggered after mouse move event
	eventList.push_back(EVENT_OBJECT_NAMECHANGE); // whenever the mouse changes
	eventList.push_back(EVENT_OBJECT_DESCRIPTIONCHANGE);
	eventList.push_back(EVENT_OBJECT_VALUECHANGE);// whenever the time changes
	eventList.push_back(EVENT_OBJECT_PARENTCHANGE);
	eventList.push_back(EVENT_OBJECT_HELPCHANGE);
	eventList.push_back(EVENT_OBJECT_DEFACTIONCHANGE);
	eventList.push_back(EVENT_OBJECT_ACCELERATORCHANGE);
	eventList.push_back(EVENT_OBJECT_INVOKED);
	eventList.push_back(EVENT_OBJECT_TEXTSELECTIONCHANGED);
	eventList.push_back(EVENT_OBJECT_CONTENTSCROLLED);
	eventList.push_back(EVENT_OBJECT_END);

	return eventList;
}

////////////////////////
// Callback Functions //
////////////////////////

// This serves as the callback function that is called when a registered Windows event is triggered.
// It gets the name of the appropriate accessiblity object and passes that name to Java.
void CALLBACK WinEventCallback(HWINEVENTHOOK hook, DWORD eventID, HWND window, LONG idObject, LONG idChild, DWORD dwEventThread, DWORD dwmsEventTime)
{
	// Gets the accessiblity object that is the main object for which the event was called.
	IAccessible* pAccParent = NULL;
	VARIANT varChild, varRole;
	HRESULT hr = AccessibleObjectFromEvent(window, idObject, idChild, &pAccParent, &varChild);

	// Initialize the UIA listener for a new window when it becomes the foreground window.
	if (eventID == EVENT_SYSTEM_FOREGROUND)
	{
		if (uia != NULL)
		{
			if (uia->checkStatusOfHandler() == 1)
				uia->StopEventHandler();
			uia->StartEventHandler(window);	
		}
	}

	// If the object exists
	if ((hr == S_OK) && (pAccParent != NULL))
	{
		// Get the Accessibility object's name and check if it's valid
		BSTR nameCom;
		if (pAccParent->get_accName(varChild, &nameCom) == S_OK)
		{
			// Convert the COM string to a standard C string.
			_bstr_t nameComT(nameCom);
			char* name = new char[nameComT.length() + 1];
			strcpy(name, nameComT);

			// Getting the component type of the IAccessible object
			string comp;
			if (pAccParent->get_accRole(varChild, &varRole) == S_OK)
			{
				 comp = getComponentName(varRole);
			}

			string cat, ty;
			getWinEventNames(eventID, cat, ty);

			// Getting the keyboard shortcut of the IAccessible object
			char* shortcut = "";
			BSTR keyShortcut;
			if (pAccParent->get_accKeyboardShortcut(varChild, &keyShortcut) == S_OK)
			{
				_bstr_t keyShortcutT(keyShortcut);
				shortcut = new char[keyShortcutT.length() + 1];
				strcpy(shortcut, keyShortcutT);
			}

			// Getting the Children of the IAccessible object
				long nChild;
				unsigned long nFetched = 0;
				bool shouldContinue = true;
				IEnumVARIANT *pEnum = NULL;
				VARIANT cvarChild, cvarRole;
				VariantInit(&cvarChild);
				IAccessible *pAcc = NULL;
				IAccessible *pIAccChild = NULL;

				// initialize cvarChild
				cvarChild.vt = VT_I4;
				cvarChild.lVal = CHILDID_SELF;

				// initialize pEnum
				HRESULT hr = pAccParent->QueryInterface(IID_IEnumVARIANT, (PVOID*) &pEnum);
				if (pEnum)
					pEnum->Reset();

				string childInfo = "";
				if ( (pAccParent->get_accChildCount(&nChild) == S_OK) )
				{

					for (long index = 1; (index < nChild) && shouldContinue; index++)
					{
						VariantClear(&cvarChild);

						if (pEnum)
						{
							hr = pEnum->Next(1, &cvarChild, &nFetched);
							if (!SUCCEEDED(hr))
							{
								shouldContinue = false;
								break;
							}
						}
						else
						{
							cvarChild.vt = VT_I4;
							cvarChild.lVal = index;
						}

						// get IDispatch interface for the child
						IDispatch *pDisp = NULL;

						if (cvarChild.vt == VT_I4)
						{
							hr = pAccParent->get_accChild(cvarChild, &pDisp);
						}
						else if (cvarChild.vt == VT_DISPATCH)
						{
							pDisp = cvarChild.pdispVal;
						}

						// get IAccessible interface for the child
						if (pDisp)
						{
							hr = pDisp->QueryInterface(IID_IAccessible, (void**)&pAcc);
						}

						// get information about the child
						if (pAcc)
						{
							VariantInit(&cvarChild);
							cvarChild.vt = VT_I4;
							cvarChild.lVal = CHILDID_SELF;
							pIAccChild = pAcc;
						}
						else
						{
							pIAccChild = pAccParent;
							cvarChild = varChild;
						}

						///////////////////////////////////// Get info for children 
						string childName, childRole, childShortcut;

						// component type (role)
						if (pIAccChild->get_accRole(cvarChild, &cvarRole) == S_OK)
						{
							childRole = getComponentName(cvarRole);
						}

						// name
						BSTR cnameCom;	
						if (pIAccChild->get_accName(cvarChild, &cnameCom) == S_OK)
						{
							_bstr_t cnameComT(cnameCom);
							char* cname = new char[cnameComT.length() + 1];
							strcpy(cname, cnameComT);

							childName += cname;
						}

						// keyboard shortcut
						char* cshortcut = "";
						BSTR ckeyShortcut;
						if (pIAccChild->get_accKeyboardShortcut(cvarChild, &ckeyShortcut) == S_OK)
						{
							_bstr_t ckeyShortcutT(ckeyShortcut);
							cshortcut = new char[ckeyShortcutT.length() + 1];
							strcpy(cshortcut, ckeyShortcutT);

							childShortcut += cshortcut;
						}

						childInfo += createChildXML(childName, childRole, childShortcut);
						/////////////////////////////////////////
					}
				}

			// Create an xml of the event and sets it as a jvalue
			string xmlString = createXML(eventID, cat, ty, comp, name, shortcut, nChild, childInfo);
			xmlString += zero;
			char* xmlCharArray = new char[xmlString.size()];
			for ( int i = 0; i < xmlString.size(); i++)
				xmlCharArray[i] = xmlString[i];

			jvalue xml;
			xml.l = jniEnv->NewStringUTF(xmlCharArray);

			// Send to java
			jniEnv->CallVoidMethodA(jniObject, methodID, &xml/*&param*/);

			delete[] name;
		}
	}
}

// This fuction serves as the callback function for mouse events.
// It creates the xml for the event and passes that to java.
LRESULT CALLBACK MouseEventCallback(int eventID, WPARAM wparam, LPARAM lparam)
{
	if (eventID != HC_ACTION)
		return CallNextHookEx(0,eventID,wparam,lparam);

	MOUSEHOOKSTRUCT* mouse = (MOUSEHOOKSTRUCT*)lparam;	

	IAccessible* pAcc = NULL;
	VARIANT varChild;
	HRESULT hr = AccessibleObjectFromPoint(mouse->pt, &pAcc, &varChild);

	if ((hr == S_OK) && (pAcc != NULL))
	{
		string point;
		stringstream strstream;
		strstream << mouse->pt.x << "," << mouse->pt.y;
		strstream >> point;
			
		string but, ty;
		getMouseEventNames(wparam, but, ty);

		// Create an xml of the event and sets it as a jvalue
		string xmlString = createMouseXML(wparam, ty, point, but);
		xmlString += zero;
		char* xmlCharArray = new char[xmlString.size()];
		for ( int i = 0; i < xmlString.size(); i++)
			xmlCharArray[i] = xmlString[i];

		// Set the xml a jvalue and call the Java method using the jvalue as a parameter
		jvalue xml;
		xml.l = jniEnv->NewStringUTF(xmlCharArray);

		// Send to java
		jniEnv->CallVoidMethodA(jniObject, methodID,&xml);
	}

	return CallNextHookEx(0,eventID,wparam,lparam);
}

// This function serves as the callback function for keyboard events.
// It creates the xml for the event and passes that to java.
LRESULT CALLBACK KeyboardEventCallback(int eventID, WPARAM wparam, LPARAM lparam)
{
	if (eventID != HC_ACTION)
		return CallNextHookEx(0,eventID,wparam,lparam);

	string keyPressed = "";
	KBDLLHOOKSTRUCT*  keyboard = (KBDLLHOOKSTRUCT*)lparam;

	keyPressed = getKeyPressedName(keyboard->vkCode);

	// Create an xml of the event and sets it as a jvalue
	string xmlString = createKeyboardXML(wparam, keyPressed);
	xmlString += zero;
	char* xmlCharArray = new char[xmlString.size()];
	for ( int i = 0; i < xmlString.size(); i++)
		xmlCharArray[i] = xmlString[i];

	// Set the xml a jvalue and call the Java method using the jvalue as a parameter
	jvalue xml;
	xml.l = jniEnv->NewStringUTF(xmlCharArray);

	// Send to java
	jniEnv->CallVoidMethodA(jniObject, methodID, &xml);

	return CallNextHookEx(0, eventID, wparam, lparam);
}

///////////////////
// XML Functions //
///////////////////

// This creates a string of xml to describe a keyboard event.
// The xml is formated according to the Screen Reader XML Spec doccument.
string createKeyboardXML(DWORD eventID, string key)
{
	string xmlCode = "";
	xmlCode += (XML_HEADER + "\n");
	xmlCode += ("<" + ROOT_TAG + ">\n");

		xmlCode += ("\t<" + CATEGORY_TAG + ">" + "Keyboard" + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + KEYBOARD_TYPE_TAG + ">" + ((eventID == WM_KEYUP)? "KeyPress" : "KeyRelease") + "</" + KEYBOARD_TYPE_TAG + ">\n");
		xmlCode += ("\t<" + READING_TAG + ">" + key + "</" + READING_TAG + ">\n");

	xmlCode += ("</" + ROOT_TAG + ">\n");

	return xmlCode;
}

// This creates a string of xml to describe a mouse event.
// The xml is formated according to the Screen Reader XML Spec doccument.
string createMouseXML(DWORD eventID, string type, string position, string button)
{
	string xmlCode = "";
	xmlCode += (XML_HEADER + "\n");
	xmlCode += ("<" + ROOT_TAG + ">\n");

		xmlCode += ("\t<" + CATEGORY_TAG + "> " + "Mouse" + " </" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + MOUSE_TYPE_TAG + ">" + type + "</" + MOUSE_TYPE_TAG + ">\n");
		if ( type != "Wheel" ) 
		{ 
			xmlCode += ("\t<" + POSITION_TAG + ">" + position + "</" + POSITION_TAG + ">\n"); 
		}
		if ( ( type != "Wheel" ) && ( type != "Move" ) ) 
		{ 
			xmlCode += ("\t<" + MOUSE_BUTTON_TAG + ">" + button + "</" + MOUSE_BUTTON_TAG + ">\n"); 
		}

	xmlCode += ("</" + ROOT_TAG + ">\n");

	return xmlCode;
}

// This creates a string of xml to describe the event.
// The xml is formated according to the Screen Reader XML Spec doccument.
string createXML(DWORD eventID, string category, string type, string component, string name, string shortcut, long numChildren, string childInfo)
{
	string xmlCode = "";
	xmlCode += (XML_HEADER + "\n");
	xmlCode += ("<" + ROOT_TAG + ">\n");

	// Focus Events
	if ( category == "Focus" )
	{
		xmlCode += ("\t<" + CATEGORY_TAG + ">" + category + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + FOCUS_TYPE_TAG + ">" + type + "</" + FOCUS_TYPE_TAG + ">\n");
		if ( ( type != "Desktop" ) && (type != "MouseCaptureStart" ) && ( type != "MouseCaptureStop" ) && ( type != "SwitchWindow" ) )
		{
			xmlCode += ("\t<" + COMPONENT_TAG + ">" + component + "</" + COMPONENT_TAG + ">\n");
		}
		if ( type != "SwitchWindow" )
		{
			xmlCode += ("\t<" + READING_TAG + ">" + name + "</" + READING_TAG + ">\n");
		}
		
	}
	// Window Events
	else if ( category == "Window" )
	{
		xmlCode += ("\t<" + CATEGORY_TAG + ">" + category + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + WINDOW_TYPE_TAG + ">" + type + "</" + WINDOW_TYPE_TAG + ">\n");
		xmlCode += ("\t<" + COMPONENT_TAG + ">" + component + "</" + COMPONENT_TAG + ">\n");
		xmlCode += ("\t<" + READING_TAG + ">" + name + "</" + READING_TAG + ">\n");
	}
	// Notification Events
	else if ( category == "Notification" )
	{
		xmlCode += ("\t<" + CATEGORY_TAG + ">" + category + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + NOTIFICATION_TYPE_TAG + ">" + type + "</" + NOTIFICATION_TYPE_TAG + ">\n");
		if ( type != "Sound" )
		{
			xmlCode += ("\t<" + READING_TAG + ">" + name + "</" + READING_TAG + ">\n");
		}
	}
	// Menu Events
	else if ( category == "Menu" )
	{
		xmlCode += ("\t<" + CATEGORY_TAG + ">" + category + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + MENU_TYPE_TAG + ">" + type + "</" + MENU_TYPE_TAG + ">\n");
		if ( ( type != "PopUpMenuOpen" ) && ( type != "PopUpMenuClose" ) )
		{
			xmlCode += ("\t<" + READING_TAG + ">" + name + "</" + READING_TAG + ">\n");
		}
	}
	// Property Change Events
	else if ( category == "PropertyChange" )
	{
		xmlCode += ("\t<" + CATEGORY_TAG + ">" + category + "</" + CATEGORY_TAG + ">\n");
		xmlCode += ("\t<" + PROPERTY_CHANGE_TYPE_TAG + ">" + type + "</" + PROPERTY_CHANGE_TYPE_TAG + ">\n");
		xmlCode += ("\t<" + COMPONENT_TAG + ">" + component + "</" + COMPONENT_TAG + ">\n");
		xmlCode += ("\t<" + READING_TAG + ">" + name + "</" + READING_TAG + ">\n");
	}

	if (shortcut != "")
		xmlCode += ("\t<" + SHORTCUT_TAG + ">" + shortcut + "</" + SHORTCUT_TAG + ">\n");
	if (numChildren > 0)
	{
		string c;
		stringstream ss;
		ss << numChildren;
		ss >> c;

		xmlCode += ("\t<" + CHILD_COUNT_TAG + ">" + c + "</" + CHILD_COUNT_TAG + ">\n");
	}
	if ( (childInfo != "") && (numChildren > 0) )
		xmlCode += ("\t<" + CHILDREN_TAG + ">\n" + childInfo + "\t</" + CHILDREN_TAG + ">\n");

	xmlCode += ("</" + ROOT_TAG + ">\n");

	return xmlCode;
}

// This creates a string of xml to describe a single child of an event.
// This is to be added into the Children tag.
// The xml is formated according to the Screen Reader XML Spec doccument.
string createChildXML(string childName, string childRole, string childShortcut)
{
	string childXML = "";

	childXML += ("\t\t<" + CHILD_TAG + ">\n");
	childXML += ("\t\t\t<" + CHILD_NAME_TAG + ">" + childName + "</" + CHILD_NAME_TAG + ">\n");
	childXML += ("\t\t\t<" + CHILD_COMPONENT_TAG + ">" + childRole + "</" + CHILD_COMPONENT_TAG + ">\n");
	childXML += ("\t\t\t<" + CHILD_SHORTCUT_TAG + ">" + childShortcut + "</" + CHILD_SHORTCUT_TAG + ">\n");
	childXML += ("\t\t</" + CHILD_TAG + ">\n");

	return childXML;
}

// This gets the name of the keyboard event.
string getKeyPressedName(int code)
{
	string keyPressed = "";
	switch(code)
	{
		case (VK_LBUTTON):		{ keyPressed = "LButton"; break; }
		case (VK_RBUTTON):		{ keyPressed = "RButton"; break; }
		case (VK_CANCEL):		{ keyPressed = "Cancel"; break; }
		case (VK_MBUTTON):		{ keyPressed = "MButton"; break; }
		case (VK_XBUTTON1):		{ keyPressed = "XButton1"; break; }
		case (VK_XBUTTON2):		{ keyPressed = "XButton2"; break; }
		case (VK_BACK):			{ keyPressed = "Backspace"; break; }
		case (VK_TAB):			{ keyPressed = "Tab"; break; }
		case (VK_CLEAR):		{ keyPressed = "Clear"; break; }
		case (VK_RETURN):		{ keyPressed = "Return"; break; }
		//case (VK_SHIFT):		{ keyPressed = "SHIFT"; break; } // handled with LSHIFT and RSHIFT
		//case (VK_CONTROL):	{ keyPressed = "CONTROL"; break; } // handled with LCONTROL and RCONTROL
		case (VK_MENU):			{ keyPressed = "Menu"; break; }
		case (VK_PAUSE):		{ keyPressed = "Pause"; break; }
		case (VK_CAPITAL):		{ keyPressed = "CapsLock"; break; }
		//case (VK_KANA):  { keyPressed = "KANA"; break; }
		//case (VK_JUNJA): { keyPressed = "JUNJA"; break; }
		//case (VK_FINAL): { keyPressed = "FINAL"; break; }
		//case (VK_HANJA): { keyPressed = "HANJA"; break; }
		case (VK_ESCAPE):		{ keyPressed = "Esc"; break; }
		case (VK_CONVERT):		{ keyPressed = "Convert"; break; }
		case (VK_NONCONVERT):	{ keyPressed = "Nonconvert"; break; }
		case (VK_ACCEPT):		{ keyPressed = "Accept"; break; }
		case (VK_MODECHANGE):	{ keyPressed = "ModeChange"; break; }
		case (VK_SPACE):		{ keyPressed = "Space"; break; }
		case (VK_PRIOR):		{ keyPressed = "PageUp"; break; }
		case (VK_NEXT):			{ keyPressed = "PageDown"; break; }
		case (VK_END):			{ keyPressed = "End"; break; }
		case (VK_HOME):			{ keyPressed = "Home"; break; }
		case (VK_LEFT):			{ keyPressed = "Left"; break; }
		case (VK_UP):			{ keyPressed = "Up"; break; }
		case (VK_RIGHT):		{ keyPressed = "Right"; break; }
		case (VK_DOWN):			{ keyPressed = "Down"; break; }
		case (VK_SELECT):		{ keyPressed = "Select"; break; }
		case (VK_PRINT):		{ keyPressed = "Print"; break; }
		case (VK_EXECUTE):		{ keyPressed = "Execute"; break; }
		case (VK_SNAPSHOT):		{ keyPressed = "Snapshot"; break; }
		case (VK_INSERT):		{ keyPressed = "Insert"; break; }
		case (VK_DELETE):		{ keyPressed = "Delete"; break; }
		case (VK_HELP):			{ keyPressed = "Help"; break; }
		case ('0'): { keyPressed = "0"; break; }
		case ('1'): { keyPressed = "1"; break; }
		case ('2'): { keyPressed = "2"; break; }
		case ('3'): { keyPressed = "3"; break; }
		case ('4'): { keyPressed = "4"; break; }
		case ('5'): { keyPressed = "5"; break; }
		case ('6'): { keyPressed = "6"; break; }
		case ('7'): { keyPressed = "7"; break; }
		case ('8'): { keyPressed = "8"; break; }
		case ('9'): { keyPressed = "9"; break; }
		case ('A'): { keyPressed = "A"; break; }
		case ('B'): { keyPressed = "B"; break; }
		case ('C'): { keyPressed = "C"; break; }
		case ('D'): { keyPressed = "D"; break; }
		case ('E'): { keyPressed = "E"; break; }
		case ('F'): { keyPressed = "F"; break; }
		case ('G'): { keyPressed = "G"; break; }
		case ('H'): { keyPressed = "H"; break; }
		case ('I'): { keyPressed = "I"; break; }
		case ('J'): { keyPressed = "J"; break; }
		case ('K'): { keyPressed = "K"; break; }
		case ('L'): { keyPressed = "L"; break; }
		case ('M'): { keyPressed = "M"; break; }
		case ('N'): { keyPressed = "N"; break; }
		case ('O'): { keyPressed = "O"; break; }
		case ('P'): { keyPressed = "P"; break; }
		case ('Q'): { keyPressed = "Q"; break; }
		case ('R'): { keyPressed = "R"; break; }
		case ('S'): { keyPressed = "S"; break; }
		case ('T'): { keyPressed = "T"; break; }
		case ('U'): { keyPressed = "U"; break; }
		case ('V'): { keyPressed = "V"; break; }
		case ('W'): { keyPressed = "W"; break; }
		case ('X'): { keyPressed = "X"; break; }
		case ('Y'): { keyPressed = "Y"; break; }
		case ('Z'): { keyPressed = "Z"; break; }
		case (VK_LWIN):			{ keyPressed = "Win"; break; }
		case (VK_RWIN):			{ keyPressed = "Win"; break; }
		case (VK_APPS):			{ keyPressed = "Apps"; break; }
		case (VK_SLEEP):		{ keyPressed = "Sleep"; break; }
		case (VK_NUMPAD0):		{ keyPressed = "NumPad0"; break; }
		case (VK_NUMPAD1):		{ keyPressed = "NumPad1"; break; } 
		case (VK_NUMPAD2):		{ keyPressed = "NumPad2"; break; }
		case (VK_NUMPAD3):		{ keyPressed = "NumPad3"; break; }
		case (VK_NUMPAD4):		{ keyPressed = "NumPad4"; break; }
		case (VK_NUMPAD5):		{ keyPressed = "NumPad5"; break; } 
		case (VK_NUMPAD6):		{ keyPressed = "NumPad6"; break; }
		case (VK_NUMPAD7):		{ keyPressed = "NumPad7"; break; }
		case (VK_NUMPAD8):		{ keyPressed = "NumPad8"; break; }
		case (VK_NUMPAD9):		{ keyPressed = "NumPad9"; break; }
		case (VK_MULTIPLY):		{ keyPressed = "Multiply"; break; }
		case (VK_ADD):			{ keyPressed = "Add"; break; } 
		case (VK_SEPARATOR):	{ keyPressed = "Separator"; break; }
		case (VK_SUBTRACT):		{ keyPressed = "Subtract"; break; }
		case (VK_DECIMAL):		{ keyPressed = "Decimal"; break; }
		case (VK_DIVIDE):		{ keyPressed = "Divide"; break; }
		case (VK_F1):	{ keyPressed = "F1"; break; }
		case (VK_F2):	{ keyPressed = "F2"; break; } 
		case (VK_F3):	{ keyPressed = "F3"; break; }
		case (VK_F4):	{ keyPressed = "F4"; break; }
		case (VK_F5):	{ keyPressed = "F5"; break; }
		case (VK_F6):	{ keyPressed = "F6"; break; } 
		case (VK_F7):	{ keyPressed = "F7"; break; }
		case (VK_F8):	{ keyPressed = "F8"; break; }
		case (VK_F9):	{ keyPressed = "F9"; break; }
		case (VK_F10):	{ keyPressed = "F10"; break; }
		case (VK_F11):	{ keyPressed = "F11"; break; }
		case (VK_F12):	{ keyPressed = "F12"; break; } 
		case (VK_F13):	{ keyPressed = "F13"; break; }
		case (VK_F14):	{ keyPressed = "F14"; break; }
		case (VK_F15):	{ keyPressed = "F15"; break; }
		case (VK_F16):	{ keyPressed = "F16"; break; } 
		case (VK_F17):	{ keyPressed = "F17"; break; }
		case (VK_F18):	{ keyPressed = "F18"; break; }
		case (VK_F19):	{ keyPressed = "F19"; break; }
		case (VK_F20):	{ keyPressed = "F20"; break; }
		case (VK_F21):	{ keyPressed = "F21"; break; }
		case (VK_F22):	{ keyPressed = "F22"; break; }
		case (VK_F23):	{ keyPressed = "F23"; break; }
		case (VK_F24):	{ keyPressed = "F24"; break; }
		case (VK_NUMLOCK):			{ keyPressed = "NumLock"; break; }
		case (VK_SCROLL):			{ keyPressed = "ScrollLock"; break; }
		case (VK_OEM_NEC_EQUAL):	{ keyPressed = "Equal"; break; } // '=' key on numpad
		case (VK_OEM_CLEAR):		{ keyPressed = "Clear"; break; }
		// left and right shift and controls
		case (VK_LSHIFT):	{ keyPressed = "Shift"; break; }
		case (VK_RSHIFT):	{ keyPressed = "Shift"; break; }
		case (VK_LCONTROL):	{ keyPressed = "Control"; break; }
		case (VK_RCONTROL):	{ keyPressed = "Control"; break; }
		case (VK_LMENU):	{ keyPressed = "Alt"; break; }
		case (VK_RMENU):	{ keyPressed = "Alt"; break; }
		// multimedia keyboard keys
		case (VK_BROWSER_BACK):			{ keyPressed = "BrowserBack"; break; }
		case (VK_BROWSER_FORWARD):		{ keyPressed = "BrowserForward"; break; }
		case (VK_BROWSER_REFRESH):		{ keyPressed = "BrowserRefresh"; break; } 
		case (VK_BROWSER_STOP):			{ keyPressed = "BrowserStop"; break; }
		case (VK_BROWSER_SEARCH):		{ keyPressed = "BrowserSearch"; break; }
		case (VK_BROWSER_FAVORITES):	{ keyPressed = "BrowserFavorites"; break; }
		case (VK_BROWSER_HOME):			{ keyPressed = "BrowserHome"; break; } 
		case (VK_VOLUME_MUTE):			{ keyPressed = "VolumeMute"; break; }
		case (VK_VOLUME_DOWN):			{ keyPressed = "VolumeDown"; break; }
		case (VK_VOLUME_UP):			{ keyPressed = "VolumeUp"; break; }
		case (VK_MEDIA_NEXT_TRACK):		{ keyPressed = "MediaNextTrack"; break; }
		case (VK_MEDIA_PREV_TRACK):		{ keyPressed = "MediaPreviousTrack"; break; }
		case (VK_MEDIA_STOP):			{ keyPressed = "MediaStop"; break; }
		case (VK_MEDIA_PLAY_PAUSE):		{ keyPressed = "MediaPlayPause"; break; }
		case (VK_LAUNCH_MAIL):			{ keyPressed = "LaunchMail"; break; }
		case (VK_LAUNCH_MEDIA_SELECT):	{ keyPressed = "LaunchMediaSelect"; break; }
		case (VK_LAUNCH_APP1):			{ keyPressed = "LaunchApp1"; break; }
		case (VK_LAUNCH_APP2):			{ keyPressed = "LaunchApp2"; break; }
		// symbol keys
		case (VK_OEM_1):		{ keyPressed = "Misc1"; break; } //misc
		case (VK_OEM_PLUS):		{ keyPressed = "=+"; break; }
		case (VK_OEM_COMMA):	{ keyPressed = ",<"; break; }
		case (VK_OEM_MINUS):	{ keyPressed = "-_"; break; }
		case (VK_OEM_PERIOD):	{ keyPressed = ".>"; break; }
		case (VK_OEM_2):		{ keyPressed = "Misc2"; break; } // misc
		case (VK_OEM_3):		{ keyPressed = "Misc3"; break; } // misc
		case (VK_OEM_4):		{ keyPressed = "Misc4"; break; } // misc
		case (VK_OEM_5):		{ keyPressed = "Misc5"; break; } // misc
		case (VK_OEM_6):		{ keyPressed = "Misc6"; break; } // misc
		case (VK_OEM_7):		{ keyPressed = "Misc7"; break; } // misc
		case (VK_OEM_8):		{ keyPressed = "Misc8"; break; } // misc
		case (VK_OEM_102):		{ keyPressed = "Misc102"; break; } // misc
	} // end switch

	return keyPressed;
}

// This gets the name of the mouse event.	
void getMouseEventNames(int code, string &button, string &type)
{
	switch (code)
	{
		case (WM_MOUSEMOVE):     { type = "Move"; break; }
		case (WM_LBUTTONDOWN):   { type = "Click"; button = "Left"; break; }
		case (WM_LBUTTONUP):     { type = "Release"; button = "Left"; break; }
		case (WM_RBUTTONDOWN):   { type = "Click"; button = "Right"; break; }
		case (WM_RBUTTONUP):     { type = "Release"; button = "Right"; break; }
		case (WM_MBUTTONDOWN):   { type = "Click"; button = "Middle"; break; }
		case (WM_MBUTTONUP):     { type = "Release"; button = "Middle"; break; }
		case (WM_MOUSEWHEEL):    { type = "Wheel"; break; }
		case (WM_XBUTTONDOWN):   { type = "Click"; button = "X"; break; }
		case (WM_XBUTTONUP):     { type = "Release"; button = "X"; break; }
		case (WM_MOUSEHWHEEL):   { type = "Wheel"; break; }
		default:				 {type = "Error"; button = "Error"; break; }
	}
}

// This gets the name of the win event.
void getWinEventNames(int code, string &category, string &type)
{
	switch(code)
	{
		// Object Events
		case (EVENT_OBJECT_CREATE):					{ category = "Window"; type = "CreateComponent"; break; }
		case (EVENT_OBJECT_DESTROY):				{ category = "Window"; type = "DestroyComponent"; break; }
		case (EVENT_OBJECT_SHOW):					{ category = "Window"; type = "ShowComponent"; break; }
		case (EVENT_OBJECT_HIDE):					{ category = "Window"; type = "HideComponent"; break; }
		case (EVENT_OBJECT_REORDER):				{ category = "Window"; type = "ReorderComponents"; break; }
		case (EVENT_OBJECT_FOCUS):					{ category = "Focus"; type = "Component"; break; }
		case (EVENT_OBJECT_SELECTION):				{ category = "PropertyChange"; type = "Selection"; break; }//
		case (EVENT_OBJECT_SELECTIONADD):			{ category = "PropertyChange"; type = "Selection"; break; }
		case (EVENT_OBJECT_SELECTIONREMOVE):		{ category = "PropertyChange"; type = "Selection"; break; }
		case (EVENT_OBJECT_SELECTIONWITHIN):		{ category = "PropertyChange"; type = "Selection"; break; }
		case (EVENT_OBJECT_STATECHANGE):			{ category = "PropertyChange"; type = "State"; break; }
		case (EVENT_OBJECT_LOCATIONCHANGE):			{ category = "PropertyChange"; type = "Location"; break; }
		case (EVENT_OBJECT_NAMECHANGE):				{ category = "PropertyChange"; type = "Name"; break; }
		case (EVENT_OBJECT_DESCRIPTIONCHANGE):		{ category = "PropertyChange"; type = "Description"; break; }
		case (EVENT_OBJECT_VALUECHANGE):			{ category = "PropertyChange"; type = "Value"; break; }
		case (EVENT_OBJECT_PARENTCHANGE):			{ category = "PropertyChange"; type = "Parent"; break; }
		case (EVENT_OBJECT_HELPCHANGE):				{ category = "PropertyChange"; type = "Help"; break; }
		case (EVENT_OBJECT_DEFACTIONCHANGE):		{ category = "PropertyChange"; type = "DefaultAction"; break; }
		case (EVENT_OBJECT_ACCELERATORCHANGE):		{ category = "PropertyChange"; type = "KeyboardShortcut"; break; }//
		case (EVENT_OBJECT_INVOKED):				{ category = "Window"; type = "ElementTriggered"; break; }
		case (EVENT_OBJECT_TEXTSELECTIONCHANGED):	{ category = "PropertyChange"; type = "TextSelection"; break; }//
		case (EVENT_OBJECT_CONTENTSCROLLED):		{ category = "Window"; type = "Scroll"; break; }	//?	

		// System Events
		case (EVENT_SYSTEM_SOUND):				{ category = "Notification"; type = "Sound"; break; }
		case (EVENT_SYSTEM_ALERT):				{ category = "Notification"; type = "Alert"; break; }
		case (EVENT_SYSTEM_FOREGROUND):			{ category = "Focus"; type = "Component"; break; }
		case (EVENT_SYSTEM_MENUSTART):			{ category = "Menu"; type = "MenuOpen"; break; }
		case (EVENT_SYSTEM_MENUEND):			{ category = "Menu"; type = "MenuClose"; break; }
		case (EVENT_SYSTEM_MENUPOPUPSTART):		{ category = "Menu"; type = "PopUpMenuOpen"; break; }
		case (EVENT_SYSTEM_MENUPOPUPEND):		{ category = "Menu"; type = "PopUpMenuClose"; break; }
		case (EVENT_SYSTEM_CAPTURESTART):		{ category = "Focus"; type = "MouseCaptureStart"; break; }
		case (EVENT_SYSTEM_CAPTUREEND):			{ category = "Focus"; type = "MouseCaptureStop"; break; }
		case (EVENT_SYSTEM_MOVESIZESTART):		{ category = "Window"; type = "MoveOrResize"; break; }
		case (EVENT_SYSTEM_MOVESIZEEND):		{ category = "Window"; type = "MoveOrResize"; break; }
		case (EVENT_SYSTEM_CONTEXTHELPSTART):	{ category = "Window"; type = "HelpMode"; break; }
		case (EVENT_SYSTEM_CONTEXTHELPEND):		{ category = "Window"; type = "HelpMode"; break; }
		case (EVENT_SYSTEM_DRAGDROPSTART):		{ category = "Window"; type = "DragDropMode"; break; }
		case (EVENT_SYSTEM_DRAGDROPEND):		{ category = "Window"; type = "DragDropMode"; break; }
		case (EVENT_SYSTEM_DIALOGSTART):		{ category = "Notification"; type = "DialogBox"; break; }
		case (EVENT_SYSTEM_DIALOGEND):			{ category = "Notification"; type = "DialogBox"; break; }
		case (EVENT_SYSTEM_SCROLLINGSTART):		{ category = "Window"; type = "Scroll"; break; }
		case (EVENT_SYSTEM_SCROLLINGEND):		{ category = "Window"; type = "Scroll"; break;  }
		case (EVENT_SYSTEM_SWITCHSTART):		{ category = "Focus"; type = "SwitchWindow"; break; }
		case (EVENT_SYSTEM_SWITCHEND):			{ category = "Focus"; type = "SwitchWindow"; break; }
		case (EVENT_SYSTEM_MINIMIZESTART):		{ category = "Window"; type = "Minimize"; break; }
		case (EVENT_SYSTEM_MINIMIZEEND):		{ category = "Window"; type = "RestoreMinimized"; break; }
		case (EVENT_SYSTEM_DESKTOPSWITCH):		{ category = "Focus"; type = "Desktop"; break; }
		case (EVENT_SYSTEM_ARRANGMENTPREVIEW):	{ category = "Notification"; type = "PreviewBox"; break; }

	}
}

// This gets the name of the component from the variant that it is passed.
string getComponentName(VARIANT role)
{
	string component;

	switch(role.lVal)
	{
		case (ROLE_SYSTEM_CARET):		{ component = "Caret"; break; }
		case (ROLE_SYSTEM_CHECKBUTTON): { component = "CheckBox"; break; }
		case (ROLE_SYSTEM_CURSOR):		{ component = "Cursor"; break; }
		case (ROLE_SYSTEM_DIAL):		{ component = "Spinner"; break; }
		case (ROLE_SYSTEM_SPINBUTTON):	{ component = "Spinner"; break; }
		case (ROLE_SYSTEM_GRAPHIC):		{ component = "Picture"; break; }
		case (ROLE_SYSTEM_LIST):		{ component = "List"; break; }
		case (ROLE_SYSTEM_LISTITEM):	{ component = "ListItem"; break; }
		case (ROLE_SYSTEM_MENUBAR):		{ component = "Menu"; break; }
		case (ROLE_SYSTEM_MENUPOPUP):	{ component = "MenuList"; break; }
		case (ROLE_SYSTEM_MENUITEM):	{ component = "MenuItem"; break; }
		case (ROLE_SYSTEM_PAGETAB):		{ component = "Tab"; break; }
		case (ROLE_SYSTEM_PUSHBUTTON):	{ component = "Button"; break; }
		case (ROLE_SYSTEM_RADIOBUTTON):	{ component = "RadioButton"; break; }
		case (ROLE_SYSTEM_SLIDER):		{ component = "Slider"; break; }
		case (ROLE_SYSTEM_APPLICATION):	{ component = "Window"; break; }
		case (ROLE_SYSTEM_WINDOW):		{ component = "Window"; break; }
		case (ROLE_SYSTEM_TOOLBAR):		{ component = "Toolbar"; break; }
		case (ROLE_SYSTEM_STATICTEXT):	{ component = "Label"; break; }
		case (ROLE_SYSTEM_TITLEBAR):	{ component = "Title"; break; }
		case (ROLE_SYSTEM_TEXT):		{ component = "Text"; break; }
		case (ROLE_SYSTEM_SCROLLBAR):	{ component = "Scrollbar"; break; }
		case (ROLE_SYSTEM_TABLE):		{ component = "Table"; break; }
		case (ROLE_SYSTEM_CLOCK):		{ component = "Clock"; break; }
		
	}

	return component;
}

