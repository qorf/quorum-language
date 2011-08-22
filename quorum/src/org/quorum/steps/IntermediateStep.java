/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.execution.ExecutionStep;

/**
 *
 * @author Andreas Stefik
 */
public abstract class IntermediateStep extends ExecutionStep {

    protected AbstractVirtualMachine vm;
    
    public abstract void execute();

    public abstract void unexecute();

    public void setVirtualMachine(AbstractVirtualMachine vm) {
        this.vm = vm;
    }

    /** By default, the next step in this class increments the current
     * instruction pointer by one. This method is overriden for opcodes
     * that need to modify the instruction pointer (e.g., jump, call, return).
     * @return
     */
    @Override
    public int nextStep() {
        int step = vm.getExecution().getExecutionPosition();
        return step + 1;
    }

    
    @Override
    public boolean hasNextStep() {
        return true;
    }

}
