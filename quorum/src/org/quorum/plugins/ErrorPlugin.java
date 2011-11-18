/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.HashMap;
import java.util.Iterator;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.ExceptionManager;
import org.quorum.vm.interfaces.LineInformation;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.execution.RuntimeScope;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author Melissa Stefik
 */
public class ErrorPlugin implements Plugin{
    public static final String KEY = "Libraries.Language.Errors.Error";
    public static final String INIT_MESSAGE = "InitMessage";
    public static final String INIT_STACK_TRACE_FROM_ERROR = "InitStackTraceFromError";
    public static final String GET_STACK_SIZE = "GetStackSize";
    public static final String GET_CLASS_NAME_INTEGER = "GetClassName:integer";
    public static final String GET_METHOD_NAME_INTEGER = "GetMethodName:integer";
    public static final String GET_FILE_NAME_INTEGER = "GetFileName:integer";
    public static final String GET_LINE_NAME_INTEGER = "GetLineNumber:integer";
    public static final String SYSTEM_SET_MESSAGE = "SystemSetMessage:text";

    protected HashMap<Integer, RuntimeError> runtimeErrors;

    public ErrorPlugin() {
        runtimeErrors = new HashMap<Integer, RuntimeError>();
    }

    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        if(call.getActionName().equals(INIT_MESSAGE)) {
            initMessage(call, ret);
        }else if(call.getActionName().equals(INIT_STACK_TRACE_FROM_ERROR)) {
            initStackTraceFromError(call, ret);
        }else if(call.getActionName().equals(GET_STACK_SIZE)) {
            getStackSize(call, ret);
        }else if(call.getActionName().equals(GET_CLASS_NAME_INTEGER)) {
            getClassNameInteger(call, ret);
        }else if(call.getActionName().equals(GET_METHOD_NAME_INTEGER)) {
            getMethodNameInteger(call, ret);
        }else if(call.getActionName().equals(GET_FILE_NAME_INTEGER)) {
            getFileNameInteger(call, ret);
        }else if(call.getActionName().equals(GET_LINE_NAME_INTEGER)) {
            getLineNameInteger(call, ret);
        }else if(call.getActionName().equals(SYSTEM_SET_MESSAGE)) {
            systemSetMessage(call, ret);
        }


