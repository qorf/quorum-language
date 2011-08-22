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
public class ExecutionStartAction extends Action{

    @Override
    protected String speakForward() {
        return "";
    }

    @Override
    protected String speakBackward() {
        return "Can't back up. You are at the beginning of the execution.";
    }

    @Override
    public void setStep(ExecutionStep step) {
    }

}
