/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.api.debugger.DebuggerManagerAdapter;
import org.netbeans.api.debugger.Session;
import org.netbeans.api.debugger.Watch;

/**
 *
 * @author Andreas Stefik
 */
public class SessionListener extends DebuggerManagerAdapter {

    public SessionListener() {
        DebuggerManager.getDebuggerManager().addDebuggerListener(
                DebuggerManager.PROP_WATCHES,
                this);

        Watch[] watches = DebuggerManager.getDebuggerManager().getWatches();
        for (Watch watch : watches) {
            watch.addPropertyChangeListener(this);
        }
    }

    @Override
    public void sessionAdded(Session session) {
        super.sessionAdded(session);
    }

    @Override
    public void sessionRemoved(Session session) {
        super.sessionRemoved(session);
    }

    @Override
    public void watchAdded(Watch watch) {
        watch.addPropertyChangeListener(this);
        //QuorumWatchModel.update();
    }

    @Override
    public void initWatches() {
        int a = 5;
    }

    @Override
    public void watchRemoved(Watch watch) {
        watch.removePropertyChangeListener(this);
        //QuorumWatchModel.update();
    }
}
