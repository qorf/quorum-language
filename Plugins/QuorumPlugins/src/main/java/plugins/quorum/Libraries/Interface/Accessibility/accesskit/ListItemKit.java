package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Item_;

public class ListItemKit extends ItemKit{
    public ListItemKit() {
        SetRole(Role.LIST_ITEM);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof ListItem_) { //technically not compiler guaranteed. You can set the code to anything.
            ListItem_ listItem = (ListItem_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);

            builder.setLabel(listItem.GetText());
            builder.setDescription(listItem.GetDescription());
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }
}
