/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 *
 * @author Jeff Wilson
 */
public class FileDebugPlugin extends FilePlugin{    
    @Override
    public PluginReturn unexecute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumDebugFile inst = (QuorumDebugFile)instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumDebugFile();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if (action.equals(OPEN_NATIVE)) {
            try {
                inst.unOpen();
            } catch (IOException ex) {
                // ignore, the user shouldn't need to know this happened.
            }
        }
        else if (action.equals(CLOSE)) {
            try {
                inst.unClose();
            } catch (IOException ex) {
                // ignore, the user shouldn't need to know this happened.
            }
        }
        else if (action.equals(READ)) {
            try {
                inst.unRead();
            } catch (IOException ex) {
                // ignore, the user shouldn't need to know this happened.
            }
        }
        else if (action.equals(READ_WITH_AMOUNT)) {
            try {
                inst.unRead();
            } catch (IOException ex) {
                // ignore, the user shouldn't need to know this happened.
            }
        }
        else if (action.equals(READ_LINE)) {
            try {
                inst.unRead();
            } catch (IOException ex) {
                // ignore, the user shouldn't need to know this happened.
            }
        }
        else if (action.equals(WRITE)) {
            try {
                inst.unWrite();
            } catch (IOException ex) {
                // ignore
            }
        }
        else if (action.equals(WRITE_LINE)) {
            try {
                inst.unWrite();
            } catch (IOException ex) {
                // ignore
            }
        }
        else if (action.equals(SET_POSITION)) {
            try {
                inst.unSetPosition();
            } catch (IOException ex) {
                // ignore
            }
        }
        else if (action.equals(REWIND)) {
            try {
                inst.unSetPosition();
            } catch (IOException ex) {
                // ignore
            }
        }
        else if (action.equals(CREATE_DIRECTORY)) {
            inst.unCreateDirectory();
        }
        else if (action.equals(DELETE)) {
            inst.unDelete();
        }
        else if (action.equals(MOVE)) {
            inst.unMove();
        }
        return ret;
    }


    @Override
    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileInterface inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumDebugFile();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if (action.equals(GET_MODE)) {
            setPluginReturnValue(ret, inst.GetMode());
        }
        else if (action.equals(OPEN_NATIVE)) {
            int mode = call.getArgument("mode").getResult().integer;
            boolean append = call.getArgument("append").getResult().boolean_value;
            boolean write = call.getArgument("write").getResult().boolean_value;
            try {
                inst.Open(mode, append, write);
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
            try {
                long time = inst.GetLastModified();
            } catch (FileNotFoundException ex) {
                throwQuorumException("FileNotFoundError: The requested file was not found on disk.", ErrorTypeDescriptor.getFileNotFoundError());
            }
        }
        else if (action.equals(GET_DIRECTORY_LISTING_NATIVE)) {
            setPluginReturnValue(ret, inst.GetDirectoryListing());
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
        else if (action.equals(GET_NAME)) {
            setPluginReturnValue(ret, inst.GetName());
        }
        else if (action.equals(GET_EXTENSION)) {
            setPluginReturnValue(ret, inst.GetExtension());
        }
        else if (action.equals(GET_PARENT)) {
            setPluginReturnValue(ret, inst.GetParent());
        }
        else if (action.equals(GET_URI)) {
            setPluginReturnValue(ret, inst.GetURI());
        }
        else if (action.equals(GET_FILE_SIZE)) {
            setPluginReturnValue(ret, inst.GetFileSize());
        }
        else if (action.equals(GET_FREE_SPACE)) {
            setPluginReturnValue(ret, inst.GetFreeSpace());
        }
        else if (action.equals(GET_TOTAL_SPACE)) {
            setPluginReturnValue(ret, inst.GetTotalSpace());
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
            setPluginReturnValue(ret, System.getProperty("line.separator"));
        }
        else if (action.equals(GET_PATH)){
            setPluginReturnValue(ret, inst.GetPath());
        }
        else if (action.equals(SET_PATH)){
            String path = call.getArgument("path").getResult().text;
            inst.SetPath(path);
        }
        return ret;
    }
 
    @Override
    public boolean isDebugPlugin() {
        return true;
    }

    @Override
    public boolean isExecutePlugin() {
        return false;
    }
    
    @Override
    public boolean canDebugBackward() {
        return true;
    } 
}