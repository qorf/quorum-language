/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.Stack;

/**
 *  This class stores an ExpressionValue and its historical metadata.
 * @author Aaron Willows
 */
public class DataObject {
    Stack<ExpressionValue> values;
    private String name;

    public DataObject() {
        values = new Stack<ExpressionValue>();
        
    }

    /**
     * Pushes a value and a time onto this DataObjects stack
     * @param value the value to push
     * @param time the time to push
     */
    public void pushValue(ExpressionValue value, TimeStamp time) {
        value.setTimeStamp(time);
        values.push(value);

        
    }
    public void pushValue(ExpressionValue value) {
        values.push(value);
    }

    /**
     * Pops the value off this DataObjects stack
     */
    public ExpressionValue popValue() {
        if(!isEmpty()) {
            return values.pop();
        }
        else {
            return null;
        }
        
    }

    /**
     * Gets the latest ExpressionValue without popping it off the stack
     * @return the value at the top of the stack
     */
    public ExpressionValue getCurrentValue() {
        if(!isEmpty()) {
            return values.peek();
        }
        else {
            return null;
        }
    }

    /**
     * Returns the string representation of this DataObject's internal
     * ExpressionValue object.
     * 
     * @return
     */
    @Override
    public String toString() {
        ExpressionValue value = getCurrentValue();
        if(value != null) {
            return value.toString();
        }

        return "";
    }

    public boolean isEmpty() {
        return values.size() <= 0;
    }

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
     * Returns the exact op-code in which the current value was set.
     * If there is no current value, this returns the impossible op-code
     * value of -1.
     *
     * @return
     */
    public long getMachineTimeStamp() {
        ExpressionValue value = getCurrentValue();
        if(value != null) {
            return value.getTimeStamp().getMachineTime();
        }

        return -1;
    }

    /**
     * Returns the actual real-world time when this
     * object had its value set.
     * @return
     */
    public String getFormattedDateTime() {
        ExpressionValue value = getCurrentValue();
        if(value != null) {
            TimeStamp stamp = value.getTimeStamp();
            return stamp.getFormattedDateTime();
        }

        return "";
    }
    
}
