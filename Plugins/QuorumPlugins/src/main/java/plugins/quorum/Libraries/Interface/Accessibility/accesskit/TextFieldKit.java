package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.NodeId;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Interface.Controls.TextBox_;
import dev.accesskit.TextPosition;
import dev.accesskit.TextSelection;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Item_;

public class TextFieldKit extends ItemKit{
    private final TextFieldLineKit lineKit;

    public TextFieldKit() {
        SetRole(Role.TEXT_FIELD);
        lineKit = new TextFieldLineKit();
        AddChild(lineKit);
    }

    @Override
    public void SetItem(Item_ item) {
        super.SetItem(item);
        lineKit.SetItem(item);
    }

    public NodeId GetLineNodeID() {
        return lineKit.GetNodeID();
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof TextField_) { //technically not compiler guaranteed. You can set the code to anything.
                TextField_ field = (TextField_) item;
                TextFieldSelection_ selection = field.GetSelection();
                int start = selection.GetStartIndex();
                int end = selection.GetEndIndex();
                if(selection.IsEmpty()) {
                    start = field.GetCaretPosition();
                    end = field.GetCaretPosition();
                }
                NodeId lineID = GetLineNodeID();
                TextPosition anchor, focus;
                if (selection.IsEmpty() || selection.IsCaretAtEnd()) {
                    anchor = new TextPosition(lineID, start);
                    focus = new TextPosition(lineID, end);
                } else {
                    anchor = new TextPosition(lineID, end);
                    focus = new TextPosition(lineID, start);
                }
                builder.setTextSelection(new TextSelection(anchor, focus));
                builder.setTextSelection(new TextSelection(anchor, focus));
            }
            BuildChildren(builder);
            return builder.build();
        }
        return null;
    }
    public static float[] GetCharacterWidths(TextField_ field) {
        Number32BitArray array = (Number32BitArray) field.GetCharacterWidths();
        return array.plugin_.floats;
    }

    public static float[] GetCharacterXPositions(TextField_ field) {
        Number32BitArray array = (Number32BitArray) field.GetCharacterXPositions();
        return array.plugin_.floats;
    }
}
