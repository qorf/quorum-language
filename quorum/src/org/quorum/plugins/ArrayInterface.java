/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import org.quorum.execution.ExpressionValue;

/**
 * This represents an interface for a general Native Array implementation
 * in Quorum.
 * 
 * @author Andreas Stefik
 */
public interface ArrayInterface {

    /**
     * Sets a value in the array.
     *
     * @param index
     * @param value
     * @return
     */
    public void set(int index, ExpressionValue value);

    /**
     * Returns an existing value in the array.
     *
     * @param index
     * @return
     */
    public ExpressionValue get(int index);

    /**
     * Returns the size of the array.
     * @return
     */
    public int getSize();

    /**
     * Sets the size of the array.
     * 
     * @param size
     */
    public void setSize(int size);

    /**
     * Returns true if the array is dynamic and false if it not.
     * @return 
     */
    public boolean isResizable();

    /**
     * Sets the resize flag in an array.
     * @param resizable 
     */
    public void setResizable(boolean resizable);

    /**
     * Get the capacity of the array.
     * @return 
     */
    public int getMaxSize();

    /**
     * Add a value to the array.
     * 
     * @param index
     * @param object 
     */
    public void add(int index, ExpressionValue object);

    /**
     * Add a value to the end of the array
     * @param object 
     */
    public void add(ExpressionValue object);
    
    /**
     * returns true if the array is empty(has not instantiated positions).
     * @return 
     */
    public boolean isEmpty();
    
    /**
     * Clears the array.
     */
    public void empty();
    
    /**
     * Removes the element at a given index and returns that element.
     * 
     * @param integer
     * @return 
     */
    public ExpressionValue removeAt(int index);
    
    /**
     * Set the capacity for the array.
     * 
     * @param integer 
     */
    public void setMaxSize(int integer);
}
