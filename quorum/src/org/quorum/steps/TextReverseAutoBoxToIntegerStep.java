/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.Result;

/**
 * Text object to integer primitive.
 * 
 * @author Melissa Stefik
 */
public class TextReverseAutoBoxToIntegerStep extends IntegerReverseAutoBoxStep{
    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        try {
            result.integer = Integer.parseInt(value.getResult().text);
            this.isInErrorState = false;
        }
        catch(NumberFormatException e) {
            RuntimeError er = new RuntimeError("CastError: An error occurred while casting text to an integer.", ErrorTypeDescriptor.getCastErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(er);
            this.wasInErrorState = true;
        }
        result.type = Result.INTEGER;
        return result;
    }
}
