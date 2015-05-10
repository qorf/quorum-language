/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * Handles radiobutton processing.
 * @author jeff
 */
public class RadioButtonProcessor extends AbstractSpeechProcessor {
    private boolean checked = false;
    private boolean keyEvent = false;

    public String process() {
        if (!keyEvent) {
            if (checked)
                text += ", selected radio button";
            else
                text += ", unselected radio button";
        }
        else {
            if (checked)
                text = "Selected " + text + " radio button";
            else
                text = "Unselected " + text + " radio button";
        }

        return text;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
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
