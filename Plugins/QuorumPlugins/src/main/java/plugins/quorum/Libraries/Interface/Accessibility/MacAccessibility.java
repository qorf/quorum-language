package plugins.quorum.Libraries.Interface.Accessibility;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.accesskit.*;
import org.lwjgl.glfw.GLFWNativeCocoa;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import plugins.quorum.Libraries.Interface.Accessibility.accesskit.*;
import plugins.quorum.Libraries.Interface.AccessibilityManager;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.ControlActivationEvent_;
import quorum.Libraries.Interface.Events.FocusEvent_;
import quorum.Libraries.Interface.Events.MenuChangeEvent_;
import quorum.Libraries.Interface.Events.ProgressBarValueChangedEvent_;
import quorum.Libraries.Interface.Events.SelectionEvent_;
import quorum.Libraries.Interface.Events.TextChangeEvent_;
import quorum.Libraries.Interface.Events.TreeChangeEvent_;
import quorum.Libraries.Interface.Events.TreeTableChangeEvent_;
import quorum.Libraries.Interface.Events.WindowFocusEvent_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;

/**
 *
 * @author andreasstefik
 */
public class MacAccessibility {
    public java.lang.Object me_ = null;
    private static MacosSubclassingAdapter adapter;
    private static boolean isWindowFocused = true;

