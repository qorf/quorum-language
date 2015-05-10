/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import org.accessibility.reading.processors.RadioButtonProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *  This is a screen reader for JRadioButtons.
 * 
 * @author Andreas Stefik
 * @author Melissa Stefik
 */
public class JRadioButtonReader extends AbstractScreenReader{
    private JRadioButton radio;

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
       if (radio == null || getUberEvent().key.getKeyCode() != KeyEvent.VK_SPACE)
            return new NullProcessor();

        RadioButtonProcessor proc = new RadioButtonProcessor();
        proc.setKeyEvent(true);

        //When a checkbox is selected using the space bar read "selected" followed by the name of the checkbox followed by "Check Box"
        proc.setChecked(radio.isSelected());
        proc.setText(radio.getText());

        return proc;
    }
    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (radio == null)
            return new NullProcessor();

        RadioButtonProcessor proc = new RadioButtonProcessor();
        proc.setChecked(radio.isSelected());
        proc.setText(radio.getText());
        return proc;
    }

    @Override
    protected String getKeyEventString() {
        String text = "";
        if(getUberEvent().key.getKeyCode() == KeyEvent.VK_SPACE) {
            text = "Selected ";
            text += radio.getText() + " Radio Button";
        }
        return text;
    }

    @Override
    protected String getFocusEventString() {

        String text = "";
        text = radio.getText();

        if (radio != null)
        {
            if(radio.isSelected()) 
                text += ", Selected Radio Button";
            else 
                text += ", Unselected Radio Button"; 
        }

        return text;
    }

    
    public void setObject(Object object) {
        radio = null;
        radio = (JRadioButton) object;
    }
}
