package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.IOSInput;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;

public class TextBoxIOS extends TextAdjust{
    public boolean isAccessibilityElement() {
        return true;
    }
    public TextBoxIOS(UIAccessibilityContainer container)
    {
        super(container);
    }

    public void Initialize(TextField_ field) {
        UIAccessibilityTraits traits = UIAccessibilityTraits.SearchField;
        this.setAccessibilityTraits(traits);
        super.Initialize(field);
    }

    public void Focus() {
        IOSInput input = ((quorum.Libraries.Game.IOSInput) GameStateManager.input).plugin_;
        input.setFocusedTextAdjust(this);
        input.setOnscreenKeyboardVisible(true);
    }

    public void FocusLost() {
        IOSInput input = ((quorum.Libraries.Game.IOSInput) GameStateManager.input).plugin_;
        input.setFocusedTextAdjust(null);
        input.setOnscreenKeyboardVisible(false);
    }

    public TextBox_ GetTextBox() {
        TextBox_ box = (TextBox_) GetItem();
        return box;
    }

    @Override
    public void Insert(String string) {
        GetTextBox().Insert(string);
    }

    @Override
    public void DeleteBackward() {
        GetTextBox().DeleteBackward();
    }

    public String GetText() {
        return GetTextBox().GetText();
    }
}
