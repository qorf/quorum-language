/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.startup;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.Fix;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.accessibility.options.AccessibilityOptions;

/**
 *
 * @author jeff
 */

public class EditorHintsListener implements ListSelectionListener, ComponentListener, WindowListener {
    private static EditorHintsListener instance = null;
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    
    private EditorHintsListener() {
        
    }
    
    public static EditorHintsListener getInstance() {
        if (instance == null)
            instance = new EditorHintsListener();
        
        return instance;
    }
    
    public void valueChanged(ListSelectionEvent lse) {
        JList list = (JList) lse.getSource();
        Object selectedValue = list.getSelectedValue();
        
        if (AccessibilityOptions.isSelfVoicing() && selectedValue != null && selectedValue instanceof Fix) {
            Fix f = (Fix)selectedValue;
            speech.speak(f.getText(), SpeechPriority.MEDIUM_HIGH);
        }
    }

    public void componentResized(ComponentEvent ce) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentMoved(ComponentEvent ce) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentShown(ComponentEvent ce) {

    }

    public void componentHidden(ComponentEvent ce) {
        speech.stop();
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
    }

    public void windowClosed(WindowEvent we) {
        speech.stop();
    }

    public void windowIconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeiconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowActivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void windowDeactivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
