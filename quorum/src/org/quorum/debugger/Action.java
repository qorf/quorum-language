/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import org.quorum.execution.ExecutionStep;

/**
 *
 * @author Andreas Stefik and nsamsan
 */
public abstract class Action {
    private boolean forward;
    
    /** Returns a string representation of the
     * state of the current virtual machine and the step that was
     * conducted inside of it.
     * @return
     */
    public String speak() {
        if(forward) {
            return speakForward();
        }
        else {
            return speakBackward();
        }
    }

    protected abstract String speakForward();

    protected abstract String speakBackward();

    /**
     * @param step the step to set
     */
    public abstract void setStep(ExecutionStep step);

    /**
     * @return the forward
     */
    public boolean isForward() {
        return forward;
    }

    /**
     * @param forward the forward to set
     */
    public void setForward(boolean forward) {
        this.forward = forward;
    }
}
