/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a <= comparison.
 *
 * @author Andreas Stefik
 */
public class BinaryLessEqualsIntegerNumberStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.boolean_value = left.getResult().integer <= right.getResult().number;
        result.type = Result.BOOLEAN;
        return result;
    }
}
