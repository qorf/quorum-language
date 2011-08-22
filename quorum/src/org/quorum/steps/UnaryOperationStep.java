/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * A parent class for a unary step.
 *
 * @author Andreas Stefik
 */
public abstract class UnaryOperationStep extends IntermediateStep{
    protected int register;
    protected int resultRegister;

    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(getRegister());
        ExpressionValue result = new ExpressionValue();
        result.setType(value.getType());
        
        result.setRegister(getResultRegister());
        Result res = calculateOpcode(value);
        result.setType(res.getType());
        result.setResult(res);
        data.setRegister(getResultRegister(),result);
    }
    
    @Override
    public void unexecute() {
        if(wasInErrorState){
            runtimeError = null;
            vm.unThrowException(callStateBeforeException);
            wasInErrorState = false;
        }
        DataEnvironment data = vm.getDataEnvironment();
        data.popRegister(resultRegister);
    }

    protected abstract Result calculateOpcode(ExpressionValue value);
    
    /**
     * @return the register
     */
    public int getRegister() {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(int register) {
        this.register = register;
    }

    /**
     * @return the resultRegister
     */
    public int getResultRegister() {
        return resultRegister;
    }

    /**
     * @param resultRegister the resultRegister to set
     */
    public void setResultRegister(int resultRegister) {
        this.resultRegister = resultRegister;
    }
}
