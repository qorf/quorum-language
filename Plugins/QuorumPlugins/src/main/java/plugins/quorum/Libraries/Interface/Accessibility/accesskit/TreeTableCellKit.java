package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import plugins.quorum.Libraries.Interface.Accessibility.MacAccessibility;
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

            int rowIndex = MacAccessibility.GetCellRowIndex(control);
            int columnIndex = MacAccessibility.GetCellColumnIndex(control);

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
