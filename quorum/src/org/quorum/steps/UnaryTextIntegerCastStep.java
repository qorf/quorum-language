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
public class UnaryTextIntegerCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.text = ""+(value.getResult().integer);

        result.type = Result.TEXT;
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
