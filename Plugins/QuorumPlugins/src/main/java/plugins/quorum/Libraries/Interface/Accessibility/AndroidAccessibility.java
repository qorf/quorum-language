package plugins.quorum.Libraries.Interface.Accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
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
import java.util.List;

/**
 *
 * @author andreasstefik
 */
public class AndroidAccessibility extends AccessibilityNodeProvider {
    public java.lang.Object me_ = null;

    public class VirtualView {

        public final int id;
        public final String name;
        public final String description;

        public VirtualView(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }

    public static VirtualView findVirtualViewById(int id) {
        List<VirtualView> children = AndroidApplication.virtualChildren;
        final int childCount = children.size();
        for (int i = 0; i < childCount; i++) {
            VirtualView child = children.get(i);
            if (child.id == id) {
                return child;
            }
        }
        return null;
    }

    public void sendAccessibilityEventForVirtualView(VirtualView virtualView, int eventType) {
        // If touch exploration, i.e. the user gets feedback while touching
        // the screen, is enabled we fire accessibility events.
        if (AndroidApplication.accessibilityManager.isTouchExplorationEnabled()) {
            AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
            event.setClassName(virtualView.getClass().getName());
            event.getText().add(virtualView.description);
            AndroidApplication.accessibilityManager.sendAccessibilityEvent(event);
        }
    }

    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {}

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {}

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void  SelectionChanged(SelectionEvent_ event) {}

    public void  ButtonActivated(Button_ button) {}

    public void  ToggleButtonToggled(ToggleButton_ button) {}

    public void  FocusChanged(FocusEvent_ event) throws Exception {


        View view = AndroidApplication.viewRoot;




        AccessibilityManager accessibilityManager = AndroidApplication.accessibilityManager;
        if(accessibilityManager == null) {
            return;
        }

        Item_ item = event.Get_Libraries_Interface_Events_FocusEvent__gainedFocus_();
        if(item == null) {
            return;
        }

        AccessibilityNodeProvider accessibilityNodeProvider = AndroidApplication.accessibilityNodeProvider;

        int id = item.GetHashCode();
        VirtualView temp = findVirtualViewById(id);

        sendAccessibilityEventForVirtualView(temp, AccessibilityEvent.TYPE_ANNOUNCEMENT);

    }

    public void  NativeAdd(Item_ item) throws Exception {

        AccessibilityManager accessibilityManager = AndroidApplication.accessibilityManager;
        if(accessibilityManager == null) {
            return;
        }


        View view = AndroidApplication.viewRoot;
        if(view == null) {
            return;
        }
        AccessibilityNodeProvider accessibilityNodeProvider = AndroidApplication.accessibilityNodeProvider;
        if(accessibilityNodeProvider == null) {
            return;
        }

        int id = item.GetHashCode();
        AccessibilityNodeInfo node = accessibilityNodeProvider.createAccessibilityNodeInfo(id);
        String name = item.GetName();
        String description = item.GetDescription();
        AndroidApplication.virtualChildren.add(new VirtualView(id,name,description));
        AndroidApplication.accessibilityNodeProvider.createAccessibilityNodeInfo(id);

        /*
        int code = item.GetAccessibilityCode();
        if(code == item.Get_Libraries_Interface_Item__BUTTON_()) { //this is a button.
            System.out.println("This is a button.");
            Button_ button = (Button_) item;

        }
        */
    }

    public void  NativeRemove(Item_ item) {
        if(item == null) {
            return;
        }

        int id = item.GetHashCode();
        VirtualView temp = findVirtualViewById(id);
        if(temp == null)
            return;
        else
            AndroidApplication.virtualChildren.remove(temp);

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
