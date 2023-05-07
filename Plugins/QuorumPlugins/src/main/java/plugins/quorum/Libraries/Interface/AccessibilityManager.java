package plugins.quorum.Libraries.Interface;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import plugins.quorum.Libraries.Game.DesktopDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Controls.ButtonGroup_;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.Cell_;
import quorum.Libraries.Interface.Controls.Column_;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.MenuItem_;
import quorum.Libraries.Interface.Controls.MenuRoot_;
import quorum.Libraries.Interface.Controls.ProgressBar_;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Controls.Spreadsheet_;
import quorum.Libraries.Interface.Controls.TabPane_;
import quorum.Libraries.Interface.Controls.Tab_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.TextStyles.TextHighlight_;
import quorum.Libraries.Interface.Controls.TextStyles.TextStyle_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Controls.TreeTableCell_;
import quorum.Libraries.Interface.Controls.TreeTableColumn_;
import quorum.Libraries.Interface.Controls.TreeTable_;
import quorum.Libraries.Interface.Controls.Tree_;
import quorum.Libraries.Interface.Events.MenuChangeEvent_;
import quorum.Libraries.Interface.Events.TextChangeEvent_;
import quorum.Libraries.Interface.Events.TreeChangeEvent_;
import quorum.Libraries.Interface.Events.TreeTableChangeEvent;
import quorum.Libraries.Interface.Events.TreeTableChangeEvent_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Selections.SpreadsheetSelection_;
import quorum.Libraries.Interface.Selections.TabPaneSelection_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Selections.TreeSelection_;
import quorum.Libraries.Language.Object_;


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
        TREE_TABLE_CELL,
        TEXT_FIELD,
        LIST,
        LIST_ITEM,
        TREE_TABLE,
        DIALOG,
        POPUP_MENU,
        PROGRESS_BAR,
        GROUP
    }
            
    enum MenuChanges {
        EXPANDED,
        COLLAPSED;
    }
    
    enum TreeChanges {
        EXPANDED,
        COLLAPSED;
    }
    
    enum TreeTableChanges {
        EXPANDED,
        COLLAPSED;
    }
    
    private static final HashMap<Integer, AccessibilityCodes> ACCESSIBILITYCODES_MAP = new HashMap<>();
    private static final HashMap<Integer, MenuChanges> MENUCHANGES_MAP = new HashMap<>();
    private static final HashMap<Integer, TreeChanges> TREECHANGES_MAP = new HashMap<>();
    private static final HashMap<Integer, TreeTableChanges> TREE_TABLE_CHANGES_MAP = new HashMap<>();
    private static final quorum.Libraries.Interface.Item ACCESSIBILITYCODES = new quorum.Libraries.Interface.Item();
    private static final quorum.Libraries.Interface.Events.MenuChangeEvent MENUCHANGECODES = new quorum.Libraries.Interface.Events.MenuChangeEvent();
    private static final quorum.Libraries.Interface.Events.TreeChangeEvent TREECHANGECODES = new quorum.Libraries.Interface.Events.TreeChangeEvent();
    private static final TreeTableChangeEvent TREE_TABLE_CHANGE_CODES = new quorum.Libraries.Interface.Events.TreeTableChangeEvent();
    
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
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_TABLE_CELL_(), AccessibilityCodes.TREE_TABLE_CELL);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXT_FIELD_(), AccessibilityCodes.TEXT_FIELD);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__LIST_(), AccessibilityCodes.LIST);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__LIST_ITEM_(), AccessibilityCodes.LIST_ITEM);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TREE_TABLE_(), AccessibilityCodes.TREE_TABLE);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__TEXT_FIELD_(), AccessibilityCodes.TEXT_FIELD);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__DIALOG_(), AccessibilityCodes.DIALOG);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__POPUP_MENU_(), AccessibilityCodes.POPUP_MENU);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__PROGRESS_BAR_(), AccessibilityCodes.PROGRESS_BAR);
        ACCESSIBILITYCODES_MAP.put(ACCESSIBILITYCODES.Get_Libraries_Interface_Item__GROUP_(), AccessibilityCodes.GROUP);
        
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__OPENED_(), MenuChanges.EXPANDED);
        MENUCHANGES_MAP.put(MENUCHANGECODES.Get_Libraries_Interface_Events_MenuChangeEvent__CLOSED_(), MenuChanges.COLLAPSED);
        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__OPENED_(), TreeChanges.EXPANDED);
        TREECHANGES_MAP.put(TREECHANGECODES.Get_Libraries_Interface_Events_TreeChangeEvent__CLOSED_(), TreeChanges.COLLAPSED);
        TREE_TABLE_CHANGES_MAP.put(TREE_TABLE_CHANGE_CODES.Get_Libraries_Interface_Events_TreeTableChangeEvent__OPENED_(), TreeTableChanges.EXPANDED);
        TREE_TABLE_CHANGES_MAP.put(TREE_TABLE_CHANGE_CODES.Get_Libraries_Interface_Events_TreeTableChangeEvent__CLOSED_(), TreeTableChanges.COLLAPSED);
        
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
            String windowTitle = GameStateManager.game.GetDesktopConfiguration().Get_Libraries_Game_DesktopConfiguration__title_();
            InitializeAccessibilityNative(org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window(DesktopDisplay.window), windowTitle);
