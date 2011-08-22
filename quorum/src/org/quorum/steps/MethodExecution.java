/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.MethodDescriptor;

/**
 * Used by the Linker to grab all op-codes generated for a particular
 * function.
 * 
 * @author Andreas Stefik
 */
public class MethodExecution extends LinearExecution {
    private boolean mainMethod;
    private MethodDescriptor methodDescriptor;
    
    /**
     * @return the staticKey
     */
    public String getStaticKey() {
        return methodDescriptor.getStaticKey();
    }

    @Override
    public String toString() {
        return "method: " + methodDescriptor.getStaticKey();
    }

    @Override
    public void add(IntermediateStep step) {
        super.add(step);
        step.setMethodDescriptor(getMethodDescriptor());
    }

    /**
     * @return the mainMethod
     */
    public boolean isMainMethod() {
        return mainMethod;
    }

    /**
     * @param mainMethod the mainMethod to set
     */
    public void setMainMethod(boolean mainMethod) {
        this.mainMethod = mainMethod;
    }

    /**
     * @return the methodDescriptor
     */
    public MethodDescriptor getMethodDescriptor() {
        return methodDescriptor;
    }

    /**
     * @param methodDescriptor the methodDescriptor to set
     */
    public void setMethodDescriptor(MethodDescriptor methodDescriptor) {
        this.methodDescriptor = methodDescriptor;
    }
}
