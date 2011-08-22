/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a multiplication step for integers.
 * 
 * @author Andreas Stefik and Aaron Willows
 */
public class BinaryMultiplyStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.integer = left.getResult().integer * right.getResult().integer;
        result.type = Result.INTEGER;
        return result;
    }
}
