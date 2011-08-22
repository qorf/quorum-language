/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.MoveStep;

/**
 *
 * @author nsamsan
 */
public class MoveAction extends Action{
    private MoveStep step;
    @Override
    public String speak() {
        return ""; // do not say anything when moving to a register
    }

    @Override
    protected String speakForward() {
        return "";
    }

    @Override
    protected String speakBackward() {
        return "";
    }
    
    @Override
    public void setStep(ExecutionStep step) {
        this.step = (MoveStep) step;
    }
}
