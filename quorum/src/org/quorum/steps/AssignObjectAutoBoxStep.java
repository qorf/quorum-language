/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Assignment opcode for Objects assigned to primitives.
 * 
 * @author Melissa Stefik
 */
public class AssignObjectAutoBoxStep extends AssignmentStep implements AutoBoxAssignmentStep{

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue var = de.getObject(result.getObjectHash()).getVariable("value");
        Result res = new Result();
        res = var.getResult();
        return res;
    }

    public ExpressionValue getPrimitiveValue() {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue object = vm.getDataEnvironment().getRegister(register);
        ExpressionValue primitive = de.getObject(object.getObjectHash()).getVariable("value");
        return primitive;
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.ASSIGNMENT_AUTOBOX_STEP.getName();
    }
}
