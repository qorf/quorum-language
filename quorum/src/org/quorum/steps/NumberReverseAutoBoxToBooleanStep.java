/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Number Object to boolean primitive.
 * 
 * @author Melissa Stefik
 */
public class NumberReverseAutoBoxToBooleanStep extends IntegerReverseAutoBoxStep{
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
