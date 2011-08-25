/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides an and step for boolean values.
 * 
 * @author Aaron Willows and Andreas Stefik
 */
public class BinaryAndStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.boolean_value = left.getResult().boolean_value && right.getResult().boolean_value;
        result.type = Result.BOOLEAN;
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
