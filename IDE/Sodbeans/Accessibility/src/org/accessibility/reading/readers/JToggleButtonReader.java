/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import javax.accessibility.AccessibleContext;
import javax.swing.JToggleButton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.ToggleButtonProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andreas Stefik
 * @author Andrew Hauck
 */
public class JToggleButtonReader extends AbstractScreenReader{
    private JToggleButton button;
    private Pattern pattern;

    public JToggleButtonReader()
    {pattern = setRegexPattern("<html><center>([a-zA-Z\\s]+)</center></html>");}

    private Pattern setRegexPattern(String patternString)
    { return Pattern.compile(patternString);}

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (button == null)
            return new NullProcessor();

        ToggleButtonProcessor proc = new ToggleButtonProcessor();
        
        String text = button.getText();
        if(text == null || text == "") {
            AccessibleContext ac = button.getAccessibleContext();
            if(ac != null) {
                text = ac.getAccessibleName();
            }
            
            if(text == null || text == "") {
                text = ac.getAccessibleDescription();
            }
            
            if(text == null || text == "") {
                text = button.getToolTipText();
            }
        }
        proc.setText(text);
        
        return proc;
    }

    @Override
    protected String getFocusEventString() {

        if(button != null)
        {
            String name = button.getText();
            if(name != null)
            {
                Matcher matcher = pattern.matcher(name);

                if(matcher.find())
                    name = matcher.replaceAll(matcher.group(1));

               return name;
            }
        }
        return "";
    }

    public void setObject(Object object) {
        button = null;
        button = (JToggleButton) object;
    }
}
