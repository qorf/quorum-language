/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import org.openide.util.Exceptions;
import org.accessibility.reading.processors.ButtonProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;
import org.accessibility.sound.SoundUtility;

/**
 * Reads out text for JButtons passed to it.
 * 
 * @author Andreas Stefik
 */
public class JButtonReader extends AbstractScreenReader{
    private JButton button;
    private String weblink = "org.netbeans.modules.welcome.content.WebLink";

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        ButtonProcessor proc = new ButtonProcessor();

        if (button == null)
            return new NullProcessor();

        String name;
        AccessibleContext context = button.getAccessibleContext();
        if (context.getAccessibleName() != null){
            name = context.getAccessibleName();
        }
        else{
            name = button.getText();
        }
            String clazz = button.getClass().getName();

        if(clazz != null && clazz.compareTo(weblink)==0) { //it's a web link, to at least give its URL.
            //grab its private field
            try {
                //it's a web link, to at least give its URL.
                //grab its private field
                Field url = button.getClass().getDeclaredField("url");
                url.setAccessible(true);
                name = (String) url.get(button);

            } catch (NoSuchFieldException ex) {
                Exceptions.printStackTrace(ex);
            } catch (SecurityException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        if(name == null || name == "") {
            AccessibleContext ac = button.getAccessibleContext();
            if(ac != null) {
                name = ac.getAccessibleDescription();
            }
        }

        //ok, does it at least have a tooltip?
        if(name == null) {
            name = button.getToolTipText();
        }

        //a text field?
        if(name == null) {
            name = button.getText();
        }

        proc.setText(name);
        
        return proc;
    }
    
    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        SoundUtility player = SoundUtility.instance();
        KeyEvent e = this.getUberEvent().key;
        if((e.getKeyCode() == KeyEvent.VK_SPACE && this.getUberEvent().preprocess) 
           || (e.getKeyCode() == KeyEvent.VK_ENTER  && !this.getUberEvent().preprocess)) {
            player.playClick();
        }
        
        return new NullProcessor();
    
    }

    public void setObject(Object object) {
       button = null;
       button = (JButton) object;
    }

}
