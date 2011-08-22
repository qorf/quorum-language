/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;

/**
 *
 * @author stefika
 */
public class ExecutionEndAction extends Action{

    @Override
    protected String speakForward() {
        return "Can't step forward. You are at the end of the execution.";
    }

    @Override
    protected String speakBackward() {
        return "Can't step forward. You are at the end of the execution.";
    }

    @Override
    public void setStep(ExecutionStep step) {
    }
}
