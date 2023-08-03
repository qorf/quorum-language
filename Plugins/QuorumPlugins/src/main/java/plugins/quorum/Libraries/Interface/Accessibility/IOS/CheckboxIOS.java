package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.*;
import quorum.Libraries.Interface.Controls.Checkbox_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Item_;

public class CheckboxIOS extends ItemIOS {
    @Override
    public boolean isAccessibilityElement() {
        return true;
    }

    public CheckboxIOS(UIAccessibilityContainer container) {
        super(container);
    }

    Checkbox_ checkbox;

    public void setCheckbox(Checkbox_ checkbox) {
        this.checkbox = checkbox;
    }

    public void Initialize(Checkbox_ checkbox) {
        System.out.println("it entered Initialize");
        setCheckbox(checkbox);
        UIAccessibilityTraits traits = UIAccessibilityTraits.None;
        this.setAccessibilityTraits(traits);
        super.Initialize(checkbox);
    }

    @Override
    public boolean activate() {
        System.out.println("it entered activate");
        boolean isToggled = checkbox.GetToggleState();
        if (isToggled == true) {
            checkbox.Activate();
            checkbox.SetToggleState(false);
            checkbox.ClickedMouse();
            System.out.println("accessibility double tap ON");
            return false;
        } else {
            checkbox.Activate();
            checkbox.SetToggleState(true);;
            checkbox.ClickedMouse();
            System.out.println("accessibility double tap OFF");
            return true;
        }
    }
}