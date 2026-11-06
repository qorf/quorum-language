package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.*;
import quorum.Libraries.Interface.Controls.ButtonGroup_;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Item_;
public class RadioButtonIOS extends ItemIOS {
    @Override
    public boolean isAccessibilityElement() {
        return true;
    }

    public RadioButtonIOS(UIAccessibilityContainer container) {
        super(container);
    }

    RadioButton_ radioButton;

    public void setRadioButton(RadioButton_ radioButton) {
        this.radioButton = radioButton;
    }

    public void Initialize(RadioButton_ radioButton) {
        setRadioButton(radioButton);
        UIAccessibilityTraits traits = UIAccessibilityTraits.Button;
        this.setAccessibilityTraits(traits);
        super.Initialize(radioButton);
    }

    // Read as "selected" when on.
    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        if (radioButton.GetToggleState()) {
            return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.Selected.value());
        }
        return traits;
    }

    // Its place in its group, as "1 of 3".
    @Override
    public String getAccessibilityValue() {
        ButtonGroup_ group = radioButton.GetButtonGroup();
        if (group == null) {
            return null;
        }
        for (int i = 0; i < group.GetSize(); i++) {
            if (group.GetButtonAt(i) == radioButton) {
                return (i + 1) + " of " + group.GetSize();
            }
        }
        return null;
    }

    // Selects it; a selected radio button stays selected.
    @Override
    public boolean activate() {
        if (!radioButton.GetToggleState()) {
            radioButton.Activate();
        }
        return true;
    }
}
