/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;

/**
 * This opcode handles auto-boxing for Text to the Text class.
 *
 * @author Melissa Stefik
 */
public class TextAutoBoxStep extends UnaryOperationStep{

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
        temp.setType(TypeDescriptor.getTextObjectType());
        temp.setResult(res);

        data.setRegister(getRegister(), temp);

    }

    @Override
    public void unexecute() {
        DataEnvironment data = vm.getDataEnvironment();
        data.popRegister(resultRegister);
    }

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();

        result.text = value.getResult().text;
        result.type = Result.TEXT;

        return result;
    }

}
