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
        setCheckbox(checkbox);
        UIAccessibilityTraits traits = UIAccessibilityTraits.Button;
        this.setAccessibilityTraits(traits);
        super.Initialize(checkbox);
    }

    @Override
    public String getAccessibilityValue() {
        return checkbox.GetToggleState() ? "checked" : "not checked";
    }

    // Toggles once, as a click does, and tells iOS it was handled (false
    // would make VoiceOver tap the checkbox as well, toggling it back).
    @Override
    public boolean activate() {
        checkbox.Activate();
        return true;
    }
}