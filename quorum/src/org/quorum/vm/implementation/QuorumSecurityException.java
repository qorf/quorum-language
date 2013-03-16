/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

/**
 *
 * @author stefika
 */
public class QuorumSecurityException extends ClassNotFoundException{
    private String className = "";

    public QuorumSecurityException(String string) {
        super(string);
    }
    
    /**
     * The class the user was trying to access, but wasn't allowed to.
     * 
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * The class the user was trying to access, but wasn't allowed to.
     * 
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
}
