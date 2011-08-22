/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 * This class holds information regarding packages and classes in the
 * standard library.
 * 
 * @author Andreas Stefik
 */
public class LibraryIndexEntry {

    /**
     * The path of the file relative to the root of the standard library path.
     */
    protected String name;

    /**
     * The full package name for an item.
     */
    protected String fullPackageName;

    /**
     * The path to the file containing this item.
     */
    protected String path;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the fullPackageName
     */
    public String getFullPackageName() {
        return fullPackageName;
    }

    /**
     * Returns the fully resolved class name for this index entry.
     * 
     * @return
     */
    public String getFullClassName() {
        return fullPackageName + "." + name;
    }
    
    /**
     * Returns an array of strings for each subpackage.
     * 
     * @return 
     */
    public String[] getPackageArray() {
        String[] split = fullPackageName.split("\\.");
        return split;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
}
