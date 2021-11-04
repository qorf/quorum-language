/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

/**
 * Represents the operating systems supported.
 *
 * @author Jeff Wilson and Andreas Stefik
 */
public enum OperatingSystem {
    WINDOWS_XP,
    WINDOWS_VISTA,
    WINDOWS7,
    WINDOWS8,
    WINDOWS_10,
    WINDOWS_OTHER,
    MAC_OSX,
    LINUX;

    /**
     * Returns the number of bits this operating system is, typically 32
     * or 64.
     *
     * @return the number of bits.
     */
    public static int getNumberBits() {
        String numBits = System.getProperty("sun.arch.data.model");
        return Integer.parseInt(numBits);
    }
    /*
     * Get the current operating system in use.
     *
     * @returns The current operating system
     */
    public static OperatingSystem getOS() {
        String os = System.getProperty("os.name");
        if(os.compareTo("Windows Vista") == 0) {
            return WINDOWS_VISTA;
        }
        else if(os.compareTo("Windows 10") == 0)
        {
            return WINDOWS_10;
        }
        else if(os.compareTo("Windows XP") == 0)
        {
            return WINDOWS_XP;
        }
        else if (os.compareTo("Windows 7") == 0) {
            return WINDOWS7;
        }
        else if (os.compareTo("Mac OS X") == 0) {
            return MAC_OSX;
        }
        else if (os.compareTo("Linux") == 0) {
            // Turns out on Linux, it's just "Linux." -- cool
            return LINUX;
        }
        else if (os.compareTo("Windows 8") == 0) {
            return WINDOWS8;
        }
        else if (os.contains("Windows")) {
            // It's windows, but we don't know which.
            return WINDOWS_OTHER;
        }
        else {
            return null;
        }
    }
}
