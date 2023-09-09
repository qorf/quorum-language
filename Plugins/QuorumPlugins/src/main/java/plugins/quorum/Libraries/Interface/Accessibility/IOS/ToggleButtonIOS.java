package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Controls.ToggleButton_;

public class ToggleButtonIOS extends ItemIOS {
    @Override
    public boolean isAccessibilityElement() {
        return true;
    }

    public ToggleButtonIOS(UIAccessibilityContainer container)
    {
        super(container);
    }

    ToggleButton_ toggleButton;

            public void setToggleButton(ToggleButton_ toggleButton) {
        this.toggleButton = toggleButton;
    }

    public void Initialize(ToggleButton_ toggleButton) {
        System.out.println("it entered Initialize");
        setToggleButton(toggleButton);
        UIAccessibilityTraits traits = UIAccessibilityTraits.None;
        this.setAccessibilityTraits(traits);
        super.Initialize(toggleButton);
    }

    @Override
    public boolean activate() {
        System.out.println("it entered activate");
        boolean isToggled = toggleButton.GetToggleState();
        if (isToggled == true) {
            toggleButton.Activate();
            toggleButton.SetToggleState(false);
            toggleButton.ClickedMouse();
            System.out.println("accessibility double tap ON");
            return false;
        } else {
            toggleButton.Activate();
            toggleButton.SetToggleState(true);;
            toggleButton.ClickedMouse();
            System.out.println("accessibility double tap OFF");
            return true;
        }
    }
}
