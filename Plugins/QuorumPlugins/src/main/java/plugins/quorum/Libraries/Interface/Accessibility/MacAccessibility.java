package plugins.quorum.Libraries.Interface.Accessibility;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.accesskit.Role;
import dev.accesskit.TreeUpdate;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Interface.Accessibility.accesskit.ButtonKit;
import plugins.quorum.Libraries.Interface.Accessibility.accesskit.ItemKit;
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

    static {
        try
        {
            java.io.File file = new java.io.File(AccessibilityManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String runLocation = file.getParentFile().getAbsolutePath();
            String nativeFile = runLocation + "/jni/libaccesskit_jni.dylib";
            System.load(nativeFile);
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

    public void  BoundsChanged(Item_ item) {}

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

    public void  ButtonActivated(Button_ button) {}

    public void  ToggleButtonToggled(ToggleButton_ button) {}

    public void  FocusChanged(FocusEvent_ event) {
        Item_ item = event.GetNewFocus();
        if(item != null) {
            ItemKit kit = items.get((item.GetHashCode()));
            if(kit != null) { //somehow send this to access kit.

            }
        }
    }

    public boolean NativeAdd(Item_ item)
    {
        int code = item.GetAccessibilityCode();
        Role role = null;
        ItemKit itemKit = null;
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || !item.IsShowing()) {
            return false;
        } else if (code == item.Get_Libraries_Interface_Item__ITEM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CUSTOM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            ButtonKit kit = new ButtonKit();
            kit.SetItem(item);
            items.put(item.GetHashCode(), kit);
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
        } else if (code == item.Get_Libraries_Interface_Item__MENU_BAR_()) {
        } else if (code == item.Get_Libraries_Interface_Item__MENU_ITEM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__PANE_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TREE_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TOOLBAR_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TAB_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TAB_PANE_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TABLE_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CELL_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()) {
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_()) {
        } else if (code == item.Get_Libraries_Interface_Item__DIALOG_()) {
        } else if (code == item.Get_Libraries_Interface_Item__POPUP_MENU_()) {
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()) {
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_CELL_()) {
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CHART_SECTION_()) {
        } else if (code == item.Get_Libraries_Interface_Item__CHART_ITEM_()) {
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
        }

        //no idea what this does, but something I presume
        //itemKit is the new thing to be added to the system
        TreeUpdate tree = new TreeUpdate();

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
