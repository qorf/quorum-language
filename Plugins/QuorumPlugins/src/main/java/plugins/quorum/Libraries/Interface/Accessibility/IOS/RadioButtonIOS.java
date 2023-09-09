package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.*;
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
        System.out.println("it entered Initialize");
        setRadioButton(radioButton);
        UIAccessibilityTraits traits = UIAccessibilityTraits.None;
        this.setAccessibilityTraits(traits);
        super.Initialize(radioButton);
    }

    @Override
    public boolean activate() {
        System.out.println("it entered activate");
        boolean isToggled = radioButton.GetToggleState();
        if (isToggled == true) {
            radioButton.Activate();
            radioButton.SetToggleState(false);
            radioButton.ClickedMouse();
            System.out.println("accessibility double tap ON");
            return false;
        } else {
            radioButton.Activate();
            radioButton.SetToggleState(true);;
            radioButton.ClickedMouse();
            System.out.println("accessibility double tap OFF");
            return true;
        }
    }
}
