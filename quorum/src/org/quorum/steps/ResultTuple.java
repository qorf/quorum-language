/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Morgoth
 */
public class ResultTuple {
    private ExpressionValue value;
    private ExecutionStep step;
    private int nextRegister;

    /**
     * @return the value
     */
    public ExpressionValue getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ExpressionValue value) {
        this.value = value;
    }

    /**
     * @return the step
     */
    public ExecutionStep getStep() {
        return step;
    }

    /**
     * @param step the step to set
     */
    public void setStep(ExecutionStep step) {
        this.step = step;
    }

    /**
     * @return the nextRegister
     */
    public int getNextRegister() {
        return nextRegister;
    }

    /**
     * @param nextRegister the nextRegister to set
     */
    public void setNextRegister(int nextRegister) {
        this.nextRegister = nextRegister;
    }
}
