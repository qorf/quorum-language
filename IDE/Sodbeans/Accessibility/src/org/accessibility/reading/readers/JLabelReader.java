/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import javax.swing.JLabel;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.RawProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andreas Stefik
 */
public class JLabelReader extends AbstractScreenReader{
    private JLabel label;

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (label == null)
            return new NullProcessor();

        RawProcessor proc = new RawProcessor();

        proc.setText(label.getText());
        return proc;
    }
    
    @Override
    protected String getFocusEventString() {
        
        if(label != null)
        {
        String name = label.getText();

          if(name != null)
            return name;
        }
        return "";
    }

    public void setObject(Object object) {
        label = null;
        label = (JLabel) object;
    }
}
