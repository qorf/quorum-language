/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Auto-cast from the Integer object to integer
 *
 * @author Melissa Stefik
 */
public class IntegerReverseAutoBoxStep extends IntegerAutoBoxStep{


    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(getRegister());
        ExpressionValue result = new ExpressionValue();

        ExpressionValue variable = data.getObject(value.getObjectHash()).getVariable("value");

        result.setRegister(getResultRegister());
        Result res = calculateOpcode(variable);
        result.setType(res.getType());
        result.setResult(res);
        data.setRegister(getResultRegister(),result);
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
