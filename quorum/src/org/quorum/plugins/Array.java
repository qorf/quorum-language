/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.Vector;
import org.quorum.execution.ExpressionValue;

/**
 * This class represents an array, as implemented natively through Java.
 *
 * @author Melissa and Andreas Stefik
 */
public class Array implements ArrayInterface{
    protected Vector<ExpressionValue> array;
    public static final int DEFAULT_CAPACITY = 10;
    protected boolean resizable = true;

    public Array() {
        array = new Vector(DEFAULT_CAPACITY);
    }

    public void set(int index, ExpressionValue value) {
        array.set(index, value);
    }

    public ExpressionValue get(int index) {
        if(index >= 0 && index < array.size()) {
            return array.get(index);
        }
        return null;
    }

    public int getSize() {
        return array.size();
    }

    public void setSize(int size) {
        array.setSize(size);
    }
    
    public boolean isResizable(){
        return resizable;
    }
    
    public void setResizable(boolean resizable){
        this.resizable = resizable;
    }

    public int getMaxSize() {
        return array.capacity();
    }

    public void add(int index, ExpressionValue object) {
        array.add(index, object);
    }

    public void add(ExpressionValue object) {
        array.add(object);
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void empty() {
        array.clear();
    }

    public ExpressionValue removeAt(int index) {
       return array.remove(index);
    }

    public void setMaxSize(int integer) {
        if(array.capacity() > integer){
            array.trimToSize();
        }
        array.ensureCapacity(integer);
    }
}