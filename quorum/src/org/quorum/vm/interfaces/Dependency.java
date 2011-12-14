/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

import java.io.File;

/**
 * A class representing a dependency on the system.
 * 
 * @author Andreas Stefik
 */
public class Dependency {
    private File file;
    private String relativePath = "";
    private boolean isExecutionDependency = true;
    
    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the relativePath
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * @param relativePath the relativePath to set
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    /**
     * Returns true if this dependency should be taken into account when
     * writing a system's executable.
     * 
     * @return the isExecutionDependency
     */
    public boolean isExecutionDependency() {
        return isExecutionDependency;
    }

    /**
     * @param isExecutionDependency the isExecutionDependency to set
     */
    public void setExecutionDependency(boolean isExecutionDependency) {
        this.isExecutionDependency = isExecutionDependency;
    }
}
