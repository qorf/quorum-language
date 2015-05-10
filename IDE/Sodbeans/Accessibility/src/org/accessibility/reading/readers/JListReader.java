/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.lang.reflect.Field;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import javax.swing.JList;
import org.openide.util.Exceptions;
import org.accessibility.reading.UberEvent;
import org.accessibility.reading.processors.ListProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 * Reads the value for a JList on the system.
 * 
 * @author Andreas Stefik
 */
public class JListReader extends AbstractScreenReader{
    private JList list;

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        if (list == null)
            return new NullProcessor();

        AccessibleContext context = list.getAccessibleContext();
        ListProcessor proc = new ListProcessor();
        proc.setKeyEvent(true);
        proc.setSelectedIndex(list.getSelectedIndex());
        proc.setLastIndex(list.getModel().getSize() - 1);
        proc.setKeyCode(getUberEvent().key.getKeyCode());
        
        if (context != null)
            proc.setAccessibleSelection(context.getAccessibleSelection());
        else
            proc.setSelectedObject(list.getSelectedValue());

        return proc;
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (list == null)
            return new NullProcessor();

        ListProcessor proc = new ListProcessor();
        AccessibleContext context = list.getAccessibleContext();

        if (context != null) {
            proc.setText(context.getAccessibleName());
        }

        return proc;
    }
    
    public void setObject(Object object) {
        list = null;
        list = (JList) object;
    }
}