        return ret;
    }

    /**
     * Initializes the Error on the quorum side by populating the stack trace.
     * 
     * @param call
     * @param ret 
     */
    private void initStackTraceFromError(PluginCall call, PluginReturn ret){
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }

        //get the hash of the stack trace item in question
        RuntimeError re = runtimeErrors.get(call.getCallingObject().getHashKey());

        if(re == null) { //we must make an array available
            re = new RuntimeError();
            runtimeErrors.put(call.getCallingObject().getHashKey(), re);
        }
        ExpressionValue variable = call.getCallingObject().getVariable("errorMessage");
        if(variable !=  null){
            re.setErrorMessage(variable.getResult().text);
            re.setObjectHash(call.getCallingObject().getHashKey());
        }

        //get the vm and populate the stack trace infromation with the information
        //in the vm.
        AbstractVirtualMachine vm = call.getVirtualMachine();
        if(vm != null){
            call.getCallingObject().getParents();

            RuntimeError runtimeError = vm.getExceptions().exceptionStackPeek();

            if(runtimeError == null){
                Iterator<RuntimeScope> callStackIterator = vm.getDataEnvironment().callStackIterator();
                
                while(callStackIterator.hasNext()){
                    RuntimeScope next = callStackIterator.next();
                    if(next != null && (next instanceof ActivationRecord || next instanceof RuntimeObject)){

                        LineInformation lineInfo = next.getLineInformation();
                        if(lineInfo != null){
                            String methodName = lineInfo.getMethodName();
                            String  clazzName = lineInfo.getClassName();
                            String fileName = lineInfo.getFile();
                            int lineNumber = lineInfo.getStartLine();

                            StackTraceElement item = new StackTraceElement();
                            item.init(clazzName, methodName, fileName, lineNumber);
                            re.addStackItem(item);
                        }
                    }
                }
            }else{
                Iterator<StackTraceElement> iterator = runtimeError.getStack().iterator();
                while(iterator.hasNext()){
                    StackTraceElement next = iterator.next();
                    StackTraceElement item = new StackTraceElement();
                    item.init(next.getClassName(), next.getMethodName(), next.getFileName(), next.getLineNumber());
                    re.addStackItem(item);
                }
            }
            vm.getExceptions().addErrorObject(call.getCallingObject().getHashKey(), re);
        }
    }

    /**
     * Initializes the error message.
     * 
     * @param call
     * @param ret 
     */
    private void initMessage(PluginCall call, PluginReturn ret){
        if(call.getCallingObject() == null || call.getCallingObject().isNull()) {
            //invalid call
            return;
        }
        
        int hash = call.getCallingObject().getHashKey();
        AbstractVirtualMachine vm = call.getVirtualMachine();
        ExceptionManager em = vm.getRuntimeErrors();
        ExpressionValue ev = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        if(em.hasErrors()){
            RuntimeError e = em.exceptionStackPeek();
            if(e != null){
                String message = e.getErrorMessage();
                ev.getResult().text = message;
            }
        }
        ret.setReturnValue(ev);
        
    }
    
    /**
     * Explicitly sets the Error Managers error message. This specifically, will
     * ensure the message is propagated to the sodbeans output window when an
     * uncaught error happens.
     * 
     * @param call
     * @param ret 
     */
    private void systemSetMessage(PluginCall call, PluginReturn ret){
        if(call.getCallingObject() == null || call.getCallingObject().isNull()) {
            //invalid call
            return;
        }
        
        ExpressionValue argument = call.getArgument("message");
        String message = argument.getResult().text;
        
        int hash = call.getCallingObject().getHashKey();
        AbstractVirtualMachine vm = call.getVirtualMachine();
        ExceptionManager em = vm.getRuntimeErrors();
        ExpressionValue ev = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        RuntimeError e = em.getErrorObject(hash);
        if(e != null){
            e.setErrorMessage(message);
        }
        
    }

    /**
     * Gets the stack size of the stack trace.
     * 
     * @param call
     * @param ret 
     */
    private void getStackSize(PluginCall call, PluginReturn ret) {
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }
        //get the hash of the stack trace item in question
        RuntimeError runtimeError = runtimeErrors.get(call.getCallingObject().getHashKey());
        if(runtimeError != null) {
            int size = runtimeError.getStackSize();
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            value.getResult().integer = size;
            ret.setReturnValue(value);
        }
    }

    /**
     * Gets the class name of an individual stack trace item.
     * 
     * @param call
     * @param ret 
     */
    private void getClassNameInteger(PluginCall call, PluginReturn ret) {
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }
        int index = call.getArgument("index").getResult().integer;

        //get the hash of the stack trace item in question
        RuntimeError runtimeError = runtimeErrors.get(call.getCallingObject().getHashKey());
        if(runtimeError != null) {
            String name = runtimeError.getClassName(index);
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
            value.getResult().text = name;
            ret.setReturnValue(value);
        }
    }

    /**
     * Get the method name of an individual stack trace item.
     * 
     * @param call
     * @param ret 
     */
    private void getMethodNameInteger(PluginCall call, PluginReturn ret) {
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }
        int index = call.getArgument("index").getResult().integer;

        //get the hash of the stack trace item in question
        RuntimeError runtimeError = runtimeErrors.get(call.getCallingObject().getHashKey());
        if(runtimeError != null) {
            String name = runtimeError.getMethodName(index);
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
            value.getResult().text = name;
            ret.setReturnValue(value);
        }
    }

    /**
     * Gets the file name of an individual stack trace item.
     * 
     * @param call
     * @param ret 
     */
    private void getFileNameInteger(PluginCall call, PluginReturn ret) {
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }
        int index = call.getArgument("index").getResult().integer;

        //get the hash of the stack trace item in question
        RuntimeError runtimeError = runtimeErrors.get(call.getCallingObject().getHashKey());
        if(runtimeError != null) {
            String name = runtimeError.getFileName(index);
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
            value.getResult().text = name;
            ret.setReturnValue(value);
        }
    }

    /**
     * Gets the line number of an individual stack trace item.
     * 
     * @param call
     * @param ret 
     */
    private void getLineNameInteger(PluginCall call, PluginReturn ret) {
        if(call.getCallingObject() == null || call.getCallingObject().getHashKey() <= -1) {
            //invalid call
            return;
        }
        int index = call.getArgument("index").getResult().integer;

        //get the hash of the stack trace item in question
        RuntimeError runtimeError = runtimeErrors.get(call.getCallingObject().getHashKey());
        if(runtimeError != null) {
            int line = runtimeError.getLineNumber(index);
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            value.getResult().integer = line;
            ret.setReturnValue(value);
        }
    }

    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    public boolean isValidCall(PluginCall call) {
        if( call.getActionName().equals(INIT_MESSAGE) ||
                call.getActionName().equals(INIT_STACK_TRACE_FROM_ERROR)||
                call.getActionName().equals(GET_STACK_SIZE)||
                call.getActionName().equals(GET_CLASS_NAME_INTEGER) ||
                call.getActionName().equals(GET_METHOD_NAME_INTEGER) ||
                call.getActionName().equals(GET_FILE_NAME_INTEGER) ||
                call.getActionName().equals(GET_LINE_NAME_INTEGER) ||
                call.getActionName().equals(SYSTEM_SET_MESSAGE)) {
            return true;
        }

        return false;
    }

    public boolean canDebugBackward() {
        return true;
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public String getName() {
        return "Error";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public void reset() {
        runtimeErrors = new HashMap<Integer, RuntimeError>();
    }

}
