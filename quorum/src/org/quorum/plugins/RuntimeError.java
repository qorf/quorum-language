/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.ArrayList;
import java.util.Iterator;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.LineInformation;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.RuntimeScope;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.MethodDescriptor;


/**
 * Mimics the Error class in the quorum standard library to track runtime errors
 * and the stack trace.
 *
 * @author Melissa Stefik
 */
public class RuntimeError {
    private ErrorTypeDescriptor errorType;
    private String errorMessage = "";
    private ArrayList<StackTraceElement> stackTrace;
    private int errorObjectHash = -1;
    private LineInformation location;


    public RuntimeError(){
        stackTrace = new ArrayList<StackTraceElement>();
        errorType = new ErrorTypeDescriptor();
        location = new LineInformation();

    }
    /**
     * Constructor for a runtime error with a message.
     *
     * @param message errors message describing the error and it's source
     * @param type type of error(ErrorTypeDescriptor)
     * @param vm current virtual machine
     */
    public RuntimeError(String message, ErrorTypeDescriptor type, AbstractVirtualMachine vm){
        stackTrace = new ArrayList<StackTraceElement>();
        location = new LineInformation();
        errorMessage = message;
        errorType = type;

        initLocation(vm);
        initStackTrace(vm);
    }

    /**
     * Constructor for the runtime error without a message.
     *
     * @param type type of error(ErrorTypeDescriptor)
     * @param vm  current virtual machine
     */
    public RuntimeError(ErrorTypeDescriptor type, AbstractVirtualMachine vm){
        stackTrace = new ArrayList<StackTraceElement>();
        location = new LineInformation();
        errorType = type;

        initLocation(vm);
        initStackTrace(vm);
    }

    /**
     * initialize the current errors location
     *
     * @param vm
     */
    private void initLocation(AbstractVirtualMachine vm){
        ExecutionStep currentStep = vm.getExecution().getCurrentStep();
        MethodDescriptor method = currentStep.getMethodDescriptor();
        location.setClassName(((ClassDescriptor)method.getParent()).getStaticKey());
        location.setMethodName(method.getStaticKey());
        location.setStartLine(currentStep.getBeginLine());
        location.setFile(currentStep.getFileKey());
    }

    /**
     * Initialize the current errors stack trace.
     *
     * @param vm
     */
    private void initStackTrace(AbstractVirtualMachine vm){
        Iterator<RuntimeScope> callStackIterator = vm.getDataEnvironment().callStackIterator();
        while(callStackIterator.hasNext()){
            RuntimeScope next = callStackIterator.next();
            if(next instanceof ActivationRecord){
                StackTraceElement element = new StackTraceElement();
                LineInformation lineInformation = next.getLineInformation();
                if(lineInformation != null){
                    element.init(lineInformation.getClassName(), lineInformation.getMethodName(), lineInformation.getFile(), lineInformation.getStartLine());
                    stackTrace.add(element);
                }
            }
        }
        StackTraceElement element = new StackTraceElement();
        element.init(location.getClassName(), location.getMethodName(), location.getFile(), location.getStartLine());
        stackTrace.add(element);
    }

    /**
     *
     * @return Get the error error message for the runtime error.
     */
    public String getErrorMessage(){
        return errorMessage;
    }

    /**
     *
     * @return the static key representing the error
     */
    public String getStaticKey(){
        if(errorType != null){
            return errorType.toString();
        }else{
            return "";
        }
    }

    /**
     *
     * @return the errors type
     */
    public ErrorTypeDescriptor getErrorType(){
        return errorType;
    }

    /**
     *
     * @return the stack trace
     */
    public ArrayList<StackTraceElement> getStackTrace(){
        return stackTrace;
    }

    /**
     * Generate the stack trace for the error.
     * 
     * @param stack
     */
    public void setStackTrace(ArrayList<StackTraceElement> stack){
        stackTrace = new ArrayList<StackTraceElement>();
        for(int i = 0; i < stack.size(); i++){
            stackTrace.set(i, stack.get(i));
        }
    }

    /**
     * Set the errors associated object, saving by hash code.
     *
     * @param objectHash
     */
    public void setObjectHash(int objectHash) {
        errorObjectHash = objectHash;
    }

    /**
     *
     * @return the error objects hash value.
     */
    public int getObjectHash(){
        return errorObjectHash;
    }

    public void addStackItem(StackTraceElement item){
        stackTrace.add(item);
    }

    public String getClassName(int index){
        StackTraceElement element = stackTrace.get(index);
        if(element != null){
            return element.getClassName();
        }
        return "";
    }

    public String getMethodName(int index){
        StackTraceElement element = stackTrace.get(index);
        if(element != null){
            return element.getMethodName();
        }
        return "";
    }

    public String getFileName(int index){
        StackTraceElement element = stackTrace.get(index);
        if(element != null){
            return element.getFileName();
        }
        return "";
    }

    public int getLineNumber(int index){
        StackTraceElement element = stackTrace.get(index);
        if(element != null){
            return element.getLineNumber();
        }
        return 1;
    }

    public int getStackSize(){
        return stackTrace.size();
    }

    public String getStackTraceMessage(){
        String traceString = errorMessage + " ";
        int size = stackTrace.size();
        for(int i = 0; i < size; i++){
            StackTraceElement item = stackTrace.get(i);
            traceString = traceString + " " + item.getFileName();
            traceString = traceString + " " + item.getClassName();
            traceString = traceString + " " + item.getMethodName();
            traceString = traceString + " [" + item.getLineNumber() + "] ";
        }
        return traceString;
    }

    public void setErrorMessage(String text) {
        errorMessage = text;
    }

    public void setErrorType(ErrorTypeDescriptor errorType) {
        this.errorType = errorType;
    }
}
