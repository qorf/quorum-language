#pragma once

#include "jni.h"
#include <UIAutomation.h>
#include <iostream>
#include <string>

#ifndef LOG
#define LOG 0
#endif

class WindowRoot;
WindowRoot* GetMainWindowRoot();

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
	jmethodID GetTreeSelectionPointer;
	jmethodID GetTabPaneSelectionPointer;
	jmethodID GetSpreadsheetSelectionPointer;
	jmethodID SetTabSelection;

	// Used for TreeItems.
	jmethodID GetTreeItemSetSize;
	jmethodID GetTreeItemSetPosition;

	// Used for MenuItems.
	jmethodID GetMenuItemSetSize;
	jmethodID GetMenuItemSetPosition;

	// Used for cells and Spreadsheets/TreeTable.
	jmethodID GetCellColumnIndex;
	jmethodID GetCellRowIndex;
	jmethodID GetCellText;
	jmethodID GetTableColumnsSize;
	jmethodID GetTableRowsSize;
	jmethodID GetTableItem;

	// Used for Lists.
	jmethodID GetListItemText;

	// Used to calculate bounding rectangles.
	jmethodID GetBoundingRectangle;

	// Used to indicate that the accessibility system needs to stop pumping messages for a time, to allow the engine to process input.
	jmethodID PauseEventPolling;

	// Used to determine if spelling errors are present at a given index in a TextBox.
	jmethodID IsErrorAtIndex;

	// Used when navigating by word to return new lines as needed.
	jmethodID IsIndexAtEndOfLine;
};
struct JClass_TextBox
{
	jclass me;
	jmethodID GetCaretLine;
	jmethodID GetCaretPosition;
	jmethodID GetIndexOfLine;
	jmethodID GetLineIndexOfCharacter;
	jmethodID GetSize;
	jmethodID GetText;
	jmethodID GetPartialText;
	jmethodID GetCurrentLineText;
	jmethodID GetSelection;
	jmethodID Select;
	jmethodID GetTokenStartIndex;
	jmethodID GetTokenEndIndex;
	jmethodID IsBeginningOfToken;
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
	jmethodID IsPassword;
};
struct JClass_TextFieldSelection
{
	jclass me;
	jmethodID IsEmpty;
	jmethodID GetStartIndex;
	jmethodID GetEndIndex;
};
struct JClass_Button
{
	jclass me;
	jmethodID Activate;
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
	jmethodID Hide;
	jmethodID IsModal;
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
};
struct JClass_ProgressBar
{
	jclass me;
	jmethodID GetValue;
	jmethodID GetMinimum;
	jmethodID GetMaximum;
};

extern JClass_AccessibilityManager JavaClass_AccessibilityManager;
extern JClass_TextBox JavaClass_TextBox;
extern JClass_TextBoxSelection JavaClass_TextBoxSelection;
extern JClass_TextField JavaClass_TextField;
extern JClass_TextFieldSelection JavaClass_TextFieldSelection;
extern JClass_Button JavaClass_Button;
extern JClass_ToggleButton JavaClass_ToggleButton;
extern JClass_Item JavaClass_Item;
extern JClass_ToolBar JavaClass_ToolBar;
extern JClass_Dialog JavaClass_Dialog;
extern JClass_List JavaClass_List;
extern JClass_Spreadsheet JavaClass_Spreadsheet;
extern JClass_TreeTable JavaClass_TreeTable;
extern JClass_Cell JavaClass_Cell;
extern JClass_ListItem JavaClass_ListItem;
extern JClass_ProgressBar JavaClass_ProgressBar;
