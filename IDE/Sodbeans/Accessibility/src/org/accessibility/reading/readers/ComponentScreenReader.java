/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import org.accessibility.reading.UberEvent;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 * A general class for an object that can read some part of the screen. Subclasses
 * of this item are specialized in that they read certain kinds of components.
 * For example, one subclass might be good at reading an editor, while another
 * is good at reading Jtree objects.
 * 
 * @author Andreas Stefik
 */
public interface ComponentScreenReader {
    /**
     * Sets a component that will ultimately be read.
     * @param component
     */
    public void setObject(Object object);

    /**
     * Analyzes the component that was set and attempts to provide a reading
     * for that component. Not all items are naturally accessible, but this
     * attempts to force the items to be accessible anyway.
     */
    public void read();

    /**
     * Returns a speech processor object. This object will read the component's
     * values in a component specific way. For example, a text editor might
     * read differently than a menu item.
     * 
     * @return 
     */
    public SpeechProcessor getRead();

    /**
     * Sets a generic event which encapsulates all events that have come
     * from the system and possibly some hints on how to read them.
     * @param event
     */
    public void setUberEvent(UberEvent event);
    
    /**
     * This call sets the magnifier, if active, to the given component.
     */
    public void magnify();
}
