package plugins.quorum.Libraries.Interface.Accessibility.accesskit;


import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Item_;

public class ButtonKit extends ClickableKit {
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
            builder.setDescription(item.GetDescription());
            return builder;
        }
        return null;
    }
}
