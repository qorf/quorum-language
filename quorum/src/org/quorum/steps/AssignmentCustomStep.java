/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * An assignment step for custom types.
 * 
 * @author Andreas Stefik
 */
public class AssignmentCustomStep extends AssignmentStep{
    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        Result res = new Result();
        res.noConvert = true;
        return res;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}