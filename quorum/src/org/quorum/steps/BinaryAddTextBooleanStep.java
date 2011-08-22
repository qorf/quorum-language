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
public class BinaryAddTextBooleanStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        result.text = left.getResult().text + right.getResult().boolean_value;
        result.type = Result.TEXT;
        return result;
    }
}