/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Containers;

import java.util.Vector;
import quorum.Libraries.Language.Object$Interface;

/**
 * An implementation of the native plugin for Libraries.Containers.Array.
 * 
 * @author Andreas Stefik
 */
public class Array {
    public java.lang.Object ___$$$Calling___$$$___Object$$$___ = null;
    public static final int DEFAULT_CAPACITY = 10;
    protected boolean resizable = true;
    private Vector<Object$Interface> array;
    
    public Array() {
        array = new Vector(DEFAULT_CAPACITY);
    }
    
    
    public void SetNative(int location, Object$Interface value) {
        array.set(location, value);
    }
    
    
    public Object GetNative(int location) {
        return array.get(location);
    }
    
    public void SetSizeNative(int size) {
        array.setSize(size);
    }
    
    public void AddNative(int location, Object$Interface value) {
        array.add(location, value);
    }
    
    public void AddNative(Object$Interface value) {
        array.add(value);
    }
    
    public Object RemoveAtNative(int location) {
        return array.remove(location);
    }
    
    public int GetMaxSize() {
        return array.capacity();
    }
    
    public void SetMaxSize(int size) {
        if(array.capacity() > size){
            array.trimToSize();
        }
        array.ensureCapacity(size);
    }
    
    public int GetSize() {
        return array.size();
    }
    
    public boolean GetAutoResize() {
        return resizable;
    }
    
    public void SetAutoResize(boolean resizable)
    {
        this.resizable = resizable;
    }
    
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void empty() {
        array.clear();
    }
}
