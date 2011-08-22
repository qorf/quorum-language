/*
 * Execution.java
 *
 * Created on March 21, 2007, 6:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.*;
import org.quorum.steps.DetectInfo;

/**
 * This class represents a particular execution of source code. It takes as input
 * a set of execution steps. Each step can be undone, redone, knows where to 
 * go next in the execution, and where to go back to in the execution. The execution
 * can be "observed" by any class that registers events on the execution. Execution 
 * may also be halted, continued, stepped, and is essentially a flexible
 * environment for executing source code.
 *
 * @author Andreas Stefik
 */
public class Execution {    
    /** This variable represents the previous set of actions that the virtual
     * machine has completed. The size of this stack is equivalent
     * to the value from getTimeStamp().
     */
    private Stack<Integer> executionSequence;
    
    /**
     * The current position in the execution. Zero is, for example, the beginning of
     * the execution sequence.
     */
    private int executionPosition;
    
    /**
     * Keeps a list of all steps in the execution sequence. Steps know, themselves, 
     * where to go to next in the sequence.
     */
    private Vector<ExecutionStep> steps;


   

    
    /** Creates a new instance of Execution */
    public Execution() {
        steps = new Vector<ExecutionStep>();
        executionSequence = new Stack<Integer>();
        executionPosition = -1;
        
    }
    
    /**
     * This method adds a new ExecutionStep into the sequence.
     * @param step The new step.
     */
    public void addStep(ExecutionStep step) {
        steps.add(step);
        
        if(getExecutionPosition() == -1) {
            executionPosition = 0;
        }
    }
    
    /**
     * Adds a set of steps to this particular Execution.
     * @param moreSteps A collection of steps.
     */
    public void addStep(Vector<ExecutionStep> moreSteps) {
        steps.addAll(moreSteps);
        
        if(getExecutionPosition() == -1) {
            executionPosition = 0;
        }
    }

    /** This method allows the user to directly set the compiled set of
     * steps into this execution.
     * @param moreSteps
     */
    public void setSteps(Vector<ExecutionStep> steps) {
        this.steps = steps;

        if(getExecutionPosition() == -1) {
            executionPosition = 0;
        }
    }

    /**
     * This method removes a step from the execution sequence.
     * @param step a step to remove
     * @return Whether or not a step was removed
     */
    public boolean removeStep(ExecutionStep step) {
        return steps.remove(step);
    }


    /**
     * This method causes the entire system to rollback to a particular
     * point in time. The point to which it rolls back is a machine time stamp
     * (an op-code number), not a date and time. Date and time rollback
     * might be added at a later date. This function is guaranteed to halt.
     * Worst case, this function will rollback to the beginning of the
     * program.
     *
     * @param stamp
     */
    public void rollback(int stamp) {
        //if the stamp is invalid, make it zero
        if(stamp < 0) {
            stamp = 0;
        }
        //if the stamp is in the future (or now), ignore it
        if(stamp >= getTimeStamp()) {
            return;
        }


        while(stamp < getTimeStamp() && !isExecutionAtBeginning()) {//rollback
            unstep();
        }
    }


    /**
     * This function rolls the execution forward to a particular point
     * in time. The point to which it rolls back is a machine time stamp
     * (an op-code number), not a date and time. Date and time rollback
     * might be added at a later date. This function is guaranteed to
     * halt. In cases like this, this is similar to never reaching a breakpoint.
     *
     * @param stamp
     */
    public void rollForward(int stamp) {
        //if the stamp is less than 0, return
        if(stamp < 0) {
            return;
        }
        //if the stamp is in the past, ignore it
        if(stamp < getTimeStamp()) {
            return;
        }

        //rollforward until we hit the stamp
        while(stamp >= getTimeStamp() && !isExecutionFinished()) {//execute
            step();
        }
    }
    
    /**
     * This operation asks the current ExecutionStep object to conduct a step and
     * increments its pointer to the next ExecutionStep.
     */
    public ExecutionStep step() {
        if(!isExecutionFinished()) {
            //first get the current step
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            //execute the current step
            step.execute();

            this.executionSequence.push(getExecutionPosition());
            int newPosition = step.nextStep();
            if(isValidExecutionStep(newPosition)) {
                executionPosition = newPosition;
            }
            else {
                executionPosition = Integer.MAX_VALUE;
            }
            return step;
        }
        else {
            return null;
        }
    }
    
    /**
     * Gets the current line number that this ExecutionStep is relative too.
     * @return The line number.
     */
    public int getLineNumber() {
        int defaultValue = 1;
        if(isExecutionAtBeginning()) {
            if(this.steps.isEmpty()) {
               return defaultValue; 
            }
            else {
                return steps.elementAt(0).getBeginLine();
            }
        }
        else if(this.isExecutionFinished()) {
            if(this.steps.isEmpty()) {
                return defaultValue;
            }
            else {
                return steps.elementAt(executionSequence.peek()).getBeginLine();
            }
        }
        else {
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            return step.getBeginLine();
        }
    }
    
