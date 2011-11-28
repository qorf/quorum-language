/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Compute;

/**
 * Java bytecode plugin for the "Random" class.
 * 
 * @author jeff
 */
public class Random {
    public java.lang.Object ___$$$Calling___$$$___Object$$$___ = null;
    private java.util.Random random = new java.util.Random();
    
    public void InitializeNative() {
        long seed = System.currentTimeMillis();
        random.setSeed(seed);
    }
    
    public int RandomIntegerNative(int maximum) {
        return random.nextInt(maximum + 1);
    }
    
    public void SetSeed(double seed) {
        random.setSeed((long)seed);
    }
    
    public int RandomInteger() {
        return random.nextInt(2147483647);
    }
    
    public double RandomNumber() {
        return random.nextDouble();
    }
    
    public boolean RandomBoolean() {
        return random.nextBoolean();
    }

}
