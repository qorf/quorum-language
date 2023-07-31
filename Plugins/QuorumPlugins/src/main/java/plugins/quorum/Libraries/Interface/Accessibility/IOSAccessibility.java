package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.uikit.*;
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

    public boolean NativeAdd(Item_ item) throws Exception {
        UIViewController controller = IOSDisplay.theViewController;
        UIView view = controller.getView();

        //This is probably all wrong, but is a test to see if we can get it to fire on.
        int code = item.Get_Libraries_Interface_Item__accessibilityCode_();
        if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            HiddenButton subview = new HiddenButton();
            //subview.setHidden(true);

            subview.setAccessibilityIdentifier(item.GetName());
            System.out.println(item.GetName());
            view.addSubview(subview);

            return true;
        } else if(code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
            HiddenTextField subview = new HiddenTextField();
            subview.setAccessibilityIdentifier(item.GetName());
            System.out.println(item.GetName());
            view.addSubview(subview);

            return true;
        }

        return false;
    }

    private class HiddenView extends UIView {
        public HiddenView() {

        }
    }

    private class HiddenButton extends UIButton {
        public HiddenButton() {

        }
    }
    private class HiddenTextField extends UITextField {
        public HiddenTextField () {

            setKeyboardType(UIKeyboardType.Default);
            setReturnKeyType(UIReturnKeyType.Done);
            setAutocapitalizationType(UITextAutocapitalizationType.None);
            setAutocorrectionType(UITextAutocorrectionType.No);
            setSpellCheckingType(UITextSpellCheckingType.No);
            setHidden(true);
        }

        @Override
        public void deleteBackward () {
            //app.input.inputProcessor.keyTyped((char)8);
            //super.deleteBackward();
            //Gdx.graphics.requestRendering();
        }
    }

    public boolean NativeRemove(Item_ item) {
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
