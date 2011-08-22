/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

/**
 * This class represents the result of loading libraries on the system.
 * 
 * @author Andreas Stefik
 */
public class PluginLoadingResult {
    protected boolean passed = false;
    protected String message = "";

    /**
     * @return the passed
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
