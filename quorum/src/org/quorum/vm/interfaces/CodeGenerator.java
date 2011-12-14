/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * A CodeGenerator object outputs some kind of raw machine code or bytecode.
 * 
 * @author Andreas Stefik
 */
public interface CodeGenerator {
    
    /**
     * This method generates java bytecode for all classes on the system.
     * 
     */
    public void generate();
    
    /**
     * This method flushes all of the bytecode arrays for the generated
     * bytecode to disk.
     */
    public void writeToDisk() throws IOException;
    
    /**
     * @return the buildFolder
     */
    public File getBuildFolder();

    /**
     * @param buildFolder the buildFolder to set
     */
    public void setBuildFolder(File buildFolder);
    
    /**
     * The folder that will be used to produce the final executable code.
     * 
     * @return 
     */
    public File getDistributionFolder();
    
    /**
     * Sets the folder that will create the final executable package.
     * @param distributionFolder 
     */
    public void setDistributionFolder(File distributionFolder);
    
    /**
     * Returns the name of the final executable that will be created
     * by the system.
     * 
     * @return 
     */
    public String getDistributionName();
    
    /**
     * Sets the name of the final executable that will be created by
     * the system.
     * @param name 
     */
    public void setDistributionName(String name);
    
    /**
     * Adds a dependency into the distributed code for the target platform.
     * If the file passed is a folder, the entire folder will be copied 
     * verbatim to the dependency folder, but no attempt will be made to
     * write the dependencies into the target execution. As such, dependencies
     * must be managed individually, but folders (e.g., images or resources), 
     * can be copied raw.
     * 
     * @param file 
     */
    public void addDependency(File file);
    
    /**
     * This method allows the user to add a dependency into a folder of the 
     * user's choice. No attempt will be made to inject these dependencies
     * into the target's execution (e.g., a jar file's manifest). This is useful
     * if you want to copy a series of files, one-by-one, into a specified
     * folder.
     * 
     * @param file
     * @param relativePath 
     */
    public void addDependency(File file, String relativePath);
    
    /**
     * Returns an iterator of all dependencies loaded on the system.
     * 
     * @return 
     */
    public Iterator<Dependency> getDependencies();
    
    /**
     * Clears out all dependencies on the target system.
     * 
     */
    public void clearDependencies();
    
    /**
     * Returns the number of dependencies currently loaded on the target
     * platform.
     * 
     * @return 
     */
    public int getNumberOfDependencies();
    
    /**
     * Returns the file on the system that is to be used as the entry point
     * for execution.
     * 
     * @return 
     */
    public File getMainFile();
    
    /**
     * Sets the file on the system that is to be used as the entry point for
     * execution.
     * 
     * @param file 
     */
    public void setMainFile(File file);
}
