/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.AssignmentStep;
import org.quorum.execution.ExpressionValue;

/**
 * This action determines appropriate speech for an AssignmentStep object.
 * 
 * @author Andreas Stefik
 */
public class AssignmentAction extends Action{
    private AssignmentStep step;

    @Override
    protected String speakForward() {
        String name = step.getVariable().getName();
        name = nameMangler(name);
        String value = step.getValue().toString();
        if(!value.isEmpty() && value.charAt(0) == '-') {
            value = "minus " + value.substring(1);
        }
        return name + " to " + value;
    }

    @Override
    protected String speakBackward() {
        String name = step.getVariable().getName();
        name = nameMangler(name);
        ExpressionValue val = step.getVariableValue();
        if(val == null) {
            return name + " restored to undefined";
        }
        else {
            return name + " restored to " + val.toString();
        }
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (AssignmentStep) step;
    }

    //Should this be given its own class, with lots of methods?
    private String nameMangler(String string) {
        if(string.compareToIgnoreCase("a") == 0) {
            return "eh";
        }
        else {
            return string;
        }
    }



}
