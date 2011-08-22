/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * not= comparison operator for objects.
 *
 * @author Melissa Stefik
 */
public class BinaryNotEqualsCustomCustomStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if(!left.isNull() && !right.isNull()) {
            result.boolean_value = left.getObjectHash() != right.getObjectHash();
        }
        result.type = Result.BOOLEAN;
        return result;
    }
}