//            DesktopDisplay.ForceWindowVisible();
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
    private static native void InitializeAccessibilityNative(long GLFW_WindowHandle, String windowName);
    private native void ShutdownAccessibilityNative();
    
    /**
     Accessible Object Creation Functions
    */
    private native long CreateItemNative(long parent, String name, String description, Item_ item);
    private native long CreateButtonNative(long parent, String name, String description, Item_ item);
    private native long CreateCheckBoxNative(long parent, String name, String description, Item_ item);
    private native long CreateRadioButtonNative(long parent, String name, String description, long group, Item_ item);
    private native long CreateTextBoxNative(long parent, String name, String description, TextBox_ quorumSelf);
    private native long CreateTextFieldNative(long parent, String name, String description, TextField_ quorumField);
    private native long CreateMenuBarNative(long parent, String name, Item_ item);
    private native long CreateMenuNative(long parent, String name, Item_ item); //popup menus are called Menus in UIA
    private native long CreateProgressBarNative(long parent, String name, String description, Item_ item);
    private native long CreateTreeNative(long parent, String name, Item_ item);
    private native long CreateTabPaneNative(long parent, String name, Item_ item);
    private native long CreateTabNative(long parent, String name, Item_ item);
    private native long CreateToolBarNative(long parent, String name, Item_ item);
    private native long CreateDialogNative(long parent, String name, Item_ item);
    private native long CreateListNative(long parent, String name, Item_ item);
    private native long CreateListItemNative(long parent, String name, Item_ item);
    private native long CreateSpreadsheetNative(long parent, String name, Item_ item);
    private native long CreateCellNative(long parent, String name, Item_ item, boolean isTreeCell);
    private native long CreateTreeTableNative(long parent, String name, Item_ item);
    private native long CreateMenuItemNative(long parent, String name, String shortcut, boolean isMenu, long parentMenu, long parentMenuBar, Item_ item, boolean isPopupMenu);
    private native long CreateTreeItemNative(long parent, String name, String description, boolean isMenu, boolean isExpanded, long parentMenu, long parentMenuBar, Item_ item);
    private native long CreateGroupNative(long parent, String name, String description, Item_ item);
    private native boolean RemoveNative(long itemToRemove);
    private native boolean RemovePopupMenuItemNative(long itemToRemove);
    private native boolean RemoveMenuItemNative(long itemToRemove);
    private native boolean RemoveTreeItemNative(long itemToRemove);

    
    /**
    Accessible Object Event Response Functions
    */
    private native boolean ButtonInvoked(long nativePointer);
    private native boolean ProgressBarValueChanged(long nativePointer, double value);
    private native boolean ToggleButtonToggled(long nativePointer, boolean selected);
    private native boolean NameChangedNative(long nativePointer, String name);
    private native boolean DescriptionChangedNative(long nativePointer, String description);
    private native boolean TextBoxTextSelectionChangedNative(long nativePointer);
    private native boolean TextFieldTextSelectionChangedNative(long nativePointer);
    private native boolean TextBoxTextChangedNative(long nativePointer);
    private native boolean TextFieldTextChangedNative(long nativePointer);
    private native boolean TextFieldPasswordPropertyChangedNative(long nativePointer, boolean isPassword);
    private native long SetFocusNative(long nativePointer);
    private native boolean SelectMenuItemNative(long selectedMenuItem);
    private native boolean SelectListItemNative(long selectedListItem);
    private native boolean DeselectMenuItemNative(long menubar);
    private native boolean SelectCellNative(long cellPointer);
    private native boolean SelectRadioButtonNative(long buttonPointer);
    private native boolean MenuExpandedNative(long nativePointer);
    private native boolean MenuCollapsedNative(long nativePointer);
    private native boolean SelectTreeItemNative(long selectedMenuItem);
    private native boolean SubtreeExpandedNative(long nativePointer);
    private native boolean SubtreeCollapsedNative(long nativePointer);
    private native boolean TreeTableRowExpandedNative(long nativePointer);
    private native boolean TreeTableRowCollapsedNative(long nativePointer);
    private native void NotifyTextBoxNative(long nativePointer, String say);
    private native void NotifyClients(long nativePointer, String say, int type);
    private native void ReselectTreeItemNative(long nativePointer);
    
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
                
            // For now, consider TOGGLE_BUTTON and CHECKBOX to be identical cases.
            case TOGGLE_BUTTON:
            case CHECKBOX:
                nativePointer = CreateCheckBoxNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case RADIO_BUTTON:
                RadioButton_ button = (RadioButton_)item;
                ButtonGroup_ group = button.GetButtonGroup();
                long groupPointer = GetItemPointer(group);
                if (groupPointer < 0)
                    groupPointer = 0;
                nativePointer = CreateRadioButtonNative(parentLong, item.GetName(), item.GetDescription(), groupPointer, item);
                break;
            case TABLE:
                nativePointer = CreateSpreadsheetNative(parentLong, item.GetName(), item);
                break;
            case TREE_TABLE:
                nativePointer = CreateTreeTableNative(parentLong, item.GetName(), item);
                break;
            case CELL:
                nativePointer = CreateCellNative(parentLong, item.GetName(), item, false);
                break;
            case TREE_TABLE_CELL:
                nativePointer = CreateCellNative(parentLong, item.GetName(), item, true);
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
            case GROUP:
                nativePointer = CreateGroupNative(parentLong, item.GetName(), item.GetDescription(), item);
                break;
            case TEXTBOX:
                TextBox_ textbox = (TextBox_)item;
                nativePointer = CreateTextBoxNative(parentLong, textbox.GetName(), textbox.GetDescription(), textbox);
                if (nativePointer != 0 && textbox.IsEmpty() == false)
                {
                    // Push down the textbox's starting text.
                    TextBoxTextChangedNative(nativePointer);
                }
                
                break;
            case TEXT_FIELD:
                TextField_ textField = (TextField_)item;
                nativePointer = CreateTextFieldNative(parentLong, textField.GetName(), textField.GetDescription(), textField);
                if (nativePointer != 0 && textField.IsEmpty() == false)
                {
                    // Push down the textbox's starting text.
                    TextFieldTextChangedNative(nativePointer);
                }
                break;
            case MENU_BAR:
                nativePointer = CreateMenuBarNative(parentLong, item.GetName(), item);
                break;
            case POPUP_MENU:
                nativePointer = CreateMenuNative(parentLong, item.GetName(), item);
                break;
            case PROGRESS_BAR:
                nativePointer = CreateProgressBarNative(parentLong, item.GetName(), item.GetDescription(), item);
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
                
                if(menuItem.GetAccessibilityCode() == menuItem.Get_Libraries_Interface_Item__POPUP_MENU_()) {
                    nativePointer = CreateMenuItemNative(parentLong, menuItem.GetName(), menuItem.GetShortcut(), menuItem.IsMenu(), parentMenu, menuBar, item, true);
                } else {
                    nativePointer = CreateMenuItemNative(parentLong, menuItem.GetName(), menuItem.GetShortcut(), menuItem.IsMenu(), parentMenu, menuBar, item, false);
                }
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
                    
                nativePointer = CreateTreeItemNative(parentLong, treeItem.GetName(), 
                        treeItem.GetDescription(), treeItem.IsSubtree(), 
                        treeItem.IsOpen(), parentSubtree, parentTree, treeItem);
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
    
    public void Notify(Item_ item, String say, int type) {
        Long itemToNotify = ITEM_MAP.get(item);
        if (itemToNotify == null)
            return;
        
        NotifyClients(itemToNotify, say, type);
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
            case MENU_ITEM:
                MenuItem_ mi = (MenuItem_) item;
                MenuRoot_ root = mi.GetMenuRoot();
                int parentCode = root.GetAccessibilityCode();
                if(parentCode == root.Get_Libraries_Interface_Item__POPUP_MENU_()) {
                    wasRemoved = RemovePopupMenuItemNative(itemToRemove);
                } else {
                    wasRemoved = RemoveMenuItemNative(itemToRemove);
                }
                break;
            case TREE_ITEM:
                wasRemoved = RemoveTreeItemNative(itemToRemove);
                break;
            case TABLE:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            case CELL:
                wasRemoved = RemoveNative(itemToRemove);
                break;
            default:
                wasRemoved = RemoveNative(itemToRemove);
                break;
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
        boolean selected;
        selectedItem = ITEM_MAP.get(item);

        if (selectedItem == null) {
            return false;
        }
        switch(code)
        {
            case MENU_ITEM:
                selected = SelectMenuItemNative(selectedItem);
                break;
            case TREE_ITEM:
                selected = SelectTreeItemNative(selectedItem);
                break;
            case LIST_ITEM:
                selected = SelectListItemNative(selectedItem);
                break;
            case CELL:
            case TREE_TABLE_CELL:
                selected = SelectCellNative(selectedItem);
                break;
            case RADIO_BUTTON:
                selected = SelectRadioButtonNative(selectedItem);
            default:
                selected = false;
        }
        return selected;
    }
    
    public void NativeReselect(TreeItem_ item)
    {
        Long selectingItem = ITEM_MAP.get(item);
        if (selectingItem == null)
            return;
        
        ReselectTreeItemNative(selectingItem);
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
    
    public boolean NativeTreeTableChanged(TreeTableChangeEvent_ event)
    {
        Array_ cells = event.GetTreeTableCells();
        if (cells.IsEmpty())
            return false;
        
        Item_ item = (Item_)cells.Get(0);
        Long itemToChange = ITEM_MAP.get(item);
        TreeTableChanges code = TREE_TABLE_CHANGES_MAP.get(event.GetEventType());
        boolean wasChanged = false;
        
        // Retreive native pointer for given object
        if (itemToChange == null) {
            return true;
        }
        switch(code)
        {
            case EXPANDED:
            {
                wasChanged = TreeTableRowExpandedNative(itemToChange);
                break;
            }
            case COLLAPSED:
            {
                wasChanged = TreeTableRowCollapsedNative(itemToChange);
                break;
            }
            default:
        }
        return wasChanged;
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
        TextBoxTextSelectionChangedNative(nativePointer);
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
        TextFieldTextSelectionChangedNative(nativePointer);
    }
    
    public void NativeTextChanged(TextBox_ textBox, TextChangeEvent_ event)
    {
        if (ITEM_MAP.containsKey(textBox) == false)
            return;
        
        long nativePointer = ITEM_MAP.get((Item_)textBox);
        
        int length = event.GetDeletedText().length();
        
        TextBoxTextChangedNative(nativePointer);
    }
    
    public void NativeTextChanged(TextField_ textField, TextChangeEvent_ event)
    {
        if (ITEM_MAP.containsKey(textField) == false)
            return;
        
        long nativePointer = ITEM_MAP.get((Item_)textField);
        TextFieldTextChangedNative(nativePointer);
    }
    
    public void TextFieldUpdatePassword(TextField_ item) {
        if (ITEM_MAP.containsKey(item) == false)
            return;
        
        long nativePointer = ITEM_MAP.get(item);
        TextFieldPasswordPropertyChangedNative(nativePointer, item.IsPassword());
    }
    
    public void ProgressBarValueChanged(ProgressBar_ item) {
        if (ITEM_MAP.containsKey(item) == false)
            return;
        
        long nativePointer = ITEM_MAP.get(item);
        
        ProgressBarValueChanged(nativePointer, (int)item.GetValue());
    }
    
    public void NameChanged(Item_ item)
    {
        if (ITEM_MAP.containsKey(item) == false)
            return;
        
        long nativePointer = ITEM_MAP.get(item);
        
        NameChangedNative(nativePointer, item.GetName());
    }
    
    public void DescriptionChanged(Item_ item)
    {
        if (ITEM_MAP.containsKey(item) == false)
            return;
        
        long nativePointer = ITEM_MAP.get(item);
        
        DescriptionChangedNative(nativePointer, item.GetDescription());
    }
    
    public void BoundsChanged(Item_ item) {}
    
    public boolean OnButtonActivation(Button_ button)
    {
        if (ITEM_MAP.containsKey(button) == false)
            return false;
        
        // Retrieve native pointer for given object
        long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer == 0)
        {
            return false;
        }
        
        if (button instanceof Tab_)
        {
            // Inform the native layer that the Tab is being selected, unless it's
            // otherwise handled by the system.
            return false;
        }
        else if (button instanceof RadioButton_)
        {
            // Inform the native layer that the RadioButton is being selected,
            // unless it's otherwise handled by the system.
            return false;
        }
        else if (button instanceof ToggleButton_)
        {
            // Inform the native layer that the ToggleButton is being toggled,
            // unless it's otherwise handled by the system.
            return false;
        }
        else
        {
            // Assume it's a normal button.
            return ButtonInvoked(nativePointer);
        }
    }
    
    public static void ActivateButton(Button_ button)
    {
        // Some additional logic may be needed here for other types.
        button.Activate();
    }
    
    public boolean OnToggleButtonToggle(ToggleButton_ button)
    {
        if (ITEM_MAP.containsKey(button) == false)
            return false;
        
        // Retrieve native pointer for given object
        long nativePointer = ITEM_MAP.get(button);
        
        if (nativePointer == 0)
            return false;
        
        if (button instanceof Tab_)
        {
            // Tabs don't typically implement the toggle provider in UIA.
            return false;
        }
        else if (button instanceof RadioButton_)
        {
            // RadioButtons in UIA explicitly shouldn't support the toggle provider.
            return false;
        }
        else
        {
            return ToggleButtonToggled(nativePointer, button.GetToggleState());
        }
    }
    
    public static void ToggleToggleButton(ToggleButton_ button)
    {
        // Some additional logic may be needed here for different types.
        button.SetToggleState(!button.GetToggleState());
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
    
    /*
    Used by the native layer. Given a TreeItem_ object, returns how many items
    are in the set that this TreeItem is in.
    */
    public static int GetTreeItemSetSize(TreeItem_ item)
    {
        TreeItem_ parent = item.GetParentTreeItem();
        if (parent == null)
        {
            Tree_ tree = item.GetTree();
            if (tree == null)
                return 1;
            else
                return tree.GetSize();
        }
        else
        {
            return parent.GetSize();
        }
    }
    
    /*
    Used by the native layer. Given a TreeItem_ object, returns the 1-indexed
    position of that TreeItem within its set.
    */
    public static int GetTreeItemSetPosition(TreeItem_ item)
    {
        Array_ treeItems;
        TreeItem_ parent = item.GetParentTreeItem();
        
        if (parent != null)
            treeItems = parent.Get_Libraries_Interface_Controls_TreeItem__treeItems_();
        else
        {
            Tree_ tree = item.GetTree();
            if (tree != null)
                treeItems = tree.Get_Libraries_Interface_Controls_Tree__treeItems_();
            else
                return 1;
        }
        
        for (int i = 0; i < treeItems.GetSize(); i++)
        {
            if (treeItems.Get(i) == item)
                return i + 1;
        }
        
        return 1;
    }
    
    /*
    Used by the native layer. Given a MenuItem_ object, returns how many items
    are in the set that this MenuItem is in.
    */
    public static int GetMenuItemSetSize(MenuItem_ item)
    {
        MenuItem_ parent = item.GetParentMenu();
        if (parent == null)
        {
            MenuRoot_ root = item.GetMenuRoot();
            if (root == null)
                return 1;
            else
                return root.GetSize();
        }
        else
        {
            return parent.GetSize();
        }
    }
    
    /*
    Used by the native layer. Given a MenuItem_ object, returns the 1-indexed
    position of that MenuItem within its set.
    */
    public static int GetMenuItemSetPosition(MenuItem_ item)
    {
        MenuItem_ parent = item.GetParentMenu();
        
        if (parent != null)
        {
            Array_ menuItems = parent.Get_Libraries_Interface_Controls_MenuItem__menuItems_();
            for (int i = 0; i < menuItems.GetSize(); i++)
            {
                if (menuItems.Get(i) == item)
                    return i + 1;
            }
        }
        else
        {
            MenuRoot_ root = item.GetMenuRoot();
            if (root != null)
            {
                Iterator_ iterator = root.GetIterator();
                int i = 0;
                while (iterator.HasNext())
                {
                    i = i + 1;
                    if (iterator.Next() == item)
                        return i;
                }
            }
        }
        
        return 1;
    }
    
    /*
    Used by the native layer. Given a Spreadsheet_ object, returns a pointer for
    the selected TreeItem, or 0 if there's no selection.
    */
    public static long GetSpreadsheetSelectionPointer(Spreadsheet_ sheet)
    {
        SpreadsheetSelection_ selection = sheet.GetSelection();
        Item_ item = selection.Get();
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
    
    /*
    Used by the native layer. Returns the index of the column containing the
    provided cell. The cell given might be a Cell object or a TreeTableCell. If
    the value couldn't be retrieved for some reason, this returns -1.
    */
    public static int GetCellColumnIndex(Object_ object)
    {
        if (object instanceof Cell_)
        {
            Cell_ cell = (Cell_)object;
            Column_ column = cell.GetColumn();
            
            if (column != null)
            {
                Spreadsheet_ spreadsheet = cell.GetSpreadsheet();
                if (spreadsheet != null)
                    return spreadsheet.GetColumnIndex(column);
            }
        }
        else if (object instanceof TreeTableCell_)
        {
            TreeTableCell_ cell = (TreeTableCell_)object;
            TreeTableColumn_ column = cell.GetColumn();
            
            if (column != null)
            {
                TreeTable_ treeTable = cell.GetTreeTable();
                if (treeTable != null)
                    return treeTable.GetColumnIndex(column);
            }
        }
        
        // If we can't retrieve the correct position, return -1 to indicate failure.
        return -1;
    }
    
    /*
    Used by the native layer. Returns the index of the row containing a cell
    (its y-index, essentially). The cell given might be a Cell object or a
    TreeTableCell. If the value can't be retrieved, this returns -1.
    */
    public static int GetCellRowIndex(Object_ object)
    {
        if (object instanceof Cell_)
        {
            Cell_ cell = (Cell_)object;
            Column_ column = cell.GetColumn();
            
            if (column != null)
            {
                return column.GetCellIndex(cell);
            }
        }
        else if (object instanceof TreeTableCell_)
        {
            TreeTableCell_ cell = (TreeTableCell_)object;
            TreeTableColumn_ column = cell.GetColumn();
            
            if (column != null)
            {
                return column.GetCellIndex(cell);
            }
        }
        
        // If we can't retrieve the correct position, return -1 to indicate failure.
        return -1;
    }
    
    public static long GetTableItem(Object_ object, int row, int column)
    {
        Spreadsheet_ spreadsheet = null;
        TreeTable_ treeTable = null;
        
        if (object instanceof Spreadsheet_)
        {
            spreadsheet = (Spreadsheet_)object;
        }
        else if (object instanceof TreeTable_)
        {
            treeTable = (TreeTable_)object;
        }
        
        Item_ item = null;
        if (spreadsheet != null)
        {
            item = spreadsheet.GetCell(row, column);
        }
        else if (treeTable != null)
        {
            item = treeTable.GetCell(row, column);
        }
        
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
    
    
    /*
    Used by the native layer. Returns the total number of columns in a TreeTable
    or Spreadsheet. If a Cell or TreeTableCell is given, gives information about
    the table structure that contains that cell. If the value can't be retrieved,
    this returns -1.
    */
    public static int GetTableColumnsSize(Object_ object)
    {
        Spreadsheet_ spreadsheet = null;
        TreeTable_ treeTable = null;
        
        if (object instanceof Spreadsheet_)
        {
            spreadsheet = (Spreadsheet_)object;
        }
        else if (object instanceof TreeTable_)
        {
            treeTable = (TreeTable_)object;
        }
        else if (object instanceof Cell_)
        {
            spreadsheet = ((Cell_)object).GetSpreadsheet();
        }
        else if (object instanceof TreeTableCell_)
        {
            treeTable = ((TreeTableCell_)object).GetTreeTable();
        }
        
        if (spreadsheet != null)
        {
            return spreadsheet.GetColumnsSize();
        }
        else if (treeTable != null)
        {
            return treeTable.GetColumnsSize();
        }
        
        // If we can't retrieve the size, return -1 to indicate failure.
        return -1;
    }
    
    /*
    Used by the native layer. Returns the total number of rows in a TreeTable
    or Spreadsheet. If a Cell or TreeTableCell is given, gives information about
    the table structure that contains that cell. If the value can't be retrieved,
    this returns -1.
    */
    public static int GetTableRowsSize(Object_ object)
    {
        Spreadsheet_ spreadsheet = null;
        TreeTable_ treeTable = null;
        
        if (object instanceof Spreadsheet_)
        {
            spreadsheet = (Spreadsheet_)object;
        }
        else if (object instanceof TreeTable_)
        {
            treeTable = (TreeTable_)object;
        }
        else if (object instanceof Cell_)
        {
            spreadsheet = ((Cell_)object).GetSpreadsheet();
        }
        else if (object instanceof TreeTableCell_)
        {
            treeTable = ((TreeTableCell_)object).GetTreeTable();
        }
        
        if (spreadsheet != null)
        {
            return spreadsheet.GetRowsSize();
        }
        else if (treeTable != null)
        {
            /*
            TO-DO: The TreeTable doesn't currently have a way to calculate its number of rows.
            It's not clear if this should be number of top-most rows, currently visible rows, or all total rows.
            */
            return 0;
        }
        
        // If we can't retrieve the size, return -1 to indicate failure.
        return -1;
    }
    
    /*
    Used by the native layer. Returns the text value of a given cell.
    The cell given might be a Cell object or a TreeTableCell. 
    If the value can't be retrieved, this returns the empty string.
    */
    public static String GetCellText(Object_ object)
    {
        if (object instanceof Cell_)
        {
            Cell_ cell = (Cell_)object;
            return cell.GetText();
        }
        else if (object instanceof TreeTableCell_)
        {
            TreeTableCell_ cell = (TreeTableCell_)object;
            return cell.GetText();
        }
        
        // If we can't retrieve the real text, return the empty string as a default.
        return "";
    }
    
    public static String GetListItemText(Object_ object)
    {
        if (object instanceof ListItem_)
        {
            ListItem_ item = (ListItem_)object;
            return item.GetText();
        }
        
        // If we can't retrieve the real text, return the empty string as a default.
        return "";
    }
    
    public static double[] GetBoundingRectangle(Object_ item)
    {
        // The order of values in the bounding box is left, top, width, height.
        double[] bounds = new double[4];
        
        DesktopDisplay_ display = (DesktopDisplay_)GameStateManager.display;
        
        if (display == null)
        {
            for (int i = 0; i < bounds.length; i++)
                bounds[i] = 0;
            
            return bounds;
        }
        
        double windowX = display.GetDisplayX();
        double windowY = display.GetDisplayY();
        
        /*
        We need to know the height to correct for the difference in y
        direction between Windows and Quorum (0 is at the top in Windows, and
        and the bottom in Quorum).
        */
        double windowHeight = display.GetHeight();
        
        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_) item).GetScreenY());
            
            if (itemX == Double.NaN)
                itemX = 0;
            
            if (itemY == Double.NaN)
                itemY = 0;
            
            bounds[0] = windowX + itemX;
            bounds[1] = windowY + (windowHeight - (itemY + ((Item2D_) item).GetHeight()));
            bounds[2] = ((Item2D_) item).GetWidth();
            bounds[3] = ((Item2D_) item).GetHeight();
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.
            
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
            
            bounds[0] = windowX + rectangle.GetX();
            bounds[1] = windowY + (windowHeight - (rectangle.GetY() + rectangle.GetHeight()));
            bounds[2] = rectangle.GetWidth();
            bounds[3] = rectangle.GetHeight();
        }
        else
        {
            // If we don't know what it is, we set the values to 0 to provide a safe default.
            for (int i = 0; i < bounds.length; i++)
                bounds[i] = 0;
        }
        
        return bounds;
    }
    
    public static double[] GetTextBoundingRectangle(Object_ object, int startIndex, int endIndex)
    {
        // The order of values in the bounding box is left, top, width, height.
        double[] bounds = new double[4];
        
//        DesktopDisplay_ display = (DesktopDisplay_)GameStateManager.display;
//        
//        if (display == null)
//        {
//            for (int i = 0; i < bounds.length; i++)
//                bounds[i] = 0;
//            
//            return bounds;
//        }
//        
//        double windowX = display.GetDisplayX();
//        double windowY = display.GetDisplayY();
//        
//        /*
//        We need to know the height to correct for the difference in y
//        direction between Windows and Quorum (0 is at the top in Windows, and
//        and the bottom in Quorum).
//        */
//        double windowHeight = display.GetHeight();
//        
//        if (object instanceof TextBox_)
//        {
//            TextBox_ textBox = (TextBox_)object;
//            Rectangle_ rectangle = textBox.GetTextBoundingBox(startIndex, endIndex);
//            
//            double itemX = ((Item2D_)item).GetScreenX();
//            double itemY = (((Item2D_) item).GetScreenY());
//            
//            if (itemX == Double.NaN)
//                itemX = 0;
//            
//            if (itemY == Double.NaN)
//                itemY = 0;
//            
//            bounds[0] = windowX + itemX;
//            bounds[1] = windowY + (windowHeight - (itemY + ((Item2D_) item).GetHeight()));
//            bounds[2] = ((Item2D_) item).GetWidth();
//            bounds[3] = ((Item2D_) item).GetHeight();
//        }
//        else if (item instanceof Item3D_)
//        {
//            // This is only a place holder, to place a small box roughly at the
//            // center of a 3D object in the screen. To calculate this correctly,
//            // check how we calculate mouse input detection for 3D objects.
//            
//            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
//            
//            bounds[0] = windowX + rectangle.GetX();
//            bounds[1] = windowY + (windowHeight - (rectangle.GetY() + rectangle.GetHeight()));
//            bounds[2] = rectangle.GetWidth();
//            bounds[3] = rectangle.GetHeight();
//        }
//        else
//        {
//            // If we don't know what it is, we set the values to 0 to provide a safe default.
//            for (int i = 0; i < bounds.length; i++)
//                bounds[i] = 0;
//        }
        
        return bounds;
    }
    
    public static void PauseEventPolling()
    {
        ((quorum.Libraries.Game.DesktopDisplay)GameStateManager.display).plugin_.PauseEventPolling();
    }
    
    public static boolean IsErrorAtIndex(TextBox_ textBox, int index)
    {
        Array_ array = textBox.GetTextStylesAtIndex(index);
        for (int i = 0; i < array.GetSize(); i++)
        {
            TextHighlight_ highlight = ((TextStyle_)array.Get(i)).GetHighlight();
            if (highlight != null && highlight.IsError())
                return true;
        }
            
        return false;
    }
    
    public static boolean IsIndexAtEndOfLine(TextBox_ textBox, int globalIndex)
    {
        int line = textBox.GetLineIndexOfCharacter(globalIndex);
        int lineIndex = globalIndex - textBox.GetIndexOfLine(line);
        String text = textBox.GetLine(line);
        String separator = textBox.GetLineSeparator();
        
        return lineIndex == (text.length() - separator.length()) && text.endsWith(separator);
    }
}