    private static TreeUpdate weirdTreeState = null;
    private static final NodeId ROOT_ID = new NodeId(1);
    static NodeId focus = ROOT_ID;
    static {
        try
        {
            java.io.File file = new java.io.File(AccessibilityManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String runLocation = file.getParentFile().getAbsolutePath();
            String nativeFile = runLocation + "/jni/libaccesskit_jni.dylib";
            System.load(nativeFile);

            System.out.println("Attempting to get mac handle");
            long handle = GLFWNativeCocoa.glfwGetCocoaWindow(DesktopDisplay.window);

            NodeBuilder builder = new NodeBuilder(Role.WINDOW);
            Node root = builder.build();
            TreeUpdate result = new TreeUpdate();
            result.add(ROOT_ID, root);
            result.setTree(new Tree(ROOT_ID));
            weirdTreeState = result;

            adapter = MacosSubclassingAdapter.forWindow(handle, new TreeUpdateSupplier() {
                @Override
                public TreeUpdate get() {
                    System.out.println("FIred the weird state tree thing.");
                    SetTreeUpdateFocus(weirdTreeState);
                    return result;
                }
            });
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    HashMap<Integer, ItemKit> items = new HashMap<Integer, ItemKit>();

    private static native void InitializeAccessibilityNative(long GLFW_WindowHandle, String windowName);

    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {


    }

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {}

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void  SelectionChanged(SelectionEvent_ event) {}

    public void TextSelectionChanged(TextBoxSelection_ selection)
    {

    }

    public void TextSelectionChanged(TextFieldSelection_ selection)
    {

    }

    public boolean Select(Item_ item)
    {
        return false;
    }

    public void  ButtonActivated(Button_ button) {
        if(button == null) {
            return;
        }
        items.get(button.GetHashCode());
    }

    public void  ToggleButtonToggled(ToggleButton_ button) {}

    private static void SetTreeUpdateFocus(TreeUpdate update) {
        if (isWindowFocused) {
            System.out.println("Setting focus in set tree update to: " + focus);
            update.setFocus(focus);
        } else {
            update.clearFocus();
        }
    }

    public void  FocusChanged(FocusEvent_ event) {
        Item_ item = event.GetNewFocus();
        if(item != null) {
            ItemKit kit = items.get((item.GetHashCode()));
            if(kit != null) {
                focus = kit.GetNodeID();
                TreeUpdate update = new TreeUpdate();
                System.out.println("Setting focus to: " + item.GetName());
                SetTreeUpdateFocus(update);
            }
        }
    }

    public boolean NativeAdd(Item_ item)
    {
        isWindowFocused = true;
        int code = item.GetAccessibilityCode();
        Role role = null;
        ItemKit itemKit = null;
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || !item.IsShowing()) {
            return false;
        } else if (code == item.Get_Libraries_Interface_Item__ITEM_()) {
            itemKit = new ItemKit();
        } else if (code == item.Get_Libraries_Interface_Item__CUSTOM_()) {
            itemKit = new ItemKit();
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
            CheckboxKit kit = new CheckboxKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
            RadioButtonKit kit = new RadioButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            ButtonKit kit = new ButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
            ToggleButtonKit kit = new ToggleButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
            TextboxKit kit = new TextboxKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_BAR_()) {
            MenuBarKit kit = new MenuBarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_ITEM_()) {
            MenuItemKit kit = new MenuItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__PANE_()) {
            PaneKit kit = new PaneKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_()) {
            TreeKit kit = new TreeKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()) {
            TreeItemKit kit = new TreeItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TOOLBAR_()) {
            ToolbarKit kit = new ToolbarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_()) {
            ToggleButtonKit kit = new ToggleButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_PANE_()) {
            TabPaneKit kit = new TabPaneKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TABLE_()) {
            TableKit kit = new TableKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CELL_()) {
            CellKit kit = new CellKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
            TextFieldKit kit = new TextFieldKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()) {
            ListKit kit = new ListKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()) {
            ListItemKit kit = new ListItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_()) {
            TreeTableKit kit = new TreeTableKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__DIALOG_()) {
            DialogKit kit = new DialogKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__POPUP_MENU_()) {
            PopupMenuKit kit = new PopupMenuKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()) {
            ProgressBarKit kit = new ProgressBarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_CELL_()) {
            TreeTableKit kit = new TreeTableKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()) {
            GroupKit kit = new GroupKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()) {
            ChartKit kit = new ChartKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_SECTION_()) {
            ChartSectionKit kit = new ChartSectionKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_ITEM_()) {
            ChartItemKit kit = new ChartItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
            LabelKit kit = new LabelKit();
            itemKit = kit;
        }

        if(itemKit != null) {
            itemKit.SetItem(item);
            items.put(item.GetHashCode(), itemKit);
//            Node build = itemKit.Build();
//
//            NodeBuilder builder = new NodeBuilder(Role.WINDOW);
//            builder.addChild(itemKit.GetNodeID());
//
//            TreeUpdate update = new TreeUpdate();
//            update.add(itemKit.GetNodeID(), build);
//            update.setTree(new Tree(ROOT_ID));
//            SetTreeUpdateFocus(update);
        }


        //this is dumb so don't keep this


        System.out.println("About to update focus.");
        adapter.updateIfActive(new TreeUpdateSupplier() {
            @Override
            public TreeUpdate get() {
                TreeUpdate update = new TreeUpdate();
                NodeBuilder builder = new NodeBuilder(Role.WINDOW);
                Iterator<ItemKit> iterator = items.values().iterator();
                System.out.println("Starting Add");
                while(iterator.hasNext()) {
                    ItemKit next = iterator.next();
                    NodeId id = next.GetNodeID();
                    builder.addChild(id);
                    update.add(id, next.Build());
                    System.out.println("Item name: " + id.toString());
                }
                Node root = builder.build();
                update.add(ROOT_ID, root);
                update.setTree(new Tree(ROOT_ID));
                weirdTreeState = update;
                SetTreeUpdateFocus(update);
                return update;
            }
        });


        //SetTreeUpdateFocus(update);
        return false;
    }

    public boolean NativeRemove(Item_ item)
    {
        if(item != null) {
            ItemKit kit = items.get((item.GetHashCode()));
            if(kit != null) { //somehow remove this from the system

            }
        }
        return false;
    }

    public void  MenuChanged(MenuChangeEvent_ event) {}

    public void  TreeChanged(TreeChangeEvent_ event) {}

    public void  TreeTableChanged(TreeTableChangeEvent_ event) {}

    public void  ControlActivated(ControlActivationEvent_ event) {}

    public void  TextChanged(TextChangeEvent_ event) {}

    public void  WindowFocusChanged(WindowFocusEvent_ event) {}

    public void  Notify(Item_ item, String value) {}

    public void  Notify(Item_ item, String value, int notificationType) {}

    public void  Shutdown() {}
}
