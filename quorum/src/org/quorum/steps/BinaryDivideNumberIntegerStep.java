package org.quorum.steps;

import org.quorum.plugins.RuntimeError;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 * Provides a subtraction step for implicitly type cast numbers and integers.
 *
 * @author Andreas Stefik
 */
public class BinaryDivideNumberIntegerStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if(right.getResult().integer == 0){
            this.runtimeError = new RuntimeError("Divide By Zero Error: An error occurred while attempting to divide by zero.", ErrorTypeDescriptor.getDivideByZeroErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(runtimeError);
            this.wasInErrorState = true;
        }else{
            result.number = left.getResult().number / right.getResult().integer;
            result.type = Result.NUMBER;
        }
        return result;
    }
}
