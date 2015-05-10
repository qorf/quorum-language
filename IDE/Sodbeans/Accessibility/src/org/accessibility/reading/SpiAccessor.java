/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading;

/**
 *
 * @author susanna
 */
import javax.swing.text.Document;


/**
 *
 * @author Vita Stejskal
 */
public abstract class SpiAccessor {

    private static SpiAccessor ACCESSOR = null;

    public static synchronized void register(SpiAccessor accessor) {
        assert ACCESSOR == null : "Can't register two SPI package accessors!"; //NOI18N
        ACCESSOR = accessor;
    }

    public static synchronized SpiAccessor get() {
        try {
            Class clazz = Class.forName(MatcherContext.class.getName());
        } catch (ClassNotFoundException e) {
            // ignore
        }

        assert ACCESSOR != null : "There is no SPI package accessor available!"; //NOI18N
        return ACCESSOR;
    }

    protected SpiAccessor() {
    }

    public abstract MatcherContext createCaretContext(Document document, int offset, boolean backward, int lookahead);
}
