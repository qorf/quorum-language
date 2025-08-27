package plugins.quorum.Libraries.Interface.Accessibility.accesskit;


import dev.accesskit.Node;
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
            Node builder = new Node(GetRole());
            builder.setBounds(rect);
            builder.setLabel(item.GetName());
            return builder;
        }
        return null;
    }
}
