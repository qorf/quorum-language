/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;
import java.io.File;

/**
 * Implements the appropriate methods for the "Path" class in Quorum. As a
 * specific instance isn't required for these methods, all methods are
 * static.
 * @author jeff
 */
public class QuorumPath {
    /**
     * Is the path absolute? 
     * 
     * @param path
     * @return 
     */
    public static boolean IsPathAbsoluteNative(String path) {
        // May be of the form:
        // /(stuff)
        // \(stuff)
        // \\(stuff) (accessing shares on windows)
        // [a-zA-Z]:(stuff) (drive letters on windows)
        return path.matches("[a-zA-Z]:[\\\\/].*") || path.matches("[\\\\/].*") || path.matches("\\\\\\\\.*");
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
    public static String FixPathSeparatorsNative(String path) {
        if (path.contains("/") && File.separatorChar == '\\') {
            return path.replace("/", "\\");
        }
        else if (path.contains("\\") && File.separatorChar == '/') {
            return path.replace("\\", "/");
        }
        else {
            // Path must be correct. No changes.
            return path;
        }
    }
}
