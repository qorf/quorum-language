package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import plugins.quorum.Libraries.Game.IOSDisplay;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.ComboBox_;
import quorum.Libraries.Interface.Controls.List_;

/*
    A choice in an open combo box's list. It reads its text, with the
    Selected trait on the highlighted choice, as other list items do. Unlike
    them, VoiceOver landing on it changes nothing: the combo box keeps the
    focus (taking it away would close the list) and no choice is made. A
    double tap chooses it, which writes it into the field and closes the
    list, as a click does.
 */
public class ComboBoxChoiceIOS extends ListItemIOS {
    private ComboBox_ comboBox = null;

    public ComboBoxChoiceIOS(UIAccessibilityContainer container) {
        super(container);
    }

    public void SetComboBox(ComboBox_ box) {
        comboBox = box;
    }

    public ComboBox_ GetComboBox() {
        return comboBox;
    }

    // Records where VoiceOver is, without moving Quorum's focus or selection.
    @Override
    public void didBecomeFocused() {
        focused = true;
        voiceOverFocus = this;
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver moved to " + getClass().getSimpleName() + " \"" + getAccessibilityLabel() + "\"");
        }
    }

    @Override
    public boolean activate() {
        List_ list = GetListItem().GetList();
        if (comboBox == null || list == null || !comboBox.IsOpen()) {
            return false;
        }
        int row = list.GetIndexOf(GetListItem());
        if (row < 0) {
            return false;
        }

        // Highlight this row (MoveHighlight counts from the highlighted row,
        // or from before the first row when none is), then choose it.
        int highlighted = comboBox.GetHighlightedIndex();
        if (highlighted < 0) {
            comboBox.MoveHighlight(row + 1);
        } else if (highlighted != row) {
            comboBox.MoveHighlight(row - highlighted);
        }
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver chose \"" + getAccessibilityLabel() + "\" in combo box \"" + comboBox.GetName() + "\"");
        }
        comboBox.ChooseHighlighted();
        IOSDisplay.RequestFrame();
        return true;
    }
}
