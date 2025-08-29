package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import dev.accesskit.Toggled;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Item_;

public class ToggleButtonKit extends ItemKit{
    public ToggleButtonKit() {
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
            if(item instanceof ToggleButton_) { //technically not compiler guaranteed. You can set the code to anything.
                ToggleButton_ box = (ToggleButton_) item;
                boolean state = box.GetToggleState();
                if(state) {
                    builder.setToggled(Toggled.TRUE);
                } else {
                    builder.setToggled(Toggled.FALSE);
                }
            }
            return builder;
        }
        return null;
    }
}
