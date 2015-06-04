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
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.accessibility.options.AccessibilityOptions;

/**
 *
 * @author jeff
 */

public class CodeCompletionListener implements ListSelectionListener, ComponentListener, WindowListener {
    private static boolean codeCompletionOpen = false;
    private static CodeCompletionListener instance = null;
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    
    private CodeCompletionListener() {
        
    }
    
    public static CodeCompletionListener getInstance() {
        if (instance == null)
            instance = new CodeCompletionListener();
        
        codeCompletionOpen = true;
        return instance;
    }
    
    public static boolean isCodeCompletionOpen() {
        return codeCompletionOpen;
    }
    
    public void valueChanged(ListSelectionEvent lse) {
        if (AccessibilityOptions.isSelfVoicing()) {
            JList list = (JList) lse.getSource();
            Object selectedValue = list.getSelectedValue();    
            read(selectedValue);
        }
    }

    public void componentResized(ComponentEvent ce) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentMoved(ComponentEvent ce) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void componentShown(ComponentEvent ce) {
        codeCompletionOpen = true;
    }

    public void componentHidden(ComponentEvent ce) {
        codeCompletionOpen = false;
    }

    public void windowOpened(WindowEvent we) {
        codeCompletionOpen = true;
    }

    public void windowClosing(WindowEvent we) {
        codeCompletionOpen = false;
    }

    public void windowClosed(WindowEvent we) {
        codeCompletionOpen = false;
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

    /**
     * Read a code completion item out loud.
     * @param selectedValue 
     */
    public void read(Object selectedValue) {
        if (AccessibilityOptions.isSelfVoicing() && selectedValue != null) {
            if (selectedValue.getClass().getName().startsWith("org.netbeans.modules.editor.java.JavaCompletionItem")) {
                String originalName = selectedValue.toString();
                StringBuilder newName = new StringBuilder();
                
                // If this code completion item is a method, it will contain parenthesis.
                int leftParenIndex = originalName.indexOf('(');
                if (leftParenIndex != -1) {
                    // It is a method. Take a substring up to the point where the first '(' appears.
                    String firstPart = originalName.substring(0, leftParenIndex);
                    
                    // Isolate modifiers such as "public void" etc by going up to the last space.
                    int lastSpaceIndex = firstPart.lastIndexOf(' ');
                    if (lastSpaceIndex != -1) {
                        newName.append(originalName.substring(lastSpaceIndex, originalName.length()));
                        newName.append(' ');
                        newName.append(firstPart.substring(0, lastSpaceIndex));
                    }
                } else {
                    // Not a method. Take the last word and move it to the front.
                    String[] words = originalName.split(" ");
                    if (words.length > 1) {
                        String lastWord = words[words.length - 1];
                        newName.append(lastWord);
                        newName.append(' ');
                        newName.append(originalName.substring(0, originalName.length()-lastWord.length()));
                    } else if (words.length == 1) {
                        newName.append(originalName);
                    }
                }
                speech.speak(newName.toString(), SpeechPriority.MEDIUM_HIGH);
            } else {
                speech.speak(selectedValue.toString(), SpeechPriority.MEDIUM_HIGH);
            }
        }        
    }
}
