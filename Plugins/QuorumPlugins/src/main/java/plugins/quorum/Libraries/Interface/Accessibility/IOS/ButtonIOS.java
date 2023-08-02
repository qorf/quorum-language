package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.IOSApplication;
import quorum.Libraries.Game.IOSConfiguration;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;

import java.util.Set;

public class ButtonIOS extends UIAccessibilityElement implements UIAccessibilityAction, UIAccessibilityFocus{
    @Override
    public boolean isAccessibilityElement() {
        return true;
    }

    public ButtonIOS(UIAccessibilityContainer container)
    {
        super(container);
    }

    Button_ button;

    public void SetButton(Button_ button)
    {
        this.button = button;

    }

    public void Initialize(Button_ button) {
        this.button = button;
        UIAccessibilityTraits traits = UIAccessibilityTraits.Button;
        this.setAccessibilityTraits(traits);
//        action

    }

    @Override
    public CGRect getAccessibilityFrame() {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        System.out.println("getting the frame");
        if (button instanceof Item2D_)
        {
            double itemX = ((Item2D_)button).GetScreenX();
            double itemY = (((Item2D_) button).GetScreenY());
            if (itemX == Double.NaN) {
                itemX = 0;
            }

            if (itemY == Double.NaN) {
                itemY = 0;
            }

            width = (int) (((Item2D_) button).GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (((Item2D_) button).GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(itemX / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (itemY / IOSApplication.containerScaleFactorHeight)));
        }
        else if (button instanceof Item3D_) {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.
            Rectangle_ rectangle = ((Item3D_) button).GetScreenBounds();

            width = (int) (rectangle.GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (rectangle.GetY() + rectangle.GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int) (rectangle.GetX() / IOSApplication.containerScaleFactorWidth);
            y = (int) (IOSApplication.accessibilityContainerBounds.getHeight() - (height + (rectangle.GetY() / IOSApplication.containerScaleFactorHeight)));
        }

        return new CGRect(x, y, width, height);
    }

    @Override
    public NSArray<UIAccessibilityCustomAction> getAccessibilityCustomActions() {
        return null;
    }

    @Override
    public void setAccessibilityCustomActions(NSArray<UIAccessibilityCustomAction> v) {

    }

    @Override
    public boolean activate() {
        button.SetDepression(true);
        button.ClickedMouse();
        System.out.println("accessibility double tap");
        return true;
    }

    @Override
    public void increment() {

    }

    @Override
    public void decrement() {

    }

    @Override
    public boolean scroll(UIAccessibilityScrollDirection direction) {
        return false;
    }

    @Override
    public boolean performEscape() {
        return false;
    }

    @Override
    public boolean performMagicTap() {
        return false;
    }

    @Override
    public void didBecomeFocused() {

    }

    @Override
    public void didLoseFocus() {

    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public Set<String> getAssistiveTechnologyFocusedIdentifiers() {
        return null;
    }
}
