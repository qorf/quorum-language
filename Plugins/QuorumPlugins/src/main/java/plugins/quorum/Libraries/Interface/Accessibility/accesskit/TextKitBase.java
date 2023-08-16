package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;

abstract class TextKitBase extends ItemKit {
    abstract boolean IsBeginningOfToken(int lineIndex, int charIndexInLine);

    final NodeBuilder BuildLineBase(int lineIndex, String s) {
        NodeBuilder builder = new NodeBuilder(Role.INLINE_TEXT_BOX);
        builder.setTextDirection(TextDirection.LEFT_TO_RIGHT);
        builder.setValue(s);
        ArrayList<Byte> characterLengths = new ArrayList<Byte>();
        ArrayList<Byte> wordLengths = new ArrayList<Byte>();
        byte wordLength = 0;
        // FUTURE: handle combining characters
        for (int i = 0; i < s.length(); ) {
            if (i > 0 && IsBeginningOfToken(lineIndex, i)) {
                wordLengths.add(wordLength);
                wordLength = 0;
            }
            byte byteCount;
            if (s.charAt(i) == '\r' && (i + 1) < s.length() && s.charAt(i + 1) == '\n') {
                byteCount = 2;
                i += 2;
            } else {
                int codePoint = s.codePointAt(i);
                byte byteCount = UTF8ByteCount(codePoint);
                i += Character.charCount(codePoint);
            }
            characterLengths.add(byteCount);
            wordLength += byteCount;
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

        return builder;
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
