/*
 * VirtualMachineListener.java
 *
 * Created on March 26, 2007, 4:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 * This class represents a generic listening interface listeners must implement
 * in order to be sent messages regarding the current behavior of a particular
 * virtual machine. Good examples of classes that would be listeners are
 * a printing listener, which prints the execution flow of a program, a logging
 * listener, and a sonified debugger, which plays sounds depending upon
 * what type of event is passed to the listener.
 * @author Andreas Stefik
 */
public interface VirtualMachineListener {

    /**
     * Indicates that the AbstractVirtualMachine object fired an event. 
     * Common examples of events are assigning a value to a variable, incrementing a 
     * variable, conditional statements executing, and others.
     * @param event The event fired by the virtual machine.
     */
    public void actionPerformed(VirtualMachineEvent event);
}
