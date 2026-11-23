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
public class Number64BitArray {
    public java.lang.Object me_ = null;
    public double[] doubles;
    
    public double Get(int index) throws InvalidLocationError {
        if(doubles == null || index >= doubles.length || index < 0) {
            throw new InvalidLocationError();
        }
        return doubles[index];
    }
    
    public void Set(int index, double value) throws InvalidLocationError {
        if(doubles == null || index >= doubles.length || index < 0) {
            throw new InvalidLocationError();
        }
        doubles[index] = value;
    }
    
    
    public int GetSize() {
        // A new array has no items until SetSize.
        if(doubles == null) {
            return 0;
        }
        return doubles.length;
    }
    
    public void SetSize(int size) {
        // As documented: the items that fit are kept, and new places are 0.
        // (A new array used to replace them all with zeros.)
        if(doubles == null) {
            doubles = new double[size];
        } else {
            doubles = java.util.Arrays.copyOf(doubles, size);
        }
    }
}
