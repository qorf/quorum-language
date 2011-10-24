/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import org.quorum.execution.OmniscientStack;
import org.quorum.symbols.TypeDescriptor;

/**
 * A helper class to encapsulate the standard operations stacks must deal
 * with when pushing and popping from methods, like tracking the max 
 * stack size, handling constants and variables, or other issues.
 * 
 * @author Andreas Stefik
 */
public class BytecodeStack {
    private Stack<TypeDescriptor> expressionTypes = new Stack<TypeDescriptor>();
    private OmniscientStack<LabelStackValue> labels = new OmniscientStack<LabelStackValue>();
    private HashMap<Integer, TypeDescriptor> variables = new HashMap<Integer, TypeDescriptor>();
    private HashMap<Integer, Integer> variableNumberMappings = new HashMap<Integer, Integer>();
    private int currentConditionalBytecode = 0;
    private int maxVariablesSize = 0;
    private int currentVariablesSize = 0;
    private int maxSize = 0;
    private int currentSize = 0;
    
    
    public void setMappedStartingVariableNumber(int variableNumber) {
        variableNumberMappings.put(1, variableNumber);
    }
    
    /**
     * This method pushes expression types onto the stack.
     * 
     * @param value 
     */
    public void pushExpressionType(TypeDescriptor type) {
        expressionTypes.push(type);
    }
    
    /**
     * This method pops the last expression type.
     * @return 
     */
    public TypeDescriptor popExpressionType() {
        TypeDescriptor pop = expressionTypes.pop();
        return pop;
    }
    
    /**
     * This method gets without removing the last expression type.
     * @return 
     */
    public TypeDescriptor peekExpressionType() {
        return expressionTypes.peek();
    }
    
    /**
     * Push a label onto the label stack.
     * 
     * @param value 
     */
    public void pushLabel(LabelStackValue value) {
        labels.push(value);
    }
    
    /**
     * Pop a label off the label stack.
     * 
     * @return 
     */
    public LabelStackValue popLabel() {
        if(labels.isEmpty()){
            return null;
        }
        return labels.pop();
    }
    
    /**
     * peek a label on the top of the label stack.
     * 
     * @return 
     */
    public LabelStackValue peekLabel() {
        return labels.peek();
    }
    
    /**
     * Undo a label pop.
     */
    public void undoLabel(){
        labels.undo();
    }
    
    /**
     * Pushes a value for a particular variable onto a hash map that can
     * later be queried.
     * 
     * @param location
     * @param value 
     */
    public void setVariable(int location, TypeDescriptor valueType) {
        int mappedVariableNumber = getMappedVariableNumber(location);
        variables.put(location, valueType);
        
        int size = getSize(valueType);
        
        variableNumberMappings.put(location + 1, mappedVariableNumber + size);
        currentVariablesSize += size;
        if(currentVariablesSize > maxVariablesSize) {
            maxVariablesSize = currentVariablesSize;
        }
    }
    
    /**
     * Pops a value off of a hash map that stores the value
     * of variables.
     * 
     * @param location
     * @return 
     */
    public TypeDescriptor removeVariable(int location) {
        TypeDescriptor valueType = variables.remove(location);
        currentVariablesSize -= getSize(valueType);
        return valueType;
    }
    
    
    /**
     * Returns a value from a hash map storing variable values.
     * 
     * @param location
     * @return 
     */
    public TypeDescriptor getVariable(int location) {
        return variables.get(location);
    }
    
    /**
     * This method tells the stack to start a new method definition.
     * Specifically, this method clears out the internal constants/variables
     * stacks and resets the max size value for a new method.
     * 
     */
    public void startMethod(int startingVariableNumber) {
        maxSize = 0;
        currentSize = 0;
        maxVariablesSize = 0;
        currentVariablesSize = 0;
        variables.clear();
        labels.clear();
        variableNumberMappings.clear();
        setMappedStartingVariableNumber(startingVariableNumber);
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
        return currentSize;
    }

    /**
     * is the labels field empty.
     */
    public boolean isEmptyLabel() {
        if(labels.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Returns the maximum total size of the variables stored in the stack
     * achieved while processing push and pop values.
     * 
     * @return 
     */
    public int getMaxVariablesSize() {
        return maxVariablesSize;
    }
    
    /**
     * Returns the current total size of the variables stored in the stack
     * 
     * @return 
     */
    public int getCurrentVariablesSize() {
        return currentVariablesSize;
    }
    
    /**
     * 
     * 
     * @param variableNumber
     * @return 
     */
    public int getMappedVariableNumber(int variableNumber) {
        if (variableNumberMappings.get(variableNumber) != null)
            return variableNumberMappings.get(variableNumber);
        else
            return -1;
    }

    /**
     * @return the currentConditionalBytecode
     */
    public int getCurrentConditionalBytecode() {
        return currentConditionalBytecode;
    }

    /**
     * @param currentConditionalBytecode the currentConditionalBytecode to set
     */
    public void setCurrentConditionalBytecode(int currentIfBytecode) {
        this.currentConditionalBytecode = currentIfBytecode;
    }
    
    /**
     * Helper method: determines the size of a type for a variable position number.
     * 
     * @param valueType returns 2 if a number and 1 in any other case.
     * @return 
     */
    private int getSize(TypeDescriptor valueType){
        if(valueType.isNumber()){
            return 2;
        }else{
            return 1;
        }
    }
}
