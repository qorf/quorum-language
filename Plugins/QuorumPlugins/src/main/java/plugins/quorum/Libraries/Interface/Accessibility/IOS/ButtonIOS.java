package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Robots.Lego.Button;
import quorum.Libraries.Game.IOSConfiguration;
import quorum.Libraries.Interface.Controls.Button_;

public class ButtonIOS extends UIAccessibilityElement implements UIAccessibilityAction {
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
        CGRect rect = CGRect.Null();
        rect.setX(button.GetX());
        rect.setY(button.GetY());
        rect.setHeight(button.GetHeight());
        rect.setWidth(button.GetWidth());
        this.setAccessibilityFrame(rect);
        this.setAccessibilityFrameInContainerSpace(rect);
//        action

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
}
