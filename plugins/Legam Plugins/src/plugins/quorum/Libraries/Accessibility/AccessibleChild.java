/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Accessibility;

/**
 *
 * @author Andreas Stefik
 */
public class AccessibleChild {
    private String name;
    private String component;
    private String shortcut;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * @return the shortcut
     */
    public String getShortcut() {
        return shortcut;
    }

    /**
     * @param shortcut the shortcut to set
     */
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
}
