/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 *
 * @author Melissa Stefik
 */
public class ObjectCastStep extends UnaryOperationStep{

    @Override
    public void execute() {

        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(getRegister());
        ExpressionValue result = new ExpressionValue();
        result.setType(value.getType());

        result.setObjectHash(value.getObjectHash());
        result.setRegister(getResultRegister());
        Result res = calculateOpcode(value);
        result.setResult(res);
        data.setRegister(getResultRegister(),result);
    }

    @Override
    public void unexecute() {
        DataEnvironment data = vm.getDataEnvironment();
        data.popRegister(resultRegister);
    }

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();

        result = value.getResult();

        return result;
    }

}
