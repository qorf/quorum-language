package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;
import java.util.Collections;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Item_;

public class TextFieldKit extends TextKitBase {
    private boolean textDirty = true;

    public TextFieldKit() {
        SetRole(Role.TEXT_INPUT);
    }

    private NodeId GetLineID() {
        long id = (long)(GetItem().GetHashCode()) << 32;
        return new NodeId(id);
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
                NodeId lineID = GetLineID();
                TextPosition anchor, focus;
                if (selection.IsEmpty() || selection.IsCaretAtEnd()) {
                    anchor = new TextPosition(lineID, start);
                    focus = new TextPosition(lineID, end);
                } else {
                    anchor = new TextPosition(lineID, end);
                    focus = new TextPosition(lineID, start);
                }
                builder.setTextSelection(new TextSelection(anchor, focus));
            }
            BuildChildren(builder);
            return builder.build();
        }
        return null;
    }

    public void SetTextDirty() {
        textDirty = true;
    }

    @Override
    public Iterable<NodeId> GetInternalChildren() {
        return Collections.singleton(GetLineID());
    }

    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        if (!textDirty) {
            return Collections.emptySet();
        }
        return GetInternalChildren();
    }

    @Override
    public void ClearDirtyInternalChildren() {
        textDirty = false;
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        if (!id.equals(GetLineID())) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        Item_ item = GetItem();
        if(item != null && item instanceof TextField_) {
            TextField_ field = (TextField_) item;
            String s = field.GetText();
            NodeBuilder builder = BuildLineBase(0, s);
            return builder.build();
        }
        return null;
    }

    @Override
    boolean IsBeginningOfToken(int lineIndex, int i) {
        if (lineIndex != 0) {
            throw new IllegalArgumentException("non-zero line index");
        }
        TextField_ field = (TextField_)GetItem();
        return field.IsBeginningOfToken(i);
    }
}
