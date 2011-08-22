/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Melissa Stefik
 */
public class UnaryBooleanNumberCastStep  extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        if(value.getResult().number == 0.0)
            result.boolean_value = false;
        else
            result.boolean_value = true;

        result.type = Result.BOOLEAN;
        return result;
    }
}
