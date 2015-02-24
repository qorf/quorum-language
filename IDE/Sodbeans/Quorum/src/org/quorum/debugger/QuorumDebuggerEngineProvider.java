/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import org.netbeans.api.debugger.DebuggerEngine;
import org.netbeans.api.debugger.DebuggerEngine.Destructor;
import org.netbeans.spi.debugger.DebuggerEngineProvider;

/**
 *
 * @author jojobubu
 */
public class QuorumDebuggerEngineProvider extends DebuggerEngineProvider{

    private DebuggerEngine.Destructor destructor;
    
    @Override
    public String[] getLanguages() {
        return new String[] {"Quorum"};
    }

    @Override
    public String getEngineTypeID() {
        return "QuorumDebuggerEngine";
    }

    @Override
    public Object[] getServices() {
        return new Object[]{};
    }

    @Override
    public void setDestructor (DebuggerEngine.Destructor destructor) {
        this.destructor = destructor;
    }

    public DebuggerEngine.Destructor getDestructor () {
        return destructor;
    }

}
