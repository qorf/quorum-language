/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import java.beans.FeatureDescriptor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JTable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openide.nodes.PropertySupport;
import org.openide.util.Exceptions;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.TableProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andrew Hauck
 */
public class JTableReader extends AbstractScreenReader {

    private JTable jt;

    public JTableReader() {
    }

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        if (jt == null)
            return new NullProcessor();

        TableProcessor proc = new TableProcessor();
        proc.setKeyEvent(true);
        proc.setEndOfTable(calculateEndOfTable());
        proc.setTable(jt);
        return proc;
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (jt == null)
            return new NullProcessor();

        TableProcessor proc = new TableProcessor();
        proc.setKeyEvent(false);
        proc.setTable(jt);
        return proc;
    }

    public void setObject(Object object) {
        jt = null;
        jt = (JTable) object;
    }
    
    /**
     * Determine if the end of the table has been reached. A return value of true
     * indicates that the user is either at the leftmost/rightmost column, or the
     * topmost/bottommost row.
     * @return 
     */
    private boolean calculateEndOfTable() {
        if (this.getUberEvent().key.getKeyCode() == KeyEvent.VK_UP)
            return this.jt.getSelectedRow() == 0;
        else if (this.getUberEvent().key.getKeyCode() == KeyEvent.VK_DOWN)
            return this.jt.getSelectedRow() == this.jt.getRowCount() - 1;
        
        // Probably not at the end.
        return false;
    }
}
