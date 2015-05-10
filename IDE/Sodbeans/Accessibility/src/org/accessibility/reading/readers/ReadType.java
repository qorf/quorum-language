/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

/**
 * Determines what kind of event is being passed to an UberEvent object.
 * For example, Focus means a FocusListener event was fired, KEYBOARD means
 * a KeyboardListener, mouse a MouseListener, and so on. One special case
 * is MENU, which, due to problems on each platform, is cobbled together
 * from a series of event listeners. The object passed when MENU is the
 * ReadType, however, is an appropriate menu object.
 * 
 * @author astefik
 */
public enum ReadType {
    FOCUS, KEYBOARD, MOUSE, MENU;
}
