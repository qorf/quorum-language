/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.ReturnStep;

/**
 *
 * @author Andreas Stefik
 */
public class ReturnAction extends Action{
    private ReturnStep step;

    @Override
    protected String speakForward() {
        return "Returning from action " + step.getMethod().getStaticKey();
    }

    @Override
    protected String speakBackward() {
        return "Jump back to action " + step.getMethod().getStaticKey();
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (ReturnStep) step;
    }

}
