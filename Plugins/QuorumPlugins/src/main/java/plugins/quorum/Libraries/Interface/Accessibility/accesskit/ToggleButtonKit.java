package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import dev.accesskit.Toggled;
import quorum.Libraries.Interface.Controls.Checkbox_;
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
            if(item instanceof Checkbox_) { //technically not compiler guaranteed. You can set the code to anything.
                Checkbox_ box = (Checkbox_) item;
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
