/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

//import java.awt.event.InputEvent;
import java.awt.Point;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import org.openide.awt.MenuBar;
import org.accessibility.reading.processors.MenuItemProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 *  This reads JMenuItem objects as they are passed to the screen reader.
 * 
 * @author Andreas Stefik
 */
public class JMenuItemReader extends AbstractScreenReader{
    private JMenuItem menuItem = null;
    
    @Override
    protected SpeechProcessor getMenuEventProcessor() {
        if (menuItem == null)
            return new NullProcessor();

        MenuItemProcessor proc = new MenuItemProcessor();
        proc.setText(menuItem.getText());
        proc.setEnabled(menuItem.isEnabled());
        proc.setAccelerator(menuItem.getAccelerator());
        proc.setMenu(menuItem.getParent() instanceof MenuBar); // is it a menu or a menu item?
        proc.setMnemonic(menuItem.getMnemonic());
        proc.setHasSubmenu(menuItem.getSubElements().length > 0);
        
        // Is this item checked?
        if (menuItem instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem jc = (JCheckBoxMenuItem)menuItem;
            proc.setCheckable(true);
            proc.setChecked(jc.isSelected());
        }
        
        return proc;
    }

    public void setObject(Object object) {
        menuItem = (JMenuItem) object;
    }
    
    @Override
    public void magnify() {
        if(magnifier.isStarted()) {
            try { //ignore any errors thrown.
                Point loc = menuItem.getLocationOnScreen();
                magnifier.setFocusCenter(loc.x, loc.y);
            }
            catch(Exception e) {}
        }
    }
}
