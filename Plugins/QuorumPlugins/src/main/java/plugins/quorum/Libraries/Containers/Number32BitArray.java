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
public class Number32BitArray {
    public java.lang.Object me_ = null;
    public float[] floats;
    
    public double Get(int index) throws InvalidLocationError {
        if(floats == null || index >= floats.length || index < 0) {
            throw new InvalidLocationError();
        }
        return floats[index];
    }
    
    public void Set(int index, double value) throws InvalidLocationError {
        if(floats == null || index >= floats.length || index < 0) {
            throw new InvalidLocationError();
        }
        floats[index] = (float) value;
    }
    
    
    public int GetSize() {
        // A new array has no items until SetSize.
        if(floats == null) {
            return 0;
        }
        return floats.length;
    }
    
    public void SetSize(int size) {
        // As documented: the items that fit are kept, and new places are 0.
        // (A new array used to replace them all with zeros.)
        if(floats == null) {
            floats = new float[size];
        } else {
            floats = java.util.Arrays.copyOf(floats, size);
        }
    }
}
