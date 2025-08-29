package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import plugins.quorum.Libraries.Interface.AccessibilityManager;
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

            int rowIndex = AccessibilityManager.GetCellRowIndex(control);
            int columnIndex = AccessibilityManager.GetCellColumnIndex(control);

            //this may need to change as access kit changes. It feels like this
            //should be setValue, but that may be an incorrect assumption.
            builder.setLabel(control.GetName());
        } else {
            builder.setLabel(item.GetName() + ", " + item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }
}
