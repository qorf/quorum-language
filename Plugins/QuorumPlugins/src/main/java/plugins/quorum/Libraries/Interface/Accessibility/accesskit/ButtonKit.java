package plugins.quorum.Libraries.Interface.Accessibility.accesskit;


import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import quorum.Libraries.Interface.Item_;

public class ButtonKit extends ItemKit{
    public ButtonKit() {
        SetRole(Role.BUTTON);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            return builder.build();
        }
        return null;
    }
}
