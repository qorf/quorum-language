/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 *
 * @author astefik
 */
public interface VirtualMachineAnnotator {
    /**
     * Indicates that the AbstractVirtualMachine object fired an event.
     * Common examples of events are assigning a value to a variable, incrementing a
     * variable, conditional statements executing, and others.
     * @param event The event fired by the virtual machine.
     */
    public void actionPerformed(VirtualMachineEvent event);
}
