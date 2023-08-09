package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Item_;

public class RadioButtonKit extends ItemKit{
    public RadioButtonKit() {
        SetRole(Role.RADIO_BUTTON);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof RadioButton_) { //technically not compiler guaranteed. You can set the code to anything.
                RadioButton_ box = (RadioButton_) item;
                boolean state = box.GetToggleState();
                if(state) {
                    builder.setCheckedState(CheckedState.TRUE);
                } else {
                    builder.setCheckedState(CheckedState.FALSE);
                }
            }
            return builder.build();
        }
        return null;
    }
}
