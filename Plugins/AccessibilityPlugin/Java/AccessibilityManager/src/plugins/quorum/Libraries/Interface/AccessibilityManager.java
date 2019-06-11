package plugins.quorum.Libraries.Interface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import plugins.quorum.Libraries.Game.DesktopDisplay;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.List_;

import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.MenuItem_;
import quorum.Libraries.Interface.Controls.TabPane_;
import quorum.Libraries.Interface.Controls.Tab_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Controls.Tree_;
import quorum.Libraries.Interface.Events.MenuChangeEvent_;
import quorum.Libraries.Interface.Events.TreeChangeEvent_;
import quorum.Libraries.Interface.Selections.TabPaneSelection_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Selections.TreeSelection_;


/**
 * This is the Java Windows side of the Accessibility. 
 * 
 * @author Matthew Raybuck, William Allee, and Andreas Stefik
 */
public class AccessibilityManager 
{
    enum AccessibilityCodes {
        NOT_ACCESSIBLE,
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
        TAB_PANE,
        TABLE,
        CELL,
        TEXT_FIELD,
        LIST,
        LIST_ITEM,
        TREE_TABLE,
        DIALOG
    }
            
    enum MenuChanges {
        EXPANDED,
        COLLAPSED;
    }
    
    enum TreeChanges {
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
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_(), AccessibilityCodes.NOT_ACCESSIBLE);
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
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TAB_PANE_(), AccessibilityCodes.TAB_PANE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TABLE_(), AccessibilityCodes.TABLE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__CELL_(), AccessibilityCodes.CELL);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXT_FIELD_(), AccessibilityCodes.TEXT_FIELD);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__LIST_(), AccessibilityCodes.LIST);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__LIST_ITEM_(), AccessibilityCodes.LIST_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_TABLE_(), AccessibilityCodes.TREE_TABLE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXT_FIELD_(), AccessibilityCodes.TEXT_FIELD);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__DIALOG_(), AccessibilityCodes.DIALOG);
        
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__OPENED_(), MenuChanges.EXPANDED);
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__CLOSED_(), MenuChanges.COLLAPSED);
        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__OPENED_(), TreeChanges.EXPANDED);
        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__CLOSED_(), TreeChanges.COLLAPSED);
        
        try
        {
            java.io.File file = new java.io.File(AccessibilityManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String runLocation = file.getParentFile().getAbsolutePath();
            String nativeFile;
            
            if (System.getProperty("os.arch").contains("x86")) {
                nativeFile = runLocation + "\\jni\\AccessibilityManagerWindows32.dll";
            }
            else {
                nativeFile = runLocation + "\\jni\\AccessibilityManagerWindows64.dll";
            }
            System.load(nativeFile);
            InitializeAccessibilityNative(org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window(DesktopDisplay.window));
        }
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public AccessibilityManager(){};
    public java.lang.Object me_ = null;
    
    private static volatile long frameCount = 0;
    
    /** Container to associate the Quorum item with its respective native pointer. */
    private static final HashMap<Item_, Long> ITEM_MAP = new HashMap<>();

    /* Native Windows API (Win32) Function Declarations */
    private static native void InitializeAccessibilityNative(long GLFW_WindowHandle);
    private native void ShutdownAccessibilityNative();
    
    /**
     Accessible Object Creation Functions
    */
    private native long CreateItemNative(long parent, String name, String description, Item_ item);
    private native long CreateButtonNative(long parent, String name, String description, Item_ item);
    private native long CreateCheckBoxNative(long parent, String name, String description, Item_ item);
    private native long CreateRadioButtonNative(long parent, String name, String description, Item_ item);
    private native long CreateTextBoxNative(long parent, String name, String description, TextBox_ quorumSelf);
    private native long CreateTextFieldNative(long parent, String name, String description, TextField_ quorumField);
    private native long CreateMenuBarNative(long parent, String name, Item_ item);
    private native long CreateTreeNative(long parent, String name, Item_ item);
    private native long CreateTabPaneNative(long parent, String name, Item_ item);
    private native long CreateTabNative(long parent, String name, Item_ item);
    private native long CreateToolBarNative(long parent, String name, Item_ item);
    private native long CreateDialogNative(long parent, String name, Item_ item);
    private native long CreateListNative(long parent, String name, Item_ item);
    private native long CreateListItemNative(long parent, String name, Item_ item);
    private native long CreateSpreadsheetNative(long parent, String name, Item_ item);
    private native long CreateCellNative(long parent, String name, Item_ item);
    private native long CreateTreeTableNative(long parent, String name, Item_ item);
    private native long CreateMenuItemNative(long parent, String name, String shortcut, boolean isMenu, long parentMenu, long parentMenuBar, Item_ item);
    private native long CreateTreeItemNative(long parent, String name, String description, boolean isMenu, boolean isExpanded, long parentMenu, long parentMenuBar, Item_ item);
    private native boolean RemoveNative(long itemToRemove);
    private native boolean RemoveMenuItemNative(long itemToRemove);
    private native boolean RemoveTreeItemNative(long itemToRemove);

    
    /**
    Accessible Object Event Response Functions
    */
    private native boolean InvokeButtonNative(long nativePointer);
    private native boolean UpdateToggleStatusNative(long nativePointer, boolean selected);
    private native boolean TextBoxTextSelectionChangedNative(long nativePointer, String TextValue, int startIndex, int endIndex);
    private native boolean TextFieldTextSelectionChangedNative(long nativePointer, String textValue, int startIndex, int endIndex);
    private native boolean UpdateCaretPositionNative(long nativePointer, String fullText, int caretIndex);
    private native long SetFocusNative(long nativePointer);
    private native boolean SelectMenuItemNative(long selectedMenuItem);
    private native boolean DeselectMenuItemNative(long menubar);
    private native boolean MenuExpandedNative(long nativePointer);
    private native boolean MenuCollapsedNative(long nativePointer);
    private native boolean SelectTreeItemNative(long selectedMenuItem);
    private native boolean SubtreeExpandedNative(long nativePointer);
    private native boolean SubtreeCollapsedNative(long nativePointer);
    private native void NotifyTextBoxNative(long nativePointer, String say);
    
    private native boolean IsScreenReaderListeningNative();
    
    // Shutdown: Closes the COM library on the native level.
    public void Shutdown()
    {
        ShutdownAccessibilityNative();
    }
    
    public boolean IsScreenReaderListening() {
        return IsScreenReaderListeningNative();
    }
    
    public boolean NativeAdd(Item_ item)
    {
        long nativePointer;
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        if (ITEM_MAP.get(item) != null)
            return true;

        if (code == AccessibilityCodes.NOT_ACCESSIBLE)
            return false;
        
        long parentLong = GetAccessibleParentHelper(item);
        switch(code)
        {
            case ITEM:
                nativePointer = CreateItemNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case CUSTOM:
                // Not implemented yet. Create as Item for now.
                nativePointer = CreateItemNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case CHECKBOX:
                nativePointer = CreateCheckBoxNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case RADIO_BUTTON:
                nativePointer = CreateRadioButtonNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case TABLE:
                nativePointer = CreateSpreadsheetNative(parentLong, item.GetName(), item);
                break;
            case TREE_TABLE:
                nativePointer = CreateTreeTableNative(parentLong, item.GetName(), item);
                break;
            case CELL:
                nativePointer = CreateCellNative(parentLong, item.GetName(), item);
                break;
            case LIST:
                nativePointer = CreateListNative(parentLong, item.GetName(), item);
                break;
            case LIST_ITEM:
                ListItem_ listItem = (ListItem_)item;
                List_ list = listItem.GetList();
                long listItemLong = GetItemPointer(list);
                nativePointer = CreateListItemNative(listItemLong, item.GetName(), item);
                break;
            case BUTTON:
                nativePointer = CreateButtonNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case TEXTBOX:
                TextBox_ textbox = (TextBox_)item;
                nativePointer = CreateTextBoxNative(parentLong, textbox.GetName(), textbox.GetDescription(), textbox);
                break;
            case TEXT_FIELD:
                TextField_ textField = (TextField_)item;
                nativePointer = CreateTextFieldNative(parentLong, textField.GetName(), textField.GetDescription(), textField);
                break;
            case MENU_BAR:
                nativePointer = CreateMenuBarNative(parentLong, item.GetName(), item);
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
                
                nativePointer = CreateMenuItemNative(parentLong, menuItem.GetName(), menuItem.GetShortcut(), menuItem.IsMenu(), parentMenu, menuBar, item);
                break;
            case TREE:
                nativePointer = CreateTreeNative(parentLong, item.GetName(), item);
                break;
            case TREE_ITEM:
            {
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
                    
                nativePointer = CreateTreeItemNative(parentLong, treeItem.GetName(), treeItem.GetDescription(), treeItem.IsSubtree(), treeItem.IsOpen(), parentSubtree, parentTree, treeItem);
            }
                break;
            case TOOLBAR:
                nativePointer = CreateToolBarNative(parentLong, item.GetName(), item);
                break;
            case TAB_PANE:
                nativePointer = CreateTabPaneNative(parentLong, item.GetName(), item);
                break;
            case TAB:
                Tab_ tab = (Tab_)item;
                TabPane_ pane = tab.GetTabPane();
                long paneLong = GetItemPointer(pane);
                nativePointer = CreateTabNative(paneLong, item.GetName(), item);
                break;
            case DIALOG:
                nativePointer = CreateDialogNative(parentLong, item.GetName(), item);
                break;
            default: //if the code doesn't match one of these, don't add it, including the NOT_ACCESSIBLE 
                nativePointer = 0;
                break;
        }
        
        if (nativePointer == 0) {
            return false;
        }

        // Add item and respective pointer to collection.
        ITEM_MAP.put(item, nativePointer);
        return true;
    }
    
    private long GetItemPointer(Item_ item) {
        long parentLong = -1;
        if(item != null) {
            Long l = ITEM_MAP.get(item);
            if(l != null) {
                parentLong = l;
            }
        }
        return parentLong;
    }
    
    private long GetAccessibleParentHelper(Item_ item) {
        Item_ parent = item.GetAccessibleParent();
        long parentLong = -1;
        if(parent != null) {
            Long l = ITEM_MAP.get( parent);
            if(l != null) {
                parentLong = l;
            }
        }
        return parentLong;
    }
    
    public void Notify(Item_ item, String say) {
        Long itemToRemove = ITEM_MAP.get(item);
        if(item instanceof TextBox_) {
            NotifyTextBoxNative(itemToRemove, say);
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
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case CUSTOM:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case BUTTON:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case RADIO_BUTTON:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case CHECKBOX:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case MENU_BAR:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case TREE:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case MENU_ITEM:
                wasRemoved = RemoveMenuItemNative(itemToRemove);
                break;
            case TREE_ITEM:
                wasRemoved = RemoveTreeItemNative(itemToRemove);
                break;
            case DIALOG:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            default:
                return false;
        }
        
        if (wasRemoved) {
            ITEM_MAP.remove(item);
        }
        return true;
    }
    
    public boolean Select(Item_ item)
    {
        Long selectedItem;
        AccessibilityCodes code = ACCESSIBILITYCODES_MAP.get(item.GetAccessibilityCode());
        boolean Selected;
        selectedItem = ITEM_MAP.get(item);

        if (selectedItem == null) {
            return false;
        }
        switch(code)
        {
            case MENU_ITEM:
                Selected = SelectMenuItemNative(selectedItem);
                break;
            case TREE_ITEM:
                Selected = SelectTreeItemNative(selectedItem);
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
                    Deselected = DeselectMenuItemNative(deselectedItem);
                else
                    Deselected = false;
                
                break;
            default:
                Deselected = false;
        }
        return Deselected;
    }
    
    /** SetFocus: Sets the focus to the specified item in UI Automation. This will also update what item has focus within the
               Accessibility Manager.
          Returns: boolean of success or failure.
    * */
    public boolean SetFocus(Item_ item)
    {
        Long nativePointer = ITEM_MAP.get(item);
        if (nativePointer != null)
        {
            boolean result = SetFocusNative(nativePointer) != 0;
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
        if (itemToChange == null) {
            return true;
        }
        
        switch(code)
        {
            case EXPANDED:
            {
                wasChanged = MenuExpandedNative(itemToChange);
                break;
            }
            case COLLAPSED:
            {
                wasChanged = MenuCollapsedNative(itemToChange);
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
        if (itemToChange == null) {
            return true;
        }
        switch(code)
        {
            case EXPANDED:
            {
                wasChanged = SubtreeExpandedNative(itemToChange);
                break;
            }
            case COLLAPSED:
            {
                wasChanged = SubtreeCollapsedNative(itemToChange);
                break;
            }
            default:
        }
        return wasChanged;
    }
    
    /** InvokeButton: Invoke a button through UI Automation
          Returns: boolean of success or failure.
    * */
    public boolean InvokeButton(Item_ button)
    {
        // Retreive native pointer for given object
        long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer != 0)
        {
            return InvokeButtonNative(nativePointer);
        }
        else
            return false;
    }
    
    /** UpdateToggleState: Update the selected status of a toggle button down at the native
                        level. This can be used for any button that can be toggled.
          Returns: boolean of success or failure
    * */
    public boolean UpdateToggleState(Item_ button, boolean selected)
    {
        Long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer != null)
        {
            return UpdateToggleStatusNative(nativePointer, selected);
        }
        else            
            return false;
    }
    
    public void TextSelectionChanged(TextBoxSelection_ selection)
    {
        TextBox_ textbox = selection.GetTextBox();
        if (textbox == null) {
            return;
        }
        Long nativePointer = ITEM_MAP.get((Item_)textbox);
        
        if (nativePointer == null) {
            return;
        }
        TextBoxTextSelectionChangedNative(nativePointer, textbox.GetText(),
            selection.GetStartIndex(), selection.GetEndIndex());
    }
    
    public void TextSelectionChanged(TextFieldSelection_ selection)
    {
        TextField_ textField = selection.GetTextField();
        if (textField == null) {
            return;
        }
        Long nativePointer = ITEM_MAP.get((Item_)textField);
        
        if (nativePointer == null) {
            return;
        }
        TextFieldTextSelectionChangedNative(nativePointer, textField.GetText(),
            selection.GetStartIndex(), selection.GetEndIndex());
    }
    
    public void CaretPositionChanged(Item_ item, Text_ fullText)
    {
        Long nativePointer = ITEM_MAP.get(item);
        if (nativePointer != null)
        {
            TextBox_ textbox = (TextBox_)item;
        
            UpdateCaretPositionNative(nativePointer, textbox.GetText(), textbox.GetCaretPosition());
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
    
    /*
    Used by the native layer. Given a Tree_ object, returns a pointer for the
    selected TreeItem, or 0 if there's no selection.
    */
    public static long GetTreeSelectionPointer(Tree_ tree)
    {
        TreeSelection_ selection = tree.GetSelection();
        Item_ item = selection.GetTreeItem();
        if (item == null)
            return 0;
        
        Long itemPointer = ITEM_MAP.get(item);
        
        if (itemPointer == null)
            return 0;
        else
            return itemPointer;
    }
    
    public static void SetTabSelection(TabPane_ tabs, Tab_ tab) {
        tabs.Select(tab);
    }
    /*
    This requests the selected tab from the tab pane and retrieves its pointer.
    */
    public static long GetTabPaneSelectionPointer(TabPane_ tabs) {
        TabPaneSelection_ selection = tabs.GetSelection();
        Item_ item = selection.GetTab();
        if (item == null) {
            return 0;
        }
        Long itemPointer = ITEM_MAP.get(item);
        
        if (itemPointer == null) {
            return 0;
        }
        else {
            return itemPointer;
        }
    }
}
