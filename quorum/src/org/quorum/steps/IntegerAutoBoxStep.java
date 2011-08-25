/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.TypeDescriptor;

/**
 * This opcode handles auto-boxing for integer to the Integer class.
 *
 * @author Melissa Stefik
 */
public class IntegerAutoBoxStep extends UnaryOperationStep{

    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(getRegister());

        data.callStackUndo();
        RuntimeObject callStackPeek = (RuntimeObject)data.callStackPeek();
        ExpressionValue variable = callStackPeek.getVariable("value");
        
        Result res = calculateOpcode(value);
        variable.setResult(res);

        data.callStackPop();

        ExpressionValue temp = new ExpressionValue();
        temp.setObjectHash(callStackPeek.getHashKey());
        temp.setType(TypeDescriptor.getIntegerObjectType());
        temp.setResult(res);

        data.setRegister(getRegister(), temp);

    }

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();

        result.integer = value.getResult().integer;
        result.type = Result.INTEGER;

        return result;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
