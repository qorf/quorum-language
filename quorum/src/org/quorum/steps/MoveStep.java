/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Andreas Stefik
 */
public class MoveStep extends IntermediateStep {

    /** This number represents the value of a temporary variable in the 
     * symbol table. This value is conceptually similar to a "register."
     */
    private int temp;

    private ExpressionValue value;
    
    @Override
    public void execute() {
        vm.getDataEnvironment().setRegister(temp, value);
    }

    @Override
    public void unexecute() {
        vm.getDataEnvironment().popRegister(temp);
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(int temp) {
        this.temp = temp;
    }
    
    /**
     * @return the value
     */
    public ExpressionValue getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ExpressionValue value) {
        this.value = value;
    }
    
    public String getStaticKey() {
        return IntermediateConstants.MOVE_STEP.getName();
    }
}
