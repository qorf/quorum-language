/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.Stack;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;

/**
 * Alert step without a message being passed.
 * 
 * @author Melissa Stefik
 */
public class SimpleAlertStep extends IntermediateStep{
    private int messageRegister;
    private String message = "";

    @Override
    public void execute() {
        message = getRegisterString(getMessageRegister());

        TypeDescriptor type = new TypeDescriptor();
        type.setName(ErrorTypeDescriptor.ERROR);
        this.runtimeError = new RuntimeError(message, ErrorTypeDescriptor.getErrorType(), vm);

        setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
        this.isInErrorState = vm.throwException(runtimeError);
        this.wasInErrorState = true;
    }

    @Override
    public void unexecute() {
        if(wasInErrorState){
            runtimeError = null;
            vm.unThrowException(callStateBeforeException);
            wasInErrorState = false;
        }
    }

    @Override
    public String getStaticKey() {
        return "";
    }

    /**
     * @return the messageRegistar
     */
    public int getMessageRegister() {
        return messageRegister;
    }

    /**
     * @param messageRegistar the messageRegistar to set
     */
    public void setMessageRegister(int messageRegister) {
        this.messageRegister = messageRegister;
    }

    private String getRegisterString(int register) {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue value = de.getRegister(register);
        Result res = value.getResult();
        return res.text;
    }
}
