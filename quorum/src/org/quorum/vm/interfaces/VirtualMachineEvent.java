/*
 * VirtualMachineEvent.java
 *
 * Created on March 26, 2007, 4:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

//import org.sonify.gui.speech.SpeechText;
import org.quorum.execution.ExecutionStep;

/**
 * This class represents an event that occurred in a currently executing 
 * instance of an AbstractVirtualMachine object
 * @author Andreas Stefik
 */
public class VirtualMachineEvent {
    /**
     * The step in the virtual machine that actually executed.
     */
    private ExecutionStep step;
    
    /**
     * This boolean values determines whether the even thrown was an execute
     * or an unexecute event. In other words, this flag determines whether the user
     * is going forwards or backwards in the program.
     */
    private boolean executeEvent;
    
    /**
     * The virtual machine that threw the event.
     */
    private AbstractVirtualMachine virtualMachine;

    private boolean buildEvent = false;
    private boolean buildAllEvent = false;

    private boolean buildSuccessful = false;
    
    /**
     * A key that is used to represent an abstract event of some kind
     * that may be unrelated to a specific virtual machine event.
     * For example, this key might be set to indicate that the vm is
     * at either the beginning or end of the execution.
     */
    private String eventKey = "";

    public VirtualMachineEvent() {
    }

    /**
     * Creates a new instance of VirtualMachineEvent
     * @param vm The virtual machine throwing the event.
     * @param step The step
     * @param executeEvent Whether or not the program is going forwards or backwards.
     */
    public VirtualMachineEvent(ExecutionStep step, AbstractVirtualMachine vm, boolean executeEvent) {
        this.step = step;
        this.executeEvent = executeEvent;
        virtualMachine = vm;
    }

    /**
     * This is the step that was executed on the virtual machine.
     * @return The step
     */
    public ExecutionStep getStep() {
        return step;
    }

    /**
     * This boolean values determines whether the even thrown was an execute
     * or an unexecute event. In other words, this flag determines whether the user
     * is going forwards or backwards in the program.
     * @return True if the virtual machine is executing in the forward direction (Not
     * reversing the program).
     */
    public boolean isExecuteEvent() {
        return executeEvent;
    }

    /**
     * This method returns the source of the event, an AbstractVirtualMachine
     * currently executing a program.
     * @return The Virtual Machine throwing the event.
     */
    public AbstractVirtualMachine getVirtualMachine() {
        return virtualMachine;
    }
    
    /** 
     * If this function returns true, it indicates the virtual machine
     * should immediately stop what it is doing and not allow the program
     * to continue executing.
     * @return
     */
    public boolean isVirtualMachineStopError() {
        return false;
    }

    /**
     * @return the buildEvent
     */
    public boolean isBuildEvent() {
        return buildEvent;
    }

    /**
     * @param buildEvent the buildEvent to set
     */
    public void setBuildEvent(boolean buildEvent) {
        this.buildEvent = buildEvent;
    }

    /**
     *
     * @return the buldAllEvent
     */
    public boolean isBuildAllEvent(){
        return buildAllEvent;
    }

    /**
     *
     * @param buildAllEvent the buildAllEvent to set
     */
    public void setBuildAllEvent(boolean buildAllEvent){
        this.buildAllEvent = buildAllEvent;
    }

    /**
     * Returns the absolute path of the file being worked with.
     * @return
     */
    public String getFile() {
        return getStep().getFileKey();
    }

    /**
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * An event indicating that the debugger has stopped.
     * 
     * @return
     */
    public boolean isDebuggerStopEvent() {
        return false;
    }

    public boolean isNaturalTerminationEvent() {
        return false;
    }

    public boolean isErrorStateEvent(){
        return false;
    }

    /**
     * @return the buildSuccessful
     */
    public boolean isBuildSuccessful() {
        return buildSuccessful;
    }

    /**
     * @param buildSuccessful the buildSuccessful to set
     */
    public void setBuildSuccessful(boolean buildSuccessful) {
        this.buildSuccessful = buildSuccessful;
    }
}
