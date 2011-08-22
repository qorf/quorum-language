/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger.audio;

import org.quorum.debugger.Action;
import org.quorum.vm.interfaces.ExceptionManager;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;
import org.quorum.plugins.Console;
import org.quorum.plugins.StandardInputOutput;

/**
 * This action represents the case when the virtual machine encounters an error.
 * For example, this error might be executed under conditions similar to a
 * null pointer error or an array index out of bounds. These errors are
 * always runtime errors.
 * 
 * @author Andreas Stefik
 */
public class VirtualMachineErrorAction extends Action{

    public VirtualMachineErrorAction() {
        if(console == null) {
            console = new StandardInputOutput();
        }
    }
    
    /**
     * @return the console
     */
    public static Console getConsole() {
        return console;
    }

    /**
     * @param aConsole the console to set
     */
    public static void setConsole(Console aConsole) {
        console = aConsole;
    }

    private ExceptionManager thrownException = null;
    private static Console console;

    @Override
    protected String speakForward() {
        String error = "Error: Sodbeans has encountered an error and cannot proceed.";
        if(thrownException != null && thrownException.hasErrors()){
            RuntimeError exception = thrownException.exceptionStackPeek();
            getConsole().post(exception.getStackTraceMessage());
            error = exception.getErrorMessage();
        }else if(thrownException != null && thrownException.hasAlerts()){
            ExpressionValue alertStackPeek = thrownException.alertStackPeek();
            RuntimeError exception = thrownException.getErrorObject(alertStackPeek.getObjectHash());
            if(exception != null){
                getConsole().post(exception.getStackTraceMessage());
                error = exception.getErrorMessage();
            }
        }
        return error;
    }

    @Override
    protected String speakBackward() {
        return "Sodbeans is automatically recovering from an error.";
    }

    @Override
    public void setStep(ExecutionStep step) {
    }

    /**
     * @param thrownException the thrownException to set
     */
    public void setThrownException(ExceptionManager thrownException) {
        this.thrownException = thrownException;
    }

}
