package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.NodeId;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import java.util.ArrayList;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Item_;

public class TextFieldLineKit extends ItemKit{
    private final NodeId id;

    public TextFieldLineKit() {
        SetRole(Role.INLINE_TEXT_BOX);
        id = new NodeId(hashCode());
    }

    @Override
    public NodeId GetNodeID() {
        return id;
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null && item instanceof TextField_) {
            TextField_ field = (TextField_) item;
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setTextDirection(TextDirection.LEFT_TO_RIGHT);
            String s = field.GetText();
            builder.setValue(s);
            ArrayList<byte> characterLengths = new ArrayList<byte>();
            ArrayList<byte> wordLengths = new ArrayList<byte>();
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
            builder.setCharacterLengths((byte[])(characterLengths.toArray()));
            builder.setWordLengths((byte[])(wordLengths.toArray()));
            return builder.build();
        }
        return null;
    }

    private static byte UTF8ByteCount(int CodePoint) {
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
