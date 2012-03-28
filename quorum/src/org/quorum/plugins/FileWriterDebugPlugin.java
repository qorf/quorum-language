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
import org.quorum.execution.ExpressionValue;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.symbols.ErrorTypeDescriptor;

/**
 *
 * @author Jeff Wilson
 */
public class FileWriterDebugPlugin extends FileWriterPlugin{    
    @Override
    public PluginReturn unexecute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileWriterDebug inst = (QuorumFileWriterDebug)instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFileWriterDebug();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        if(action.equals(OPEN_FOR_WRITE_NATIVE)) {
            try {
                inst.unOpenForWriteNative();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if(action.equals(OPEN_FOR_WRITE_APPEND_NATIVE)) {
            try {
                inst.unOpenForWriteAppendNative();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(CLOSE)) {
            try {
                inst.unClose();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }
        }
        else if (action.equals(WRITE_NATIVE)) {
            try {
                inst.unWriteNative();
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }            
        }
        else if (action.equals(WRITE_LINE_NATIVE)) {
            try {
                inst.unWriteLineNative();
            } catch (EOFException ex) {
                throwQuorumException("EndOfFileError: " + ex.getMessage(), ErrorTypeDescriptor.getEndOfFileError());
            } catch (IOException ex) {
                throwQuorumException("InputOutputError: " + ex.getMessage(), ErrorTypeDescriptor.getInputOutputError());
            }            
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