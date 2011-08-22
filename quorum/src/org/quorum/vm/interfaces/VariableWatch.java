/*
 * VariableWatch.java
 *
 * Created on October 22, 2007, 6:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 *  This class represents the "presentation" of a variable to a user. Essentially,
 *  this class stores string representation of data and presents them to the user.
 *
 * @author Andreas Stefik
 */
public class VariableWatch {
    private String name;
    private String type;
    private String value;
    private boolean container;
    
    /** Creates a new instance of VariableWatch */
    public VariableWatch() {
        container = false;
    }

    public VariableWatch(String n, String t, String v) {
        name = n;
        type = t;
        value = v;
        container = false;
    }
    
    public VariableWatch(String n, String t, String v, boolean contain) {
        name = n;
        type = t;
        value = v;
        container = contain;
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isContainer() {
        return container;
    }
    
}
