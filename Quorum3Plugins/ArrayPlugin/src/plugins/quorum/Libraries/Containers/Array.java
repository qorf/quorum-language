/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Containers;

import java.util.Vector;
import quorum.Libraries.Language.Object_;

/**
 * An implementation of the native plugin for Libraries.Containers.Array.
 * 
 * @author Andreas Stefik
 */
public class Array {
    public java.lang.Object me_ = null;
    public static final int DEFAULT_CAPACITY = 10;
    protected boolean resizable = true;
    private Vector<Object_> array;
    
    public Array() {
        array = new Vector(DEFAULT_CAPACITY);
    }
    
    
    public void SetNative(int location, Object_ value) {
        array.set(location, value);
    }
    
    
    public Object_ GetNative(int location) {
        return array.get(location);
    }
    
    public void SetSizeNative(int size) {
        array.setSize(size);
    }
    
    public void AddNative(int location, Object_ value) {
        array.add(location, value);
    }
    
    public void AddNative(Object_ value) {
        array.add(value);
    }
    
    public Object_ RemoveAtNative(int location) {
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
    
    public boolean IsEmpty() {
        return array.isEmpty();
    }

    public void Empty() {
        array.clear();
    }
}
