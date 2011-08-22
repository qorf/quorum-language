/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;
import org.quorum.steps.ConditionalJumpLoopStep;

/**
 * Returns a string representation of a step executing forward or in reverse.
 * 
 * @author Andreas Stefik and Neelima Samsani
 */
public class ConditionalJumpLoopAction extends Action{

    private ConditionalJumpLoopStep jStep;

    @Override
    protected String speakForward() {
        long incCounter = jStep.getIterationNumber();
        if(!jStep.isLoopFinished()) {
            if(jStep.isEndValueKnown()) {
                ExpressionValue value = jStep.getTotalNumberOfTimes();
                return "repeat " + incCounter + " of " + value.toString();
            }
            else {
                return "repeat " + incCounter;
            }
        }
        else {
            return "repeat finished";
        }
    }

    @Override
    protected String speakBackward() {
        long decCounter = jStep.getIterationNumber();
        if(!jStep.isLoopFinished()) {
            return "repeat " + (decCounter + 1) + " restored";
        }
        else {
            return "repeat finished" + " restored";
        }
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.jStep = (ConditionalJumpLoopStep) step;
    }


}
