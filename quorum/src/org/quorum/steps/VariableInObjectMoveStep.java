/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 *
 * @author Melissa Stefik
 */
class VariableInObjectMoveStep extends IntermediateStep{

    private int temp;
    private VariableParameterCommonDescriptor obj;
    private String variableName;
    @Override
    public void execute() {
        String objName = getObj().getStaticKey();
        ExpressionValue eVal = new ExpressionValue();

        //get the variable from the object
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(objName);

        if(variableValue != null){
            int objHash = variableValue.getObjectHash();
            RuntimeObject ro = vm.getDataEnvironment().getObject(objHash);
            if(ro != null)
                eVal = ro.getVariable(variableName);
        }

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
     * @return the variableName
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * @param variableName the variableName to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * @return the obj
     */
    public VariableParameterCommonDescriptor getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(VariableParameterCommonDescriptor obj) {
        this.obj = obj;
    }

}
