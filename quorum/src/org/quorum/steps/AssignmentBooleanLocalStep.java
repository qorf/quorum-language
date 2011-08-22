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
public class AssignmentBooleanLocalStep extends AssignmentLocalStep{

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        Result r = new Result();
        r.boolean_value = result.getResult().boolean_value;
        r.type = r.BOOLEAN;

        return r;
    }

}
