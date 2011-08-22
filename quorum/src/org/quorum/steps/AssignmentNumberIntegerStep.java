/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 *
 * @author Melissa Stefik
 */
public class AssignmentNumberIntegerStep extends AssignmentStep {

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        Result r = new Result();
        r.number = (double) result.getResult().integer;
        r.type = r.NUMBER;
        
        return r;
    }
}
