/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.api.debugger.DebuggerEngine;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.api.debugger.LazyDebuggerManagerListener;
import org.netbeans.api.debugger.Properties;
import org.netbeans.api.debugger.Session;
import org.netbeans.api.debugger.Watch;

/**
 * This class is responsible for storing breakpoint information for Quorum.
 * A majority of this code has been adopted from the NetBeans PHP debugger's
 * PersistenceManager class.
 * 
 * @author Jeff Wilson and Andreas Stefik
 */
public class PersistenceManager implements LazyDebuggerManagerListener{
    /**
     * The identifiers for the breakpoint list from Properties
     */
    private static final String QUORUM_DEBUGGER = "debugger";
    private static final String QUORUM_BREAKPOINTS = "QuorumBreakpoints";
    
    @Override
    public String[] getProperties() {
        return new String [] {
            DebuggerManager.PROP_BREAKPOINTS_INIT,
            DebuggerManager.PROP_BREAKPOINTS
            /*DebuggerManager.PROP_SESSIONS,
            DebuggerManager.PROP_CURRENT_ENGINE,
            DebuggerManager.PROP_DEBUGGER_ENGINES,
            DebuggerManager.PROP_WATCHES,
            DebuggerManager.PROP_WATCHES_INIT*/
        };
    }

    @Override
    public Breakpoint[] initBreakpoints() {
        Properties p = Properties.getDefault().getProperties(QUORUM_DEBUGGER).
            getProperties(DebuggerManager.PROP_BREAKPOINTS);
        Breakpoint[] breakpoints = (Breakpoint[]) p.getArray( QUORUM_BREAKPOINTS ,new Breakpoint [0]);
        ArrayList<Breakpoint> validBreakpoints = new ArrayList<Breakpoint>();
        for (int i = 0; i < breakpoints.length; i++) {
            Breakpoint breakpoint = breakpoints[i];
            if (breakpoint != null) {
                breakpoint.addPropertyChangeListener(this);
                validBreakpoints.add(breakpoint);
            }
        }
        
        
        return validBreakpoints.toArray(new Breakpoint[validBreakpoints.size()]);
    }

    @Override
    public void breakpointAdded(Breakpoint breakpoint) {
        Properties properties = Properties.getDefault().getProperties(QUORUM_DEBUGGER).
            getProperties(DebuggerManager.PROP_BREAKPOINTS);
        properties.setArray(QUORUM_BREAKPOINTS, getBreakpoints());
        breakpoint.addPropertyChangeListener(this);    
    }

    @Override
    public void breakpointRemoved(Breakpoint breakpoint) {
        Properties properties = Properties.getDefault().getProperties(QUORUM_DEBUGGER).
            getProperties(DebuggerManager.PROP_BREAKPOINTS);
        properties.setArray(QUORUM_BREAKPOINTS, getBreakpoints());
        breakpoint.removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof Breakpoint) {
            Properties.getDefault().getProperties(QUORUM_DEBUGGER).
                getProperties(DebuggerManager.PROP_BREAKPOINTS).setArray(
                QUORUM_BREAKPOINTS, getBreakpoints());
        }
    }

    private Breakpoint[] getBreakpoints() {
        return DebuggerManager.getDebuggerManager().getBreakpoints();
    }
    
    @Override
    public void initWatches() {
        int a = 5;
    }

    @Override
    public void watchAdded(Watch watch) {
        int a = 5;
    }

    @Override
    public void watchRemoved(Watch watch) {
        int a = 5;
    }

    @Override
    public void sessionAdded(Session session) {
        int a = 5;
    }

    @Override
    public void sessionRemoved(Session session) {
        int a = 5;
    }

    @Override
    public void engineAdded(DebuggerEngine engine) {
        int a = 5;
    }

    @Override
    public void engineRemoved(DebuggerEngine engine) {
        int a = 5;
    }
}
