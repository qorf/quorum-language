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
    private HashMap<Integer, Integer> quorumToJavaNumberVariableMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, TypeDescriptor> variables = new HashMap<Integer, TypeDescriptor>();
    private Stack<Integer> loopCounterVariables = new Stack<Integer>();
    private Stack<Integer> loopMaximumVariables = new Stack<Integer>();
    private int startingVariable = 0; 
    private int maxVariablesSize = 0;
    private int currentVariablesSize = 0;
    private int maxSize = 0;
    private int numberOfHiddenVariables = 0;
    
    /**
     * Push an integer counter variable onto the counter stack. Since it is an
     * integer, the variable size is always one.
     * @return 
     */
    public int pushCounterVariable() {
        numberOfHiddenVariables++;
        currentVariablesSize += 1;
        variables.put(currentVariablesSize, TypeDescriptor.getIntegerType());
        
        int location = currentVariablesSize;

        if(currentVariablesSize > maxVariablesSize) {
            maxVariablesSize = currentVariablesSize;
        }
        
        loopCounterVariables.add(location);
        return location;
    }
    
    /**
     * Get the last counter variable off the stack. Handy for nested loops.
     * @return 
     */
    public int popCounterVariable() {
        return loopCounterVariables.pop();
    }
    
    /**
     * Get the counter variable off the stack without consuming it.
     */
    public int peekCounterVariable() {
        return loopCounterVariables.peek();
    }

    /**
     * Push an integer maximum variable onto the maximum variable stack. Since it is an
     * integer, the variable size is always one.
     * @return 
     */
    public int pushMaximumVariable() {
        numberOfHiddenVariables++;
        currentVariablesSize += 1;
        variables.put(currentVariablesSize, TypeDescriptor.getIntegerType());
        
        int location = currentVariablesSize;

        if(currentVariablesSize > maxVariablesSize) {
            maxVariablesSize = currentVariablesSize;
        }
        
        loopMaximumVariables.add(location);
        return location;
    }
    
    /**
     * Pop the last maximum variable off the stack. Handy for nested loops.
     * @return 
     */
    public int popMaximumVariable() {
        return loopMaximumVariables.pop();
    }
    
    /**
     * Get the maximum variable off the stack without consuming it.
     */
    public int peekMaximumVariable() {
        return loopMaximumVariables.peek();
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
        if(labels.isEmpty()){
            return null;
        }else{
            return labels.peek();
        }
    }
    
    /**
     * Undo a label pop.
     */
    public void undoLabel(){
        labels.undo();
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
     * Pushes a value for a particular variable onto a hash map that can
     * later be queried.
     * 
     * @param location
     * @param value 
     */
    public void setVariable(int location, TypeDescriptor valueType) {
        int size = QuorumConverter.getSizeOfType(valueType);
        quorumToJavaNumberVariableMap.put(location, startingVariable + currentVariablesSize);
        variables.put(startingVariable + currentVariablesSize, valueType);
        currentVariablesSize += size;
 
        if(currentVariablesSize > maxVariablesSize) {
            maxVariablesSize = currentVariablesSize;
        }
        
        /*
        int mappedVariableNumber = getMappedVariableNumber(location + numberOfHiddenVariables);
        variables.put(location + numberOfHiddenVariables, valueType);
        
        int size = QuorumConverter.getSizeOfType(valueType);
        
        variableNumberMappings.put(location + 1, mappedVariableNumber + size);
        currentVariablesSize += size;
        if(currentVariablesSize > maxVariablesSize) {
            maxVariablesSize = currentVariablesSize;
        }*/
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
        currentVariablesSize -= QuorumConverter.getSizeOfType(valueType);
        return valueType;
    }
    
    
    /**
     * Returns a value from a hash map storing variable values.
     * 
     * @param JVMNumber
     * @return 
     */
    public TypeDescriptor getVariable(int JVMNumber) {
        return variables.get(JVMNumber);
    }
    
    /**
     * This method tells the stack to start a new method definition.
     * Specifically, this method clears out the internal constants/variables
     * stacks and resets the max size value for a new method.
     * 
     */
    public void startMethod(int startingVariableNumber) {
        maxSize = 0;
        maxVariablesSize = 0;
        currentVariablesSize = 0;
        variables.clear();
        labels.clear();
        quorumToJavaNumberVariableMap.clear();
        loopCounterVariables.clear();
        loopMaximumVariables.clear();
        setStartingVariableNumber(startingVariableNumber);
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
     * Returns the maximum total size of the variables stored in the stack
     * achieved while processing push and pop values.
     * 
     * @return 
     */
    public int getMaxVariablesSize() {
        return maxVariablesSize;
    }
    
    /**
     * Set the variable number (given by the interpretor).
     * 
     * @param quorumLocation
     */
    public void setStartingVariableNumber(int quorumLocation) {
        startingVariable = quorumLocation;
    }
        
    /**
     * Get the mapped variable number for the bytecode.
     * 
     * @param quorumLocation
     * @return 
     */
    public int getMappedVariableNumber(int quorumLocation) {
        if (quorumToJavaNumberVariableMap.get(quorumLocation) != null)
            return quorumToJavaNumberVariableMap.get(quorumLocation);
        else
            return -1;
    }
}
