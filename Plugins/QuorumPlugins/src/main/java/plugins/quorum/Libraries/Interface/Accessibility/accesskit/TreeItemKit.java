package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
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

            int size = MacAccessibility.GetTreeItemSetSize(control);
            int position = MacAccessibility.GetTreeItemSetPosition(control);
            boolean expanded = control.IsOpen();
            builder.setPositionInSet(position);
            builder.setSizeOfSet(size);

            //this may need to change as access kit changes. It feels like this
            //should be setValue, but that may be an incorrect assumption.
            builder.setLabel(control.GetName());
            builder.setDescription(control.GetDescription());
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }
}
