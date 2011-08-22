/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.VariableDescriptor;

/**
 * This class pushes the value of a register or variable onto an ActivationRecord
 * @author Aaron Willows
 */
public class PushStep extends IntermediateStep{

    private boolean pushRegister;
    private VariableDescriptor variable;
    private int register;

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unexecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
