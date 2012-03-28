/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Structure;
import org.quorum.symbols.TypeDescriptor;
import java.util.HashMap;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 * TODO: Exception handling needs to be done properly.
 * @author Jeff Wilson
 */
public class FileWriterPlugin implements Plugin {
    protected AbstractVirtualMachine vm;
    public static final String KEY = "Libraries.System.FileWriter";
    public static final String OPEN_FOR_WRITE_NATIVE = "OpenForWriteNative:text";
    public static final String OPEN_FOR_WRITE_APPEND_NATIVE = "OpenForWriteAppendNative:text";
    public static final String WRITE_NATIVE = "WriteNative:text";
    public static final String WRITE_LINE_NATIVE = "WriteLineNative:text";
    public static final String CLOSE = "Close";
    public static final String PUSH_TO_DISK = "PushToDisk";
    
    protected HashMap<Integer, QuorumFileWriter> instances;

    public FileWriterPlugin() {
        instances = new HashMap<Integer, QuorumFileWriter>();
    }

    public PluginReturn debug(PluginCall call) {
        //return execute(call);
        return null;
    }

    public PluginReturn undebug(PluginCall call) {
        return null;
    }

    public boolean canDebugBackward() {
        return false;
    }

    public boolean isValidCall(PluginCall call) {
        return false;
    }

    public String getName() {
        return "FileWriter";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    public boolean isDebugPlugin() {
        return false;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public void reset() {
        instances = new HashMap<Integer, QuorumFileWriter>();
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileWriter inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFileWriter();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
       
        if(action.equals(OPEN_FOR_WRITE_NATIVE)) {
            ExpressionValue argument = call.getArgument("path");
            String arg = argument.getResult().text;
            try {
                inst.OpenForWriteNative(arg);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if(action.equals(OPEN_FOR_WRITE_APPEND_NATIVE)) {
            ExpressionValue argument = call.getArgument("path");
            String arg = argument.getResult().text;
            try {
                inst.OpenForWriteAppendNative(arg);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(CLOSE)) {
            try {
                inst.Close();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(WRITE_NATIVE)) {
            ExpressionValue argument = call.getArgument("textToWrite");
            String arg = argument.getResult().text;
            try {
                inst.WriteNative(arg);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }            
        }
        else if (action.equals(WRITE_LINE_NATIVE)) {
            ExpressionValue argument = call.getArgument("textToWrite");
            String arg = argument.getResult().text;
            try {
                inst.WriteLineNative(arg);
            } catch (EOFException ex) {
                throwQuorumException("EndOfFileError: " + ex.getMessage(), ErrorTypeDescriptor.getEndOfFileError());
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }            
        }
        return ret;
    }

    protected void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
        ret.setReturnValue(value);
    }
    
    protected void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, String str){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().text = str;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, boolean bool){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = bool;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, Structure obj){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().structure = obj;
        ret.setReturnValue(value);
    }

    protected void setExpressionValue(ExpressionValue exp, String str){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        exp.getResult().text = str;
    }

    protected void setExpressionValue(ExpressionValue exp, double num){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        exp.getResult().number = num;
    }

    protected void setExpressionValue(ExpressionValue exp, Boolean bool){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        exp.getResult().boolean_value = bool;
    }
    
    protected void throwQuorumException(String message, ErrorTypeDescriptor e) {
        //This will throw the exception to the virtual machine
        ExecutionStep currentStep = vm.getExecution().getCurrentStep();
        currentStep.setRuntimeError(new RuntimeError(message, e, vm));
        currentStep.setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
        currentStep.setIsInErrorState(vm.throwException(currentStep.getRuntimeError()));
        currentStep.setWasInErrorState(true);
    }
}