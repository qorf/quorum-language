/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;
import org.quorum.symbols.VariableDescriptor;



/**
 * A parent class for any binary operation.
 *
 * @author Andreas Stefik
 */
public abstract class BinaryOperationStep extends IntermediateStep {

    protected int leftRegister = -1;
    protected int rightRegister = -1;
    protected int resultRegister = -1;
    

    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue left = data.getRegister(leftRegister);
        ExpressionValue right = data.getRegister(rightRegister);
        ExpressionValue result = new ExpressionValue();
        result.setType(left.getType());

        result.setRegister(resultRegister);
        Result res = calculateOpcode(left, right);
        result.setType(res.getType());
        result.setResult(res);
        data.setRegister(resultRegister, result);
    }

    @Override
    public void unexecute() {
        vm.getDataEnvironment().popRegister(resultRegister);
    }

    protected abstract Result calculateOpcode(ExpressionValue left, ExpressionValue right);

    /**
     * @return the leftRegister
     */
    public int getLeftRegister() {
        return leftRegister;
    }

    /**
     * @param leftRegister the leftRegister to set
     */
    public void setLeftRegister(int leftRegister) {
        this.leftRegister = leftRegister;
    }
    /**
     * Sets the left register to the register where the variable leftRegister is located
     * @param leftVariable the variable
     */
    public void setLeftRegister(VariableDescriptor leftVariable) {
         DataEnvironment data = vm.getDataEnvironment();
         leftRegister = data.getVariableValue(leftVariable.getStaticKey()).getRegister();
    }

    /**
     * @return the rightRegister
     */
    public int getRightRegister() {
        return rightRegister;
    }

    /**
     * @param rightRegister the rightRegister to set
     */
    public void setRightRegister(int rightRegister) {
        this.rightRegister = rightRegister;
    }
    /**
     * Sets the left register to the register where the variable leftRegister is located
     * @param leftVariable the variable
     */
    public void setRightRegister(VariableDescriptor rightVariable) {
         DataEnvironment data = vm.getDataEnvironment();
         rightRegister = data.getVariableValue(rightVariable.getStaticKey()).getRegister();
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
