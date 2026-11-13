package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import quorum.Libraries.Interface.Controls.ComboBox_;
import quorum.Libraries.Interface.Item_;

/*
    A combo box: a text field with a list of choices under it. Everything a
    text field has (its text, caret, selection and actions, TextFieldKit),
    with the role of an editable combo box, which VoiceOver reads as
    "combo box" (AXComboBox). It opens and closes, so Mac.so gives it an
    expanded state (SendExtras): "collapsed" or "expanded".

    The list is the combo box's List, with its own node (ListKit): its
    dialog has no node, so the list hangs from the window. The list never
    takes the focus; the row the keys are on is the list's selected row,
    which VoiceOver reads as the selection moves.
*/
public class ComboBoxKit extends TextFieldKit {
    public ComboBoxKit() {
        SetRole(Role.EDITABLE_COMBO_BOX);
    }

    /*
        Always EXPANDABLE, and EXPANDED while the list is open. The flags are
        sent each time the node is built, and MacAccessibility builds it
        again when the list opens or closes (ComboBoxOpened, ComboBoxClosed).
    */
    @Override
    public void SendExtras() {
        int flags = MacAccessibilityExtras.EXPANDABLE | GetMenuFlag();
        Item_ item = GetItem();
        if (item instanceof ComboBox_ && ((ComboBox_) item).IsOpen()) {
            flags = flags | MacAccessibilityExtras.EXPANDED;
        }
        MacAccessibilityExtras.setInfo(GetNodeID(), flags, 0, -1, -1, 0, null);
    }
}
