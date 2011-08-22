/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;


/**
 * This step will jump to another location in the execution if the expression in
 * the left register is false.
 * 
 * @author Andreas Stefik
 */
public class ConditionalJumpLoopStep extends JumpBaseStep{
    protected int leftRegister = -1;
    protected boolean jump;
    protected long iterationNum = 0;
    private boolean isEndValueKnown = false;
    private int howManyTimesRegister = -1;
    
    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(leftRegister);
        jump = value.getResult().boolean_value;
        iterationNum++;
    }

    @Override
    public void unexecute() {
        iterationNum--;
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(leftRegister);
        jump = value.getResult().boolean_value;
    }

    public boolean isLoopFinished() {
        return !jump;
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

    public long getIterationNumber() {
        return iterationNum;
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
    @Override
    public String toString() {
        return "[" + jump + "]jump to: " + jumpLocation;
    }
   
    @Override
    public String getStaticKey() {
        return IntermediateConstants.CONDITIONAL_JUMP_STEP.getName();
    }

    /**
     * This value returns whether or not this loop can detect how many
     * this loop will ultimately execute before terminating. For most loops,
     * this is not possible. For some, however (e.g., repeat 10 times), it is.
     * 
     * @return the isEndValueKnown
     */
    public boolean isEndValueKnown() {
        return isEndValueKnown;
    }

    /**
     *
     * This value sets whether or not this loop can detect how many
     * this loop will ultimately execute before terminating. For most loops,
     * this is not possible. For some, however (e.g., repeat 10 times), it is.
     *
     * @param isEndValueKnown the isEndValueKnown to set
     */
    public void setIsEndValueKnown(boolean isEndValueKnown) {
        this.isEndValueKnown = isEndValueKnown;
    }

    /**
     * @return the howManyTimesRegister
     */
    public int getHowManyTimesRegister() {
        return howManyTimesRegister;
    }

    /**
     * @param howManyTimesRegister the howManyTimesRegister to set
     */
    public void setHowManyTimesRegister(int howManyTimesRegister) {
        this.howManyTimesRegister = howManyTimesRegister;
    }

    /**
     * Returns the total number of times this loop will ultimately execute.
     * 
     * @return
     */
    public ExpressionValue getTotalNumberOfTimes() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(howManyTimesRegister);
        return value;
    }

}
