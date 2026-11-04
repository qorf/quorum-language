package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.Cell_;
import quorum.Libraries.Interface.Item_;

public class CellKit extends ItemKit{
    public CellKit() {
        SetRole(Role.CELL);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof Cell_) { //technically not compiler guaranteed. You can set the code to anything.
            Cell_ control = (Cell_) item;
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
