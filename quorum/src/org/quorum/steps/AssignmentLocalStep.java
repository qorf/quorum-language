/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;
import org.quorum.symbols.VariableDescriptor;

/**
 * This op-code puts the specified variable's current value, from a register,
 * into the "local" scope of the item. This op-code is predominately used
 * to force the virtual machine to not propogate values up to a parent scope.
 * 
 * @author Andreas Stefik
 */
public abstract class AssignmentLocalStep extends AssignmentStep {

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue value = de.getRegister(register);

        ExpressionValue newValue = new ExpressionValue(value);
        Result res = calculateOpcode(value);
        if(!res.noConvert) {
            newValue.setType(res.getType());
            newValue.setResult(res);
        }
        
        newValue.setName(variable.getName());


        de.setVariableValueLocalScopeOnly(variable.getStaticKey(), newValue);
    }

    protected abstract Result calculateOpcode(ExpressionValue result);

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.popVariableValueLocalScopeOnly(variable.getStaticKey());
    }
    
    @Override 
    public VariableDescriptor getVariable() {
        if (variable instanceof VariableDescriptor)
            return (VariableDescriptor) variable;
        else
            return null;
    }
    
}
