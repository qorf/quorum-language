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
 * @author Andreas Stefik
 */
public class OpcodeTracker {
    /**
     * This class maps the raw position value in a method execution object
     * to the array position in this class.
     */
    private HashMap<Integer, Integer> opcodePositionToArrayMapper = new HashMap<Integer, Integer>();
    
    /**
     * This does the opposite of opcodePositionToArrayMapper, mapping the index position
     * in the array to the raw position of the opcode in the method execution object.
     */
    private HashMap<Integer, Integer> opcodeArrayToPositionMapper = new HashMap<Integer, Integer>();
    
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
    
    /**
     * Given the raw position in the opcode list (index), add the type 
     * of opcode that is being processed.
     * 
     * @param index
     * @param type 
     */
    public void addBeginIndex(int index, OpcodeType type) {
       opcodeList.add(type);
       int position = opcodeList.size() - 1;
       opcodePositionToArrayMapper.put(position, index);
       opcodeArrayToPositionMapper.put(index, position);
    }
    
    
    /**
     * Given a raw position of an opcode in its execution object,
     * return the index of the item in the internal array.
     * @param index
     * @return 
     */
    private int getBeginIndex(int index) {
        int mappedIndex = getArrayIndex(index);
        return mappedIndex;
    }
    
    
    /**
     * Adds an item to the queue.
     * 
     * @param index 
     */
    private void addToQueue(int index) {
        int arrayIndex = getArrayIndex(index);
        queue.add(arrayIndex);
    }
    
    /**
     * Removes an item from the queue.
     * 
     * @return 
     */
    public int removeFromQueue() {
        Integer peek = peekQueue();
        return queue.remove();
    }
    
    /**
     * Returns the raw position on the top of the queue.
     * @return 
     */
    public int peekQueue() {
        Integer peek = opcodeArrayToPositionMapper.get(queue.peek());
        return peek;
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
     * Returns the index in the 
     * @param index
     * @return 
     */
    private int getArrayIndex(int index) {
        if(opcodePositionToArrayMapper.containsKey(index)) {
            return opcodePositionToArrayMapper.get(index);
        }
        else {
            return -1;
        }
    }
    
    public int getFinalIndex(int index) {
        int begin = getBeginIndex(index);
        if(opcodeList.size() - 1 > begin + 1) {
            int position = opcodeArrayToPositionMapper.get(begin + 1);
            return position - 1;
        }
        else { //it's the last value in the array list
            return numberOpcodes - 1;
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
        opcodePositionToArrayMapper.clear();
        opcodeArrayToPositionMapper.clear();
        opcodeList.clear();
    }
}
