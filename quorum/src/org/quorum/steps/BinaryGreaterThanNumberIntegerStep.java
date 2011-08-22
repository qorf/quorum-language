/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a greater than or equal to comparison.
 *
 * @author Andreas Stefik
 */
public class BinaryGreaterThanNumberIntegerStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.boolean_value = left.getResult().number > right.getResult().integer;
        result.type = Result.BOOLEAN;
        return result;
    }
}