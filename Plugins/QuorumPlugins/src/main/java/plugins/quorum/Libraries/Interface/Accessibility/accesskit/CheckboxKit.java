package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import quorum.Libraries.Interface.Controls.Checkbox_;
import quorum.Libraries.Interface.Item_;

public class CheckboxKit extends ItemKit{
    public CheckboxKit() {
        SetRole(Role.CHECK_BOX);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof Checkbox_) { //technically noto compiler guaranteed. You can set the code to anything.
                Checkbox_ box = (Checkbox_) item;
                boolean state = box.GetToggleState();
                if(state) {
                    builder.setChecked(Checked.TRUE);
                } else {
                    builder.setChecked(Checked.FALSE);
                }
            }
            return builder.build();
        }
        return null;
    }
}
