/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Assignment opcode for Objects assigned to primitives.
 *
 * @author Melissa Stefik
 */
public class AssignIntegerObjectToNumberAutoBoxStep extends AssignmentStep{

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue var = de.getObject(result.getObjectHash()).getVariable("value");
        Result res = new Result();
        res.number = (double)var.getResult().integer;
        res.type = res.NUMBER;
        return res;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
