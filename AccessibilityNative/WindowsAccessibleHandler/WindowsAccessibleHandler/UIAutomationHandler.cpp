#include "UIAutomationHandler.h"

//////////////////
// Constructors //
//////////////////

UIAHandler::UIAHandler()
{
	jniEnv = 0;
	eventHandlerAdded = false;
	automation = NULL;
	element = NULL;
}

// Deconstructor: closes the listener handle
UIAHandler::~UIAHandler()
{
	CleanUp();
}

//////////////////////
// Member Functions //
//////////////////////

void UIAHandler::SetJavaInteraction(jobject jO, jmethodID mID, JNIEnv *jE, HANDLE mThread)
{
	jniEnv = new JNIEnv;
	jniEnv = jE;
	jniObject = jO;
	methodID = mID;
}

// This function creates a thread for the UIA handler to run on.
HRESULT UIAHandler::StartEventHandler(HWND window)
{
	CoInitializeEx(NULL, COINIT_MULTITHREADED);

	win = window;

	HRESULT result = E_FAIL;

	if (backgroundThread == NULL)
		return E_FAIL;

	eventListenerReady = CreateEvent(NULL, true, false, NULL);

	if ( eventListenerReady != NULL )
	{
		backgroundThread = CreateThread(NULL, 0 , UIAMessageLoop , this , 0 , &backgroundThreadID);

		if (backgroundThread != NULL)
			result = WaitForSingleObject(eventListenerReady, 2000);

		if (result == S_OK)
			PostThreadMessage(backgroundThreadID, REGISTER, 0, 0);
	}

	return result;
}

// This function closes the thread running in the 
// background that the UIA handler is using.
void UIAHandler::StopEventHandler()
{
	if (backgroundThread != NULL)
	{
		PostThreadMessage(backgroundThreadID, CLOSE, 0, 0);
		WaitForSingleObject(backgroundThread, 2000);
	}

	CleanUp();

	CoUninitialize();
}

// This function makes the event listener ready to recieve events and 
// registers all of the event handlers for the passed UIAHandler (lparam)
DWORD WINAPI UIAHandler::UIAMessageLoop(LPVOID lparam)
{
	MSG message;
	PeekMessage(&message, NULL, WM_USER, WM_USER, PM_NOREMOVE);

	CoInitializeEx(NULL, COINIT_MULTITHREADED);

	UIAHandler *pThis = (UIAHandler*)lparam;

	PeekMessage(&message, NULL, WM_USER, WM_USER, PM_NOREMOVE);

	SetEvent(pThis->eventListenerReady);

	while(GetMessageA(&message, NULL, 0, 0) != 0)
	{
		if (message.message == CLOSE) 
		{
			break;
		}
		else if (message.message == REGISTER)
		{
			HRESULT hr = pThis->RegisterEventHandlers();
		}
		else
		{
			TranslateMessage(&message);
			DispatchMessage(&message);
		}
	}
	
	pThis->RemoveEventHandler();

	CoUninitialize();

	return 0;
}
  
// This function closes the handle for
// checking if the listener is ready.
void UIAHandler::CleanUp()
{
	if(eventListenerReady != NULL)
	{
		CloseHandle(eventListenerReady);
		eventListenerReady = NULL;
	}

	return;
}

// This function sets up all of the handlers for the UIA automation events.
HRESULT UIAHandler::RegisterEventHandlers()
{
	HRESULT hr = E_FAIL;
	assert (win != NULL);

	PROPERTYID properties[7] = {UIA_NamePropertyId, 
								UIA_HelpTextPropertyId, 
								UIA_ExpandCollapseExpandCollapseStatePropertyId,
								UIA_ToggleToggleStatePropertyId, 
								UIA_IsEnabledPropertyId, 
								UIA_ValueValuePropertyId, 
								UIA_RangeValueValuePropertyId};

	hr = CoCreateInstance(CLSID_CUIAutomation, NULL, CLSCTX_INPROC_SERVER, IID_PPV_ARGS(&automation));
	if (hr == S_OK)
	{
		hr = automation->ElementFromHandle(win, &element);
		if (hr == S_OK)
		{
			eventHandlerAdded = true;

				hr = automation->AddFocusChangedEventHandler(NULL, this);
				if (hr != S_OK)
					cout << "Initialize: could not register handler for UIA event: Focus" << endl;

				hr = automation->AddPropertyChangedEventHandlerNativeArray(element, TreeScope_Descendants, NULL, this, properties, 7);
				if (hr != S_OK)
					cout << "Initialize: could not register handler for UIA event: Property" << endl;

			vector<DWORD> eventList = CreateUIAEventList();
			for (int i = 0; i < eventList.size(); i++)
			{
				hr = automation->AddAutomationEventHandler(eventList.at(i), element, TreeScope_Descendants, NULL, this);
				if (hr != S_OK)
					cout << "Initialize: could not register handler for UIA event: " << eventList[i] << endl;
			}
		}	
	}

	if (hr != S_OK)
	{
		CleanUp();
	}

	return hr;
}

