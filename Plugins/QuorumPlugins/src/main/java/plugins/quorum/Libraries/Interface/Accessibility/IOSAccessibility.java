package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Game.IOSDelegate;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IOSAccessibility {
    public Object me_ = null;
    public HashMap mapAccessibilityElements = new HashMap<UIAccessibilityElement, Item_>();

    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {
        UIAccessibilityElement element = (UIAccessibilityElement) mapAccessibilityElements.get(item);
        if(element == null) {
            return;
        }
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_) item).GetScreenY());
            if (itemX == Double.NaN) {
                itemX = 0;
            }

            if (itemY == Double.NaN) {
                itemY = 0;
            }

            width = (int) (((Item2D_) item).GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (((Item2D_) item).GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(itemX / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (itemY / IOSApplication.containerScaleFactorHeight)));
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

            width = (int) (rectangle.GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int)(rectangle.GetY() + rectangle.GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(rectangle.GetX() / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (rectangle.GetY() / IOSApplication.containerScaleFactorHeight)));
        }
        else
        {
            return;
        }

        element.setAccessibilityFrame(new CGRect(x, y, width, height));
    }

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {
    }

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void  SelectionChanged(SelectionEvent_ event) {}

    public void  ButtonActivated(Button_ button) {}

    public void  ToggleButtonToggled(ToggleButton_ button) {}

    public void  FocusChanged(FocusEvent_ event) throws Exception {
        Item_ item = event.GetNewFocus();
        System.out.println("Focus Changed to " + item.GetName());
        UIAccessibilityElement element = (UIAccessibilityElement) mapAccessibilityElements.get(item);
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.ScreenChangedNotification, element);

    }

    public void NativeAdd(Item_ item) {
        boolean debug = true;
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if(item == null) {
            return;
        }

        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_) item).GetScreenY());
            if (itemX == Double.NaN) {
                itemX = 0;
            }

            if (itemY == Double.NaN) {
                itemY = 0;
            }

            width = (int) (((Item2D_) item).GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (((Item2D_) item).GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(itemX / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (itemY / IOSApplication.containerScaleFactorHeight)));
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

            width = (int) (rectangle.GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int)(rectangle.GetY() + rectangle.GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(rectangle.GetX() / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (rectangle.GetY() / IOSApplication.containerScaleFactorHeight)));
        }
        else
        {
            return;
        }

        if(debug) {
            System.out.println("Adding accessibility element" + x + " " + y + " " + width + " " + height);
            System.out.println("Adding accessibility element" + " " + item.GetName() + " " + item.GetDescription());

            if (item instanceof Item2D_)
            {
                System.out.println("Adding accessibility element 2d" + " " + ((Item2D_) item).GetScreenX() + " " + ((Item2D_) item).GetScreenY() + " " + ((Item2D_) item).GetWidth() + " " + ((Item2D_) item).GetHeight());
            }
        }

        UIAccessibilityElement element = new UIAccessibilityElement(IOSApplication.accessibilityContainer);


        //Get the accessibility code and do custom controls.
        int code = item.GetAccessibilityCode();
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || !item.IsShowing()) {
            return;
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            element.setAccessibilityTraits(UIAccessibilityTraits.Button);
        }

        if (item.GetName() != null) {
            element.setAccessibilityIdentifier(item.GetName() + " ID");
            element.setAccessibilityValue(item.GetName() + " Value");
            element.setAccessibilityLabel(item.GetName());
        }
        else {
            element.setAccessibilityLabel("Name");
        }

        if (item.GetDescription() != null) {
            element.setAccessibilityHint(item.GetDescription());
        }
        else {
            element.setAccessibilityHint("Description");
        }


        element.setAccessibilityElement(true);
        for (UIAccessibilityTraits trait : element.getAccessibilityTraits()) {
            System.out.println(trait.toString());
        }

        //accessibilityElement.setAccessibilityValue(item.GetDescription());
        //accessibilityElement.setAccessibilityIdentifier(item.GetAccessibilityType());
        element.setAccessibilityFrame(new CGRect(x, y, width, height));

        // UIAccessibilityTraits traits = UIAccessibilityTraits.AllowsDirectInteraction;
        // accessibilityElement.setAccessibilityTraits(traits);

        // Add the accessibility element to the list
        NSMutableArray<UIAccessibilityElement> nsArray = (NSMutableArray<UIAccessibilityElement>) IOSApplication.accessibilityContainer.getAccessibilityElements().mutableCopy();
        nsArray.add(element);
        mapAccessibilityElements.put(element, item);
        IOSApplication.accessibilityContainer.setAccessibilityElements(nsArray);

        // Inform iOS that the accessibility elements have changed
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.ScreenChangedNotification, element);

        if(debug){
            System.out.println("The bounds are" + IOSApplication.accessibilityContainer.getFrame().getX() + " " + IOSApplication.accessibilityContainer.getFrame().getY() + " " + IOSApplication.accessibilityContainer.getFrame().getWidth() + " " + IOSApplication.accessibilityContainer.getFrame().getHeight());
        }
    }

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
