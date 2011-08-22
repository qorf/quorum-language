/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;

/**
 * This step will jump to another location for exception handling.
 *
 * @author Melissa Stefik
 */
public class ConditionalJumpCheckStep extends JumpBaseStep{

    private int leftRegister = -1;
    private boolean jump;
    
    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(leftRegister);
        boolean res = value.getResult().boolean_value;
        jump = res;
    }

    @Override
    public void unexecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int nextStep() {
        if(jump) {
            return vm.getExecution().getExecutionPosition() + 1;
        }
        else {
            return vm.getExecution().getExecutionPosition() + jumpLocation;
        }
    }

    /**
     * @return the leftRegister
     */
    public int getLeftRegister() {
        return leftRegister;
    }

    /**
     * @param leftRegister the leftRegister to set
     */
    public void setLeftRegister(int leftRegister) {
        this.leftRegister = leftRegister;
    }

    /**
     * Determines whether or not the catch statement would evaluate to true or
     * false.
     *
     * @return
     */
    public boolean isTrue() {
        return jump;
    }

    @Override
    public String toString() {
        return "[" + !jump + "]jump to: " + jumpLocation;
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.CONDITIONAL_DETECT_JUMP_STEP.getName();
    }

}
