/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

import java.io.File;
import java.io.IOException;

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
}
