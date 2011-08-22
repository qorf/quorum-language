/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.event;

import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.VirtualMachineEvent;
import org.quorum.execution.ExecutionStep;

/**
 * An error thrown to the user interface when needing to inform
 * it that an error has occurred and that we cannot continue.
 * @author Andreas Stefik
 */
public class VirtualMachineStopEvent extends VirtualMachineEvent{
    private boolean isProgramAtEnd;

    public VirtualMachineStopEvent() {
    }

    public VirtualMachineStopEvent(ExecutionStep step, 
            AbstractVirtualMachine vm, boolean executeEvent, boolean atEnd) {
        super(null, vm, executeEvent);
        isProgramAtEnd = atEnd;
    }

    @Override
    public boolean isDebuggerStopEvent() {
        return true;
    }

    @Override
    public boolean isNaturalTerminationEvent() {
        return true;
    }
}
