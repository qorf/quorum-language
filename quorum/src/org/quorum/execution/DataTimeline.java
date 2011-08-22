/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 *  This class represents the timeline for a particular data object, based
 * upon the DataObject's name.
 *
 * @author Andreas Stefik
 */
public class DataTimeline {
    private String name;
    private Stack<DataObject> stack;

    public DataTimeline() {
        stack = new Stack<DataObject>();
    }

    /**
     * Pushes a new variable instance onto the stack.
     * @param object
     */
    public void push(DataObject object) {
        stack.push(object);
    }

    /**
     * Pops a variable instance off the stack.
     * 
     * @return
     */
    public DataObject pop() {
        return stack.pop();
    }

    /**
     * Determines if this variable has a history at all.
     * 
     * @return
     */
    public boolean empty() {
        return stack.empty();
    }

    /**
     * Grabs the first variable off of the top of the stack.
     * @return
     */
    public DataObject peek() {
        return stack.peek();
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
     * Returns a history of this particular variable.
     * 
     * @return
     */
    public Iterator<DataObject> getHistory() {
        return stack.iterator();
    }

    /**
     * Returns a collection of the objects, instead of an iterator.
     * @return
     */
    public Collection<DataObject> getHistoryCollection() {
        return stack;
    }

    /**
     * Returns a collection that explicitly removes some elements from the front
     * of the list. This is useful, especially, for displaying history
     * variables where all but the most recent value are displayed.
     * 
     * @param subset
     * @return
     */
    public Collection<DataObject> getHistoryCollection(int subset) {
        return stack.subList(subset, stack.size() - 1);
    }

    /**
     * Searches the history of this variable to determine if one of them
     * was set at the time "stamp."
     * @param stamp
     * @return
     */
    public DataObject findObjectAtMachineTimeStamp(long stamp) {
        for(int i = 0; i < stack.size(); i++) {
            DataObject obj = stack.elementAt(i);
            if(obj.getMachineTimeStamp() == stamp) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Searches through the values for this variable and returns any whose
     * string representation of the value match the parameter value.
     * 
     * @param value
     * @return
     */
    public Iterator<DataObject> findObjectWithValue(String value) {
        LinkedList<DataObject> list = new LinkedList<DataObject>();
        for(int i = 0; i < stack.size(); i++) {
            DataObject obj = stack.elementAt(i);
            if(obj.getCurrentValue().toString().compareTo(value) == 0) {
                list.add(obj);
            }
        }
        return list.iterator();

    }
}
