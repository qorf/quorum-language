package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.*;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Item_;


public class ButtonIOS extends ItemIOS {
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

    /**
     * Called when the button is activated.
     * When buttons are activated, they should show visual feedback.
     */
    @Override
    public boolean activate() {
        if (button != null) {
            // Give the button focus so it shows visual feedback
            button.Focus();
            // Mirror the Quorum button click so visual state updates
            button.SetDepression(true);
            button.ClickedMouse();
            button.SetDepression(false);
            button.Activate();
        }
        return true;
    }

    /**
     * Called when accessibility focus moves to this button.
     */
    @Override
    public void Focus() {
        super.Focus();
        if (button != null) {
            // Give the button Quorum focus so it shows the focus visual state
            button.Focus();
        }
    }

    /**
     * Called when accessibility focus leaves this button.
     * Manually clear focus.
     */
    @Override
    public void FocusLost() {
        // The button's LostFocus will be called by the focus manager
        super.FocusLost();
    }
}