// This function creates a vector of UIA Event Constants 
// so an event handler can be created for each.
vector<DWORD> UIAHandler::CreateUIAEventList()
{
	vector<DWORD> eventList;

	eventList.push_back(UIA_ToolTipOpenedEventId);
	//eventList.push_back(UIA_ToolTipClosedEventId);
	//eventList.push_back(UIA_StructureChangedEventId);
	eventList.push_back(UIA_MenuOpenedEventId);
	//eventList.push_back(UIA_AutomationPropertyChangedEventId);
	//eventList.push_back(UIA_AutomationFocusChangedEventId);
	eventList.push_back(UIA_AsyncContentLoadedEventId);
	//eventList.push_back(UIA_MenuClosedEventId);
	//eventList.push_back(UIA_LayoutInvalidatedEventId);
	//eventList.push_back(UIA_Invoke_InvokedEventId);
	eventList.push_back(UIA_SelectionItem_ElementAddedToSelectionEventId);
	eventList.push_back(UIA_SelectionItem_ElementRemovedFromSelectionEventId);
	eventList.push_back(UIA_SelectionItem_ElementSelectedEventId);
	//eventList.push_back(UIA_Selection_InvalidatedEventId);
	eventList.push_back(UIA_Text_TextSelectionChangedEventId);
	eventList.push_back(UIA_Text_TextChangedEventId);
	//eventList.push_back(UIA_Window_WindowOpenedEventId);
	//eventList.push_back(UIA_Window_WindowClosedEventId);
	//eventList.push_back(UIA_MenuModeStartEventId);
	eventList.push_back(UIA_MenuModeEndEventId);
	//eventList.push_back(UIA_InputReachedTargetEventId);
	//eventList.push_back(UIA_InputReachedOtherElementEventId);
	//eventList.push_back(UIA_InputDiscardedEventId);

	return eventList;
}

// This function removes all of the handlers 
// for the current UIA automation element.
void UIAHandler::RemoveEventHandler()
{
	PostThreadMessage(backgroundThreadID, CLOSE, 0, 0);
	vector<DWORD> eventList = CreateUIAEventList();
	if (element != NULL)
	{
		if(eventHandlerAdded)
		{
			eventHandlerAdded = false;
			for (int i = 0; i < eventList.size(); i++)
			{
				automation->RemoveAutomationEventHandler(eventList.at(i), element, this);
			}
			automation->RemoveFocusChangedEventHandler(this);
			automation->RemovePropertyChangedEventHandler(element, this);
		}
		element->Release();
		element = NULL;
	}
	if (automation != NULL)
	{
		automation->Release();
		automation = NULL;
	}	
}

// If there is currently a window being handled, then it returns true, 
// but if no window is currently being handled, it returns false.
bool UIAHandler::checkStatusOfHandler()
{
	return eventHandlerAdded;
}

// This function serves as the handler for the UIA automation events.
// It creates the xml for the event and passes that to java.
HRESULT STDMETHODCALLTYPE UIAHandler::HandleAutomationEvent(IUIAutomationElement *sender, EVENTID eventId)
{
	return S_OK;
}

// This function serves as the handler for the UIA automation focus changed event.
// It creates the xml for the event and passes that to java.
HRESULT STDMETHODCALLTYPE UIAHandler::HandleFocusChangedEvent(IUIAutomationElement *sender)
{
	return S_OK;
}

// This function serves as the handler for the UIA automation property changed event.
// It creates the xml for the event and passes that to java.
HRESULT STDMETHODCALLTYPE UIAHandler::HandlePropertyChangedEvent(IUIAutomationElement *sender, PROPERTYID propertyId, VARIANT newValue)
{
	return S_OK;
}

///////////////////////////////////////
// IUIAutomationEventHandler methods //
///////////////////////////////////////

HRESULT STDMETHODCALLTYPE UIAHandler::QueryInterface(REFIID riid, void** ppInterface)
{
	if ((riid == __uuidof(IUnknown)) || (riid == __uuidof(IUIAutomationEventHandler)))
    {
        *ppInterface = static_cast<IUIAutomationEventHandler*>(this);
    }
	else
	{
		*ppInterface = NULL;
		return E_NOINTERFACE;
	}

	this->AddRef();
    return S_OK;
}
ULONG STDMETHODCALLTYPE UIAHandler::AddRef()
{
    return InterlockedIncrement(&refCount);
}
ULONG STDMETHODCALLTYPE UIAHandler::Release()
{
    ULONG ret = InterlockedDecrement(&refCount);
    if (ret == 0)
    {
        delete this;
    }

    return ret;
}
