/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * Number object to integer primitive.
 * 
 * @author Melissa Stefik
 */
public class NumberReverseAutoBoxToIntegerStep extends NumberReverseAutoBoxStep{
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