    /**
     * Gets the current line number that this ExecutionStep is relative too.
     * @return The line number.
     */
    public int getColumnNumber() {
        int defaultValue = 1;
        if(isExecutionAtBeginning()) {
            if(this.steps.isEmpty()) {
               return defaultValue; 
            }
            else {
                return steps.elementAt(0).getBeginColumn();
            }
        }
        else if(this.isExecutionFinished()) {
            if(this.steps.isEmpty()) {
                return defaultValue;
            }
            else {
                return steps.elementAt(executionSequence.peek()).getBeginColumn();
            }
        }
        else {
            ExecutionStep step = steps.elementAt(executionSequence.peek());
            return step.getBeginColumn();
        }
    }
    
    /**
     * Gets the current line number that this ExecutionStep is relative too.
     * @return The line number.
     */
    public int getPreviousLineNumber() {
        if(!isExecutionAtBeginning()) {
            //first peek at the previous entry, if there is one
            ExecutionStep step = steps.elementAt(executionSequence.peek());
            return step.getBeginLine();
        }
        else {
            return 1; //return a default value instead of throwing an error
        } 
    }
    
    public boolean isValidExecutionStep() {
        return isValidExecutionStep(executionPosition);
    }
    
    private boolean isValidExecutionStep(int position) {
        if(position >= 0 && position < steps.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    /** This returns a numeric code indicating the time at which this virtual
     * machine is executing. For example, if 5 execution steps have been
     * executed, then the timestamp would return 5.
     * @return
     */
    public int getTimeStamp() {
        return executionSequence.size();
    }
    
    /**
     * This operation literally undoes the operation done by Step(), essentially
     * making the entire execution sequence undoable.
     */
    public ExecutionStep unstep() {
        if(!isExecutionAtBeginning()) {
            //first get the current step
            executionPosition = executionSequence.pop();
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            step.unexecute();
            return step;
        }
        else {
            return null;
        }
    }
    
    /**
     * Determines whether the current execution sequence is entirely finished.
     * @return true if the program has finished
     */
    public boolean isExecutionFinished() {
        if(getExecutionPosition() >= steps.size()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Restarts the current program from the beginning.
     */
    public void restartExecution() {
        this.executionPosition = 0;
        this.executionSequence.empty();
    }
    
    /**
     * Removes all steps from the execution sequence.
     */
    public void clear() {
        steps.clear();
        executionSequence.clear();
    }
    
    /**
     * Determines whether the program is at the beginning of its execution
     * @return true if at the beginning of the program
     */
    public boolean isExecutionAtBeginning() {
        return this.executionSequence.empty();
    }
    
    /** Sets this execution to an invalid step. Used when the user compiles
     * multiple times, sometimes successful, sometimes not.
     */
    public void setInvalidExecutionStep() {
        this.executionPosition = -1;
    }

    /** This method returns the execution position of the virtual machine.
     * In a modern architecture, this is approximately equivalent to the
     * instruction pointer.
     * @return the executionPosition
     */
    public int getExecutionPosition() {
        return executionPosition;
    }

    /**
     * Returns the step at the current execution position.
     * 
     * @return
     */
    public ExecutionStep getCurrentStep() {
        if(isValidExecutionStep()) {
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            return step;
        }
        else {
            return null;
        }
    }

    /**
     * Returns the most recent step that was executed on the system.
     * 
     * @return
     */
    public ExecutionStep getPreviousStep() {
        if(!this.isExecutionAtBeginning()) {
            ExecutionStep step = steps.elementAt(this.executionSequence.peek());
            return step;
        }
        else {
            return null;
        }
    }

    /**
     * Returns the next step that can be executed on the system.
     * 
     * @return
     */
    public ExecutionStep getNextStep() {
        if(!this.isExecutionFinished()) {
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            return step;
        }
        else {
            return null;
        }
    }

    /**
     * Returns the file key for either the currently executing item or, if
     * at the end, the most recently executed item.
     * 
     * @return
     */
    public String getCurrentFileBeingExecuted() {
        String defaultValue = null;
        if(isExecutionAtBeginning()) {
            if(this.steps.isEmpty()) {
               return defaultValue;
            }
            else {
                return steps.elementAt(0).getFileKey();
            }
        }
        else if(this.isExecutionFinished()) {
            if(this.steps.isEmpty()) {
                return defaultValue;
            }
            else {
                return steps.elementAt(executionSequence.peek()).getFileKey();
            }
        }
        else {
            ExecutionStep step = steps.elementAt(getExecutionPosition());
            return step.getFileKey();
        }
    }

    /**
     * Jump the execution to a detect statement. This method will update the
     * execution position and is to be used only for exceptions.
     *
     * @param landingPad
     * @return If the error state has been resolved and the execution is
     * no longer in an error state return false.
     */
    public boolean jumpToLandingPad(DetectInfo landingPad) {
        if(landingPad != null){
            
            this.executionSequence.push(getExecutionPosition());
            int nextPosition = landingPad.getGlobalLocation();
            if(this.isValidExecutionStep(nextPosition)){
                executionPosition = nextPosition;
            }else{
                executionPosition = Integer.MAX_VALUE;
            }

            //step();
            return false;
        }
        return true;
    }

    /**
     * This sets the program counter/execution position. Only use this method
     * if you REALLY, REALLY, REALLY, REALLY know what you are doing.
     * 
     * @param startPosition
     */
    public void setExecutionPosition(int startPosition) {
        this.executionPosition = startPosition;
    }

}