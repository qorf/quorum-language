/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.Iterator;
import java.util.Stack;


/**
 * This class maintains a stack and provides the ability to undo and redo operations on it
 * @author Aaron Willows
 */
public class OmniscientStack<T> {
    /**
     * The basic stack for this OmniscientStack
     */
    private Stack<T> stack;
    /**
     * The stack of operations that have occured on this stack
     */
    private Stack<StackSave<T>> undoStack;


    /**
     * pushes a value onto the stack
     * @param value
     */
    public void push(T value) {
        StackSave<T> save = new StackSave<T>();
        save.setPush(true);
        save.setValue(value);
        undoStack.push(save);
        stack.push(value);
    }

    /**
     * pops a value off the stack
     * @return
     */
    public T pop() {
        T value = stack.pop();
        StackSave<T> save = new StackSave<T>();
        save.setPush(false);
        save.setValue(value);
        undoStack.push(save);
        return value;
    }

    /**
     * undos the last action. If no actions have been preformed it does nothing
     */
    public void undo() {
        if(undoStack.isEmpty()) {
           return;
        }
        StackSave<T> save = undoStack.pop();
        if(save.isPush()) {
            stack.pop();
        }
        else {
            stack.push(save.getValue());
        }
    }


    /**
     * Gets the value at the top of the stack without removing it
     * @return
     */
    public T peek() {
        return stack.peek();
    }

    private class StackSave<T> {
        /**
         * If true the action to undo is a push action otherwise the action is
         * a pop action
         */
        private boolean push;
        private T value;

        /**
         * @return the push
         */
        public boolean isPush() {
            return push;
        }

        /**
         * @param push the push to set
         */
        public void setPush(boolean push) {
            this.push = push;
        }

        /**
         * @return the value
         */
        public T getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(T value) {
            this.value = value;
        }


    }


    public void clear() {
        stack.clear();
        undoStack.clear();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean isUndoEmpty(){
        return undoStack.isEmpty();
    }

    public OmniscientStack() {
        stack = new Stack<T>();
        undoStack = new Stack<StackSave<T>>();
    }

    /**
     * Returns the stack from an omniscient stack.
     * @return stack
     */
    public Stack<T> getStack(){
        return stack;
    }
}
