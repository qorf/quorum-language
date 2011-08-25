package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a comparison operator for objects.
 *
 * @author Andreas Stefik
 */
public class BinaryEqualsCustomNullStep extends BinaryOperationStep {

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if (left.isNull()) {
            result.boolean_value = true;
        }
        else {
            result.boolean_value = false;
        }
        result.type = Result.BOOLEAN;
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
