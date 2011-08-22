/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * When executed this stepInto copies an ExpressionValue from a variable of 
 * the current object into a register
 *
 * @author Melissa Stefik
 */
public class MeVariableMoveStep extends IntermediateStep{

    /**
     * This number represents the value of a temporary variable in the
     * symbol table. This value is conceptually similar to a "register."
     */
    private int temp;
    private int index = -1;
    private VariableParameterCommonDescriptor value;

    @Override
    public void execute() {
        String queryName = value.getStaticKey();
        ExpressionValue eVal;

        //get the variable from the this object
        RuntimeObject ro = vm.getDataEnvironment().getThisObject();
        eVal = ro.getVariable(queryName);

        if (eVal != null) {
            ExpressionValue newValue = new ExpressionValue(eVal);
            vm.getDataEnvironment().setRegister(temp, newValue);
        } else {
            this.isInErrorState = true;
        }
    }

    @Override
    public void unexecute() {
        if (!this.isInErrorState) {
            vm.getDataEnvironment().popRegister(temp);
        }
    }

    /**
     *
     * @return the value
     */
    public VariableParameterCommonDescriptor getValue(){
        return value;
    }

    /**
     * Set the variable value
     * @param vd
     */
    public void setValue(VariableParameterCommonDescriptor vd) {
        value = vd;
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }
    
    /**
     * Set the temp register
     * @param resultRegister
     */
    public void setTemp(int resultRegister) {
        temp = resultRegister;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * set the register
     * @param register
     */
    public void setIndex(int register) {
        index = register;
    }
}
