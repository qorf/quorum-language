/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.ConditionalJumpIfStep;

/**
 *
 * @author nsamsan
 */
public class ConditionalJumpIfAction extends Action {
    private ConditionalJumpIfStep step;

    @Override
    protected String speakForward() {
        boolean result = step.isTrue();
        if(result) {
            return "if" + " true";
        }
        else {
            return "if" + " false";
        }
    }

    @Override
    protected String speakBackward() {
        boolean result = step.isTrue();
        if(result) {
            return "if" + "true" + " restored";
        }
        else {
            return "if" + "false" + " restored";
        }
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (ConditionalJumpIfStep) step;
    }

}
