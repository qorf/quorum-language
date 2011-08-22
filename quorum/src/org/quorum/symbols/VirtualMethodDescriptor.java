/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import org.quorum.execution.ActivationRecord;
import org.quorum.execution.ExpressionValue;

/**
 * An object that represents the items in a v-table. This
 * tracks the activation record for each method in the v-table.
 *
 * @author Melissa Stefik
 */
public class VirtualMethodDescriptor {
    private ActivationRecord activationRecord = new ActivationRecord();
    private MethodDescriptor methodDescriptor = new MethodDescriptor();

    public VirtualMethodDescriptor(MethodDescriptor md){
        setMethodDescriptor(md);
    }

    /**
     * Get the location of the virtual method descriptor
     * @return
     */
    public int getLocation(){
        int loc = -1;
        if(methodDescriptor != null){
            loc = methodDescriptor.getLocation().getStart();
        }
        return loc;
    }
    
    /**
     * Set the method descriptor this virtual method is associated with.
     * @param md
     */
    private void setMethodDescriptor(MethodDescriptor md){
        methodDescriptor = md;
        ExpressionValue ret = new ExpressionValue();
        ret.setType(md.getReturnType());
        activationRecord.setReturnValue(ret);
        activationRecord.build(md);
    }
    
    /**
     * Get the method descriptor this virtual method is associated with.
     * @return
     */
    public MethodDescriptor getMethodDescriptor(){
        
        return methodDescriptor;
    }

    /**
     * Get the activation record for an item in a virtual table.
     * @return
     */
    public ActivationRecord getActivationRecord(){
        return activationRecord;
    }

}
