/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.plugins.RuntimeError;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 *
 * @author Melissa Stefik
 */
public class UnaryNumberTextCastStep extends UnaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        try {
            result.number = Double.parseDouble(value.getResult().text);
            this.isInErrorState = false;
        }
        catch(NumberFormatException e) {
            this.runtimeError = new RuntimeError("CastError: An error occurred while casting text to a number.", ErrorTypeDescriptor.getCastErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(runtimeError);
            this.wasInErrorState = true;
        }

        result.type = Result.NUMBER;
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
