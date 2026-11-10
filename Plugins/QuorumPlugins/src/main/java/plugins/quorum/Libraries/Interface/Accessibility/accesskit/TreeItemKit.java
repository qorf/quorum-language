package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import plugins.quorum.Libraries.Interface.Accessibility.MacAccessibility;
import quorum.Libraries.Interface.Behaviors.Controls.TreeOpenPopupMenuBehavior;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Controls.Tree_;
import quorum.Libraries.Interface.Events.BehaviorEvent;
import quorum.Libraries.Interface.Item_;

public class TreeItemKit extends ItemKit{
    public TreeItemKit() {
        SetRole(Role.TREE_ITEM);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof TreeItem_) { //technically not compiler guaranteed. You can set the code to anything.
            TreeItem_ control = (TreeItem_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setLabel(control.GetName());
            builder.setDescription(control.GetDescription());

            // Selected: VoiceOver reads it (AXSelected, the tree's
            // selected rows). Position and size: AccessKit keeps them, but
            // its macOS adapter doesn't pass them on yet (see step 7).
            builder.setSelected(control.IsSelected());
            builder.setPositionInSet(MacAccessibility.GetTreeItemSetPosition(control));
            builder.setSizeOfSet(MacAccessibility.GetTreeItemSetSize(control));
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        A row of its tree, with its level (0 at the top) and whether it is
        open, so VoiceOver can say "level 2, expanded". A Properties panel
        section header (PropertiesSectionItem) is a tree item too, and
        always opens and closes.
    */
    @Override
    public void SendExtras() {
        int flags = MacAccessibilityExtras.OUTLINE_ROW | GetMenuFlag();
        int level = 0;
        int position = 0;
        int count = -1;
        Item_ item = GetItem();
        if (item instanceof TreeItem_) {
            TreeItem_ control = (TreeItem_) item;
            if (control.IsSubtree()) {
                flags = flags | MacAccessibilityExtras.EXPANDABLE | MacAccessibilityExtras.CAN_DISCLOSE;
                if (control.IsOpen()) {
                    flags = flags | MacAccessibilityExtras.EXPANDED;
                }
            }
            TreeItem_ parent = control.GetParentTreeItem();
            while (parent != null) {
                level = level + 1;
                parent = parent.GetParentTreeItem();
            }
            // Its place among its parent's items, as for list rows.
            position = MacAccessibility.GetTreeItemSetPosition(control);
            count = MacAccessibility.GetTreeItemSetSize(control);
        }
        MacAccessibilityExtras.setInfo(GetNodeID(), flags, level, -1, count, position, null);
    }
    /*
        CLICK (VoiceOver's press, or AXSelected set to true) selects the
        item. FOCUS focuses the tree on this item, as clicking it does, so
        "keyboard focus follows VoiceOver cursor" works on rows. EXPAND and
        COLLAPSE open and close it (from Mac.so: VO-\ sets AXDisclosing).
        Only a tree that can take the focus offers FOCUS.
    */
    @Override
    protected void AddActions(Node node, Item_ item) {
        super.AddActions(node, item);
        if (item instanceof TreeItem_) {
            TreeItem_ control = (TreeItem_) item;
            node.addAction(Action.CLICK);
            Tree_ tree = control.GetTree();
            if (!item.IsFocusable() && tree != null && tree.IsFocusable()) {
                node.addAction(Action.FOCUS);
            }
            if (control.IsSubtree()) {
                node.addAction(control.IsOpen() ? Action.COLLAPSE : Action.EXPAND);
            }
        }
    }

    @Override
    public void DoAction(ActionRequest request) {
        Item_ item = GetItem();
        if (!(item instanceof TreeItem_)) {
            super.DoAction(request);
            return;
        }
        TreeItem_ control = (TreeItem_) item;
        Tree_ tree = control.GetTree();
        if (request.action == Action.CLICK) {
            control.Select();
        } else if (request.action == Action.FOCUS && !item.IsFocusable() && tree != null && tree.IsFocusable()) {
            tree.Focus();
            control.Select();
        } else if (request.action == Action.EXPAND) {
            if (control.IsSubtree() && !control.IsOpen()) {
                control.Open();
            }
        } else if (request.action == Action.COLLAPSE) {
            if (control.IsSubtree() && control.IsOpen()) {
                control.Close();
            }
        } else if (request.action == Action.SHOW_CONTEXT_MENU) {
            // As Shift+F10 on the tree does for its selected item: the menu
            // acts on the selection, so the item is selected first.
            if (HasPopupMenu(control)) {
                if (!control.IsSelected()) {
                    control.Select();
                }
                BehaviorEvent event = new BehaviorEvent();
                event.SetItem(control);
                new TreeOpenPopupMenuBehavior().Run(event);
            }
        } else {
            super.DoAction(request);
        }
    }
}
