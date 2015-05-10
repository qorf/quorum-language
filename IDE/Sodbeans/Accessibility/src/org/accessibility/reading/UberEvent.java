/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import org.accessibility.reading.readers.ReadType;

/**
 * This class represents an event which is a combination of other event types
 * and rolled up into one. This is essentially a support class, which makes it
 * so different types of visual components, which all send different event
 * types, can be rolled up into one set of events for processing.
 * 
 * @author Andreas Stefik
 */
public class UberEvent {
    public KeyEvent key;
    public MouseEvent mouse;
    public PropertyChangeEvent property;
    public Object object;
    public ReadType readType;
    /**
     * This flag is true only if the keyboard event is a preprocessing event
     * passed through a java.awt.KeyEventDispatcher's 
     */
    public boolean preprocess;

    public void reset() {
        key = null;
        mouse = null;
        property = null;
        object = null;
        readType = null;
        preprocess = false;
    }
}
