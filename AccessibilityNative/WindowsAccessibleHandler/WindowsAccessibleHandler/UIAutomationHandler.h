#ifndef UIAUTOMATION_HANDLER
#define UIAUTOMATION_HANDLER

#include <comdef.h>
#include <Windows.h>
#include <OleAcc.h>
#include <iostream>
#include <vector>
#include <sstream>
#include <string>
#include <xstring>
#include <oleauto.h>
#include <UIAutomationClient.h>
#include <atlstr.h>
#include <jni.h>
#include <cassert>

const int CLOSE = (WM_USER + 100);
const int REGISTER = (WM_USER + 500);
const int UIA_EVENT = (WM_USER + 200);

using namespace std;

class UIAHandler : public IUIAutomationEventHandler, public IUIAutomationFocusChangedEventHandler, public IUIAutomationPropertyChangedEventHandler
{
public: 
	UIAHandler();
    ~UIAHandler();
	
	HRESULT StartEventHandler(HWND window);
	void StopEventHandler();
	HRESULT STDMETHODCALLTYPE HandleAutomationEvent(IUIAutomationElement *sender, EVENTID eventId);
	HRESULT STDMETHODCALLTYPE HandleFocusChangedEvent(IUIAutomationElement *sender);
	HRESULT STDMETHODCALLTYPE HandlePropertyChangedEvent(IUIAutomationElement *sender, PROPERTYID propertyId, VARIANT newValue);

	bool checkStatusOfHandler();
	void SetJavaInteraction(jobject jO, jmethodID mID, JNIEnv *jE, HANDLE mThread);

	// IUnknown methods (Required for IUIAutomationEventHandler)
    HRESULT STDMETHODCALLTYPE QueryInterface(REFIID riid, void** ppInterface);
    ULONG STDMETHODCALLTYPE AddRef(); 
    ULONG STDMETHODCALLTYPE Release();

private:
	HRESULT RegisterEventHandlers();
	static DWORD WINAPI UIAMessageLoop(LPVOID lparam);
	void CleanUp();
	void RemoveEventHandler();

	vector<DWORD> CreateUIAEventList();

	// private variables
	IUIAutomation* automation;
    IUIAutomationElement* element;
	LONG refCount;
	HANDLE backgroundThread;
	DWORD backgroundThreadID;
	HANDLE eventListenerReady;
    BOOL eventHandlerAdded;

	HANDLE messageThread;
	DWORD messageThreadID;

	HWND win;

	// Java private variables
	JNIEnv *jniEnv;
	jobject jniObject;
	jmethodID methodID;
};

#endif