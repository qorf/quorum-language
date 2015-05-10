/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * A processor for JSlider objects
 * @author jeff
 */
public class SliderProcessor extends AbstractSpeechProcessor {
    private boolean keyEvent = false;
    private int minimum = 0;
    private int maximum = 0;
    private int value = 0;
    
    @Override
    public String process() {
        if (keyEvent)
        {
                if (value == minimum)
            {
                return "minimum value for " + text;
            }
            else if (value == maximum)
            {
                return "maximum value for " + text;
            }
            else
            {
                return value + " of " + maximum + " for " + text;
            }
        }
        
        // Not a key event. Return current value and name.
        return text + ", has value " + value + " of " + maximum;
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

    /**
     * @return the minimum
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * @return the maximum
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the Value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
