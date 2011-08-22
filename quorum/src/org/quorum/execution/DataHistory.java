/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 *  This class represents an overarching history manager for all data
 * that has been put into the system while a program is executing. It allows
 * you to query based upon time stamps, variable names, and other
 * attributes, to make it easier to determine what happened while your
 * program was executing.
 *
 * This history operates invariant to program scope. In other words,
 * it does not account for naming conflicts, and simply "overrides" values
 * according to their time stamp.
 *
 * @author Andreas Stefik
 */
public class DataHistory {

    private HashMap<String, DataTimeline> history;
    private HashMap<Long, DataObject> timeHistory;
    private HashMap<String, Stack<DataObject>> valueHistory;
    
    public DataHistory() {
        history = new HashMap<String, DataTimeline>();
        timeHistory = new HashMap<Long, DataObject>();
        valueHistory = new HashMap<String, Stack<DataObject>>();
    }

    /**
     * Determines whether there is any variables on the system. By definition,
     * if there are no variable histories, then there must be no variables.
     * 
     * @return
     */
    public boolean empty() {
        return history.size() == 0;
    }


    /**
     * Push a variable onto this variable's history.
     * @param object
     */
    public void push(DataObject object) {
        //does data object have a timeline?
        if(history.containsKey(object.getName())) {
            DataTimeline time = history.get(object.getName());
            time.push(object);
        }
        else { //create a new timeline for it
            DataTimeline time = new DataTimeline();
            time.setName(object.getName());
            time.push(object);
            history.put(object.getName(), time);
        }

        //insert values into our time stamp tracker
        timeHistory.put(object.getMachineTimeStamp(), object);

        //and finally, store values in our "value" tracker
        if(valueHistory.containsKey(object.toString())) {
            Stack<DataObject> stack = valueHistory.get(object.toString());
            stack.push(object);
        }
        else {
            Stack<DataObject> stack = new Stack<DataObject>();
            stack.push(object);
            valueHistory.put(object.toString(), stack);
        }
    }

    /**
     * Returns the variable that is on the top of the stack for a particular
     * variable of name key.
     *
     * @param key
     * @return
     */
    public DataObject peek(String key) {
        DataTimeline time = history.get(key);
        if(time != null) {
            if(!time.empty()) {
                return time.peek();
            }
        }

        return null;
    }

    /**
     * Returns the history of a variable with the name key.
     *
     * @param key
     * @return
     */
    public DataTimeline getVariableHistory(String key) {
        return history.get(key);
    }


    /** Pops a variable off of the top of a variable (key)'s history.
     * 
     * @param key
     * @return
     */
    public DataObject pop(String key) {
        DataTimeline time = history.get(key);
        if(time != null) {
            if(!time.empty()) {
                DataObject obj = time.pop();
                timeHistory.remove(obj.getMachineTimeStamp());
                Stack<DataObject> stack = valueHistory.get(obj.toString());
                if(stack != null) {
                    stack.pop();
                    if(stack.isEmpty()) {
                        valueHistory.remove(obj.toString());
                    }
                }
                
                
                return obj;
            }
        }

        return null;
    }

    /**
     * Returns an iterator with all values that have ever been set on the 
     * system.
     *
     * @return
     */
    public Iterator<DataTimeline> getCompleteHistory() {
        return history.values().iterator();
    }



    /**
     * Searches through the history of the variables to determine
     * if a variable was set at a particular machine time stamp.
     * @param stamp
     * @return
     */
    public DataObject findTimeStamp(long stamp) {
        return timeHistory.get(stamp);
    }

    /**
     * This function searches through the history of the variables looking
     * for a value that was set. Variables are compared by their string 
     * representation of the variable, so the values 45 and 45 match even
     * if one was of type integer and the other was of type long.
     * 
     * @param value
     * @return
     */
    public Iterator<DataObject> findValues(String value) {
        Stack<DataObject> stack = valueHistory.get(value);
        if(stack == null) {
            stack = new Stack<DataObject>();
        }

        return stack.iterator();
    }

    /**
     * Destroys all history on the system.
     */
    void clear() {
        history.clear();
        timeHistory.clear();
        valueHistory.clear();
    }
}
