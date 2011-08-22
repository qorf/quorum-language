/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Melissa Stefik
 */
public class UnaryIntegerIntegerCastStep extends UnaryOperationStep {

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.integer = value.getResult().integer;

        result.type = Result.INTEGER;
        return result;
    }
}
