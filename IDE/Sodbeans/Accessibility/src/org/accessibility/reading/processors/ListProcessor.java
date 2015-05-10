/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import org.openide.util.Exceptions;
import org.sodbeans.phonemic.AbstractSpeechProcessor;
import org.accessibility.sound.SoundUtility;

/**
 * A processor list JList objects.
 * 
 * @author jeff
 */
public class ListProcessor extends AbstractSpeechProcessor {
    private int selectedIndex = 0;
    private int lastIndex = 0;
    private AccessibleSelection accessibleSelection = null;
    private Object selectedObject = null;
    private boolean keyEvent;
    private int keyCode = 0;

    public String process() {
        // Focus events are fairly simple.
        if (!keyEvent) {
            // TODO:  we should not be getting a string and just changing it.
            // This will need to be delt with later.
            if(text.equals("Types of new objects")) {
                text = "Types of new projects";
            }
            else if (text.trim().isEmpty()) {
                // Tell the user a listbox gained focus.
                text = "Listview";
            }
            return text;
        }

        // Otherwise, it is a key event.
        if(accessibleSelection != null) {
            int count = accessibleSelection.getAccessibleSelectionCount();
            if(count == 1) {
                Accessible acc = accessibleSelection.getAccessibleSelection(0);
                AccessibleContext contextItem = acc.getAccessibleContext();

                if(contextItem != null) {
                    text = contextItem.getAccessibleName();
                }

                //When the focus is on the first item and it is the only item read the text followed by only entry
                if (selectedIndex == 0 && selectedIndex == lastIndex) {
                    text += ", only entry";
                }

                //When the focus is on the first item read the text followed by top entry
                else if(selectedIndex == 0) {
                    text += ", top entry";
                    
                    if (getKeyCode() == KeyEvent.VK_UP)
                        SoundUtility.instance().playTopOfList();
                }
                //When the focus is on the last item read the text followed by bottom entry
                else if(selectedIndex == lastIndex) {
                    text += ", bottom entry";
                    
                    if (getKeyCode() == KeyEvent.VK_DOWN)
                    SoundUtility.instance().playTopOfList();
                }
                // If we're in the middle, read the entry number.
                else {
                    text += ", entry " + (selectedIndex + 1) + " of " + (lastIndex + 1);
                }
            }
            else if (count > 1) {
                text = "Multiple selections - indices " + (selectedIndex + 1) + " through " + (selectedIndex + count) + ", including ";
                for(int i = 0; i < count; i++) {
                    Accessible acc = accessibleSelection.getAccessibleSelection(i);
                    AccessibleContext contextItem = acc.getAccessibleContext();
                    if(contextItem != null) {
                        text += contextItem.getAccessibleName() + " ";
                    }
                    else {
                        text += "non-accessible item";
                    }
                }
            }
        }
        else {
            if(selectedObject != null && selectedObject.getClass().getName().compareTo("org.netbeans.api.java.source.ElementHandle") == 0) {
                Class cls = selectedObject.getClass();
                try{
                    Field f = cls.getDeclaredField("signatures");
                    f.setAccessible(true);
                    Object result = f.get(selectedObject);

                if (result instanceof String[] && result != null) {
                    String[] s = (String[]) result;
                    text = s[0].toString();
                }

                } catch (IllegalArgumentException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IllegalAccessException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (NoSuchFieldException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (SecurityException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            else if (selectedObject != null){
                text = selectedObject.toString();
            }
        }
        
        return text;
    }

    /**
     * @return the selectedIndex
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * @param selectedIndex the selectedIndex to set
     */
    public void setSelectedIndex(int currentIndex) {
        this.selectedIndex = currentIndex;
    }

    /**
     * @return the lastIndex
     */
    public int getLastIndex() {
        return lastIndex;
    }

    /**
     * @param lastIndex the lastIndex to set
     */
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    /**
     * @return the accessibleSelection
     */
    public AccessibleSelection getAccessibleSelection() {
        return accessibleSelection;
    }

    /**
     * @param accessibleSelection the accessibleSelection to set
     */
    public void setAccessibleSelection(AccessibleSelection accessibleSelection) {
        this.accessibleSelection = accessibleSelection;
    }

    /**
     * @return the selectedObject
     */
    public Object getSelectedObject() {
        return selectedObject;
    }

    /**
     * @param selectedObject the selectedObject to set
     */
    public void setSelectedObject(Object selectedObject) {
        this.selectedObject = selectedObject;
    }

    /**
     * @return the keyEvent
     */
    public boolean isKeyEvent() {
        return keyEvent;
    }

    /**
     * @param keyEvent the keyEvent to set
     */
    public void setKeyEvent(boolean keyEvent) {
        this.keyEvent = keyEvent;
    }

    /**
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
