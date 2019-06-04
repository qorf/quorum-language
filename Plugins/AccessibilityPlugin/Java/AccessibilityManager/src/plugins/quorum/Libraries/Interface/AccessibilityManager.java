package plugins.quorum.Libraries.Interface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import plugins.quorum.Libraries.Game.DesktopDisplay;

import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.MenuItem_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Events.MenuChangeEvent_;
import quorum.Libraries.Interface.Events.TreeChangeEvent_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;


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
        TEXT_FIELD,
        MENU_BAR,
        MENU_ITEM,
        PANE,
        TREE,
        TREE_ITEM,
        TOOLBAR,
        TAB,
        TABPANE;
    }
    
    enum MenuChanges
    {
        EXPANDED,
        COLLAPSED;
    }
    
    enum TreeChanges
    {
        EXPANDED,
        COLLAPSED;
    }
    
    private static final HashMap<Integer, AccessibilityCodes> ACCESSIBILITYCODES_MAP = new HashMap<>();
    private static final HashMap<Integer, MenuChanges> MENUCHANGES_MAP = new HashMap<>();
    private static final HashMap<Integer, TreeChanges> TREECHANGES_MAP = new HashMap<>();
    private static final quorum.Libraries.Interface.Item ACCESSIBILITYCODES = new quorum.Libraries.Interface.Item();
    private static final quorum.Libraries.Interface.Events.MenuChangeEvent MENUCHANGECODES = new quorum.Libraries.Interface.Events.MenuChangeEvent();
    private static final quorum.Libraries.Interface.Events.TreeChangeEvent TREECHANGECODES = new quorum.Libraries.Interface.Events.TreeChangeEvent();
    
    static
    {
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__ITEM_(), AccessibilityCodes.ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__CUSTOM_(), AccessibilityCodes.CUSTOM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__CHECKBOX_(), AccessibilityCodes.CHECKBOX);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__RADIO_BUTTON_(), AccessibilityCodes.RADIO_BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__BUTTON_(), AccessibilityCodes.BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TOGGLE_BUTTON_(), AccessibilityCodes.TOGGLE_BUTTON);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXTBOX_(), AccessibilityCodes.TEXTBOX);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXT_FIELD_(), AccessibilityCodes.TEXT_FIELD);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__MENU_BAR_(), AccessibilityCodes.MENU_BAR);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__MENU_ITEM_(), AccessibilityCodes.MENU_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__PANE_(), AccessibilityCodes.PANE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_(), AccessibilityCodes.TREE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_ITEM_(), AccessibilityCodes.TREE_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TOOLBAR_(), AccessibilityCodes.TOOLBAR);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TAB_(), AccessibilityCodes.TAB);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TABPANE_(), AccessibilityCodes.TABPANE);
        
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__OPENED_(), MenuChanges.EXPANDED);
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__CLOSED_(), MenuChanges.COLLAPSED);

        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__OPENED_(), TreeChanges.EXPANDED);
        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__CLOSED_(), TreeChanges.COLLAPSED);
        
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
            
            NativeWin32InitializeAccessibility(org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window(DesktopDisplay.window));
        }
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public AccessibilityManager(){};
    public java.lang.Object me_ = null;
    
    private static volatile long frameCount = 0;
    
    // Container to associate the Quorum item with its respective native pointer.
    private static final HashMap<Item_, Long> ITEM_MAP = new HashMap<>();

    // ====== Native Windows API (Win32) Function Declarations

    private static native void NativeWin32InitializeAccessibility(long GLFW_WindowHandle);
    
    private native void NativeWin32ShutdownAccessibility();
    
    //
    // ==== Accessible Object Creation Functions
    //
    
    // NativeWin32CreateItem: Creates a custom control with the most basic accessibility information in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateItem(String name, String description, Item_ item);
    
    // NativeWin32CreatePushButton: Creates a button control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateButton(String name, String description, Item_ item);
    
    // NativeWin32CreateToggleButton: Creates a checkbox control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateCheckBox(String name, String description, Item_ item);
    
    // NativeWin32CreateRadioButton: Creates a radio button control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateRadioButton(String name, String description, Item_ item);

    // NativeWin32CreateTextBox: Creates an edit control in UI Automation.
    //      Returns: null on failure, otherwise the native pointer associated with item
    private native long NativeWin32CreateTextBox(String name, String description, TextBox_ quorumSelf);

    private native long NativeWin32CreateTextField(String name, String description, TextField_ quorumField);
    
    // NativeWin32CreateMenuBar: Creates a MenuBar control in UI Automation
    //
    private native long NativeWin32CreateMenuBar(String name, Item_ item);

    private native long NativeWin32CreateTree(String name, Item_ item);

    
    // NativeWin32CreateMenuItem: Creates a MenuItem control in UI Automation.
    //
    private native long NativeWin32CreateMenuItem(String name, String shortcut, boolean isMenu, long parentMenu, long parentMenuBar, Item_ item);
    
    private native long NativeWin32CreateTreeItem(String name, String description, boolean isMenu, boolean isExpanded, long parentMenu, long parentMenuBar, Item_ item);
    
    
    // NativeWin32Remove: Removes a item control from UI Automation hierarchy.
    //
    private native boolean NativeWin32Remove(long itemToRemove);
    
    // NativeWin32RemoveMenuItem: Removes a MenuItem control from UI Automation hierarchy.
    //
    private native boolean NativeWin32RemoveMenuItem(long itemToRemove);
    
    private native boolean NativeWin32RemoveTreeItem(long itemToRemove);

    
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
    //
    private native boolean NativeWin32TextBoxTextSelectionChanged(long nativePointer, String TextValue, int startIndex, int endIndex);
    
    private native boolean NativeWin32TextFieldTextSelectionChanged(long nativePointer, String textValue, int startIndex, int endIndex);
    
    // NativeWin32UpdateCaretPosition: Will speak the given string adjacentCharacter.
    //
    private native boolean NativeWin32UpdateCaretPosition(long nativePointer, String fullText, int caretIndex);
    
    // NativeWin32SetFocus: Sets the keyboard focus onto the given item with UI Automation.
    //      Returns: null on failure, otherwise the native pointer of previously focused item.
    private native long NativeWin32SetFocus(long nativePointer);

        // NativeWin32SelectMenuItem: Selects a MenuItem control in the UI Automation hierarchy.
    //
    private native boolean NativeWin32SelectMenuItem(long selectedMenuItem);
    
    // NativeWin32DeselectMenuItem: Deselects a MenuItem control in the UI Automation hierarchy.
    //
    private native boolean NativeWin32DeselectMenuItem(long menubar);

    private native boolean NativeWin32MenuExpanded(long nativePointer);
    
    private native boolean NativeWin32MenuCollapsed(long nativePointer);
    
    private native boolean NativeWin32SelectTreeItem(long selectedMenuItem);
    
    private native boolean NativeWin32SubtreeExpanded(long nativePointer);
    
    private native boolean NativeWin32SubtreeCollapsed(long nativePointer);
    
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
        
        if (ITEM_MAP.get(item) != null)
            return true;

        switch(code)
        {
            case ITEM:
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription(), item);
                break;
            case CUSTOM:
                // Not implemented yet. Create as Item for now.
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription(), item);
                break;
            case CHECKBOX:
                // This Create function will need more parameters for state information
                // Since checkboxes don't exist some assumptions are made on the native level
                // that won't be accurate if the correct info isn't passed down.
                nativePointer = NativeWin32CreateCheckBox(item.GetName(), item.GetDescription(), item);
                break;
            case RADIO_BUTTON:
                // This Create function will need more parameters for state information
                // Since dedicated radio buttons don't exist some assumptions are made 
                // on the native level that won't be accurate if the correct info isn't passed down.
                nativePointer = NativeWin32CreateRadioButton(item.GetName(), item.GetDescription(), item);
                break;
            case BUTTON:
                nativePointer = NativeWin32CreateButton(item.GetName(), item.GetDescription(), item);
                break;
            case TEXTBOX:
                TextBox_ textbox = (TextBox_)item;
                nativePointer = NativeWin32CreateTextBox(textbox.GetName(), textbox.GetDescription(), textbox);
                break;
            case TEXT_FIELD:
                TextField_ textField = (TextField_)item;
                nativePointer = NativeWin32CreateTextField(textField.GetName(), textField.GetDescription(), textField);
                break;
            case MENU_BAR:
                nativePointer = NativeWin32CreateMenuBar(item.GetName(), item);
                break;
            case MENU_ITEM:
                MenuItem_ menuItem = (MenuItem_)item;
                
                // Get parent MenuItem pointer if it exists.
                Long parentMenuItem = ITEM_MAP.get((Item_)menuItem.GetParentMenu());
                long parentMenu = 0;
                
                // If the parent menu exists then pass that down.
                // Otherwise, native code will take care of the rest.
                if (parentMenuItem != null)
                    parentMenu = parentMenuItem;
                
                // Get parent MenuBar
                Long menuBar = ITEM_MAP.get((Item_)menuItem.GetMenuRoot());
                
                if (menuBar == null)
                    return false;
                
                nativePointer = NativeWin32CreateMenuItem(menuItem.GetName(), menuItem.GetShortcut(), menuItem.IsMenu(), parentMenu, menuBar, item);
                break;
            case TREE:
                nativePointer = NativeWin32CreateTree(item.GetName(), item);
                break;
            case TREE_ITEM:
                TreeItem_ treeItem = (TreeItem_)item;
                
                // Get parent MenuItem pointer if it exists.
                Long parentTreeItem = ITEM_MAP.get((Item_)treeItem.GetParentTreeItem());
                long parentSubtree = 0;
                
                // If the parent subtree exists then pass that down.
                // Otherwise, native code will take care of the rest.
                if (parentTreeItem != null)
                    parentSubtree = parentTreeItem;
                
                // Get parent Tree
                Long parentTree = ITEM_MAP.get((Item_)treeItem.GetTree());
                
                if (parentTree == null)
                    return false;
                    
                nativePointer = NativeWin32CreateTreeItem(treeItem.GetName(), treeItem.GetDescription(), treeItem.IsSubtree(), treeItem.IsOpen(), parentSubtree, parentTree, treeItem);
                break;
            default: // Assume Item
                nativePointer = NativeWin32CreateItem(item.GetName(), item.GetDescription(), item);
                break;
        }
        
        if (nativePointer == 0)
            return false;

        // Add item and respective pointer to collection.
        ITEM_MAP.put(item, nativePointer);
        
        return true;
    }
    
    private native void NativeWin32NotifyTextBox(long nativePointer, String say);
    
    public void Notify(Item_ item, String say) {
        Long itemToRemove = ITEM_MAP.get(item);
        if(item instanceof TextBox_) {
            NativeWin32NotifyTextBox(itemToRemove, say);
        }
    }
    
    public boolean NativeRemove(Item_ item)
    {
        Long itemToRemove = ITEM_MAP.get(item);
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        boolean wasRemoved;
        
        // Retreive native pointer for given object
        if (itemToRemove == null)
            return true;
        
        switch(code)
        {
            case ITEM:
            case CUSTOM:
            case BUTTON:
            case RADIO_BUTTON:
            case CHECKBOX:
            case MENU_BAR:
            case TREE:
                wasRemoved = NativeWin32Remove(itemToRemove);
                break;
            case MENU_ITEM:
                wasRemoved = NativeWin32RemoveMenuItem(itemToRemove);
                break;
            case TREE_ITEM:
                wasRemoved = NativeWin32RemoveTreeItem(itemToRemove);
                break;
            default:
                return false;
        }
        
        if (wasRemoved)
            ITEM_MAP.remove(item);
        
        return true;

    }
    
    public boolean Select(Item_ item)
    {
        Long selectedItem;
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        boolean Selected;
        
        // Retreive native pointer for given object
        selectedItem = ITEM_MAP.get(item);

        if (selectedItem == null)
            return false;
        
        switch(code)
        {
            case MENU_ITEM:
                Selected = NativeWin32SelectMenuItem(selectedItem);
                break;
            case TREE_ITEM:
                Selected = NativeWin32SelectTreeItem(selectedItem);
                break;
            default:
                Selected = false;
        }
        
        return Selected;
    }
    
    public boolean Deselect(Item_ item)
    {
        Long deselectedItem;
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        boolean Deselected;
        
        switch(code)
        {
            case MENU_ITEM:
                // Retreive native pointer for given object
                deselectedItem = ITEM_MAP.get(item);
                                
                if (deselectedItem != null)
                    Deselected = NativeWin32DeselectMenuItem(deselectedItem);
                else
                    Deselected = false;
                
                break;
            default:
                Deselected = false;
        }
        
        return Deselected;
    }
    
    // SetFocus: Sets the focus to the specified item in UI Automation. This will also update what item has focus within the
    //           Accessibility Manager.
    //      Returns: boolean of success or failure.
    public boolean SetFocus(Item_ item)
    {
        // Retreive native pointer for given object
        Long nativePointer = ITEM_MAP.get(item);

        if (nativePointer != null)
        {
            boolean result = NativeWin32SetFocus(nativePointer) != 0;
            return result;
        }
        else
        {
            return false;
        }
    }
    
    public boolean NativeMenuChanged(MenuChangeEvent_ event)
    {
        Item_ item = event.GetMenuItem();
        Long itemToChange = ITEM_MAP.get(item);
        MenuChanges code = MENUCHANGES_MAP.get(event.GetEventType());
        boolean wasChanged = false;
        
        // Retreive native pointer for given object
        if (itemToChange == null)
            return true;
        
        switch(code)
        {
            case EXPANDED:
            {
                wasChanged = NativeWin32MenuExpanded(itemToChange);
                break;
            }
            case COLLAPSED:
            {
                wasChanged = NativeWin32MenuCollapsed(itemToChange);
                break;
            }
            default:
        }
        
        return wasChanged;
    }
    
    public boolean NativeTreeChanged(TreeChangeEvent_ event)
    {
        Item_ item = event.GetTreeItem();
        Long itemToChange = ITEM_MAP.get(item);
        TreeChanges code = TREECHANGES_MAP.get(event.GetEventType());
        boolean wasChanged = false;
        
        // Retreive native pointer for given object
        if (itemToChange == null)
            return true;
        
        switch(code)
        {
            case EXPANDED:
            {
                wasChanged = NativeWin32SubtreeExpanded(itemToChange);
                break;
            }
            case COLLAPSED:
            {
                wasChanged = NativeWin32SubtreeCollapsed(itemToChange);
                break;
            }
            default:
        }
        
        return wasChanged;
    }
    
    // InvokeButton: Invoke a button through UI Automation
    //      Returns: boolean of success or failure.
    public boolean InvokeButton(Item_ button)
    {
        // Retreive native pointer for given object
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
        // Retreive native pointer for given object
        Long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer != null)
        {
            return NativeWin32UpdateToggleStatus(nativePointer, selected);
        }
        else            
            return false;
    }
    
    public void TextSelectionChanged(TextBoxSelection_ selection)
    {
        TextBox_ textbox = selection.GetTextBox();
        
        if (textbox == null)
            return;
        
        Long nativePointer = ITEM_MAP.get((Item_)textbox);
        
        if (nativePointer == null)
            return;
        
        NativeWin32TextBoxTextSelectionChanged(nativePointer, textbox.GetText(),
            selection.GetStartIndex(), selection.GetEndIndex());
        
    }
    
    public void TextSelectionChanged(TextFieldSelection_ selection)
    {
        TextField_ textField = selection.GetTextField();
        
        if (textField == null)
            return;
        
        Long nativePointer = ITEM_MAP.get((Item_)textField);
        
        if (nativePointer == null)
            return;
        
//        NativeWin32TextFieldTextSelectionChanged(nativePointer, textField.GetText(),
//            selection.GetStartIndex(), selection.GetEndIndex());
    }
    
    public void CaretPositionChanged(Item_ item, Text_ fullText)
    {
        Long nativePointer = ITEM_MAP.get(item);
        if (nativePointer != null)
        {
            TextBox_ textbox = (TextBox_)item;
        
            NativeWin32UpdateCaretPosition(nativePointer, textbox.GetText(), textbox.GetCaretPosition());
        }
    }
    
    public void Update()
    {
        frameCount++;
    }
    
    public static void WaitForUpdate() throws InterruptedException
    {
        long currentCount = frameCount;
        while (currentCount == frameCount)
        { 
            // Do nothing.
            Thread.sleep(1);
        }

    }
    
}
