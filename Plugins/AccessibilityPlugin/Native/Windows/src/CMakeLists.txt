cmake_minimum_required(VERSION 3.15)
project(AccessibilityManagerWindows)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded$<$<CONFIG:Debug>:Debug>")

add_library(AccessibilityManagerWindows MODULE
    AccessibilityManagerWindows.cpp
    ButtonControl.cpp
    ButtonProvider.cpp
    CellControl.cpp
    CellProvider.cpp
    CheckBoxControl.cpp
    CheckBoxProvider.cpp
    DialogControl.cpp
    DialogProvider.cpp
    Item.cpp
    ItemControl.cpp
    ItemProvider.cpp
    ListControl.cpp
    ListItemControl.cpp
    ListItemProvider.cpp
    ListProvider.cpp
    MenuBarControl.cpp
    MenuBarProvider.cpp
	MenuControl.cpp
    MenuProvider.cpp
    MenuItemControl.cpp
    MenuItemProvider.cpp
	PopupMenuItemControl.cpp
    PopupMenuItemProvider.cpp
	ProgressBarControl.cpp
	ProgressBarProvider.cpp
    RadioButtonControl.cpp
    RadioButtonProvider.cpp
    Resources.cpp
    RootItemBase.cpp
    TableControl.cpp
    TableProvider.cpp
    TabControl.cpp
    TabPaneControl.cpp
    TabPaneProvider.cpp
    TabProvider.cpp
    TextBoxControl.cpp
    TextBoxProvider.cpp
    TextBoxTextRange.cpp
    TextFieldControl.cpp
    TextFieldProvider.cpp
    TextFieldTextRange.cpp
    ToolBarControl.cpp
    ToolBarProvider.cpp
    TreeControl.cpp
    TreeItemControl.cpp
    TreeItemProvider.cpp
    TreeProvider.cpp
    WindowRoot.cpp
    WindowRootProvider.cpp
)

target_compile_definitions(AccessibilityManagerWindows PRIVATE
    UNICODE
    _UNICODE
)

target_include_directories(AccessibilityManagerWindows PRIVATE
    ../../../../../Dependencies/wil/include
    Header
)

target_link_libraries(AccessibilityManagerWindows PRIVATE
    UIAutomationCore
    comsuppwd
)

if(CMAKE_CL_64)
    set_target_properties(AccessibilityManagerWindows PROPERTIES OUTPUT_NAME "AccessibilityManagerWindows64")
else()
    set_target_properties(AccessibilityManagerWindows PROPERTIES OUTPUT_NAME "AccessibilityManagerWindows32")
endif()
