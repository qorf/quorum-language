package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a not comparison operator for objects.
 *
 * @author Andreas Stefik
 */
public class BinaryNotEqualsCustomNullStep extends BinaryOperationStep {

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if (!left.isNull()) {
            result.boolean_value = true;
        }
        else {
            result.boolean_value = false;
        }
        result.type = Result.BOOLEAN;
        return result;
    }
}