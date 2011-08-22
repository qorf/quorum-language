/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.CallStep;

/**
 *
 * @author Andreas Stefik
 */
public class CallAction  extends Action{
    private CallStep step;

    @Override
    protected String speakForward() {
        if(step.isInErrorState()) {
            return "Error: Cannot call action " + step.getMethodCallee().getStaticKey();
        }
        return "Calling action " + step.getMethodCallee().getStaticKey();
    }

    @Override
    protected String speakBackward() {
        return "Uncalling action " + step.getMethodCallee().getStaticKey();
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (CallStep) step;
    }

}
