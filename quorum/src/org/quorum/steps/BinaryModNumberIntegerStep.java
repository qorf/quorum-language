package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a subtraction step for implicitly type cast numbers and integers.
 *
 * @author Andreas Stefik
 */
public class BinaryModNumberIntegerStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.number = left.getResult().number % right.getResult().integer;
        result.type = Result.NUMBER;
        return result;
    }
}
