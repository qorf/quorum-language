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
        if(ints == null || index > ints.length || index < 0) {
            throw new InvalidLocationError();
        }
        ints[index] = (int) value;
    }
    
    
    public int GetSize() {
        return ints.length;
    }
    
    public void SetSize(int size) {
        ints = new int[size];
    }
}
