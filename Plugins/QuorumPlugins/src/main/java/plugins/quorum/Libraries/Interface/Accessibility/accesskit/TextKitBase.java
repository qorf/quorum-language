package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.ArrayList;

abstract class TextKitBase extends ItemKit {
    /*
        AccessKit keeps each word start as one unsigned byte (a character
        index), so a text run can only mark words that start in its first
        256 characters. Later words are left out, which keeps the list sorted.
    */
    private static final int MAXIMUM_WORD_START = 255;

    abstract boolean IsBeginningOfToken(int lineIndex, int charIndexInLine);

    final Node BuildLineBase(int lineIndex, String s) {
        Node builder = new Node(Role.TEXT_RUN);
        builder.setTextDirection(TextDirection.LEFT_TO_RIGHT);
        builder.setValue(s);
        ArrayList<Byte> characterLengths = new ArrayList<Byte>();
        /*
            The index of the character (an entry in characterLengths, not a
            byte or a Java char) where each word starts. Each line is its own
            text run and the first character always starts a word, as it did
            when these were word lengths.
        */
        ArrayList<Byte> wordStarts = new ArrayList<Byte>();
        // FUTURE: handle combining characters
        for (int i = 0; i < s.length(); ) {
            int characterIndex = characterLengths.size();
            if (characterIndex == 0) {
                wordStarts.add((byte) 0);
            } else if (characterIndex <= MAXIMUM_WORD_START && IsBeginningOfToken(lineIndex, i)) {
                // Indexes 128 to 255 become negative Java bytes, but the
                // bits are the same, and AccessKit reads them as unsigned.
                wordStarts.add((byte) characterIndex);
            }
            byte byteCount;
            if (s.charAt(i) == '\r' && (i + 1) < s.length() && s.charAt(i + 1) == '\n') {
                byteCount = 2;
                i += 2;
            } else {
                int codePoint = s.codePointAt(i);
                byteCount = UTF8ByteCount(codePoint);
                i += Character.charCount(codePoint);
            }
            characterLengths.add(byteCount);
        }

        //This is slow and silly, but fast to write, so it will get us prototyping.
        byte[] charLen = new byte[characterLengths.size()];
        byte[] starts = new byte[wordStarts.size()];

        for(int i = 0; i < characterLengths.size(); i++) {
            charLen[i] = characterLengths.get(i).byteValue();
        }

        for(int i = 0; i < wordStarts.size(); i++) {
            starts[i] = wordStarts.get(i).byteValue();
        }
        builder.setCharacterLengths(charLen);
        builder.setWordStarts(starts);

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
        if (codePoint <= 0x10ffff) {
            return 4;
        }
        throw new IllegalArgumentException("code point out of range");
    }
}
