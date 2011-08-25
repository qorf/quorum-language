package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.plugins.RuntimeError;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 * Provides a subtraction step for implicitly type cast numbers and integers.
 *
 * @author Andreas Stefik
 */
public class BinaryDivideIntegerNumberStep extends BinaryOperationStep{

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if(right.getResult().number == 0.0){
            this.runtimeError = new RuntimeError("Divide By Zero Error: An error occurred while attempting to divide by zero.", ErrorTypeDescriptor.getDivideByZeroErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(runtimeError);
            this.wasInErrorState = true;
        }else{
            result.number = left.getResult().integer / right.getResult().number;
            result.type = Result.NUMBER;
        }
        return result;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
