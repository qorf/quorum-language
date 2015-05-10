/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility;

/**
 * This interface defines a very general controller for the system. After
 * calling gainControl, this controller will determine which kinds of system
 * events it needs to gather information about to give accessible information
 * to the user.
 * @author astefik
 */
public interface ScreenReader {
    /**
     * This method tells the Accessible Controller to try to garner
     * access to whatever kinds of events it needs to give useful information
     * to a user. For example, an implementation might pull global keyboard
     * events from the system and delegate to sub-controllers.
     */
    public void gainControl();

    /**
     * This method returns control of whatever events it was harnessing.
     */
    public void loseControl();

    /**
     * Sets the focus of a component to an item relative to an object, if one
     * exists. If object is null, this call is ignored. Left, right, up, and
     * down should not be interpreted literally, but are in reference to the
     * keys that were pressed to make these events happen.
     * 
     * @param object
     */
    public void magicKeyLeft(Object object);

    /**
     * Sets the focus of a component to an item relative to an object, if one
     * exists. If object is null, this call is ignored. Left, right, up, and
     * down should not be interpreted literally, but are in reference to the
     * keys that were pressed to make these events happen.
     *
     * @param object
     */
    public void magicKeyRight(Object object);

    /**
     * Sets the focus of a component to an item relative to an object, if one
     * exists. If object is null, this call is ignored. Left, right, up, and
     * down should not be interpreted literally, but are in reference to the
     * keys that were pressed to make these events happen.
     *
     * @param object
     */
    public void magicKeyUp(Object object);

    /**
     * Sets the focus of a component to an item relative to an object, if one
     * exists. If object is null, this call is ignored. Left, right, up, and
     * down should not be interpreted literally, but are in reference to the
     * keys that were pressed to make these events happen.
     *
     * @param object
     */
    public void magicKeyDown(Object object);

    /**
     * This method attempts to determine the location of the user in the
     * environment and speak it to the user.
     */
    public void speakLocation();
}
