/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;
import org.openide.windows.TopComponent;
import org.accessibility.reading.processors.RawProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andrew Hauck
 */
public class JPanelReader extends AbstractScreenReader{

    private JPanel jp;

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        RawProcessor proc = new RawProcessor();
        proc.setText(getComponentString() + " gained focus");

        return proc;
    }

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        RawProcessor proc = new RawProcessor();
        proc.setText(getComponentString());

        return proc;
    }
    
    @Override
    protected String getFocusEventString() {
        return getComponentString() + " gained focus.";
    }

    @Override
    protected String getKeyEventString() {
       return getComponentString();
    }

    public void setObject(Object object) {
        jp = null;
        jp = (JPanel)object;
    }

    private String getComponentString()
    {
        String name = null;
        String tcName = null;

        if(jp != null)
        {
         name = jp.getName();
        TopComponent.Registry registry = TopComponent.getRegistry();
        if(registry != null) {
             TopComponent activated = registry.getActivated();
             if(activated != null) {
                 tcName = activated.getName();
             } else {
                 Set<TopComponent> opened = registry.getOpened();
                 if(opened.size() == 1) {
                     Iterator<TopComponent> iterator = opened.iterator();
                     if(iterator.hasNext()) {
                         TopComponent next = iterator.next();
                         if(next != null) {
                             tcName = next.getName();
                         }
                     }
                 }
             }
        }
         //tcName = TopComponent.getRegistry().getActivated().getName();

        if(name != null && !name.equals(""))
            return name ;
        else if(tcName != null && !tcName.equals(""))
            return tcName;
        }
        return "";
    }
}
