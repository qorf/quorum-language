#include "../IQuorumAccessibility/Header/jni.h"
#include <UIAnimation.h>
#include <UIAutomation.h>
#include <iostream>
#include <string>

#ifndef Resources_HEADER
#define Resources_HEADER

#ifndef LOG
#define LOG 0
#endif

// This returns the main game engine window handle for Quorum which messages must be forwarded to.
// If new windows can be created by Quorum then messages will need to be forwarded to those instead.
// Its defined in IQuorumAccessibility.cpp
HWND GetMainWindowHandle();

// This returns the central IUIAutomation object used to gather information about the environment.
IUIAutomation* GetIUIAutomation();

JNIEnv* GetJNIEnv();

WCHAR* CreateWideStringFromUTF8Win32(const char* source);

/**************************************************************
* Static Global Variables to cache Java Class and Method IDs
*
**************************************************************/
void log(std::string s);

struct JClass_AccessibilityManager
{
	jclass me;
	jmethodID WaitForUpdate;
	jmethodID GetTreeSelectionPointer;
	jmethodID GetTabPaneSelectionPointer;
	jmethodID GetSpreadsheetSelectionPointer;
	jmethodID SetTabSelection;

	// Used for cells and Spreadsheets/TreeTable.
	jmethodID GetCellColumnIndex;
	jmethodID GetCellRowIndex;
	jmethodID GetCellText;
	jmethodID GetListItemText;
};
struct JClass_TextBox
{
	jclass me;
	jmethodID GetCaretLine;
	jmethodID GetCaretPosition;
	jmethodID GetIndexOfLine;
	jmethodID GetText;
	jmethodID GetCurrentLineText;
	jmethodID GetSelection;
};
struct JClass_TextBoxSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};
struct JClass_TextField
{
	jclass me;
	jmethodID GetCaretPosition;
	jmethodID GetText;
	jmethodID GetSelection;
	jmethodID GetSize;
};
struct JClass_TextFieldSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};
struct JClass_ToggleButton
{
	jclass me;
	jmethodID SetToggleState;
	jmethodID GetToggleState;
};
struct JClass_TabPane
{
	jclass me;
};
struct JClass_Tab
{
	jclass me;
};
struct JClass_ToolBar
{
	jclass me;
};
struct JClass_Dialog
{
	jclass me;
};
struct JClass_List
{
	jclass me;
};
struct JClass_ListItem
{
	jclass me;
};
struct JClass_Spreadsheet
{
	jclass me;
};
struct JClass_TreeTable
{
	jclass me;
};
struct JClass_Cell
{
	jclass me;
};
struct JClass_Item
{
	jclass me;
	jmethodID GetName;
	jmethodID GetDescription;
	jmethodID GetHashCode;
};

extern JClass_AccessibilityManager JavaClass_AccessibilityManager;
extern JClass_TextBox JavaClass_TextBox;
extern JClass_TextBoxSelection JavaClass_TextBoxSelection;
extern JClass_TextField JavaClass_TextField;
extern JClass_TextFieldSelection JavaClass_TextFieldSelection;
extern JClass_ToggleButton JavaClass_ToggleButton;
extern JClass_Item JavaClass_Item;
extern JClass_ToolBar JavaClass_ToolBar;
extern JClass_Dialog JavaClass_Dialog;
extern JClass_Dialog JavaClass_List;
extern JClass_Dialog JavaClass_Spreadsheet;
extern JClass_Dialog JavaClass_TreeTable;
extern JClass_Dialog JavaClass_Cell;
extern JClass_Dialog JavaClass_ListItem;
#endif
