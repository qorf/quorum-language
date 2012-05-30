/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 *
 * @author Melissa Stefik
 */
public class VariableInObjectMoveStep extends IntermediateStep{

    private int temp;
    private VariableParameterCommonDescriptor obj;
    private ClassDescriptor parent;
    private String variableName;
    private TypeDescriptor variableType;
    @Override
    public void execute() {
        String objName = getObj().getStaticKey();
        ExpressionValue eVal = new ExpressionValue();

        //get the variable from the object
        ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(objName);

        if(variableValue != null){
            int objHash = variableValue.getObjectHash();
            RuntimeObject ro = vm.getDataEnvironment().getObject(objHash);
            if(ro != null && parent == null)
                eVal = ro.getVariable(variableName);
            else if(ro != null)
                eVal = ro.getVariableFromParent(parent.getStaticKey(), variableName);
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

    public TypeDescriptor getVariableType() {
        return variableType;
    }
    
    public void setVariableType(TypeDescriptor type) {
        variableType = new TypeDescriptor();
        variableType.setName(type.getName());
    }
    
    /**
     * @param obj the obj to set
     */
    public void setObj(VariableParameterCommonDescriptor obj) {
        this.obj = obj;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }

    public void setParent(ClassDescriptor parent) {
        this.parent = parent;
    }
    
    public ClassDescriptor getParent(){
        return this.parent;
    }
}
