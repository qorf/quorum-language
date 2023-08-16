package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;
import java.util.Collections;
import quorum.Libraries.Containers.MultipleLineText_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Item_;

public class TextboxKit extends TextKitBase {
    // TODO: smart dirty line tracking
    private boolean textDirty = true;

    public TextboxKit() {
        SetRole(Role.TEXT_FIELD);
    }

    private NodeId GetLineID(int lineIndex) {
        return new NodeId(lineIndex, GetItem().GetHashCode());
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            builder.setMultiline();
            if(item instanceof TextBox_) { //technically not compiler guaranteed. You can set the code to anything.
                TextBox_ box = (TextBox_) item;
                TextBoxSelection_ selection = box.GetSelection();
                int start = selection.GetStartIndex();
                int end = selection.GetEndIndex();
                if(selection.IsEmpty()) {
                    start = box.GetCaretPosition();
                    end = box.GetCaretPosition();
                }
                MultipleLineText_ value = box.GetMultipleLineText();
                int startLineIndex = value.LineIndexFromCharacterIndex(start);
                int startIndexInLine = start - value.CharacterIndexFromLineIndex(startLineIndex);
                NodeId startLineID = GetLineID(startLineIndex);
                TextPosition startPosition = new TextPosition(startLineID, startIndexInLine);
                int endLineIndex = value.LineIndexFromCharacterIndex(end);
                int endIndexInLine = end - value.CharacterIndexFromLineIndex(endLineIndex);
                NodeId endLineID = GetLineID(endLineIndex);
                TextPosition endPosition = new TextPosition(endLineID, endIndexInLine);
                TextPosition anchor, focus;
                if (selection.IsEmpty() || selection.IsCaretAtEnd()) {
                    anchor = startPosition;
                    focus = endPosition;
                } else {
                    anchor = endPosition;
                    focus = startPosition;
                }
                builder.setTextSelection(new TextSelection(anchor, focus));
            }
            BuildChildren(builder);
            return builder.build();
        }
        return null;
    }

    public void SetTextDirty() {
        // TODO: smart dirty line tracking
        textDirty = true;
    }

    @Override
    public Iterable<NodeId> GetInternalChildren() {
        TextBox_ box = (TextBox_)GetItem();
        MultipleLineText_ value = box.GetMultipleLineText();
        int lineCount = value.GetLineCount();
        ArrayList<NodeId> result = new ArrayList<NodeId>();
        for (int i = 0; i < lineCount; i++) {
            result.add(GetLineID(i));
        }
        return result;
    }

    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        // TODO: smart dirty line tracking
        if (!textDirty) {
            return Collections.emptySet();
        }
        return GetInternalChildren();
    }

    @Override
    public void ClearDirtyInternalChildren() {
        // TODO: smart dirty line tracking
        textDirty = false;
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        if (id.high != (long)(GetItem().GetHashCode())) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        int lineIndex = (int)(id.low);
        Item_ item = GetItem();
        if(item != null && item instanceof TextBox_) {
            TextBox_ box = (TextBox_) item;
            MultipleLineText_ value = box.GetMultipleLineText();
            MultipleLineText_ line = value.GetLine(lineIndex);
            String s = line.GetText();
            if ((lineIndex + 1) == value.GetLineCount()) {
                if (s.endsWith("\r\n")) {
                    s = s.substring(0, s.length() - 2);
                } else if (s.endsWith("\r") || s.endsWith("\n")) {
                    s = s.substring(0, s.length() - 1);
                }
            } else if (!s.endsWith("\r") && !s.endsWith("\n")) {
                s += value.GetLineSeparator();
            }
            NodeBuilder builder = BuildLineBase(lineIndex, s);
            return builder.build();
        }
        return null;
    }

    @Override
    boolean IsBeginningOfToken(int lineIndex, int indexInLine) {
        TextBox_ box = (TextBox_)GetItem();
        MultipleLineText_ value = box.GetMultipleLineText();
        int i = value.CharacterIndexFromLineIndex(lineIndex) + indexInLine;
        return box.IsBeginningOfToken(i);
    }
}
