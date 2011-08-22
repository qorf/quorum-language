/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Boolean object to number primitive.
 * 
 * @author Melissa Stefik
 */
public class BooleanReverseAutoBoxToNumberStep extends IntegerReverseAutoBoxStep{
    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        if(value.getResult().boolean_value)
            result.number = 1.0;
        else
            result.number = 0.0;

        result.type = Result.NUMBER;
        return result;
    }
}
