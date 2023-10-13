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
        setToggleButton(toggleButton);
        UIAccessibilityTraits traits = UIAccessibilityTraits.Button;
        this.setAccessibilityTraits(traits);
        super.Initialize(toggleButton);
    }

    @Override
    public boolean activate() {
        boolean isToggled = toggleButton.GetToggleState();
        if (isToggled == true) {
            toggleButton.Activate();
            toggleButton.SetToggleState(false);
            toggleButton.ClickedMouse();
            return false;
        } else {
            toggleButton.Activate();
            toggleButton.SetToggleState(true);;
            toggleButton.ClickedMouse();
            return true;
        }
    }
}
