package plugins.quorum.Libraries.Containers;

import quorum.Libraries.Language.Object_;

/**
 * An implementation of the native plugin for Libraries.Containers.Array.
 * 
 * @author Andreas Stefik
 */
public class Array {
    public java.lang.Object me_ = null;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_RESIZE = 2;
    protected boolean resizable = true;
    Object_[] array = null;
    private int size = 0;
    
    public Array() {
        array = new Object_[DEFAULT_CAPACITY];
    }
    
    public Array(int size) {
        array = new Object_[size];
    }
    
    public void SetNative(int location, Object_ value) {
        array[location] = value;
    }
    
    public Object_ GetNative(int location) {
        return array[location];
    }
    
    public void SetSizeNative(int size) {
        if(array.length > size) {
            Object_[] newArray = new Object_[size];
            for(int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        } else if(array.length < size) {
            Object_[] newArray = new Object_[size];
            for(int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            
            array = newArray;
        }
        this.size = size;
    }
    
    public void SetSizeNoFillNative(int size) {
        array = new Object_[size];
        this.size = size;
    }
    
    public void AddNative(int location, Object_ value) {
        //do a custom resize for speed.
        if(size + 1 >= array.length && this.resizable) {
            Object_[] newArray = new Object_[array.length * DEFAULT_RESIZE];
            //copy up to the new location
            for(int i = 0; i < location; i++) {
                newArray[i] = array[i];
            }
            newArray[location] = value;
            for(int i = location; i < size; i++) {
                newArray[i+1] = array[i];
            }
            array = newArray;
        } else if(size + 1 >= array.length && !this.resizable) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for(int i = size - 1; i >= location; i--) {
                array[i+1] = array[i];
            }
            array[location] = value;
        }
        size = size + 1;
    }
    
    public void AddNative(Object_ value) {        
        if(size >= array.length && this.resizable) {
            resize(DEFAULT_RESIZE);
        }
        array[size] = value;
        size = size + 1;
    }
    
    private void resize(int factor) {
        Object_[] newArray = new Object_[array.length * factor];
        copy(array, newArray);
        array = newArray;
    }
    
    private void copy(Object_[] from, Object_[] to) {
        if(to.length > from.length) {
            for(int i = 0; i < from.length; i++) {
                to[i] = from[i];
            }
        } else {
            for(int i = 0; i < to.length; i++) {
                to[i] = from[i];
            }
        }
    }
    
    public Object_ RemoveAtNative(int location) {
        Object_ o = array[location];
        for(int i = location + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size = size - 1;
        return o;
    }
    
    public int GetMaxSize() {
        return array.length;
    }
    
    public void SetMaxSize(int size) {
        if(array.length > size) {
            Object_[] newArray = new Object_[size];
            for(int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        } else if(array.length < size) {
            Object_[] newArray = new Object_[size];
            for(int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }//if they are equal, ignore the call.
    }
    
    public int GetSize() {
        return size;
    }
    
    public boolean GetAutoResize() {
        return resizable;
    }
    
    public void SetAutoResize(boolean resizable)
    {
        this.resizable = resizable;
    }
    
    public boolean IsEmpty() {
        return size == 0;
    }

    public void ClearContents(int start, int stop) {
        for(int i = start; i < stop; i++) {
            array[i] = null;
        }
    }

    //public CreateArrayAtSize(int size) returns Array<Temp>
    
    public void Empty() {
        array = new Object_[array.length];
        size = 0;
    }
    
    public void Empty(boolean value) {
        if(value) {
            Empty();
        } else {
            size = 0;
        }
    }
}
