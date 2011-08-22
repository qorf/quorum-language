/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.io.File;
import java.util.Iterator;

/**
 * This class represents an index for a standard library for the programming
 * language in question. It provides basic functions for querying valid
 * files that are in the standard library.
 * 
 * @author Andreas Stefik
 */
public interface StandardLibrary {
    /**
     * Determines whether a particular package exists from its full package
     * name.
     *
     * @param name
     * @return
     */
    public boolean doesPackageExist(String name);

    /**
     * Finds the file for a particular class from its package and class name.
     * 
     * @param pack
     * @param name
     * @return
     */
    public File findClass(String pack, String name);

    /**
     * Finds a file for a particular class from its index entry.
     * 
     * @param entry
     * @return
     */
    public File findClass(LibraryIndexEntry entry);

    /**
     * Finds all files associated with various classes in a particular
     * package.
     * 
     * @param pack
     * @return
     */
    public Iterator<LibraryIndexEntry> findAllClassesInPackage(String pack);
    
    /**
     * Finds all files associated with the standard library.
     * 
     * @return Returns an iterator of all standard library files.
     */
    public Iterator<LibraryIndexEntry> findAllClasses();
    
    /**
     * Returns the folder on the system where the standard library is located.
     * 
     * @return 
     */
    public File getRootFolder();
    
    /**
     * Returns the name of the package that is the root of the standard library.
     * 
     * @return 
     */
    public String getStandardLibraryRootName();
    
    /**
     * Returns the names of all subpackages underneath a particular package.
     * 
     * @param pack
     * @return 
     */
    public Iterator<String> findAllSubpackages(String pack);
}
