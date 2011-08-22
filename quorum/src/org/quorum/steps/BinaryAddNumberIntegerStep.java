/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides an addition step for integers.
 *
 * @author Andreas Stefik
 */
public class BinaryAddNumberIntegerStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.number = left.getResult().number + right.getResult().integer;
        result.type = Result.NUMBER;
        return result;
    }
}
