/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Containers;

/**
 *
 * @author Andreas Stefik
 */
public class Array {
    public void SetNative(int location, Object value) {}
    public Object GetNative(int location) {
        return null;
    }
    public void SetSizeNative(int size) {}
    public void AddNative(int location, Object value) {}
    public void AddNative(Object value) {}
    public Object RemoveAtNative(int location) {
        return null;
    }
    public int GetMaxSize() {
        return 0;
    }
    public void SetMaxSize(int size) {}
    public int GetSize() {
        return 0;
    }
    public boolean GetAutoResize() {
        return true;
    }
    public void SetAutoResize(boolean resizable)
    {
        
    }
    public void Empty() {}
    public boolean IsEmpty() {
        return true;
    }
}
