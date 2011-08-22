/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides an greater than or equal check for strings.
 *
 * @author Andreas Stefik
 */
public class BinaryGreaterEqualsStringStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.boolean_value = left.getResult().text.compareTo(right.getResult().text) >= 0;
        result.type = Result.BOOLEAN;
        return result;
    }
}