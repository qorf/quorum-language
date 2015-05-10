/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * Processor for menu items.
 * @author jeff
 */
public class MenuItemProcessor extends AbstractSpeechProcessor {
    private boolean enabled = false;
    private KeyStroke accelerator = null;
    
    /**
     * Is this a menu, or a menu item?
     */
    private boolean menu = false;
    
    /**
     * What letter is underlined?
     */
    private int mnemonic = 0;
    
    /**
     * Is this an expandable submenu? Submenus typically expand to the right.
     */
    private boolean hasSubmenu = false;
    
    /**
     * Is this a checkbox menu item? ...and is it checked?
     */
    private boolean checkable = false;
    private boolean checked = false;
    
    public String process() {
        // Replace any '...' first.
        if (text.endsWith("...")) {
            text = text.replace("...", " dot dot dot");
        }
        
        // Include underlined key, if any.
        if(mnemonic != 0)
            text += " " + KeyEvent.getKeyText(mnemonic);
        
        // Is it a menu?
        if (menu) {
            text += " menu";
        }
        
        // or is it a submenu?
        else if (hasSubmenu) {
            text += " submenu. Press right arrow to expand, ";
        }
        
        // Is it enabled?
        if (!enabled)
            text += " unavailable";

        // Is it checkable?
        if (checkable) {
            if (checked) {
                text += " checked";
            }
            else {
                text += " unchecked";
            }
        }
        
        if (accelerator != null) {
            if (!KeyEvent.getModifiersExText(accelerator.getModifiers()).isEmpty()) {
                text += ", " + KeyEvent.getModifiersExText(accelerator.getModifiers()) +
                        " + ";
            }
            else {
                text += ", ";
            }
            text += KeyEvent.getKeyText(accelerator.getKeyCode());
        }
        
        return text;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the accelerator
     */
    public KeyStroke getAccelerator() {
        return accelerator;
    }

    /**
     * @param accelerator the accelerator to set
     */
    public void setAccelerator(KeyStroke accelerator) {
        this.accelerator = accelerator;
    }

    /**
     * @return the menu
     */
    public boolean isMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    /**
     * @return the mnemonic
     */
    public int getMnemonic() {
        return mnemonic;
    }

    /**
     * @param mnemonic the mnemonic to set
     */
    public void setMnemonic(int mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * @return the hasSubmenu
     */
    public boolean getHasSubmenu() {
        return hasSubmenu;
    }

    /**
     * @param hasSubmenu the hasSubmenu to set
     */
    public void setHasSubmenu(boolean hasSubmenu) {
        this.hasSubmenu = hasSubmenu;
    }

    /**
     * @return the checkable
     */
    public boolean isCheckable() {
        return checkable;
    }

    /**
     * @param checkable the checkable to set
     */
    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
