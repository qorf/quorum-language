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
        System.out.println("shouldBeginEditing");
        return false;
    }

    @Override
    public void didBeginEditing(UITextField textField) {
        System.out.println("shouldBeginEditing");
    }

    @Override
    public boolean shouldEndEditing(UITextField textField) {
        System.out.println("shouldEndEditing");
        return false;
    }

    @Override
    public void didEndEditing(UITextField textField) {
        System.out.println("didEndEditing");
    }

    @Override
    public void didEndEditing(UITextField textField, UITextFieldDidEndEditingReason reason) {
        System.out.println("didEndEditing");
    }

    @Override
    public boolean shouldChangeCharacters(UITextField textField, NSRange range, String string) {
        System.out.println("shouldChangeCharacters");
        return false;
    }

    @Override
    public void textFieldDidChangeSelection(UITextField textField) {
        System.out.println("textFieldDidChangeSelection");
    }

    @Override
    public boolean shouldClear(UITextField textField) {
        System.out.println("shouldClear");
        return false;
    }

    @Override
    public boolean shouldReturn(UITextField textField) {
        System.out.println("shouldReturn");
        return false;
    }

    @Override
    public UIMenu getEditMenu(UITextField textField, NSRange range, NSArray<UIMenuElement> suggestedActions) {
        System.out.println("getEditMenu");
        return null;
    }

    @Override
    public void willPresentEditMenu(UITextField textField, UIEditMenuInteractionAnimating animator) {
        System.out.println("willPresentEditMenu");
    }

    @Override
    public void willDismissEditMenu(UITextField textField, UIEditMenuInteractionAnimating animator) {
        System.out.println("willDismissEditMenu");
    }
}
