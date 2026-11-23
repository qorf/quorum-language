/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Containers;

import quorum.Libraries.Language.Errors.InvalidLocationError;

/**
 *
 * @author stefika
 */
public class Integer32BitArray {
    public java.lang.Object me_ = null;
    public int[] ints;
    
    public int Get(int index) throws InvalidLocationError {
        if(ints == null || index >= ints.length || index < 0) {
            throw new InvalidLocationError();
        }
        return ints[index];
    }
    
    public void Set(int index, int value) throws InvalidLocationError {
        if(ints == null || index >= ints.length || index < 0) {
            throw new InvalidLocationError();
        }
        ints[index] = (int) value;
    }
    
    
    public int GetSize() {
        // A new array has no items until SetSize.
        if(ints == null) {
            return 0;
        }
        return ints.length;
    }
    
    public void SetSize(int size) {
        // As documented: the items that fit are kept, and new places are 0.
        // (A new array used to replace them all with zeros.)
        if(ints == null) {
            ints = new int[size];
        } else {
            ints = java.util.Arrays.copyOf(ints, size);
        }
    }
}
