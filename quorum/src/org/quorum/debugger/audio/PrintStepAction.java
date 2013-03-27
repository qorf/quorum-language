/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.PrintStep;

/**
 *
 * @author Andreas Stefik
 */
class PrintStepAction extends Action{
    private PrintStep step;
    
    public PrintStepAction() {
    }

    @Override
    protected String speakForward() {
        String param = step.getParameter();
        return param + " output to the console.";
    }

    @Override
    protected String speakBackward() {
        String param = step.getParameter();
        return param + " removed from the console.";
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (PrintStep) step;
    }

}
