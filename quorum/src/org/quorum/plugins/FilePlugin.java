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
 * @author Elliot Motl
 */
public class FilePlugin implements Plugin {
    protected AbstractVirtualMachine vm;
    public static final String KEY = "Libraries.System.File";
    
    /* -- instance methods -- */
    public static final String GET_MODE = "GetMode";
    public static final String OPEN_NATIVE = "OpenNative:text:integer:boolean:boolean";
    public static final String CLOSE = "Close";
    public static final String READ = "Read";
    public static final String READ_WITH_AMOUNT = "Read:integer";
    public static final String READ_LINE = "ReadLine";
    public static final String IS_EOF = "IsEndOfFile";
    public static final String WRITE = "Write:text";
    public static final String WRITE_LINE = "WriteLine:text";
    public static final String GET_POSITION = "GetPosition";
    public static final String SET_POSITION = "SetPosition:number";
    public static final String REWIND = "Rewind";
    public static final String FORCE_WRITE_CONTENTS = "ForceWriteContents";
    
    /* -- class methods -- */
    public static final String GET_LAST_MODIFIED_NATIVE = "GetLastModifiedNative:text";
    public static final String GET_DIRECTORY_LISTING_NATIVE = "GetDirectoryListingNative:text";
    public static final String IS_DIRECTORY = "IsDirectory:text";
    public static final String IS_HIDDEN = "IsHidden:text";
    public static final String EXISTS = "Exists:text";
    public static final String GET_NAME = "GetName:text";
    public static final String GET_EXTENSION = "GetExtension:text";
    public static final String GET_PARENT = "GetParent:text";
    public static final String GET_URI = "GetURI:text";
    public static final String GET_FILE_SIZE = "GetFileSize:text";
    public static final String GET_FREE_SPACE = "GetFreeSpace:text";
    public static final String GET_TOTAL_DISK_SPACE = "GetTotalDiskSpace:text";
    public static final String CREATE_DIRECTORY = "CreateDirectory:text";
    public static final String DELETE = "Delete:text";
    public static final String MOVE = "Move:text:text";
    public static final String GET_SYSTEM_NEWLINE = "GetSystemNewline";
    
    protected HashMap<Integer, QuorumFileInterface> instances;

    public FilePlugin() {
        instances = new HashMap<Integer, QuorumFileInterface>();
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
        instances = new HashMap<Integer, QuorumFileInterface>();
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileInterface inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFile();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if (action.equals(GET_MODE)) {
            setPluginReturnValue(ret, inst.GetMode());
        }
        else if (action.equals(OPEN_NATIVE)) {
            String path = call.getArgument("path").getResult().text;
            int mode = call.getArgument("mode").getResult().integer;
            boolean append = call.getArgument("append").getResult().boolean_value;
            boolean write = call.getArgument("write").getResult().boolean_value;
            try {
                inst.Open(path, mode, append, write);
            } catch (IllegalArgumentException ex) {
                throwQuorumException("InvalidArgumentError: " + ex.getMessage(), ErrorTypeDescriptor.getInvalidArgumentError());
            } catch (FileNotFoundException ex) {
                throwQuorumException("FileNotFoundError: The requested file was not found on disk.", ErrorTypeDescriptor.getFileNotFoundError());
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
            setPluginReturnValue(ret, true);
        }
        else if (action.equals(READ)) {
            String result = "";
            try {
                result = inst.Read();
            } catch (EOFException ex) {
                throwQuorumException("EndOfFIleError: The end of the file has been reached.", ErrorTypeDescriptor.getEndOfFileError());
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(READ_WITH_AMOUNT)) {
            int amount = call.getArgument("amount").getResult().integer;
            String result = "";
            try {
                result = inst.Read(amount);
            } catch (EOFException ex) {
                throwQuorumException("EndOfFIleError: The end of the file has been reached.", ErrorTypeDescriptor.getEndOfFileError());
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            } catch (IllegalArgumentException ex) {
                throwQuorumException("invalidArgumentError: " + ex.getMessage(), ErrorTypeDescriptor.getInvalidArgumentError());
            }
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(READ_LINE)) {
            String result = "";
            try {
                result = inst.ReadLine();
            } catch (EOFException ex) {
                throwQuorumException("EndOfFileError: The end of the file has been reached.", ErrorTypeDescriptor.getEndOfFileError());
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(IS_EOF)) {
            boolean result = inst.IsEOF();
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(WRITE)) {
            String textToWrite = call.getArgument("textToWrite").getResult().text;
            try {
                inst.Write(textToWrite);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(WRITE_LINE)) {
           String textToWrite = call.getArgument("textToWrite").getResult().text;
            try {
                inst.WriteLine(textToWrite);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(GET_POSITION)) {
            double position = -1;
            try {
                position = inst.GetPosition();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
            setPluginReturnValue(ret, position);
        }
        else if (action.equals(SET_POSITION)) {
            double position = call.getArgument("position").getResult().number;
            try {
                inst.SetPosition((long)position);
            } catch (IOException ex) {
                throwQuorumException("InputOutputError:" + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            } catch (IllegalArgumentException ex) {
                throwQuorumException("InvalidArgumentError: " + ex.getMessage(), ErrorTypeDescriptor.getInvalidArgumentError());
            }
        }
        else if (action.equals(REWIND)) {
            try {
                inst.Rewind();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(FORCE_WRITE_CONTENTS)) {
            try {
                inst.ForceWriteContents();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(GET_LAST_MODIFIED_NATIVE)) {
            String path = call.getArgument("path").getResult().text;
            try {
                long time = inst.GetLastModified(path);
            } catch (FileNotFoundException ex) {
                throwQuorumException("FileNotFoundError: The requested file was not found on disk.", ErrorTypeDescriptor.getFileNotFoundError());
            }
        }
        else if (action.equals(GET_DIRECTORY_LISTING_NATIVE)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetDirectoryListing(path));
        }
        else if (action.equals(IS_DIRECTORY)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.IsDirectory(path));
        }
        else if (action.equals(IS_HIDDEN)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.IsHidden(path));
        }
        else if (action.equals(EXISTS)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.Exists(path));
        }
        else if (action.equals(GET_NAME)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetName(path));
        }
        else if (action.equals(GET_EXTENSION)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetExtension(path));
        }
        else if (action.equals(GET_PARENT)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetParent(path));
        }
        else if (action.equals(GET_URI)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetURI(path));
        }
        else if (action.equals(GET_FILE_SIZE)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetFileSize(path));
        }
        else if (action.equals(GET_FREE_SPACE)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetFreeSpace(path));
        }
        else if (action.equals(GET_TOTAL_DISK_SPACE)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.GetTotalDiskSpace(path));
        }
        else if (action.equals(CREATE_DIRECTORY)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.CreateDirectory(path));
        }
        else if (action.equals(DELETE)) {
            String path = call.getArgument("path").getResult().text;
            setPluginReturnValue(ret, inst.Delete(path));
        }
        else if (action.equals(MOVE)) {
            String oldPath = call.getArgument("oldPath").getResult().text;
            String newPath = call.getArgument("newPath").getResult().text;
            setPluginReturnValue(ret, inst.Move(oldPath, newPath));
        }
        else if (action.equals(GET_SYSTEM_NEWLINE)) {
            setPluginReturnValue(ret, System.getProperty("line.separator"));
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