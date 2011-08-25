/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;

/**
 * This step executes a library command loaded into the virtual machine.
 * Effectively, it allows for loading third party software unrelated to the
 * code generated for the virtual machine to execute.
 * 
 * @author Andreas Stefik
 */
public abstract class NativeLibraryStep extends IntermediateStep{

    private int libraryRegister;
    private int actionRegister;
    private int parameterRegister;

    private int resultRegister;
    
    @Override
    public void execute() {
    }

    @Override
    public void unexecute() {
    }

    protected String getRegisterString(int register) {
        DataEnvironment de = vm.getDataEnvironment();
        ExpressionValue value = de.getRegister(register);
        Result res = value.getResult();
        return res.text;
    }

    /**
     * @return the libraryRegister
     */
    public int getLibraryRegister() {
        return libraryRegister;
    }

    /**
     * @param libraryRegister the libraryRegister to set
     */
    public void setLibraryRegister(int libraryRegister) {
        this.libraryRegister = libraryRegister;
    }

    /**
     * @return the actionRegister
     */
    public int getActionRegister() {
        return actionRegister;
    }

    /**
     * @param actionRegister the actionRegister to set
     */
    public void setActionRegister(int actionRegister) {
        this.actionRegister = actionRegister;
    }

    /**
     * @return the parameterRegister
     */
    public int getParameterRegister() {
        return parameterRegister;
    }

    /**
     * @param parameterRegister the parameterRegister to set
     */
    public void setParameterRegister(int parameterRegister) {
        this.parameterRegister = parameterRegister;
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

    @Override
    public abstract void visit(ExecutionStepVisitor visitor);
}
