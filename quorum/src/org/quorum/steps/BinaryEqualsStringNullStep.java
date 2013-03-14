/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 *
 * @author melissa
 */
public class BinaryEqualsStringNullStep extends BinaryOperationStep {

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
