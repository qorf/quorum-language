/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import org.quorum.plugins.RuntimeError;
import org.quorum.execution.DataEnvironment;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.Result;

/**
 * When execute() is called this step copies the value in a specified register into
 * a specfied variable.
 * 
 * @author Andreas Stefik
 */
public abstract class AssignmentStep extends IntermediateStep {

    /**
     * The variable that is going to be assigned.
     */
    protected VariableParameterCommonDescriptor variable;
    
    /**
     * The register that the value is being pulled from.
     */
    protected int register;
    
    /**
     * optional variable contained in an object "variable".
     */
    protected String subVariableName;
    
    /**
     * The parent which contains the variable being assigned a value. optional 
     * variable, only initialized if assigning to a parent variable.
     */
    private ClassDescriptor parent;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue value = de.getRegister(register);

        ExpressionValue newValue = new ExpressionValue(value);
        Result res = calculateOpcode(value);
        if (!res.noConvert) {
            newValue.setType(res.getType());
            newValue.setResult(res);
        }


        if (subVariableName.equals("")) {//if this is not an assignment to a parent vairable
            if (parent != null && parent.equals(de.getThisObject().getClazz())) {//if this is an assignment to a "me" variable.
                
                String objName = variable.getStaticKey();
                ExpressionValue eVal = new ExpressionValue();

                //get the variable from the object
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(objName);
                if (variableValue != null) {
                    RuntimeObject ro = vm.getDataEnvironment().getThisObject();
                    if (ro != null) {
                        eVal = ro.getVariable(variable.getName());
                        if (eVal != null) {//set the "me" variable to the assigned value
                            newValue.setName(variable.getName());
                            ro.addVariable(variable.getName(), newValue);
                        }
                    }
                }

                if (eVal == null) {//if the variable was not found throw an exception.
                    this.isInErrorState = true;
                    RuntimeError e = new RuntimeError("Invalid Assignment Exception", ErrorTypeDescriptor.getErrorType(), vm);
                    this.setRuntimeError(e);
                }
            } else {//if this is a basic assignment to a variable within the current scope.
                newValue.setName(variable.getName());
                de.setVariableValue(variable.getStaticKey(), newValue);
            }
        } else {//if this is an assignment to a parent variable.
            String objName = variable.getStaticKey();
            ExpressionValue eVal = new ExpressionValue();

            //get the variable from the object
            ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(objName);
            if (variableValue != null) {
                
                //assign the value to the parent variable.
                int objHash = variableValue.getObjectHash();
                RuntimeObject ro = vm.getDataEnvironment().getObject(objHash);
                if (ro != null) {
                    if (parent != null) {
                        eVal = ro.getVariableFromParent(parent.getStaticKey(), subVariableName);
                    } else {
                        eVal = ro.getVariable(subVariableName);
                    }

                    if (eVal != null && parent == null) {
                        newValue.setName(subVariableName);
                        ro.addVariable(subVariableName, newValue);
                    } else if (eVal != null) {
                        newValue.setName(subVariableName);
                        ro.addVariableToParent(parent.getStaticKey(), subVariableName, newValue);
                    }
                }
            }

            if (eVal == null) {
                this.isInErrorState = true;
                RuntimeError e = new RuntimeError("Invalid Assignment Exception", ErrorTypeDescriptor.getErrorType(), vm);
                this.setRuntimeError(e);
            }
        }
    }

    @Override
    public void unexecute() {
        if (subVariableName.equals("")) {
            DataEnvironment de = vm.getDataEnvironment();
            if (parent != null && parent.equals(de.getThisObject().getClazz())) {
                
                //get the variable from the object
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(variable.getStaticKey());
                if (variableValue != null) {
                    RuntimeObject ro = vm.getDataEnvironment().getThisObject();
                    if (ro != null) {
                        ro.undoAddVariable(variable.getName());
                    }
                }
                
            }else{
                de.popVariableValue(variable.getStaticKey());
            }
        } else {
            if (!this.isInErrorState) {
                String objName = variable.getStaticKey();

                //get the variable from the object
                ExpressionValue variableValue = vm.getDataEnvironment().getVariableValue(objName);

                if (variableValue != null) {
                    int objHash = variableValue.getObjectHash();
                    RuntimeObject ro = vm.getDataEnvironment().getObject(objHash);
                    if (ro != null) {
                        if (parent != null) {
                            ro.undoAddVariableToParent(parent.getStaticKey(), subVariableName);
                        } else {
                            ro.undoAddVariable(subVariableName);
                        }
                    }
                }
            }
        }
    }

    protected abstract Result calculateOpcode(ExpressionValue result);

    /**
     * @return the variable to copy the value in the register to.
     */
    public VariableParameterCommonDescriptor getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to copy the value in the register to.
     */
    public void setVariable(VariableParameterCommonDescriptor variable) {
        this.variable = variable;
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

    public ExpressionValue getValue() {
        return vm.getDataEnvironment().getRegister(register);
    }

    public ExpressionValue getVariableValue() {
        DataEnvironment de = vm.getDataEnvironment();
        return de.getVariableValue(variable.getStaticKey());
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.ASSIGNMENT_STEP.getName();
    }

    public void setSubVariable(String variableInObjectName) {
        subVariableName = variableInObjectName;
    }

    public String getSubVariableName() {
        return subVariableName;
    }
    
    /**
     * @return the parent
     */
    public ClassDescriptor getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(ClassDescriptor parent) {
        this.parent = parent;
    }
}
