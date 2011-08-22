/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Tries to convert a text value to an integer. If this operation fails,
 * an error condition is set.
 *
 * @author Andreas Stefik
 */
public class UnaryNumberIntegerCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        result.number = value.getResult().integer;
        result.type = Result.NUMBER;
        return result;
    }
}