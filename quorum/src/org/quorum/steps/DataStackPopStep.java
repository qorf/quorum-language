/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.DataObject;

/**
 * When executed this step pops a value off the dataStack and puts it in to a register
 * @author Aaron Willows
 */
public class DataStackPopStep  extends IntermediateStep {

    private int register;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        DataObject obj = de.dataStackPop();
        de.setRegister(getRegister(), obj.getCurrentValue());

    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.dataStackUndo();
        de.popRegister(getRegister());
    }

    /**
     * @return the register
     */
    public int getRegister() {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(int register) {
        this.register = register;
    }

}
