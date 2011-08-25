/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 * The alert opcode throws a manually triggered exception to the virtual machine.
 *
 * @author Melissa Stefik
 */
public class AlertStep extends IntermediateStep{
    private ErrorTypeDescriptor errorType;
    private int parameterRegistar;
    private String message = "";
    

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue value = de.getRegister(getParameterRegister());

        vm.getExceptions().alertStackPush(value);
        setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
        this.isInErrorState = vm.alertException(value);
        this.wasInErrorState = true;
    }

    @Override
    public void unexecute() {
        if(wasInErrorState){
            vm.unAlertException(callStateBeforeException);
            wasInErrorState = false;
        }
    }

    @Override
    public String getStaticKey() {
        return "";
    }

    public void setParameterRegister(int register) {
        parameterRegistar = register;
    }

    public int getParameterRegister(){
        return parameterRegistar;
    }

    public void setErrorType(ErrorTypeDescriptor errorType) {
        this.errorType = errorType;
    }

    public void setStackTraceMessage(String m){
        message = m;
    }
    
    public String getStackTraceMessage(){
        return message;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
