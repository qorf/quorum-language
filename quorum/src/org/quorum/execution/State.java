/*
 * State.java
 *
 * Created on March 26, 2007, 1:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.execution;

/**
 * A class representing a piece of state on the system.
 * @author Andreas Stefik
 */
public class State {
    
    /**
     * The current value of the state.
     */
    private Object value;
    /**
     * The current time stamp on the system when this state was created.
     */
    private long timeStamp;
    
    /** Creates a new instance of State */
    public State() {
    }
    
    /** Creates a new instance of State */
    public State(Object value, long timeStamp) {
        this.setTimeStamp(timeStamp);
        this.setValue(value);
    }

    public Object getValue() {
        return value;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    
    public String toString() {
        if(value != null) {
            return value.toString();
        }
        else {
            return "undefined";
        }
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
