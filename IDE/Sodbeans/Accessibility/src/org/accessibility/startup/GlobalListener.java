/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.startup;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import org.openide.util.Lookup;
import org.accessibility.ScreenReader;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * This class provides some basic listener features that are global to NetBeans.
 * Eventually, these should be better integrated into the file system.
 * 
 * @author Andreas Stefik
 */
public class GlobalListener implements AWTEventListener {
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    private static final String ACCESSIBILITY_KEYS_FOLDER_PATH = "accessibilityKeys";
    private final InputMap inMap = new InputMap();
    private final ActionMap actMap = new ActionMap();
    private ScreenReader reader = Lookup.getDefault().lookup(ScreenReader.class);

    private boolean shiftOnlyDown = false;
    private boolean ctrlOnlyDown = false;

    public GlobalListener() {
    }

    public void eventDispatched(AWTEvent event) {
        KeyStroke stroke = KeyStroke.getKeyStrokeForEvent((KeyEvent) event);

        if (stroke.getKeyCode() == KeyEvent.VK_SHIFT && !stroke.isOnKeyRelease()) {
            shiftOnlyDown = true;
        }
        else if(stroke.getKeyCode() != KeyEvent.VK_SHIFT && !stroke.isOnKeyRelease()){
            shiftOnlyDown = false;
        }

        if (stroke.getKeyCode() == KeyEvent.VK_CONTROL && !stroke.isOnKeyRelease()) {
            ctrlOnlyDown = true;
        }
        else if(stroke.getKeyCode() != KeyEvent.VK_CONTROL && !stroke.isOnKeyRelease()){
            ctrlOnlyDown = false;
        }

        if(!stroke.isOnKeyRelease()) {
            return;
        }
        KeyStroke theStroke = KeyStroke.getKeyStrokeForEvent((KeyEvent) event);

        if (theStroke.getKeyCode() == KeyEvent.VK_RIGHT
                && isMagicKeyModifier(theStroke)) {
            reader.magicKeyRight(event.getSource());
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_LEFT
                && isMagicKeyModifier(theStroke)) {
            reader.magicKeyLeft(event.getSource());
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_UP
                && isMagicKeyModifier(theStroke)) {
            reader.magicKeyUp(event.getSource());
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_DOWN
                && isMagicKeyModifier(theStroke)) {
            reader.magicKeyDown(event.getSource());
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_F5
                && isMagicKeyModifier(theStroke)) {
            reader.speakLocation();
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_C
                && isMagicKeyModifier(theStroke)) {
            //speech.copyToClipboard();
            //speech.speak("Last phrase copied to clipboard", false);
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_CONTROL && ctrlOnlyDown) {
            ctrlOnlyDown = false;
            speech.stop();
        }
        else if (theStroke.getKeyCode() == KeyEvent.VK_SHIFT && shiftOnlyDown) {
            shiftOnlyDown = false;
            speech.respeak();
        }
    }


    /**
     * Determines whether the user was holding down the alt, shift, and control
     * keys all at once.
     * 
     * @param stroke
     * @return
     */
    private boolean isMagicKeyModifier(KeyStroke stroke) {
        boolean ctrl = (stroke.getModifiers() & KeyEvent.CTRL_DOWN_MASK) != 0;
        boolean alt = (stroke.getModifiers() & KeyEvent.ALT_DOWN_MASK) != 0;
        boolean shift = (stroke.getModifiers() & KeyEvent.SHIFT_DOWN_MASK) != 0;

        //return ctrl && alt && shift;
        return ctrl && shift;
    }
}
