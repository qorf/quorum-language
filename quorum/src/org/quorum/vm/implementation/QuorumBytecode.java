/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.File;

/**
 *
 * 
 * @author Andreas Stefik
 */
public class QuorumBytecode {
    
    private byte[] output;
    private byte[] interfaceOutput;
    private String staticKey;
    private File classFile;
    private File interfaceFile;

    /**
     * @return the staticKey
     */
    public String getStaticKey() {
        return staticKey;
    }

    /**
     * @param staticKey the staticKey to set
     */
    public void setStaticKey(String staticKey) {
        this.staticKey = staticKey;
    }

    /**
     * @return the output
     */
    public byte[] getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(byte[] output) {
        this.output = output;
    }

    /**
     * @return the interfaceOutput
     */
    public byte[] getInterfaceOutput() {
        return interfaceOutput;
    }

    /**
     * @param interfaceOutput the interfaceOutput to set
     */
    public void setInterfaceOutput(byte[] interfaceOutput) {
        this.interfaceOutput = interfaceOutput;
    }

    /**
     * @return the classFile
     */
    public File getClassFile() {
        return classFile;
    }

    /**
     * @param classFile the classFile to set
     */
    public void setClassFile(File classFile) {
        this.classFile = classFile;
    }

    /**
     * @return the interfaceFile
     */
    public File getInterfaceFile() {
        return interfaceFile;
    }

    /**
     * @param interfaceFile the interfaceFile to set
     */
    public void setInterfaceFile(File interfaceFile) {
        this.interfaceFile = interfaceFile;
    }
    
}
