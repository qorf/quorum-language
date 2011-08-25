/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Melissa Stefik
 */
public class UnaryBooleanTextCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.boolean_value = Boolean.parseBoolean(value.getResult().text);
        this.isInErrorState = false;
        result.type = Result.BOOLEAN;

        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
