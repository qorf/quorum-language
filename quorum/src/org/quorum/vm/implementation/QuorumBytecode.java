/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

/**
 *
 * 
 * @author Andreas Stefik
 */
public class QuorumBytecode {
    
    private byte[] output;
    private String staticKey;

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
    
}
