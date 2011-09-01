/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.HashMap;
import java.util.Stack;

/**
 * A helper class to encapsulate the standard operations stacks must deal
 * with when pushing and popping from methods, like tracking the max 
 * stack size, handling constants and variables, or other issues.
 * 
 * @author Andreas Stefik
 */
public class BytecodeStack {
    private Stack<BytecodeStackValue> constants = new Stack<BytecodeStackValue>();
    private HashMap<Integer, BytecodeStackValue> variables = new HashMap<Integer, BytecodeStackValue>();
    
    private int maxSize = 0;
    
    
    /**
     * This method pushes constants on the stack.
     * 
     * @param value 
     */
    public void push(BytecodeStackValue value) {
        constants.push(value);
    }
    
    /**
     * This method pops constants off the stack.
     * @return 
     */
    public BytecodeStackValue pop() {
        return constants.pop();
    }
    
    /**
     * Pushes a value for a particular variable onto a hash map that can
     * later be queried.
     * 
     * @param location
     * @param value 
     */
    public void push(int location, BytecodeStackValue value) {
        variables.put(location, value);
    }
    
    /**
     * Pops a value off of a hash map that stores the value
     * of variables.
     * 
     * @param location
     * @return 
     */
    public BytecodeStackValue pop(int location) {
        return variables.remove(location);
    }
    
    /**
     * This method tells the stack to start a new method definition.
     * Specifically, this method clears out the internal constants/variables
     * stacks and resets the max size value for a new method.
     * 
     */
    public void startMethod() {
        maxSize = 0;
        constants.empty();
        variables.clear();
    }
    
    public void endMethod() {
        
    }
    
    /**
     * Returns the maximum size that this particular method has 
     * achieved while processing push and pop values.
     * 
     * @return 
     */
    public int getMaxSize() {
        return maxSize;
    }
    
    /**
     * Returns the current size of the stack.
     * 
     * @return 
     */
    public int getCurrentSize() {
        return constants.size();
    }
}
