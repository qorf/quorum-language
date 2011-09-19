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
    private Stack<BytecodeStackValue> constants = new Stack<BytecodeStackValue>();
    private OmniscientStack<LabelStackValue> labels = new OmniscientStack<LabelStackValue>();
    private HashMap<Integer, BytecodeStackValue> variables = new HashMap<Integer, BytecodeStackValue>();
    private ArrayList<ArrayList<TypeDescriptor>> frame = new ArrayList<ArrayList<TypeDescriptor>>();
    private ArrayList<ArrayList<TypeDescriptor>> endFrame = new ArrayList<ArrayList<TypeDescriptor>>();
    private HashMap<Integer, Integer> variableNumberMappings = new HashMap<Integer, Integer>();
    private int maxVariablesSize = 0;
    private int currentVariablesSize = 0;
    private int maxSize = 0;
    private int currentSize = 0;
    
    
    public void setMappedStartingVariableNumber(int variableNumber) {
        variableNumberMappings.put(1, variableNumber);
    }
    
    /**
     * This method pushes constants on the stack.
     * 
     * @param value 
     */
    public void pushConstant(BytecodeStackValue value) {
        constants.push(value);
        currentSize += value.getSize();
        if(currentSize > maxSize) {
            maxSize = currentSize;
        }
    }
    
    
    /**
     * This method pops constants off the stack.
     * @return 
     */
    public BytecodeStackValue popConstant() {
        BytecodeStackValue pop = constants.pop();
        currentSize -= pop.getSize();
        return pop;
    }
    
    /**
     * This method returns a constant value, zero-indexed from the top
     * of the stack. So, for example, if there are three values on the 
     * top of the stack and you want them in the order they were pushed on, 
     * you would call getConstantFromTop(-2), getConstantFromTop(-1), and 
     * getConstantFromTop(0).
     * 
     * @param location
     * @return 
     */
    public BytecodeStackValue getConstantFromTop(int location) {
        return constants.get(constants.size() - 1 - location);
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
    public void setVariable(int location, BytecodeStackValue value) {
        int mappedVariableNumber = getMappedVariableNumber(location);
        variables.put(location, value);
        variableNumberMappings.put(location + 1, mappedVariableNumber + value.getSize());
        currentVariablesSize += BytecodeStackValue.getSize(value.getType());
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
    public BytecodeStackValue removeVariable(int location) {
        BytecodeStackValue value = variables.remove(location);
        currentVariablesSize -= BytecodeStackValue.getSize(value.getType());
        return value;
    }
    
    
    /**
     * Returns a value from a hash map storing variable values.
     * 
     * @param location
     * @return 
     */
    public BytecodeStackValue getVariable(int location) {
        return variables.get(location);
    }
    
    /**
     * add a variable to the frame (only a local variable).
     * @param variable 
     */
    public void addFrameVariable(TypeDescriptor variable){
        if(frame.isEmpty()){
            frame.add(new ArrayList<TypeDescriptor>());
        }
        if(endFrame.isEmpty()){
            newEndFrame();
        }
        frame.get(frame.size() - 1).add(variable);
        endFrame.get(endFrame.size() - 1).add(variable);
    }
    
    /**
     * get the current frame that contains local variables from
     * a previous scope. Note: this should only be called when
     * a new scope is entered.
     * 
     * @return 
     */
    private ArrayList<TypeDescriptor> removeFrontFrame(){
        if(frame.isEmpty()){
            return null;
        }
        ArrayList<TypeDescriptor> removed = frame.remove(0);
        return removed;
    }
    
    private ArrayList<TypeDescriptor> getFrontFrame(){
        if(frame.isEmpty()){
            return null;
        }
        return frame.get(0);
    }
    
    public ArrayList<TypeDescriptor> getFrame() {
        ArrayList<TypeDescriptor> newFrame = new ArrayList<TypeDescriptor>();
        
        while(!frame.isEmpty()){
            ArrayList<TypeDescriptor> previousFrame = removeFrontFrame();
            ArrayList<TypeDescriptor> currentFrame = getFrontFrame();
            
            if(previousFrame != null){
                Iterator<TypeDescriptor> prevIt = previousFrame.iterator();
                while(prevIt.hasNext()){
                    newFrame.add(prevIt.next());
                }
                if(currentFrame != null && !currentFrame.isEmpty()){
                    ArrayList<TypeDescriptor> newScope = new ArrayList<TypeDescriptor>();
                    while(!currentFrame.isEmpty()){
                        TypeDescriptor removed = currentFrame.remove(0);
                        newScope.add(removed);
                        newFrame.add(removed);
                    }
                }
            }
        }
        clearFrame();
        return newFrame;
    }
    
    public void newFrame(){
        frame.add(new ArrayList<TypeDescriptor>());
    }
    
    private void clearFrame(){
        frame.clear();
    }
    
    /**
     * Remove the current frame from the endFrame.
     * @return 
     */
    public ArrayList<TypeDescriptor> removeEndFrame(){
        if(endFrame.isEmpty()){
            return null;
        }
        return endFrame.remove(endFrame.size() - 1);
    }
    
    /**
     * clears the frame. Note: this should be called every time
     * a new frame has been calculated (e.g. going into an if scope).
     */
    public void newEndFrame(){
        endFrame.add(new ArrayList<TypeDescriptor>());
    }
    
    /**
     * This method tells the stack to start a new method definition.
     * Specifically, this method clears out the internal constants/variables
     * stacks and resets the max size value for a new method.
     * 
     */
    public void startMethod() {
        maxSize = 0;
        currentSize = 0;
        maxVariablesSize = 0;
        currentVariablesSize = 0;
        constants.clear();
        variables.clear();
        labels.clear();
        variableNumberMappings.clear();
        setMappedStartingVariableNumber(1);
    }
    
    /**
     * This method allows you to temporarily increase and decrease the 
     * constant stack size, possibly increasing the max stack size, 
     * and decreasing it back again. This is useful if you need to push
     * something on the stack, but do not need to actually push the 
     * value onto the stack.
     * 
     * @param type 
     */
    public void implicitStackIncrease(TypeDescriptor type) {
        int size = BytecodeStackValue.getSize(type);
        this.currentSize += size;
        if(currentSize > maxSize) {
            maxSize = currentSize;
        }
        currentSize -= size;
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
        return variableNumberMappings.get(variableNumber);
    }

}
