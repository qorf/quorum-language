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
 * Provides a divide step for integers.
 * 
 * @author Andreas Stefik and Aaron Willows
 */
public class BinaryDivideStep extends BinaryOperationStep{
    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if(right.getResult().integer == 0){
            this.runtimeError = new RuntimeError("Divide By Zero Error: An error occurred while attempting to divide by zero.", ErrorTypeDescriptor.getDivideByZeroErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(runtimeError);
            this.wasInErrorState = true;
        }else{
            result.integer = left.getResult().integer / right.getResult().integer;
            result.type = Result.INTEGER;
        }
        return result;
    }
}
