/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;

/**
 * Provides a string concatenation step.
 *
 * @author Andreas Stefik
 */
public class BinaryAddIntegerTextStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.text = left.getResult().integer + right.getResult().text;
        result.type = Result.TEXT;
        return result;
    }
}