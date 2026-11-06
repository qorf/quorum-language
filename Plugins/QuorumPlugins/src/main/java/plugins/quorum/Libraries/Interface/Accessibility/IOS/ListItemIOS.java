package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Selections.ListSelection_;

/*
    A list item reads its text (every list item is named "List Item"), says
    whether it is selected, and is selected by a double tap.
 */
public class ListItemIOS extends ItemIOS {
    public ListItemIOS(UIAccessibilityContainer container) {
        super(container);
    }

    ListItem_ GetListItem() {
        return (ListItem_) GetItem();
    }

    @Override
    public void RefreshText() {
        setAccessibilityLabel(GetListItem().GetText());
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }

    public boolean IsSelected() {
        List_ list = GetListItem().GetList();
        if (list == null) {
            return false;
        }
        ListSelection_ selection = list.GetSelection();
        return selection != null && selection.GetListItem() == GetListItem();
    }

    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        if (IsSelected()) {
            return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.Selected.value());
        }
        return traits;
    }

    // Quorum's selection follows VoiceOver, as for tree items.
    @Override
    public void didBecomeFocused() {
        super.didBecomeFocused();
        GetListItem().Select();
    }

    @Override
    public boolean activate() {
        GetListItem().Select();
        return true;
    }
}
