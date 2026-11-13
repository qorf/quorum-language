package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityExpandedStatus;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import quorum.Libraries.Interface.Controls.ComboBox_;

/*
    A combo box for VoiceOver. iOS has no combo box, so it is a text field
    (its value is the text, and it takes the keyboard as a text field does).
    While its list is open, the list's choices are elements just after it
    (ComboBoxChoiceIOS). It has no adjustable swipe: on phones the choices
    are reached in the list.

    iOS 18 and later also say whether the list is open, from
    accessibilityExpandedStatus. Earlier versions never ask, and nothing is
    added to the value for them.
 */
public class ComboBoxIOS extends TextFieldIOS {
    public ComboBoxIOS(UIAccessibilityContainer container) {
        super(container);
    }

    public ComboBox_ GetComboBox() {
        return (ComboBox_) GetItem();
    }

    /*
        UIAccessibilityElement doesn't bind this property (it is in MobiVM's
        UIAccessibility protocol), so it is exported by its selector, as
        TreeItemIOS does.
     */
    @Method(selector = "accessibilityExpandedStatus")
    public @MachineSizedSInt long getExpandedStatus() {
        ComboBox_ box = GetComboBox();
        if (box == null) {
            return UIAccessibilityExpandedStatus.Unsupported.value();
        }
        return box.IsOpen() ? UIAccessibilityExpandedStatus.Expanded.value() : UIAccessibilityExpandedStatus.Collapsed.value();
    }
}
