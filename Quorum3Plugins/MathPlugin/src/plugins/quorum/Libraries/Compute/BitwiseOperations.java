/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Compute;

/**
 *
 * @author astefik
 */
public class BitwiseOperations {
    public java.lang.Object me_ = null;
    
    public int And(int left, int right){
        return left & right; 
    }
    
    public int Or(int left, int right) {
        return left | right;
    }
    public int ExclusiveOr(int left, int right) {
        return left ^ right;
    }
    public int Negate(int value) {
        return ~value;
    }
    public int ShiftLeft(int value, int amount) {
        return value << amount;
    }
    public int ShiftRight(int value, int amount) {
        return value >> amount;
    }
    public int ShiftRightPositive(int value, int amount) {
        return value >>> amount;
    }
}
