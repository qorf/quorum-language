package org.quorum.language;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;
import org.netbeans.spi.lexer.LexerInput;
import org.openide.util.Parameters;

/**
 * An adapter class that implements ANTLR's {@link CharStream} by delegating to
 * NetBeans' {@link LexerInput}. The position of the {@code LexerInput} is
 * always kept in sync with the position of the {@code CharStream}, as indicated
 * by {@link #index()}. For instance, {@link LexerInput#backup(int)} is called
 * after lookaheads to prevent the {@code LexerInput} from advancing until
 * {@link #consume()} has been called.
 *
 * <p>
 * Great care has been taken to try to conform to the specifications in the
 * {@code CharStream} Javadoc, including error handling. Any deviations are bugs
 * unless otherwise noted.
 *
 * <p>
 * This implementation has been developed from scratch; it is <em>not</em> based
 * on the
 * <a href="http://wiki.netbeans.org/New_Language_Support_Tutorial_Antlr">example
 * implementation</a>
 * in the NetBeans documentation, which as of 2015-10-27 implements an earlier
 * version of the CharStream interface (pre-ANTLR4, e.g. with different
 * {@link #mark()} semantics).
 *
 * <p>
 * This implementation, like {@link org.antlr.v4.runtime.ANTLRInputStream}, is
 * not thread-safe. The {@code CharStream} Javadoc does not specify a
 * thread-safety requirement.
 *
 * @author Eirik Bakke ({@literal ebakke@csail.mit.edu})
 */
public class AntlrCharStream implements CharStream {
    private static final Logger LOG = Logger.getLogger(AntlrCharStream.class.getName());
    private static final int UNKNOWN_CHARACTER = Integer.MIN_VALUE;
    private final LexerInput lexerInput;
    private final String sourceName;
    /**
     * For sanity checking only.
     */
    private int lastMarkerID = 100;
    /**
     * Index of the next character to be consumed.
     */
    private int index = 0;
    /**
     * The character at index - 1, possibly LexerInput.EOF, or
     * UNKNOWN_CHARACTER.
     */
    private int lastConsumedCharacter = UNKNOWN_CHARACTER;

    public AntlrCharStream(LexerInput lexerInput, String sourceName) {
        Parameters.notNull("lexerInput", lexerInput);
        Parameters.notNull("sourceName", sourceName);
        if (UNKNOWN_CHARACTER == LexerInput.EOF) {
            throw new AssertionError();
        }
        this.lexerInput = lexerInput;
        this.sourceName = sourceName;
    }

    /**
     * This invariant may be temporarily invalidated, but should hold before and
     * after public methods are called.
     */
    private void checkEOFNotConsumed() {
        if (lastConsumedCharacter == LexerInput.EOF) {
            throw new AssertionError();
        }
    }

    @Override
    public String getText(Interval interval) {
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.log(Level.FINEST, "getText({0})", interval);
        }
        Parameters.notNull("interval", interval);
        if (interval.a < 0 || interval.b < interval.a - 1) {
            throw new IllegalArgumentException("Invalid interval " + interval);
        }
        checkEOFNotConsumed();
        final char ret[] = new char[interval.length()];
        final int oldIndex = index;
        seekInternal(interval.a);
        int i;
        for (i = 0; i < interval.length(); i++) {
            consumeInternal();
            /* Technically, we should always throw an exception if "interval.b lies at or past the end of
             the stream", but org.antlr.v4.runtime.Lexer.notifyListeners relies on being able to call
             getText with an interval that ends on EOF, inclusive, as returned from index(), so permit this
             case. ANTLRInputStream also permits this. */
            if (lastConsumedCharacter == LexerInput.EOF) {
                break;
            }
            ret[i] = (char) lastConsumedCharacter;
        }
        seekInternal(oldIndex);
        checkEOFNotConsumed();
        return new String(ret, 0, i);
    }

    @Override
    public int LA(final int i) {
        checkEOFNotConsumed();
        if (i == 0) {
            throw new IllegalArgumentException("Argument i may not be 0");
        }
        final int oldIndex = index;
        final int requestedIndex = i > 0
                ? (index + i - 1)
                : (index + i);
        if (requestedIndex < 0) {
            throw new IllegalStateException();
        }
        seekInternal(requestedIndex + 1);
        if (lastConsumedCharacter == UNKNOWN_CHARACTER) {
            seekInternal(requestedIndex);
            consumeInternal();
        }
        final int ret = lastConsumedCharacter;
        seekInternal(oldIndex);
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.log(Level.FINEST, "LA({0}) returning {1}", new Object[]{
                i, ret == LexerInput.EOF ? "EOF" : ("'" + ((char) ret) + "'")
            });
        }
        checkEOFNotConsumed();
        return ret == LexerInput.EOF ? CharStream.EOF : ret;
    }

    @Override
    public void consume() {
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.finest("consume()");
        }
        checkEOFNotConsumed();
        consumeInternal();
        if (lastConsumedCharacter == LexerInput.EOF) {
            // The client should never see an index implying that the EOF has been consumed.
            seek(index - 1);
            checkEOFNotConsumed();
            throw new IllegalStateException();
        }
        checkEOFNotConsumed();
    }

    /**
     * Guaranteed not to leave lastConsumedCharacter == UNKNOWN_CHARACTER.
     * Unlike consume(), this method allows a single EOF at the end of the
     * stream to be consumed.
     */
    private void consumeInternal() {
        /* Throw an IllegalArgumentException rather than an AssertionError in this case, so don't use
         checkEOFNotConsumed. */
        if (lastConsumedCharacter == LexerInput.EOF) {
            throw new IllegalArgumentException("Can't consume more than a single EOF");
        }
        lastConsumedCharacter = lexerInput.read();
        index++;
        if (lastConsumedCharacter == UNKNOWN_CHARACTER) {
            throw new AssertionError();
        }
    }

    @Override
    public void seek(int targetIndex) {
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.log(Level.FINEST, "seek({0})", targetIndex);
        }
        checkEOFNotConsumed();
        seekInternal(targetIndex);
        checkEOFNotConsumed();
    }

    private void seekInternal(int targetIndex) {
        if (targetIndex < 0) {
            throw new IllegalArgumentException("Got negative targetIndex " + targetIndex);
        }
        if (targetIndex < index) {
            lexerInput.backup(index - targetIndex);
            lastConsumedCharacter = UNKNOWN_CHARACTER;
            index = targetIndex;
        } else {
            while (index < targetIndex) {
                consumeInternal();
                if (lastConsumedCharacter == LexerInput.EOF) {
                    // Back up to avoid returning in a state where the EOF has been consumed.
                    seekInternal(index - 1);
                    break;
                }
            }
        }
    }

    @Override
    public int index() {
        if (LOG.isLoggable(Level.FINEST)) {
            LOG.log(Level.FINEST, "index() returning {0}", index);
        }
        checkEOFNotConsumed();
        return index;
    }

    @Override
    public int mark() {
        // LexerInput does not need to be marked, so just perform some sanity checks.
        return ++lastMarkerID;
    }

    @Override
    public void release(int marker) {
        if (marker != lastMarkerID) {
            throw new IllegalArgumentException("Invalid marker " + marker);
        }
        lastMarkerID--;
    }

    @Override
    public int size() {
        // Throwing an UnsupportedOperationException is valid behavior here.
        throw new UnsupportedOperationException("Size not known");
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }
}
