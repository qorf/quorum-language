/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.ConditionalJumpCheckStep;

/**
 *
 * @author Melissa Stefik
 */
public class ConditionalJumpDetectAction extends Action {
    private ConditionalJumpCheckStep step;

    @Override
    protected String speakForward() {
        boolean result = step.isTrue();
        if(result) {
            return "detect" + " true";
        }
        else {
            return "detect" + " false";
        }
    }

    @Override
    protected String speakBackward() {
        boolean result = step.isTrue();
        if(result) {
            return "detect" + "true" + " restored";
        }
        else {
            return "detect" + "false" + " restored";
        }
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (ConditionalJumpCheckStep) step;
    }
}
