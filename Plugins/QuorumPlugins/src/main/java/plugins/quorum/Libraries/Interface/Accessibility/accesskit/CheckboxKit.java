package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import quorum.Libraries.Interface.Controls.Checkbox_;
import quorum.Libraries.Interface.Item_;

public class CheckboxKit extends ClickableKit {
    public CheckboxKit() {
        SetRole(Role.CHECK_BOX);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            Node builder = new Node(GetRole());
            builder.setBounds(rect);
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
            if(item instanceof Checkbox_) { //technically noto compiler guaranteed. You can set the code to anything.
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
