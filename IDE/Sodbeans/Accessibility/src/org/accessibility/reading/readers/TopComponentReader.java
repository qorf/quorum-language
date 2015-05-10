/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;
import org.openide.windows.TopComponent;
import org.accessibility.reading.processors.RawProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andrew Hauck
 * @author Melissa Stefik
 */
public class TopComponentReader extends AbstractScreenReader{

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        RawProcessor proc = new RawProcessor();

        proc.setText(getComponentString());

        return proc;
    }

    @Override
    protected String getFocusEventString() {
        return getComponentString();
    }

    public void setObject(Object object) {
    }

    private String getComponentString()
    {
        String name = TopComponent.getRegistry().getActivated().getName();

        if(name != null)
            return name;
        
        return "";
    }
}
