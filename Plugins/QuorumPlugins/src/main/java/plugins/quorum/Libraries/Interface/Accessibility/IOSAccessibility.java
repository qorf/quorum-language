package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Game.IOSDelegate;
import plugins.quorum.Libraries.Game.IOSDisplay;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

import java.util.ArrayList;
import java.util.List;

public class IOSAccessibility extends UIView implements UIAccessibilityContainer {
    public Object me_ = null;
    private NSMutableArray<UIAccessibilityElement> accessibilityElements = new NSMutableArray<>();

    @Override
    public NSArray<UIAccessibilityElement> getAccessibilityElements() {
        return accessibilityElements;
    }

    @Override
    public void setAccessibilityElements(NSArray<UIAccessibilityElement> nsArray) {
        accessibilityElements = (NSMutableArray<UIAccessibilityElement>) nsArray.mutableCopy();
    }

    @Override
    public UIAccessibilityContainerType getAccessibilityContainerType() {
        return null;
    }

    @Override
    public void setAccessibilityContainerType(UIAccessibilityContainerType uiAccessibilityContainerType) {
        return;
    }

    @Override
    public long getAccessibilityElementCount() {
        return accessibilityElements.size();
    }

    @Override
    public UIAccessibilityElement getAccessibilityElement(long l) {
        return accessibilityElements.get((int)l);
    }

    @Override
    public long indexOfAccessibilityElement(UIAccessibilityElement uiAccessibilityElement) {
        return accessibilityElements.indexOf(uiAccessibilityElement);
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

    public void Add(Item_ item) throws Exception {

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_) item).GetScreenY());

            if (itemX == Double.NaN)
                itemX = 0;

            if (itemY == Double.NaN)
                itemY = 0;
            x = (int)itemX;
            y = (int)itemY;
            width = (int) ((Item2D_) item).GetWidth();
            height = (int) ((Item2D_) item).GetHeight();
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.

            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

            x = (int)rectangle.GetX();
            y = (int)(rectangle.GetY());
            width = (int) (rectangle.GetWidth());
            height = (int)(rectangle.GetY() + rectangle.GetHeight());
        }



        IOSApplication iosApplication = IOSDelegate.app;
        IOSAccessibility iosAccessibility = iosApplication.getIOSAccessibility();

        UIAccessibilityElement accessibilityElement = new UIAccessibilityElement(iosAccessibility);

        accessibilityElement.setAccessibilityFrame(new CGRect(x, y, width, height));

        if (item.GetName() != null)
            accessibilityElement.setAccessibilityLabel(item.GetName());
        else
            accessibilityElement.setAccessibilityLabel("Default Name");

        if (item.GetDescription() != null)
            accessibilityElement.setAccessibilityHint(item.GetDescription());
        else
            accessibilityElement.setAccessibilityHint("Default Description");

        if (item.GetAccessibilityCode() == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_())
            accessibilityElement.setAccessibilityElement(false);
        else
            accessibilityElement.setAccessibilityElement(true);


        // Add the accessibility element to the list
        accessibilityElements.add(accessibilityElement);

        // Inform iOS that the accessibility elements have changed
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.LayoutChangedNotification, accessibilityElement);
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

    public void  Remove(Item_ item) {
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
