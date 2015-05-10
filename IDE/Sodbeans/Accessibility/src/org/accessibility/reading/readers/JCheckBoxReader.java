/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import org.accessibility.reading.processors.CheckboxProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *  Reads check boxes to the user.
 * 
 * @author Andreas Stefik
 */
public class JCheckBoxReader extends AbstractScreenReader{
    private JCheckBox checkBox;

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
       if (checkBox == null || getUberEvent().key.getKeyCode() != KeyEvent.VK_SPACE)
            return new NullProcessor();

        CheckboxProcessor proc = new CheckboxProcessor();
        proc.setKeyEvent(true);

        //When a checkbox is selected using the space bar read "selected" followed by the name of the checkbox followed by "Check Box"
        proc.setChecked(checkBox.isSelected());
        proc.setText(checkBox.getText());
        
        return proc;
    }

    @Override
    protected String getKeyEventString() {
        if(checkBox != null && getUberEvent().key.getKeyCode() == KeyEvent.VK_SPACE) {
            //When a checkbox is selected using the space bar read "selected" followed by the name of the checkbox followed by "Check Box"
            if (checkBox.isSelected()) {
                String text = "Selected ";
                text += checkBox.getText() + " Check Box";
                return text;
            }
            //When a checkbox is unselected using the space bar read "unselected" followed by the name of the checkbox followed by "Check Box"
            else {
                String text = "Unselected ";
                text += checkBox.getText() + " Check Box";
                return text;
            }
        }
        return "";
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (checkBox == null)
            return new NullProcessor();

        CheckboxProcessor proc = new CheckboxProcessor();
        proc.setChecked(checkBox.isSelected());
        proc.setText(checkBox.getText());
        return proc;
    }

    @Override
    protected String getFocusEventString() {

        if(checkBox != null)
        {
            String text = checkBox.getText();
            if(checkBox.isSelected()) {
                text += ", Selected Check Box";
            }
            else {
                text += ", Unselected Check Box";
            }
            return text;
        }
        return "";
    }


    public void setObject(Object object) {
        checkBox = null;
        checkBox = (JCheckBox) object;
    }
}
