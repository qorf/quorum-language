package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.*;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Item_;


public class ButtonIOS extends ItemIOS{
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
        SetButton(button);
        UIAccessibilityTraits traits = UIAccessibilityTraits.Button;
        this.setAccessibilityTraits(traits);
        super.Initialize(button);
    }

    @Override
    public boolean activate() {
        button.Activate();
        return true;
    }
}
