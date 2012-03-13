/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 *
 * @author Jeff Wilson
 */
public class FilePlugin implements Plugin {
    protected AbstractVirtualMachine vm;
    public static final String KEY = "Libraries.System.File";
    
    /* -- class methods -- */
    public static final String GET_LAST_MODIFIED_NATIVE = "GetLastModifiedNative";
    public static final String GET_DIRECTORY_LISTING_NATIVE = "GetDirectoryListingNative";
    public static final String GET_PARENT_DIRECTORY_NATIVE = "GetParentDirectoryNative";
    public static final String GET_SYSTEM_NEWLINE = "GetSystemNewline";
    public static final String SET_PATH_NATIVE = "SetPathNative:text";
    public static final String GET_WORKING_DIRECTORY_NATIVE = "GetSorkingDirectoryNative";
    public static final String EXISTS = "Exists";
    public static final String IS_FILE = "IsFile";
    public static final String IS_DIRECTORY = "IsDirectory";
    public static final String IS_HIDDEN = "IsHidden";
    public static final String GET_FILE_NAME = "GetFileName";
    public static final String GET_FILE_EXTENSION = "GetFileExtension";
    public static final String GET_FREE_DISK_SPACE = "GetFreeDiskSpace";
    public static final String GET_TOTAL_DISK_SPACE = "GetTotalDiskSpace";
    public static final String GET_FILE_SIZE = "GetFileSize";
    public static final String DELETE = "Delete";
    public static final String CREATE_DIRECTORY = "CreateDirectory";
    public static final String MOVE = "Move:text";
    
    protected HashMap<Integer, QuorumFilePlugin> instances;

    public FilePlugin() {
        instances = new HashMap<Integer, QuorumFilePlugin>();
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
        return "File";
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
        instances = new HashMap<Integer, QuorumFilePlugin>();
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFilePlugin inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFilePlugin();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if (action.equals(GET_LAST_MODIFIED_NATIVE)) {
            long time = inst.GetLastModifiedNative();
            setPluginReturnValue(ret, time);
        }
        else if (action.equals(GET_DIRECTORY_LISTING_NATIVE)) {
            setPluginReturnValue(ret, inst.GetDirectoryListingNative());
        }
        else if (action.equals(GET_PARENT_DIRECTORY_NATIVE)) {
            setPluginReturnValue(ret, inst.GetParentDirectoryNative());
        }
        else if (action.equals(GET_WORKING_DIRECTORY_NATIVE)) {
            setPluginReturnValue(ret, inst.GetWorkingDirectoryNative());
        }
        else if (action.equals(IS_FILE)) {
            setPluginReturnValue(ret, inst.IsFile());
        }
        else if (action.equals(IS_DIRECTORY)) {
            setPluginReturnValue(ret, inst.IsDirectory());
        }
        else if (action.equals(IS_HIDDEN)) {
            setPluginReturnValue(ret, inst.IsHidden());
        }
        else if (action.equals(EXISTS)) {
            setPluginReturnValue(ret, inst.Exists());
        }
        else if (action.equals(GET_FILE_SIZE)) {
            setPluginReturnValue(ret, inst.GetFileSize());
        }
        else if (action.equals(GET_FREE_DISK_SPACE)) {
            setPluginReturnValue(ret, inst.GetFreeDiskSpace());
        }
        else if (action.equals(GET_TOTAL_DISK_SPACE)) {
            setPluginReturnValue(ret, inst.GetTotalDiskSpace());
        }
        else if (action.equals(GET_FILE_EXTENSION)) {
            setPluginReturnValue(ret, inst.GetFileExtension());
        }
        else if (action.equals(CREATE_DIRECTORY)) {
            setPluginReturnValue(ret, inst.CreateDirectory());
        }
        else if (action.equals(DELETE)) {
            setPluginReturnValue(ret, inst.Delete());
        }
        else if (action.equals(MOVE)) {
            String newPath = call.getArgument("newPath").getResult().text;
            setPluginReturnValue(ret, inst.Move(newPath));
        }
        else if (action.equals(GET_SYSTEM_NEWLINE)) {
            setPluginReturnValue(ret, inst.GetSystemNewline());
        }
        else if (action.equals(SET_PATH_NATIVE)){
            String path = call.getArgument("path").getResult().text;
            inst.SetPathNative(path);
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