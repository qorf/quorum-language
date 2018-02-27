package plugins.quorum.Libraries.Interface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.Interface.TextBox_;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import static org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window;



/**
 *
 * @author Matthew Raybuck
 */
public class AccessibilityManager 
{
    static   //static initializer code
    {
        try
        {
            java.io.File file = new java.io.File(AccessibilityManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String runLocation = file.getParentFile().getAbsolutePath();
            
            String nativeFile;
            
            if (System.getProperty("os.arch").contains("x86"))
                nativeFile = runLocation + "\\jni\\AccessibilityManagerWindows32.dll";
            else
                nativeFile = runLocation + "\\jni\\AccessibilityManagerWindows64.dll";
            
            System.load(nativeFile);
        }
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public AccessibilityManager(){};
    
    public java.lang.Object me_ = null;
    
    // Container to associate the Quorum item with its respective HWND.
    private final HashMap<Item_, Long> itemMap = new HashMap<>();
    private Item_ focusedItem = null;
    // The handle to the main game window that GLFW creates
    private long mainWindow;

    
    // ====== Native Windows API (Win32) Function Declarations
    
    
    private native void NativeWin32InitializeAccessibility();
    
    private native void NativeWin32ShutdownAccessibility();
    
    // NativePrint: For debugging
    private native void NativePrint();
    
    // NativeWin32CreateItem: Creates a custom control with the most basic accessibility information in UI Automation.
    //      Returns: null on failure, otherwise itemHWND associated with item
    private native long NativeWin32CreateItem(long parentWindow, String name, String description);
    
    // NativeWin32CreatePushButton: Creates a button control in UI Automation.
    //      Returns: null on failure, otherwise itemHWND associated with item
    private native long NativeWin32CreatePushButton(long parentWindow, String name, String description);
    
    // NativeWin32CreateToggleButton: Creates a checkbox control in UI Automation.
    //      Returns: null on failure, otherwise itemHWND associated with item
    private native long NativeWin32CreateToggleButton(long parentWindow, String name, String description);
    
    // NativeWin32CreateRadioButton: Creates a radio button control in UI Automation.
    //      Returns: null on failure, otherwise itemHWND associated with item
    private native long NativeWin32CreateRadioButton(long parentWindow, String name, String description);
    
    // NativeWin32InvokeButton: Calls the native method that will raise a UI Automation that tells the screen reader that the button was iteracted with.
    private native boolean NativeWin32InvokeButton(long itemHWND);
    
    // NativeWin32UpdateToggleStatus: Calls the native method that is responsible for updating the native control's toggle status (on or off)
    //                                and raising the appropriate UI Automation event.
    private native boolean NativeWin32UpdateToggleStatus(long itemHWND, boolean selected);

    // NativeWin32CreateTextBox: Creates an edit control in UI Automation.
    //      Returns: null on failure, otherwise itemHWND associated with item
    private native long NativeWin32CreateTextBox(long parentWindow, String name, String description, String currentLineText, int caretLine, int caretCharacter);

    // NativeWin32TextBoxTextSelectionChanged: Calls the native method that will raise a UI Automation event for Text being changed.
    // TODO: Figure out what parameters are necessary to accomplish this.
    private native boolean NativeWin32TextBoxTextSelectionChanged(long itemHWND, String TextValue, int caretLine, int caretCharacter);
    
    
    // NativeWin32SetFocus: Sets the keyboard focus onto the given item with UI Automation.
    //      Returns: null on failure, otherwise itemHWND of previously focused item.
    private native long NativeWin32SetFocus(long itemHWND);
    
    

    
    // ===== Accessiblity Manager Function Declarations
    
    // Initialize: Gets the window handle (a pointer) for the Quorum Game window and initializes the native accessiblity library.
    //             This has to be called after the game window already exists otherwise Accessiblity won't work. It should never be
    //             called more than once either.
    public void Initialize()
    {
        mainWindow = glfwGetWin32Window(DesktopDisplay.window);
        NativeWin32InitializeAccessibility();
    }
    
    // Shutdown: Closes the COM library on the native level.
    public void Shutdown()
    {
        NativeWin32ShutdownAccessibility();
    }
    
    public boolean Add(Item_ item)
    {
        long itemHWND = 0;
        
        switch(item.GetAccessibilityCode())
        {
            case 0: // Item
                itemHWND = NativeWin32CreateItem(mainWindow, item.GetName(), item.GetDescription());
                break;
            case 1: // Custom
                // Not implemented yet. Create as Item for now.
                itemHWND = NativeWin32CreateItem(mainWindow, item.GetName(), item.GetDescription());
                break;
            case 2: // ToggleButton
                itemHWND = NativeWin32CreateToggleButton(mainWindow, item.GetName(), item.GetDescription());
                break;
            case 3: // RadioButton
                itemHWND = NativeWin32CreateRadioButton(mainWindow, item.GetName(), item.GetDescription());
                break;
            case 4: // PushButton
                itemHWND = NativeWin32CreatePushButton(mainWindow, item.GetName(), item.GetDescription());
                break;
            case 5: // TextBox
                TextBox_ textbox = (TextBox_)item;
                itemHWND = NativeWin32CreateTextBox(mainWindow, textbox.GetName(), textbox.GetDescription(), textbox.GetCurrentLineText(), textbox.GetCaretLine(), textbox.GetCaretLineIndex());
                break;
            default: // Assume Item
                itemHWND = NativeWin32CreateItem(mainWindow, item.GetName(), item.GetDescription());
                break;
        }
        
        if (itemHWND != 0)
        {
            // Add item and respective HWND to collection.
            itemMap.put(item, itemHWND);
            
            return true;
        }
        else
            return false;
    }
    
    // GetFocus: Returns the current item that has focus with UI Automation.
    public Item_ GetFocus()
    {
        return focusedItem;
    }
    
    // SetFocus: Sets the focus to the specified item in UI Automation. This will also update what item has focus within the
    //           Accessibility Manager.
    //      Returns: boolean of success or failure.
    public boolean SetFocus(Item_ item)
    {
        // Retreive HWND for given object
        long itemHWND = itemMap.get(item);
//        long prevousFocusedItem;
        if (itemHWND != 0)
        {
//            prevousFocusedItem = NativeWin32SetFocus(itemHWND);
            
            if(NativeWin32SetFocus(itemHWND) != 0)
            {
                // TODO: Decide whether or not to capture return value of NativeWin32SetFocus and store it for something.
                
                // Update focesedItem to the new focused item.
                focusedItem = item;
                //NativeWin32SetFocus(mainWindow);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    // InvokeButton: Invoke a button through UI Automation
    //      Returns: boolean of success or failure.
    public boolean InvokeButton(Item_ button)
    {
        // Retreive HWND for given object
        long itemHWND = itemMap.get(button);
        
        if (itemHWND != 0)
        {
            boolean bool = NativeWin32InvokeButton(itemHWND);
            
            return bool;
        }
        else
            return false;

    }
    
    // UpdateToggleState: Update the selected status of a toggle button down at the native
    //                    level. This can be used for any button that can be toggled.
    //      Returns: boolean of success or failure
    public boolean UpdateToggleState(Item_ button, boolean selected)
    {
        // Retreive HWND for given object
        long itemHWND = itemMap.get(button);
        
        if (itemHWND != 0)
        {
            boolean bool = NativeWin32UpdateToggleStatus(itemHWND, selected);
            
            return bool;
        }
        else            
            return false;
    }
    
    public void TextSelectionChanged(Item_ textbox)
    {
        long itemHWND = itemMap.get(textbox);
        
        TextBox_ text = (TextBox_)textbox;
        
        NativeWin32TextBoxTextSelectionChanged(itemHWND, text.GetCurrentLineText(), text.GetCaretLine(), text.GetCaretLineIndex());
    }
    
}
