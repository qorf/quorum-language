package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityExpandedStatus;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import plugins.quorum.Libraries.Game.IOSApplication;
import quorum.Libraries.Interface.Controls.TreeItem_;

/*
    A tree item says its level and, if it has children, whether it is open.
    iOS 18 and later read the state from accessibilityExpandedStatus; on
    earlier versions it is part of the value. A double tap opens or closes an
    item with children (as a tap does, see TreeItem:ToggleOpen) and selects
    others.
 */
public class TreeItemIOS extends ItemIOS {
    public TreeItemIOS(UIAccessibilityContainer container) {
        super(container);
    }

    TreeItem_ GetTreeItem() {
        return (TreeItem_) GetItem();
    }

    boolean HasChildren() {
        return GetTreeItem().GetSize() > 0;
    }

    // Level 1 for a top item, 2 for its children, and so on.
    int GetLevel() {
        int level = 1;
        TreeItem_ parent = GetTreeItem().GetParentTreeItem();
        while (parent != null) {
            level = level + 1;
            parent = parent.GetParentTreeItem();
        }
        return level;
    }

    @Override
    public String getAccessibilityValue() {
        String state = "";
        if (HasChildren() && !IOSApplication.IsAtLeastIOS(18)) {
            state = GetTreeItem().IsOpen() ? "expanded, " : "collapsed, ";
        }
        return state + "level " + GetLevel();
    }

    /*
        UIAccessibilityElement doesn't bind this property (it is in MobiVM's
        UIAccessibility protocol), so it is exported by its selector. iOS 17
        and earlier never ask for it.
     */
    @Method(selector = "accessibilityExpandedStatus")
    public @MachineSizedSInt long getExpandedStatus() {
        if (!HasChildren()) {
            return UIAccessibilityExpandedStatus.Unsupported.value();
        }
        return GetTreeItem().IsOpen() ? UIAccessibilityExpandedStatus.Expanded.value() : UIAccessibilityExpandedStatus.Collapsed.value();
    }

    // Quorum's selection follows VoiceOver, so the highlight, the keyboard
    // and a double tap all agree with where VoiceOver is.
    @Override
    public void didBecomeFocused() {
        super.didBecomeFocused();
        GetTreeItem().Select();
    }

    @Override
    public boolean activate() {
        if (HasChildren()) {
            GetTreeItem().ToggleOpen();
        } else {
            GetTreeItem().Select();
        }
        return true;
    }
}
