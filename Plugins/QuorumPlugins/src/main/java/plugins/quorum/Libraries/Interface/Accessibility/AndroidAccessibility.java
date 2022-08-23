package plugins.quorum.Libraries.Interface.Accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import plugins.quorum.Libraries.Game.AndroidApplication;
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
import android.view.accessibility.AccessibilityManager;

/**
 *
 * @author andreasstefik
 */
public class AndroidAccessibility {
    public java.lang.Object me_ = null;
    
    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {}

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {}

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void  SelectionChanged(SelectionEvent_ event) {}

    public void  ButtonActivated(Button_ button) {}

    public void  ToggleButtonToggled(ToggleButton_ button) {}

    public void  FocusChanged(FocusEvent_ event) {
        Item_ item = event.Get_Libraries_Interface_Events_FocusEvent__gainedFocus_();
        if(item == null) {
            return;
        }

        int id = item.GetHashCode();
    }

    public void  Add(Item_ item) {
        AccessibilityManager accessibilityManager = AndroidApplication.accessibilityManager;
        if(accessibilityManager == null) {
            return;
        }

        String name = item.GetName();
        String description = item.GetDescription();
        int id = item.GetHashCode();
        int code = item.GetAccessibilityCode();
        if(code == item.Get_Libraries_Interface_Item__BUTTON_()) { //this is a button.
            System.out.println("This is a button.");
            Button_ button = (Button_) item;

        }

    }

    public void  Remove(Item_ item) {
        if(item == null) {
            return;
        }

        int id = item.GetHashCode();
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
