package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Item_;

public class TextFieldKit extends ItemKit{
    public TextFieldKit() {
        SetRole(Role.TEXT_FIELD);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof TextField_) { //technically not compiler guaranteed. You can set the code to anything.
                TextField_ box = (TextField_) item;
                String s = box.GetText(); //this would get all text in the box
                int position = box.GetCaretPosition();//global caret position as a raw index from 0
            }
            return builder.build();
        }
        return null;
    }
    public float[] GetCharacterWidths(TextField_ field) {
        Number32BitArray array = (Number32BitArray) field.GetCharacterWidths();
        return array.plugin_.floats;
    }

    public float[] GetCharacterXPositions(TextField_ field) {
        Number32BitArray array = (Number32BitArray) field.GetCharacterXPositions();
        return array.plugin_.floats;
    }
}
