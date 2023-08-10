package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Item_;

public class TextboxKit extends ItemKit{
    public TextboxKit() {
        SetRole(Role.TEXT_FIELD);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof TextBox_) { //technically not compiler guaranteed. You can set the code to anything.
                TextBox_ box = (TextBox_) item;
                String s = box.GetText(); //this would get all text in the box
                int position = box.GetCaretPosition();//global caret position as a raw index from 0
            }
            return builder.build();
        }
        return null;
    }

    public float[] GetCharacterWidths(TextBox_ box, int line) {
        Number32BitArray array = (Number32BitArray) box.GetCharacterWidths(line);
        return array.plugin_.floats;
    }

    public float[] GetCharacterXPositions(TextBox_ box, int line) {
        Number32BitArray array = (Number32BitArray) box.GetCharacterXPositions(line);
        return array.plugin_.floats;
    }
}
