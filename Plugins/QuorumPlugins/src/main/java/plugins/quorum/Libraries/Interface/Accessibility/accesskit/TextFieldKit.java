package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;
import java.util.Collections;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Item_;

public class TextFieldKit extends ItemKit{
    private boolean textDirty = true;

    public TextFieldKit() {
        SetRole(Role.TEXT_FIELD);
    }

    private NodeId GetLineID() {
        return new NodeId(0, GetItem().GetHashCode());
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
            NodeBuilder builder = new NodeBuilder(Role.INLINE_TEXT_BOX);
            builder.setTextDirection(TextDirection.LEFT_TO_RIGHT);
            String s = field.GetText();
            builder.setValue(s);
            ArrayList<Byte> characterLengths = new ArrayList<Byte>();
            ArrayList<Byte> wordLengths = new ArrayList<Byte>();
            byte wordLength = 0;
            // FUTURE: handle combining characters
            for (int i = 0; i < s.length(); ) {
                if (i > 0 && field.IsBeginningOfToken(i)) {
                    wordLengths.add(wordLength);
                    wordLength = 0;
                }
                int codePoint = s.codePointAt(i);
                byte byteCount = UTF8ByteCount(codePoint);
                characterLengths.add(byteCount);
                wordLength += byteCount;
                i += Character.charCount(codePoint);
            }
            wordLengths.add(wordLength);

            //This is slow and silly, but fast to write, so it will get us prototyping.
            byte[] charLen = new byte[characterLengths.size()];
            byte[] wordLen = new byte[wordLengths.size()];

            for(int i = 0; i < characterLengths.size(); i++) {
                charLen[i] = characterLengths.get(i).byteValue();
            }

            for(int i = 0; i < wordLengths.size(); i++) {
                wordLen[i] = wordLengths.get(i).byteValue();
            }
            builder.setCharacterLengths(charLen);
            builder.setWordLengths(wordLen);

            return builder.build();
        }
        return null;
    }

    private static byte UTF8ByteCount(int codePoint) {
        if (codePoint <= 0x7f) {
            return 1;
        }
        if (codePoint <= 0x7ff) {
            return 2;
        }
        if (codePoint <= 0xffff) {
            return 3;
        }
        if (codePoint <= 0x10fff) {
            return 4;
        }
        throw new IllegalArgumentException("code point out of range");
    }
}
