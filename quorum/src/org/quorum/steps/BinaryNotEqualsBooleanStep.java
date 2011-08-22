/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides an equality check for booleans.
 *
 * @author Andreas Stefik
 */
public class BinaryNotEqualsBooleanStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.boolean_value = left.getResult().boolean_value != right.getResult().boolean_value;
        result.type = Result.BOOLEAN;
        return result;
    }
}