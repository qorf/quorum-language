/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.TabbedPanelProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *
 * @author Andreas Stefik
 */
public class JTabbedPaneReader extends AbstractScreenReader{
    private JTabbedPane tabbedPane;

    @Override
    protected SpeechProcessor getKeyEventProcessor() 
    {
               
        if(getUberEvent().key.getKeyCode() == KeyEvent.VK_LEFT ||
            getUberEvent().key.getKeyCode() == KeyEvent.VK_RIGHT ||
            getUberEvent().key.getKeyCode() == KeyEvent.VK_TAB && getUberEvent().key.isControlDown()) {
            return getFocusEventProcessor();
        }

        return new NullProcessor();
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (tabbedPane == null)
            return new NullProcessor();

        TabbedPanelProcessor proc = new TabbedPanelProcessor();
        String place = "Tab " + (tabbedPane.getSelectedIndex() + 1) + " of " + tabbedPane.getTabCount();
            proc.setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()) + ". " + place);
        
        return proc;
    }

    @Override
    protected String getKeyEventString() {
        if(getUberEvent().key.getKeyCode() == KeyEvent.VK_LEFT ||
           getUberEvent().key.getKeyCode() == KeyEvent.VK_RIGHT) {
           return getFocusEventString();
        }
        return "";
    }

    @Override
    protected String getFocusEventString() {

        String text = "";

        if(tabbedPane != null)
        {
            text = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
            text += " Tab";
        }
        return text;
    }


    public void setObject(Object object) {
        if (object instanceof JTabbedPane)
            tabbedPane = (JTabbedPane) object;
    }
}
