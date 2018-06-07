package plugins.quorum.Libraries.Interface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Containers.Array_;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import static org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window;
import quorum.Libraries.Interface.Controls.MenuItem_;



/**
 *
 * @author Matthew Raybuck
 */
public class AccessibilityManager 
{
    enum AccessibilityCodes
    {
        ITEM,
        CUSTOM,
        CHECKBOX,
        RADIO_BUTTON,
        BUTTON,
        TOGGLE_BUTTON,
        TEXTBOX,
        MENU_BAR,
        MENU_ITEM,
        PANE,
        TREE,
        TREE_ITEM,
        TOOLBAR,
        TAB,
        TABPANE;
    }
    private static final HashMap<Integer, AccessibilityCodes> ACCESSIBILITYCODES_MAP = new HashMap<>();
    private static final quorum.Libraries.Interface.Item ACCESSIBILITYCODES = new quorum.Libraries.Interface.Item();

    static
    {
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__ITEM_(), AccessibilityCodes.ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__CUSTOM_(), AccessibilityCodes.CUSTOM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__CHECKBOX_(), AccessibilityCodes.CHECKBOX);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__RADIO_BUTTON_(), AccessibilityCodes.RADIO_BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__BUTTON_(), AccessibilityCodes.BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TOGGLE_BUTTON_(), AccessibilityCodes.TOGGLE_BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXTBOX_(), AccessibilityCodes.TEXTBOX);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__MENU_BAR_(), AccessibilityCodes.MENU_BAR);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__MENU_ITEM_(), AccessibilityCodes.MENU_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__PANE_(), AccessibilityCodes.PANE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_(), AccessibilityCodes.TREE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_ITEM_(), AccessibilityCodes.TREE_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TOOLBAR_(), AccessibilityCodes.TOOLBAR);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TAB_(), AccessibilityCodes.TAB);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TABPANE_(), AccessibilityCodes.TABPANE);
        
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
            
            NativeWin32InitializeAccessibility(glfwGetWin32Window(DesktopDisplay.window));
        }
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public AccessibilityManager(){};
    public java.lang.Object me_ = null;
    
    // Container to associate the Quorum item with its respective native pointer.
    private static final HashMap<Item_, Long> ITEM_MAP = new HashMap<>();

    // ====== Native Windows API (Win32) Function Declarations

    private static native void NativeWin32InitializeAccessibility(long GLFW_WindowHandle);
    
    private native void NativeWin32ShutdownAccessibility();
    
    // NativePrint: For debugging
    private native void NativePrint();
    
    //
    // ==== Accessible Object Creation Functions
    //
    
    // NativeWin32CreateItem: Creates a custom control with the most basic accessibility information in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateItem(String name, String description);
    
    // NativeWin32CreatePushButton: Creates a button control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateButton(String name, String description);
    
    // NativeWin32CreateToggleButton: Creates a checkbox control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateCheckBox(String name, String description);
    
    // NativeWin32CreateRadioButton: Creates a radio button control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateRadioButton(String name, String description);

    // NativeWin32CreateTextBox: Creates an edit control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateTextBox(String name, String description, String fullText, int caretIndex);

    // NativeWin32CreateMenuBar: Creates a MenuBar control in UI Automation
    //
    private native long NativeWin32CreateMenuBar(String name);
    
    // NativeWin32CreateMenuItem: Creates a MenuItem control in UI Automation.
    //
    private native long NativeWin32CreateMenuItem(String name, String shortcut, long parentMenu, long parentMenuBar);
    
    //
    // ==== Accessible Object Event Response Functions
    //
    
    // NativeWin32InvokeButton: Calls the native method that will raise a UI Automation that tells the screen reader that the button was iteracted with.
    //
    private native boolean NativeWin32InvokeButton(long nativePointer);
    
    // NativeWin32UpdateToggleStatus: Calls the native method that is responsible for updating the native control's toggle status (on or off)
    //                                and raising the appropriate UI Automation event.
    private native boolean NativeWin32UpdateToggleStatus(long nativePointer, boolean selected);

    // NativeWin32TextBoxTextSelectionChanged:
    // TODO: Figure out the parameters required
    private native boolean NativeWin32TextBoxTextSelectionChanged(long nativePointer, String TextValue, int caretLine, int caretCharacter);
    
    // NativeWin32UpdateCaretPosition: Will speak the given string adjacentCharacter.
    //
    private native boolean NativeWin32UpdateCaretPosition(long nativePointer, String fullText, int caretIndex);
    
    // NativeWin32SetFocus: Sets the keyboard focus onto the given item with UI Automation.
    //      Returns: null on failure, otherwise the native pointer of previously focused item.
    private native long NativeWin32SetFocus(long nativePointer);

    //
    // ====== Accessiblity Manager Function Declarations
    //
    
    // Shutdown: Closes the COM library on the native level.
    public void Shutdown()
    {
        NativeWin32ShutdownAccessibility();
    }
    
    public boolean NativeAdd(Item_ item)
    {
        long nativePointer;
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        
        switch(code)
        {
            case ITEM:
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription());
                break;
            case CUSTOM:
                // Not implemented yet. Create as Item for now.
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription());
                break;
            case CHECKBOX:
                nativePointer = NativeWin32CreateCheckBox(item.GetName(), item.GetDescription());
                break;
            case RADIO_BUTTON:
                nativePointer = NativeWin32CreateRadioButton(item.GetName(), item.GetDescription());
                break;
            case BUTTON:
                nativePointer = NativeWin32CreateButton(item.GetName(), item.GetDescription());
                break;
            case TEXTBOX:
                TextBox_ textbox = (TextBox_)item;
                nativePointer = NativeWin32CreateTextBox(textbox.GetName(), textbox.GetDescription(), textbox.GetText(), textbox.GetCaretIndex());
                break;
            case MENU_BAR:
                nativePointer = NativeWin32CreateMenuBar(item.GetName());
                break;
            case MENU_ITEM:
                MenuItem_ menuItem = (MenuItem_)item;
                
                // Get parent MenuItem pointer if it exists.
                Long parentMenuItem = ITEM_MAP.get((Item_)menuItem.GetParentMenu());
                
                // Get parent MenuBar
                long menuBar = ITEM_MAP.get((Item_)menuItem.GetParent());
                
                nativePointer = NativeWin32CreateMenuItem(menuItem.GetName(), menuItem.GetShortcut(), parentMenuItem, menuBar);
                break;
            default: // Assume Item
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription());
                break;
        }
        
        if (nativePointer == 0)
            return false;

        // Add item and respective pointer to collection.
        ITEM_MAP.put(item, nativePointer);
        return true;
    }
    
    public boolean NativeRemove(Item_ item)
    {
        // TODO: Remove the item from the accessibility hierarchy.
        return false;
    }
    
    private boolean NativeChildAdd(Item_ childItem, Item_ parentItem)
    {
        return false;
    }
    
    // SetFocus: Sets the focus to the specified item in UI Automation. This will also update what item has focus within the
    //           Accessibility Manager.
    //      Returns: boolean of success or failure.
    public boolean SetFocus(Item_ item)
    {
        // Retreive HWND for given object
        Long nativePointer = ITEM_MAP.get(item);

        if (nativePointer != null)
            return NativeWin32SetFocus(nativePointer) != 0;
        else
            return false;
    }
    
    // InvokeButton: Invoke a button through UI Automation
    //      Returns: boolean of success or failure.
    public boolean InvokeButton(Item_ button)
    {
        // Retreive HWND for given object
        long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer != 0)
        {
            return NativeWin32InvokeButton(nativePointer);
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
        Long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer != null)
        {
            return NativeWin32UpdateToggleStatus(nativePointer, selected);
        }
        else            
            return false;
    }
    
    // TODO: Fix this method so that it behaves. Otherwise, only ever use CaretPositionChanged
    public void TextSelectionChanged(Item_ textbox)
    {
//        long nativePointer = itemMap.get(textbox);
//        
//        TextBox_ text = (TextBox_)textbox;
//        
//        NativeWin32TextBoxTextSelectionChanged(nativePointer, text.GetCurrentLineText(), text.GetCaretLine(), text.GetCaretLineIndex());
        System.out.println("This method needs to be reworked. Use CaretPositionChanged.");
    }
    
    public void CaretPositionChanged(Item_ item, Text_ fullText)
    {
        Long nativePointer = ITEM_MAP.get(item);
        if (nativePointer != null)
        {
            TextBox_ textbox = (TextBox_)item;
        
            NativeWin32UpdateCaretPosition(nativePointer, textbox.GetText(), textbox.GetCaretIndex());
        }
    }
    
}
