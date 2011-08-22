/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Number object to text primitive.
 * 
 * @author Melissa Stefik
 */
public class NumberReverseAutoBoxToTextStep extends IntegerReverseAutoBoxStep{
    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.text = ""+(value.getResult().number);

        result.type = Result.TEXT;
        return result;
    }
}
