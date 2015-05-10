/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. 
 */
package org.accessibility.startup;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import org.magnify.MagnifierFactory;
import org.magnify.MagnifierInterface;

/**
 * The listener specifically for magnifier events.
 * @author jeff
 */
public class MagnifierListener implements AWTEventListener {
    private MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    private boolean wasActive = false; // was the magnifier active before a WINDOW_DEACTIVATE event?
    
    public void eventDispatched(AWTEvent event) {
        // Handle mouse movement events.
        if (event instanceof MouseEvent) {
            MouseEvent m = (MouseEvent)event;
            // Get the X & Y position of the cursor, and move the magnifier to be
            // centered there.
            int cursorX = m.getXOnScreen();
            int cursorY = m.getYOnScreen();
            
            // Move the focus appropriately.
            magnifier.setFocusCenter(cursorX, cursorY);
            
            // Do we need to move it?
            if (!magnifier.isFullScreen()) {
                // Put the mouse cursor at the center.
                int mWidth = magnifier.getWidth();
                int mHeight = magnifier.getHeight();
                magnifier.setLocation(cursorX - (mWidth/2), cursorY - (mHeight/2));
            }
        }
        else if (event instanceof WindowEvent) {
            WindowEvent w = (WindowEvent)event;
            
            // Find out what happened--if the window is deactivated, we need
            // to turn off the magnifier, as it does not properly respond to
            // events when Sodbeans is inactive. Similarly, we need to reactivate
            // it later, if it was already active.
            int eventType = w.getID();
            
            if (eventType == WindowEvent.WINDOW_DEACTIVATED) {
                if (magnifier.isStarted()) {
                    wasActive = true; // the magnifier was active when sodbeans was "deactivated."
                    magnifier.stop();
                } else {
                    wasActive = false; // the magnifier was not active when sodbeans was "deactivated."
                }
            }
            else if (eventType == WindowEvent.WINDOW_ACTIVATED) {
                if (wasActive)
                    magnifier.start();
                else
                    wasActive = false;
            }
        }
    }
    
}
