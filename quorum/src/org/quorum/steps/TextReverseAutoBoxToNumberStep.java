/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.Result;

/**
 * Text object to number primitive.
 * 
 * @author Melissa Stefik
 */
public class TextReverseAutoBoxToNumberStep extends TextReverseAutoBoxStep{
    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        Result result = new Result();
        try {
            result.number = Double.parseDouble(value.getResult().text);
            this.isInErrorState = false;
        }
        catch(NumberFormatException e) {
            RuntimeError er = new RuntimeError("CastError: An error occurred while casting text to a number.", ErrorTypeDescriptor.getCastErrorType(), vm);
            setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
            this.isInErrorState = vm.throwException(er);
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
