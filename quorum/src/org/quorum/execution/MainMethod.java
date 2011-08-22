/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import org.quorum.steps.MethodExecution;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;

/**
 * Contains data about a programs entry point
 * @author Aaron Willows
 */
public class MainMethod {
    private int address;
    private String file;
    private MethodDescriptor descriptor;
    private ClassDescriptor classDescriptor;
    private MethodExecution execution;

    /**
     * @return the address
     */
    public int getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(int address) {
        this.address = address;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the descriptor
     */
    public MethodDescriptor getDescriptor() {
        return descriptor;
    }

    /**
     * @param descriptor the descriptor to set
     */
    public void setDescriptor(MethodDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * @return the execution
     */
    public MethodExecution getExecution() {
        return execution;
    }

    /**
     * @param execution the execution to set
     */
    public void setExecution(MethodExecution execution) {
        this.execution = execution;
    }

    /**
     * @return the classDescriptor
     */
    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }

    /**
     * @param classDescriptor the classDescriptor to set
     */
    public void setClassDescriptor(ClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }
}
