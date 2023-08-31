package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;
import java.util.Collections;
import quorum.Libraries.Containers.MultipleLineText_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Item_;

public class TextboxKit extends TextKitBase {
    private int dirtyLinesStart = 0;
    private int dirtyLinesEnd = -1;

    public TextboxKit() {
        SetRole(Role.MULTILINE_TEXT_INPUT);
    }

    private NodeId GetLineID(int lineIndex) {
        long id = (long)(GetItem().GetHashCode()) << 32;
        id |= (long)lineIndex;
        return new NodeId(id);
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

    public void MarkLineDirty(int index) {
        if (dirtyLinesStart == -1 || (dirtyLinesEnd != -1 && (index + 1) > dirtyLinesEnd)) {
            dirtyLinesEnd = index + 1;
        }
        if (dirtyLinesStart == -1 || index < dirtyLinesStart) {
            dirtyLinesStart = index;
        }
    }

    public void MarkLinesDirty(int start) {
        if (dirtyLinesStart == -1 || start < dirtyLinesStart) {
            dirtyLinesStart = start;
        }
        dirtyLinesEnd = -1;
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
        if (dirtyLinesStart == -1) {
            return Collections.emptySet();
        }
        int start = dirtyLinesStart;
        int end = dirtyLinesEnd;
        if (end == -1) {
            TextBox_ box = (TextBox_)GetItem();
            MultipleLineText_ value = box.GetMultipleLineText();
            end = value.GetLineCount();
        }
        ArrayList<NodeId> result = new ArrayList<NodeId>();
        for (int i = start; i < end; i++) {
            result.add(GetLineID(i));
        }
        return result;
    }

    @Override
    public void ClearDirtyInternalChildren() {
        dirtyLinesStart = -1;
        dirtyLinesEnd = -1;
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
