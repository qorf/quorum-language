/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a multiplication step for numbers.
 *
 * @author Andreas Stefik
 */
public class BinaryMultiplyNumberStep extends BinaryOperationStep {

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.number = left.getResult().number * right.getResult().number;
        result.type = Result.NUMBER;
        return result;
    }
}
