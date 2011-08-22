/*
 * ExecutionStep.java
 *
 * Created on March 21, 2007, 5:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.execution;

import org.quorum.vm.interfaces.LineInformation;
import org.quorum.plugins.RuntimeError;
import org.quorum.symbols.MethodDescriptor;

/**
 * This class represents one particular step in execution for the virtual machine.
 * For example, a subclass may be able to execute a++;
 *
 * @author Andreas Stefik
 */
public abstract class ExecutionStep {
    /**
     * The element number for the next step in the execution path.
     */
    private int nextStep;
    
    protected boolean isInErrorState = false;
    protected boolean wasInErrorState = false;

    protected RuntimeScope callStateBeforeException;

    protected RuntimeError runtimeError;

    /** The absolute path to the file this op-code is relative too
     * 
     */
    private String fileKey = "";
    /**
     * The element number for the previous execution step in the sequence.
     */
    private int previousStep;
    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     */
    protected int beginLine;
    protected int beginColumn;
    protected int endLine;

    protected LineInformation lineInformation;
    
    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     */
    protected int endColumn;

    private MethodDescriptor methodDescriptor;

    /** Creates a new instance of ExecutionStep */
    public ExecutionStep() {
        runtimeError = null;
        nextStep = -1;
        previousStep = -1;
    }
    
    /**
     * Does one step in the execution. If this step represents a++;, then calling 
     * undo represents calling a--;
     */
    public abstract void execute();
    
    /**
     * Does one step in the execution. If this step represents a++;, then calling 
     * this method represents calling a--;
     */
    public abstract void unexecute();
    
    /**
     * Returns the next step in the sequence.
     * @return  the next step
     */
    public int nextStep() {
        return nextStep;
    }
    
    /**
     * Returns the previous step in the sequence
     * @return the previous step
     */
    public int previousStep() {
        return previousStep;
    }
    
    /**
     * Returns whether there is another step after the current step. If there is no
     * next step, execution is finished.
     * @return Whether this is the end of the execution sequence.
     */
    public boolean hasNextStep() {
        return isValidStep(nextStep);
    }
    
    /**
     * Returns whether a step at a particular numerical position is valid.
     * @param step The step
     * @return True if the step at position "Step" is valid.
     */
    public boolean isValidStep(int step) {
        if(step != -1 && step != Integer.MAX_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Returns true if this the first step in the execution sequence.
     * @return Whether this the beginning of the execution sequence.
     */
    public boolean hasPreviousStep() {
        if(previousStep == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * This toString method returns getExecuteMessage()
     * @return Returns getExecuteMessage()
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     *  This is a convenience method for setting the line and column information
     * for a particular step.
     * 
     * @param beginLine
     * @param beginColumn
     * @param endLine
     * @param endColumn
     */
    public void setLineColumn(int beginLine, int beginColumn, int endLine, int endColumn) {
        this.beginLine = beginLine;
        this.beginColumn = beginColumn;
        this.endLine = endLine;
        this.endColumn = endColumn;
    }
    /**
     *  This is a convenience method for setting the line and column information
     * for a particular step.
     * @param location
     */
    public void setLineInformation(LineInformation location) {
        this.beginLine = location.getStartLine();
        this.beginColumn = location.getStartColumn();
        this.endLine = location.getEndLine();
        this.endColumn = location.getEndColumn();
        this.lineInformation = location;


    }

    /**
     * Returns an object representing the line and column information for this
     * object. TODO: Deprecate old line and column information in place
     * of new objects representing such information.
     *
     * @return
     */
    public LineInformation getLineInformation() {
        LineInformation info = new LineInformation();
        if(lineInformation != null){
            info = lineInformation;
        }
        
        info.setStartLine(beginLine);
        info.setStartColumn(beginColumn);
        info.setEndLine(endLine);
        info.setEndColumn(endColumn);
        
        return info;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @return An integer representing the location in the source code where this step
     * originated.
     */
    public int getBeginLine() {
        return beginLine;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @param beginLine An integer representing the location in the source code where this step
     * originated.
     */
    public void setBeginLine(int beginLine) {
        this.beginLine = beginLine;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @return An integer representing the location in the source code where this step
     * originated.
     */
    public int getBeginColumn() {
        return beginColumn;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @param beginColumn An integer representing the location in the source code where this step
     * originated.
     */
    public void setBeginColumn(int beginColumn) {
        this.beginColumn = beginColumn;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @return An integer representing the location in the source code where this step
     * originated.
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @return An integer representing the location in the source code where this step
     * originated.
     */
    public int getEndColumn() {
        return endColumn;
    }  

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @param endLine An integer representing the location in the source code where this step
     * originated.
     */
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    /**
     * All steps are generated from source code. All methods relating to line and
     * column numbers are referring to the physical, textual, location, of the original
     * tokens in the source file.
     * @param endColumn An integer representing the location in the source code where this step
     * originated.
     */
    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    /** Returns true if this execution step cannot continue, for whatever reason
     * (e.g., accessing an index that is out of bounds.)
     * @return
     */
    public boolean isInErrorState() {
        return isInErrorState;
    }
    
    /** Allows you to set, fake, or change, whether the component is in an error
     * state.
     * @param isInErrorState
     */
    public void setIsInErrorState(boolean isInErrorState) {
        this.isInErrorState = isInErrorState;
    }

    /** Returns true if this execution step cannot continue, for whatever reason
     * (e.g., accessing an index that is out of bounds.)
     * @return
     */
    public boolean wasInErrorState() {
        return wasInErrorState;
    }

    /** Allows you to track if the step was in an error state (backtrack from a
     * .
     * @param wasInErrorState
     */
    public void setWasInErrorState(boolean isInErrorState) {
        this.wasInErrorState = isInErrorState;
    }

    /**
     * Set the runtime error triggered by the step.
     * 
     * @param e
     */
    public void setRuntimeError(RuntimeError e){
        runtimeError = e;
    }

    /**
     * Get a runtime error that is triggered by the step.
     *
     * @return
     */
    public RuntimeError getRuntimeError(){
        return runtimeError;
    }
    /**
     * If this method is true, this opcode is related to popping an activation
     * record off of the stack.
     *
     * @return
     */
    public boolean isReturnStep() {
        return false;
    }

    public String getStaticKey() {
        return "";
    }

    /**
     * This method returns the absolute path to the file that this op-code
     * executes in.
     * @return the fileKey
     */
    public String getFileKey() {
        return fileKey;
    }

    /**
     * This method sets the absolute path to the file that this op-code
     * executes in.
     * @param fileKey the fileKey to set
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    /**
     * Returns the MethodDescriptor in which the current step resides.
     * 
     * @return the methodDescriptor
     */
    public MethodDescriptor getMethodDescriptor() {
        return methodDescriptor;
    }

    /**
     * @param methodDescriptor the methodDescriptor to set
     */
    public void setMethodDescriptor(MethodDescriptor descriptor) {
        this.methodDescriptor = descriptor;
    }

    /**
     * @return the callStateBeforeException
     */
    public RuntimeScope getCallStateBeforeException() {
        return callStateBeforeException;
    }

    /**
     * @param callStateBeforeException the callStateBeforeException to set
     */
    public void setCallStateBeforeException(RuntimeScope callStateBeforeException) {
        this.callStateBeforeException = callStateBeforeException;
    }
}
