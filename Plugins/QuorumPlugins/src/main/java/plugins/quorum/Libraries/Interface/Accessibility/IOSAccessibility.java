package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.uikit.UIFocusEnvironment;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Game.IOSDisplay;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item_;

import java.util.List;

public class IOSAccessibility {
    public Object me_ = null;

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
//        IOSApplication.GlobalLog("Focus Changed");
//        UIViewController controller = IOSDisplay.theViewController;
//        UIView view = controller.getView();
//        controller.setFocusGroupIdentifier("hi");
//        UIFocusEnvironment focusy = controller.getParentFocusEnvironment();
//        for (UIFocusEnvironment focuses : focusy.getPreferredFocusEnvironments()) {
//            IOSApplication.GlobalLog(focuses.getFocusGroupIdentifier());
//        }
//        List<UIFocusEnvironment> focuses2 = focusy.getPreferredFocusEnvironments();
//        Item_ item_ = event.GetNewFocus();

    }

    public void  NativeAdd(Item_ item) throws Exception {
//        IOSApplication.GlobalLog("Native Add");
//        IOSApplication.GlobalLog(item.GetName());
//        UIViewController controller = IOSDisplay.theViewController;
//        UIView view = controller.getView();
//
//        UIView subview = new UIView();
//        subview.setAccessibilityIdentifier(item.GetName());
//        view.addSubview(subview);

    }

    public void  NativeRemove(Item_ item) {
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
