/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Auto-cast from the Boolean object to boolean
 *
 * @author Melissa Stefik
 */
public class BooleanReverseAutoBoxStep extends BooleanAutoBoxStep{


    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(getRegister());
        ExpressionValue result = new ExpressionValue();

        ExpressionValue variable = data.getObject(value.getObjectHash()).getVariable("value");

        result.setRegister(getResultRegister());
        Result res = calculateOpcode(value);
        res = variable.getResult();
        result.setType(res.getType());
        result.setResult(res);
        data.setRegister(getResultRegister(),result);
    }
}
