/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Tries to convert a text value to an integer. If this operation fails,
 * an error condition is set.
 *
 * @author Andreas Stefik
 */
public class UnaryIntegerNumberCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.integer = (int) value.getResult().number;
        result.type = Result.INTEGER;
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
