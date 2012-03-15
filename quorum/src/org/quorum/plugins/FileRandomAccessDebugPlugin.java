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
public class FileRandomAccessDebugPlugin extends FileRandomAccessPlugin {    
    @Override
    public PluginReturn unexecute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumFileRandomAccessDebug inst = (QuorumFileRandomAccessDebug)instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumFileRandomAccessDebug();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        vm = call.getVirtualMachine();
        
        if (action.equals(OPEN_FOR_RANDOM_ACCESS_NATIVE)) {
            // TODO
        }
        else if (action.equals(CLOSE)) {
            // TODO
        }
        else if (action.equals(GET_POSITION)) {
            // TODO
        }
        else if (action.equals(SET_POSITION)) {
            // TODO
        }
        else if (action.equals(IS_AT_END_OF_FILE)) {
            // TODO
        }
        else if (action.equals(READ)) {
            // TODO
        }
        else if (action.equals(READ_AMOUNT)) {
            // TODO
        }
        else if (action.equals(READ_LINE)) {
            // TODO
        }
        else if (action.equals(WRITE)) {
            // TODO
        }
        else if (action.equals(WRITE_LINE)) {
            // TODO
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