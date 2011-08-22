/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 *
 * @author melissa
 */
public class AssignmentNumberLocalStep  extends AssignmentLocalStep{

    @Override
    protected Result calculateOpcode(ExpressionValue result) {
        Result r = new Result();
        r.number = result.getResult().number;
        r.type = r.NUMBER;

        return r;
    }

}
