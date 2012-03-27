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
public class FileReaderPlugin implements Plugin {
    protected AbstractVirtualMachine vm;
    public static final String KEY = "Libraries.System.FileReader";
    public static final String OPEN_FOR_READ_NATIVE = "OpenForReadNative:text";
    public static final String CLOSE = "Close";
    public static final String READ_NATIVE = "ReadNative";
    public static final String READ_AMOUNT_NATIVE = "ReadNative:integer";
    public static final String READ_LINE_NATIVE = "ReadLineNative";
    public static final String IS_AT_END_OF_FILE = "IsAtEndOfFile";
    
    protected HashMap<Integer, QuorumFileReader> instances;

    public FileReaderPlugin() {
        instances = new HashMap<Integer, QuorumFileReader>();
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
        return "FileReader";
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
        instances = new HashMap<Integer, QuorumFileReader>();
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileReader inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFileReader();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
       
        if(action.equals(IS_AT_END_OF_FILE)) {
            setPluginReturnValue(ret, inst.IsAtEndOfFile());
        }
        if(action.equals(OPEN_FOR_READ_NATIVE)) {
            ExpressionValue argument = call.getArgument("path");
            String arg = argument.getResult().text;
            try {
                inst.OpenForReadNative(arg);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals(CLOSE)) {
            try {
                inst.Close();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals(READ_NATIVE)) {
            String result = "";
            try {
                result = inst.ReadNative();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(READ_AMOUNT_NATIVE)) {
            ExpressionValue argument = call.getArgument("numberOfBytes");
            int arg = argument.getResult().integer;
            String result = "";
            try {
                result = inst.ReadNative(arg);
            } catch (EOFException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(READ_LINE_NATIVE)) {
            String result = "";
            try {
                result = inst.ReadLineNative();
            } catch (EOFException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            setPluginReturnValue(ret, result);
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