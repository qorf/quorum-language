/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.util.HashMap;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.OmniscientStack;

/**
 * This class manages the storing of and retrieving information about
 * runtime exceptions that are thrown in the quorum interpreter.
 * Runtime errors that have been handled by the "catch" statement
 * should be removed from the manager.
 *
 * @author Melissa Stefik
 */
public class ExceptionManager {
    private OmniscientStack<RuntimeError> errors = new OmniscientStack<RuntimeError>();
    private OmniscientStack<ExpressionValue> alerts = new OmniscientStack<ExpressionValue>();

    private HashMap<Integer, RuntimeError> errorObjects = new HashMap<Integer, RuntimeError>();

    public void addErrorObject(int hashCode, RuntimeError error){
        errorObjects.put(hashCode, error);
    }

    public RuntimeError getErrorObject(int hashCode){
        return errorObjects.get(hashCode);
    }
    /**
     * push a triggered runtime error onto the runtime error stack
     * @param e
     */
    public void exceptionStackPush(RuntimeError e) {
        if(e != null){
            errors.push(e);
        }
    }
    
    /**
     * If there are active runtime errors return true. If all runtime errors
     * are handled or have not been triggered return false.
     *
     * @return
     */
    public boolean hasErrors(){
        if(!errors.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Pop an error off the runtime error stack, in none exists return null.
     *
     * @return
     */
    public RuntimeError exceptionStackPop(){
        if(!errors.isEmpty()){
            return errors.pop();
        }
        return null;
    }

    /**
     * Undo the last pop off the exception stack.
     */
    public void exceptionStackUndo(){
        errors.undo();
    }

    /**
     * Clear the errors from the manager.
     */
    public void clear(){
        errors.clear();
        alerts.clear();
        errorObjects.clear();
    }

    /**
     * get (will not remove) the exception from the top of the exception stack.
     * 
     * @return
     */
    public RuntimeError exceptionStackPeek() {
        if(!errors.isEmpty()){
            return errors.peek();
        }
        return null;
    }

    /**
     * Push an alert error to the top of the alert stack.
     *
     * @param e
     */
    public void alertStackPush(ExpressionValue e) {
        if(e != null){
            alerts.push(e);
        }
    }

    /**
     * Pop the top alert error off of the alert stack.
     * @return
     */
    public ExpressionValue alertStackPop(){
        if(!alerts.isEmpty()){
            return alerts.pop();
        }
        return null;
    }

    /**
     * Get the top alert error from the alert stack without removing it
     * from the stack.
     *
     * @return
     */
    public ExpressionValue alertStackPeek() {
        if(!alerts.isEmpty()){
            return alerts.peek();
        }
        return null;
    }

    /**
     * Undo the last pop off the alert stack.
     */
    public void alertStackUndo(){
        alerts.undo();
    }

    /**
     * 
     * @return true if there is an alert in the alert stack.
     */
    public boolean hasAlerts() {
        if(!alerts.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * If there are any alerts or exceptions that have been thrown and 
     * not caught return false.
     * 
     * @return 
     */
    public boolean hasExceptions() {
        if(!alerts.isEmpty() || !errors.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
