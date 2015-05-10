package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * Handles processing for combo box objects.
 *
 * @author jeff
 */
public class ComboBoxProcessor extends AbstractSpeechProcessor {
    private boolean separator = false;
    private boolean keyEvent = false;

    public String process() {
        if (keyEvent && separator)
            return "Separator: This item is a visual separator in the combo box. It is not a valid item.";
        else if (keyEvent)
            return text;
        else
            return text + " combo box";
    }

    /**
     * @return the separator
     */
    public boolean isSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(boolean separator) {
        this.separator = separator;
    }

    /**
     * @return the keyEvent
     */
    public boolean isKeyEvent() {
        return keyEvent;
    }

    /**
     * @param keyEvent the keyEvent to set
     */
    public void setKeyEvent(boolean keyEvent) {
        this.keyEvent = keyEvent;
    }
}
