package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import plugins.quorum.Libraries.Interface.Accessibility.MacAccessibility;
import quorum.Libraries.Interface.Controls.TreeItem_;
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
        int flags = MacAccessibilityExtras.OUTLINE_ROW;
        int level = 0;
        int position = 0;
        int count = -1;
        Item_ item = GetItem();
        if (item instanceof TreeItem_) {
            TreeItem_ control = (TreeItem_) item;
            if (control.IsSubtree()) {
                flags = flags | MacAccessibilityExtras.EXPANDABLE;
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
}
