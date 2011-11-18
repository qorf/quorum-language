/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Compute;

/**
 * This is an implementation of a plugin for random numbers.
 * 
 * @author Andreas Stefik
 */
public class Random {
    public java.lang.Object ___$$$Calling___$$$___Object$$$___ = null;
    private java.util.Random random = new java.util.Random();
    
    public void InitializeNative() {
        long seed = System.currentTimeMillis();
        random.setSeed(seed);
    }
    
    public int RandomintNative(int maximum) {
        int randomNumber = random.nextInt(maximum + 1);
        return randomNumber;
    }
    
    public void SetSeed(double seed) {
        long longSeed = (long) seed;
        random.setSeed(longSeed);
    }
    
    public int RandomInteger() {
        int randomNumber = random.nextInt(2147483647);
        return randomNumber;
    }
    
    public double RandomNumber() {
        return random.nextDouble();
    }
    
    public boolean RandomBoolean() {
        return random.nextBoolean();
    }
}
