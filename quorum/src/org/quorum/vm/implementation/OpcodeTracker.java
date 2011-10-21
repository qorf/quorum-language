/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class tracks opcodes generated for a particular execution object, like 
 * a method execution or a class execution.
 * 
 * @author Andreas Stefik, Melissa Stefik
 */
public class OpcodeTracker {
    /**
     * This class maps the raw position value in a method execution object
     * to the array position in this class.
     */
    private HashMap<Integer, Integer> arrayPositionToOpcodePositionMapper = new HashMap<Integer, Integer>();
    
    /**
     * This does the opposite of arrayPositionToOpcodePositionMapper, mapping the index position
     * in the array to the raw position of the opcode in the method execution object.
     */
    private HashMap<Integer, Integer> opcodePositionToArrayPositionMapper = new HashMap<Integer, Integer>();
    
    /**
     * This hashtable maps the opcode number of the first position of a parameter
     * to a function call to the call step opcode number of the function it is
     * calling.
     * 
     * 
     * For example, the left integer is the opcode number for the first parameter
     * to a function.
     * 
     * The right integer is the opcode number for the call step.
     * 
     * integer a = f(4)
     * 
     * 0 = MoveStep(4)
     * 1 = call (f (4) )
     * 
     * 2 = assign(call (f (4 ) )
     * 
     * As such, the mapping here would be 0, 1.
     */
    private HashMap<Integer, Integer> functionParameterMapping = new HashMap<Integer, Integer>();
    
    /**
     * This stores a list of opcodes and their types in linear order.
     */
    private ArrayList<OpcodeType> opcodeList = new ArrayList<OpcodeType>();
    
    /**
     * This encapsulates the queue and dequeue process used by the bytecode
     * generator for storing when expressions should be processed and
     * when they should be stored.
     */
    private Queue<Integer> queue = new LinkedList<Integer>();
    
    
    /**
     * This is the total number of objects inside a particular method/class
     * execution object. This must be set here, otherwise the final opcode
     * length cannot be computed from the array in the getFinalIndex call.
     */
    private int numberOpcodes = -1;
    
    
    public void addFunctionParameterMapping(int param, int call) {
        functionParameterMapping.put(param, call);
    }
    
    public int getFunctionParameterMapping(int param) {
        return functionParameterMapping.get(param);
    }
    
    public boolean containsFunctionParameterMapping(int param) {
        return functionParameterMapping.containsKey(param);
    }
    
    /**
     * Given the raw position in the opcode list (index), add the type 
     * of opcode that is being processed.
     * 
     * @param index
     * @param type 
     */
    public void addBeginIndex(int opcodePosition, OpcodeType type) {
       opcodeList.add(type);
       int arrayPosition = opcodeList.size() - 1;
       arrayPositionToOpcodePositionMapper.put(arrayPosition, opcodePosition);
       opcodePositionToArrayPositionMapper.put(opcodePosition, arrayPosition);
    }
    
    /**
     * Returns the opcode type for a particular opcode position.
     * 
     * @param opcodePosition
     * @return 
     */
    public OpcodeType getOpcodeType(int opcodePosition){
        int beginIndex = getArrayPosition(opcodePosition);
        if(beginIndex != -1){
            OpcodeType op = this.opcodeList.get(beginIndex);
            return op;
        }else{
            return null;
        }
    }
    
    /**
     * Given a raw position of an opcode in its execution object,
     * return the index of the item in the internal array.
     * @param index
     * @return 
     */
    private int getArrayPosition(int opcodePosition) {
        if(opcodePositionToArrayPositionMapper.containsKey(opcodePosition)) {
            return opcodePositionToArrayPositionMapper.get(opcodePosition);
        }
        else {
            return -1;
        }
    }
    
    
    /**
     * Adds an opcode item to the queue.
     * 
     * @param index 
     */
    public void addToQueue(int opcodePosition) {
        int arrayPosition = getArrayPosition(opcodePosition);
        if(arrayPosition != -1){
            queue.add(opcodePosition);
        }
    }
    
    /**
     * Removes an item from the queue.
     * 
     * @return 
     */
    public int removeFromQueue() {
        return queue.remove();
    }
    
    /**
     * Returns the raw position on the top of the queue.
     * @return 
     */
    public int peekQueue() {
        return queue.peek();
    }
    
    /**
     * Returns the number of items in the queue.
     * 
     * @return 
     */
    public int getQueueSize() {
        return queue.size();
    }
    
    /**
     * Clear out the queue.
     */
    public void clearQueue() {
        queue.clear();
    }
    
    /**
     * Get the final opcode in the expression.
     * 
     * @param index
     * @return 
     */
    public int getFinalPosition(int opcodePosition) {
        int begin = getArrayPosition(opcodePosition);
        if(opcodeList.size() - 1 >= begin + 1) {
            int position = arrayPositionToOpcodePositionMapper.get(begin + 1);
            return position - 1;
        }
        else { //it's the last value in the array list
            return numberOpcodes;
        }
    }

    /**
     * Returns the number of opcodes this tracker is iterating over.
     * 
     * @return the numberOpcodes
     */
    public int getNumberOpcodes() {
        return numberOpcodes;
    }

    /**
     * Sets the number of opcodes this tracker is considering. This is useful
     * in that it helps determine the final opcode length.
     * 
     * @param numberOpcodes the numberOpcodes to set
     */
    public void setNumberOpcodes(int numberOpcodes) {
        this.numberOpcodes = numberOpcodes;
    }
    
    /**
     * Removes all items from this data structure.
     */
    public void clear() {
        arrayPositionToOpcodePositionMapper.clear();
        opcodePositionToArrayPositionMapper.clear();
        opcodeList.clear();
        functionParameterMapping.clear();
    }
}
