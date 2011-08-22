/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Text object to boolean primitive.
 * 
 * @author Melissa Stefik
 */
public class TextReverseAutoBoxToBooleanStep extends IntegerReverseAutoBoxStep{
    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        try {
            result.boolean_value = Boolean.parseBoolean(value.getResult().text);
            this.isInErrorState = false;
        }
        catch(NumberFormatException e) {
            this.isInErrorState = true;
        }
        result.type = Result.BOOLEAN;
        return result;
    }
}
