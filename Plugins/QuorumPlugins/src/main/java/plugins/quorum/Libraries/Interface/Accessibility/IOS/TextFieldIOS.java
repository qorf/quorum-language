package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.uikit.*;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.TextField_;

public class TextFieldIOS extends ItemIOS implements UITextFieldDelegate {
    public boolean isAccessibilityElement() {
        return true;
    }
    public TextFieldIOS(UIAccessibilityContainer container)
    {
        super(container);
    }

    public void Initialize(TextField_ field) {
        UIAccessibilityTraits traits = UIAccessibilityTraits.SearchField;
        this.setAccessibilityTraits(traits);
        super.Initialize(field);
    }

    @Override
    public boolean shouldBeginEditing(UITextField textField) {
        return false;
    }

    @Override
    public void didBeginEditing(UITextField textField) {

    }

    @Override
    public boolean shouldEndEditing(UITextField textField) {
        return false;
    }

    @Override
    public void didEndEditing(UITextField textField) {

    }

    @Override
    public void didEndEditing(UITextField textField, UITextFieldDidEndEditingReason reason) {

    }

    @Override
    public boolean shouldChangeCharacters(UITextField textField, NSRange range, String string) {
        return false;
    }

    @Override
    public void textFieldDidChangeSelection(UITextField textField) {

    }

    @Override
    public boolean shouldClear(UITextField textField) {
        return false;
    }

    @Override
    public boolean shouldReturn(UITextField textField) {
        return false;
    }

    @Override
    public UIMenu getEditMenu(UITextField textField, NSRange range, NSArray<UIMenuElement> suggestedActions) {
        return null;
    }

    @Override
    public void willPresentEditMenu(UITextField textField, UIEditMenuInteractionAnimating animator) {

    }

    @Override
    public void willDismissEditMenu(UITextField textField, UIEditMenuInteractionAnimating animator) {

    }
}
