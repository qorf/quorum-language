/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.event;

import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.ExceptionManager;
import org.quorum.vm.interfaces.VirtualMachineEvent;
import org.quorum.execution.ExecutionStep;

/**
 *
 * @author Andreas Stefik
 */
public class VirtualMachineErrorState extends VirtualMachineEvent{

    private ExceptionManager errorManager;

    public VirtualMachineErrorState(ExecutionStep step, 
            AbstractVirtualMachine vm, boolean executeEvent, ExceptionManager manager) {
        super(step, vm, executeEvent);
        errorManager = manager;
    }

    @Override
    public boolean isErrorStateEvent(){
        return false;
    }

    public ExceptionManager getErrorManager(){
        return errorManager;
    }
}
