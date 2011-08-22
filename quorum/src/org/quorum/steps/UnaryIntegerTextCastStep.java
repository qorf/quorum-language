/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.plugins.RuntimeError;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 * Tries to convert a text value to an integer. If this operation fails,
 * an error condition is set.
 *
 * @author Andreas Stefik
 */
public class UnaryIntegerTextCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        try {
            result.integer = Integer.parseInt(value.getResult().text);
            this.isInErrorState = false;
        }
        catch(NumberFormatException e) {
            this.runtimeError = new RuntimeError("CastError: An error occurred while casting text to an integer.", ErrorTypeDescriptor.getCastErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(runtimeError);
            this.wasInErrorState = true;
        }
        result.type = Result.INTEGER;
        return result;
    }
}
