/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * A not or negation step for boolean values.
 * 
 * @author Andreas Stefik and Aaron Willows
 */
public class UnaryNotStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.boolean_value = !value.getResult().boolean_value;
        result.type = Result.BOOLEAN;
        return result;
    }
}