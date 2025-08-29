package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import plugins.quorum.Libraries.Interface.Accessibility.PushDownAccessibility;
import plugins.quorum.Libraries.Interface.AccessibilityManager;
import quorum.Libraries.Interface.Controls.Spreadsheet_;
import quorum.Libraries.Interface.Item_;

public class TableKit extends ItemKit{
    public TableKit() {
        SetRole(Role.TABLE);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof Spreadsheet_) { //technically not compiler guaranteed. You can set the code to anything.
            Spreadsheet_ control = (Spreadsheet_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);

            int columnsSize = PushDownAccessibility.GetTableColumnsSize(control);
            int rowsSize = PushDownAccessibility.GetTableRowsSize(control);


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
