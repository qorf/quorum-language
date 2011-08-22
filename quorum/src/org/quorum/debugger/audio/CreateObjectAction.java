/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.execution.ExecutionStep;
import org.quorum.steps.CreateObjectStep;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 *  Outputs auditory cues when objects are created or deleted.
 * 
 * @author Andreas Stefik
 */
public class CreateObjectAction extends Action{
    private CreateObjectStep step;

    @Override
    protected String speakForward() {
        VariableParameterCommonDescriptor variable = step.getVariable();
        return "Creating object " + variable.getName();
    }

    @Override
    protected String speakBackward() {
        VariableParameterCommonDescriptor variable = step.getVariable();
        return "Deleting object " + variable.getName();
    }

    @Override
    public void setStep(ExecutionStep step) {
        this.step = (CreateObjectStep) step;
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
