/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.Stack;
import java.util.Vector;
import org.quorum.execution.ExpressionValue;

/**
 * Implements a native array that allows for backward debugging.
 * 
 * @author Melissa and Andreas Stefik
 */
public class ArrayDebug implements ArrayInterface{
    protected Vector<Stack<ExpressionValue>> arrayStack;
    protected Stack<Vector<Stack<ExpressionValue>>> vectorStack;
    private Stack<Integer> sizes;
    private Stack<Integer> setValues;
    private Stack<Boolean> resizables;
    private Stack<Stack<ExpressionValue>> removed;
    private static final int INVALID_INDEX = -1;

    public ArrayDebug() {
        arrayStack = new Vector<Stack<ExpressionValue>>(Array.DEFAULT_CAPACITY);
        vectorStack = new Stack<Vector<Stack<ExpressionValue>>>();
        sizes = new Stack<Integer>();
        setValues = new Stack<Integer>();
        resizables = new Stack<Boolean>();
        removed = new Stack<Stack<ExpressionValue>>();
        resizables.push(true);
    }

    public void set(int index, ExpressionValue value) {
        setValues.push(index);
        Stack<ExpressionValue> stack;       
        
        if(arrayStack.get(index) == null) {
            stack = new Stack<ExpressionValue>();
            arrayStack.set(index, stack);
        }
        else {
            stack = arrayStack.get(index);
        }

        stack.push(value);
    }

    public ExpressionValue unset() {
        int index = setValues.pop();
        
        if(arrayStack.get(index) == null) {
            return null;
        }
        else {
            Stack<ExpressionValue> stack = arrayStack.get(index);
            if(stack != null && !stack.isEmpty()) {
                return stack.pop();
            }
            else {
                return null;
            }
        }
    }

    public ExpressionValue get(int index) {
        Stack<ExpressionValue> stack = arrayStack.get(index);
        if(stack == null) {
            return null;
        }

        if(stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    public int getSize() {
        return arrayStack.size();
    }

    public void setSize(int size) {
        this.sizes.push(arrayStack.size());
        
        if(size >= arrayStack.size()) { ///easy, just make the array bigger
            arrayStack.setSize(size);
        }else{//make the array smaller, make sure to store items deleted.
            vectorStack.push((Vector<Stack<ExpressionValue>>) arrayStack.clone());
            arrayStack.setSize(size);
        }
    }

    public void unsetSize() {
        if(sizes.isEmpty()) {
            return;
        }
        Integer pop = sizes.pop();
        int initialSize = arrayStack.size();
        if(pop < initialSize) {
            int i = initialSize;
            while(i > pop) {
                i--;
                this.arrayStack.remove(i);
            }
            arrayStack.trimToSize();
        }else if(pop > initialSize){
            arrayStack = vectorStack.pop();
        }
    }

    public boolean isResizable() {
        return resizables.peek();
    }

    public void setResizable(boolean resizable) {
        resizables.push(resizable);
    }
    
    public void unSetResizable(){
        resizables.pop();
    }
    
    public int getMaxSize(){
        return arrayStack.capacity();
    }

    public void add(int index, ExpressionValue object) {
        setValues.push(index);
        Stack<ExpressionValue> stack = new Stack<ExpressionValue>();
        arrayStack.add(index, stack);
        stack.push(object);
    }
    
    public ExpressionValue unAddAt() {
        int index = setValues.pop();
        ExpressionValue value = arrayStack.get(index).peek();
        arrayStack.removeElementAt(index);
        return value;
    }

    public void add(ExpressionValue object) {
        Stack<ExpressionValue> stack = new Stack<ExpressionValue>();
        arrayStack.add(stack);
        stack.push(object);
    }
    
    public ExpressionValue unAdd() {
        int location = arrayStack.size() - 1;
        ExpressionValue value = arrayStack.elementAt(location).peek();
        arrayStack.removeElementAt(location);
        return value;
    }

    public boolean isEmpty() {
        return arrayStack.isEmpty();
    }

    public void empty() {
        vectorStack.push(arrayStack);
        arrayStack = new Vector<Stack<ExpressionValue>>();
    }
    
    public void unEmpty(){
        arrayStack = vectorStack.pop();
    }

    public ExpressionValue removeAt(int index) {
        setValues.push(index);
        Stack<ExpressionValue> stack = arrayStack.remove(index);
        if(stack == null) {
            return null;
        }

        if(stack.isEmpty()) {
            return null;
        }
        
        removed.push(stack);

        return stack.peek();
    }

    void unRemoveAt() {
        int index = setValues.pop();
        if(!removed.isEmpty()){
            Stack<ExpressionValue> stack = removed.pop();
            arrayStack.add(index, stack);
        }
    }

    public void setMaxSize(int integer) {
        int size = arrayStack.capacity();
        sizes.push(size);
        if(integer < size){
            arrayStack.trimToSize();  
        }
        arrayStack.ensureCapacity(integer);
    }
    
    public void unSetMaxSize(){
        Integer pop = sizes.pop();
        int size = arrayStack.capacity();
        if(pop < size){
            arrayStack.trimToSize();  
        }
        arrayStack.ensureCapacity(pop);
    }
    
}
