#ifndef CustomMessages_HEADER
#define CustomMessages_HEADER

/*
Custom Messages for the library. These are used in the different window procedures for various purposes.
They're all defined here in a header file so that way they are consistent across the library.

Since we are always forwarding messages sent to our accessible controls onto the main GLFW window
We need a way to differentiate when Quorum has sent a message to the native code and when Windows has
sent us a message. Quorum messages and Focus messages need to not be forwarded to the GLFW window otherwise
they will not be processed correctly.
*/

// This returns the main game engine window handle for Quorum which messages must be forwarded to.
HWND GetMainWindowHandle();

// General messages
#define QUORUM_SETNAME WM_USER + 1

// Button messages
#define QUORUM_INVOKEBUTTON WM_USER + 2

// Textbox messages
#define QUORUM_UPDATECARET WM_USER + 3
#define QUORUM_SETTEXT WM_USER + 4


#endif
