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
public class AssignmentTextNullStep extends AssignmentStep {

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        Result r = new Result();
        r.text = result.getResult().text;
        r.type = r.TEXT;

        return r;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }   
}
