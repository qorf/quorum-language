package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.TreeTableCell_;
import quorum.Libraries.Interface.Item_;

public class TreeTableCellKit extends ItemKit{
    public TreeTableCellKit() {
        SetRole(Role.CELL);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof TreeTableCell_) { //technically not compiler guaranteed. You can set the code to anything.
            TreeTableCell_ control = (TreeTableCell_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);

            // Row and column (MacAccessibility.GetCellRowIndex and
            // GetCellColumnIndex) aren't sent: AccessKit's macOS adapter
            // has no table attributes yet.
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
