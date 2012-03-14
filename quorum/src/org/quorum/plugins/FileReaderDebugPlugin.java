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
public class FileReaderDebugPlugin extends FileReaderPlugin{    
    @Override
    public PluginReturn unexecute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileReaderDebug inst = (QuorumFileReaderDebug)instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFileReaderDebug();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if(action.equals(OPEN_FOR_READ_NATIVE)) {
            ExpressionValue argument = call.getArgument("path");
            String arg = argument.getResult().text;
            try {
                inst.unOpenForReadNative();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderDebugPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals(CLOSE)) {
            try {
                inst.unClose();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (action.equals(READ_NATIVE)) {
            String result = "";
            try {
                inst.unReadNative();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        else if (action.equals(READ_AMOUNT_NATIVE)) {
            ExpressionValue argument = call.getArgument("numberOfBytes");
            int arg = argument.getResult().integer;
            try {
                inst.unReadNative();
            } catch (EOFException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        else if (action.equals(READ_LINE_NATIVE)) {
            try {
                inst.unReadLineNative();
            } catch (EOFException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileReaderPlugin.class.getName()).log(Level.SEVERE, null, ex);
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