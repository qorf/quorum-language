# QuorumLanguage README #

### Windows Quorum Accessibility Library (QuorumAccessibilityWindows64.dll) ###

In order to get the library working on a new machine for development you must use Visual Studio 2017 with build tools V141. When the project is first pulled from the repository it is missing a macro that is necessary for the .dll to be automatically copied into the correct folder after building. To add this macro follow these steps:

1) Open the project in Visual Studio and open the Property Manager. This is done by opening View->Other Windows->Property Manager

2) Open the tree for "IQuorumAccessibility" and then the tree for "Debug | x64".

3) Right click on Microsoft.Cpp.x64.user and open properties

4) Open the tree for "Common Properties" and then click on "User Macros"

5) Click the "Add Macro" button and for the name type "InterfaceFolder" and for the value type "..\..\..\..\..\Quorum3\Library\Standard\Native\Libraries.Interface\". Neither should have quotes.

Whenever changes have been made to the Windows quorum accessibility library the following steps must be followed to see those changes reflected in the project that is being used to test those changes:

* Rebuild Visual Studio Solution. (IQuorumAccessibility)
* Run the bootstrap script for the Quorum3 Project
* Run bootstrapped Sodbeans IDE
* Clean and Build the accessibility testing application (Testing)

### AccessibilityManager Java Project ###

When changes have been made to the java project the following steps must be followed to see those changes reflected in a project that is using the AccessibilityManager:

* Clean and Build AccessibilityManager Java project.
* Run the bootstrap script for the Quorum3 Project
* Run bootstrapped Sodbeans IDE
