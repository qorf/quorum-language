/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

/**
 * Native implementation of the "Path" class. Uses the "QuorumPath" class.
 * 
 * @author jeff
 */
public class Path {
    public java.lang.Object me_ = null;
    /**
     * Is the path absolute? 
     * 
     * @param path
     * @return 
     */
    public boolean IsPathAbsoluteNative(String path) {
        return QuorumPath.IsPathAbsoluteNative(path);
    }
    
    /**
     * Fixes a path to contain consistent separators. As an example, if the system
     * convention is \ and we provide the path:
     * 
     * C:/Windows/System32/kernel32.dll
     * 
     * The path
     * 
     * C:\Windows\System32\kernel32.dll
     * 
     * will be returned.
     * 
     * @param path
     * @return The modified path if inconsistencies are found; the original path otherwise. 
     */
    public String FixPathSeparatorsNative(String path) {
        return QuorumPath.FixPathSeparatorsNative(path);
    }
}
