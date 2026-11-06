package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Game.IOSApplication;
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
        // iOS 17 added a toggle button trait, read with its on or off state.
        long traits = UIAccessibilityTraits.Button.value();
        if (IOSApplication.IsAtLeastIOS(17)) {
            traits = traits | UIAccessibilityTraits.ToggleButtonValue.value();
        }
        this.setAccessibilityTraits(new UIAccessibilityTraits(traits));
        super.Initialize(toggleButton);
    }

    // Read as "selected" when on.
    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        if (toggleButton.GetToggleState()) {
            return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.Selected.value());
        }
        return traits;
    }

    // Toggles once, as a click does, and tells iOS it was handled.
    @Override
    public boolean activate() {
        toggleButton.Activate();
        return true;
    }
}
