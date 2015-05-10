/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import org.accessibility.reading.processors.CheckboxProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.SliderProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *  Reads JSliders to the reader.
 * 
 * @author Jeff Wilson
 */
public class JSliderReader extends AbstractScreenReader{
    private JCheckBox checkBox;
    private JSlider slider;

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
       if (slider == null || (getUberEvent().key.getKeyCode() != KeyEvent.VK_LEFT && getUberEvent().key.getKeyCode() != KeyEvent.VK_RIGHT))
            return new NullProcessor();

        SliderProcessor proc = new SliderProcessor();
        proc.setKeyEvent(true);

        //When a checkbox is selected using the space bar read "selected" followed by the name of the checkbox followed by "Check Box"
        String name = slider.getAccessibleContext().getAccessibleName();
        String desc = slider.getAccessibleContext().getAccessibleDescription();
        
        if (name != null && !name.isEmpty()) {
            proc.setText(name);
        }
        else if (desc != null && !desc.isEmpty()) {
            proc.setText(desc);
        }
        else {
            // No usable information...
            proc.setText("slider");
        }
        
        proc.setValue(slider.getValue());
        proc.setMinimum(slider.getMinimum());
        proc.setMaximum(slider.getMaximum());
        
        return proc;
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        SliderProcessor proc = new SliderProcessor();
        proc.setKeyEvent(false);

        //When a checkbox is selected using the space bar read "selected" followed by the name of the checkbox followed by "Check Box"
        String name = slider.getAccessibleContext().getAccessibleName();
        String desc = slider.getAccessibleContext().getAccessibleDescription();
        
        if (name != null && !name.isEmpty()) {
            proc.setText(name);
        }
        else if (desc != null && !desc.isEmpty()) {
            proc.setText(desc);
        }
        else {
            // No usable information...
            proc.setText("slider");
        }
        
        proc.setValue(slider.getValue());
        proc.setMinimum(slider.getMinimum());
        proc.setMaximum(slider.getMaximum());
        
        return proc;
    }

    public void setObject(Object object) {
        slider = null;
        slider = (JSlider) object;
        /*checkBox = null;
        checkBox = (JCheckBox) object;*/
    }
}
